package com.study.service.impl;

import com.study.entity.MultipartFileParam;
import com.study.service.MovieService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.*;

/**
 * @description:
 * @date: 2022/4/19 20:10
 * @author: LiHaoHan
 * @program: com.study.service.impl
 */
@Service
public class MovieServiceImpl implements MovieService {

    @Value("${file.path}")
    String filePath;

    @Value("${file.tmp.path}")
    String tmpFilePath;

    @Override
    public String playAndDownload(String range, HttpServletRequest request, HttpServletResponse response, String fileName) {
        File file = new File(filePath + fileName);
        if (!file.exists()) {
            return "文件不存在";
        }
        int length = (int) file.length();
        //解析range

        try {
            RandomAccessFile rw = new RandomAccessFile(file, "rw");

            //清除response
            response.reset();
            //设置返回头
            response.setCharacterEncoding("UTF-8");
            //设置文件长度
            if (StringUtils.isEmpty(range)) {
                response.setHeader("Content-Length", String.valueOf(length));
                response.setHeader("Content-type", "application/octet-stream");//文件下载头
                /*
                    告诉浏览器以哪种方式显示返回的文件
                    attachment:附件方式下载
                    inline:在线打开
                 */
                response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            } else {
                HashMap<String, Integer> lengths = this.parseRange(range, length);
                response.setContentType("video/mp4");
                response.setHeader("Accept-Ranges", "bytes");
                response.setHeader("Content-Length", String.valueOf(lengths.get("length")));
                response.setHeader("Content-Disposition", "inline;filename=" + URLEncoder.encode(fileName, "UTF-8"));
                response.setHeader("Content-Range", "bytes " + lengths.get("startByte") + "-" + lengths.get("endByte") + "/" + length);

                response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);
                rw.seek(lengths.get("startByte"));
                length = lengths.get("length");
            }

            byte[] bytes = new byte[(int) Math.min(length, 1024)];
            OutputStream outputStream = response.getOutputStream();

            int read = rw.read(bytes);
            //避免多余io
            length = length - bytes.length;
            while (read != -1) {
                outputStream.write(bytes, 0, read);
                if (length <= 0) {
                    break;
                }
                read = rw.read(bytes);
                length = length - bytes.length;
            }
            outputStream.flush();
            outputStream.close();
            rw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "成功";
    }

    private HashMap<String, Integer> parseRange(String range, int length) {
        // range: bytes=0-
        String[] split = range.replace("bytes=", "").split("-");//split:[0,?]
        HashMap<String, Integer> result = new HashMap<>();
        if (split.length == 1) {
            //从xxx长度读取到结尾
            Integer startBytes = new Integer(split[0]);
            result.put("startByte", startBytes);
            result.put("endByte", length - 1);
            result.put("length", length - 1);
        } else if (split.length == 2) {
            //从xxx长度读取到yyy长度
            Integer startBytes = new Integer(split[0]);
            Integer endBytes = new Integer(split[1]);
            result.put("startByte", startBytes);
            result.put("endByte", endBytes > length ? length : endBytes);
            result.put("length", endBytes > length ? length - startBytes : endBytes - startBytes);
        } else {
            System.out.println("未识别的range：" + range);
        }
        return result;
    }

    @Override
    public String upload(HttpServletRequest request, HttpServletResponse response, MultipartFileParam param) {
        // 文件名
        String fileName = param.getName();
        // 文件每次分片的下标
        int chunkIndex = param.getChunk();
        File file = new File(tmpFilePath + param.getMd5());
        if (!file.exists()) {
            file.mkdir();
        }
        File chunkFile = new File(
                filePath + param.getMd5() + "/" + chunkIndex);
        try {
            FileUtils.copyInputStreamToFile(param.getFile().getInputStream(), chunkFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "文件-:{" + fileName + "}的小标-:{" + chunkIndex + "},上传成功";
    }

    @Override
    public Integer check(String filNameMD5,String fileName) {
        // 秒传
        File file = new File(filePath + fileName);
        if (file.exists()) {
            return -1;
        }

        // 读取目录里的所有文件
        File dir = new File(tmpFilePath + filNameMD5);
        File[] childs = dir.listFiles();
        if (childs == null) {
            return 0;
        } else {
            return childs.length - 1;//文件上传中断过，返回当前已经上传到的下标
        }

    }

    @Override
    public void fileMerge(HttpServletRequest request) {
        try {
            String fileName = request.getParameter("fileName");
            String fileMd5 = request.getParameter("fileMd5");
            // 读取目录里的所有文件
            File dir = new File(tmpFilePath + fileMd5);
            File[] childs = dir.listFiles();
            if (Objects.isNull(childs) || childs.length == 0) {
                return;
            }
            // 转成集合，便于排序
            List<File> fileList = new ArrayList<File>(Arrays.asList(childs));
            Collections.sort(fileList, new Comparator<File>() {
                @Override
                public int compare(File o1, File o2) {
                    if (Integer.parseInt(o1.getName()) < Integer.parseInt(o2.getName())) {
                        return -1;
                    }
                    return 1;
                }
            });
            // 合并后的文件
            File outputFile = new File(filePath + fileName);
            // 创建文件
            if (!outputFile.exists()) {
                System.out.println("创建文件");
                outputFile.createNewFile();
            }
            try {
                int bufSize = 1024;
                BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(outputFile));
                byte[] buffer = new byte[bufSize];
                for (File child : childs) {
                    BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(child));
                    int readcount;
                    while ((readcount = inputStream.read(buffer)) > 0) {
                        outputStream.write(buffer, 0, readcount);
                    }
                    outputStream.flush();
                    inputStream.close();
                    child.delete();
                }
                outputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
                //发生异常，文件合并失败 ，删除创建的文件
                outputFile.delete();
                dir.delete();//删除文件夹
            }
            dir.delete(); //删除分片所在的文件夹
            // FIXME: 数据库操作, 记录文件存档位置
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}