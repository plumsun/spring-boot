package com.study.entity;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.concurrent.CountDownLatch;

public class DownLoadThread implements Runnable {

    private long startPos;
    private long endPos;
    private MultiTheradDownLoad task = null;
    private RandomAccessFile downloadfile = null;
    private int id;
    private File tmpfile = null;
    private RandomAccessFile rantmpfile = null;
    private CountDownLatch latch = null;
    MultipartFile multipartFile;
    InputStream is;

    public long getStartPos() {
        return startPos;
    }

    public void setStartPos(long startPos) {
        this.startPos = startPos;
    }

    public long getEndPos() {
        return endPos;
    }

    public void setEndPos(long endPos) {
        this.endPos = endPos;
    }

    public MultiTheradDownLoad getTask() {
        return task;
    }

    public void setTask(MultiTheradDownLoad task) {
        this.task = task;
    }

    public RandomAccessFile getDownloadfile() {
        return downloadfile;
    }

    public void setDownloadfile(RandomAccessFile downloadfile) {
        this.downloadfile = downloadfile;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public File getTmpfile() {
        return tmpfile;
    }

    public void setTmpfile(File tmpfile) {
        this.tmpfile = tmpfile;
    }

    public RandomAccessFile getRantmpfile() {
        return rantmpfile;
    }

    public void setRantmpfile(RandomAccessFile rantmpfile) {
        this.rantmpfile = rantmpfile;
    }

    public CountDownLatch getLatch() {
        return latch;
    }

    public void setLatch(CountDownLatch latch) {
        this.latch = latch;
    }

    public MultipartFile getMultipartFile() {
        return multipartFile;
    }

    public void setMultipartFile(MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
    }

    public DownLoadThread(long startPos, long endPos,
                          MultiTheradDownLoad task, int id, File tmpfile,
                          CountDownLatch latch, MultipartFile multipartFile, InputStream is) {
        this.startPos = startPos;
        this.endPos = endPos;
        this.task = task;
        this.tmpfile = tmpfile;
        this.multipartFile = multipartFile;
        this.is = is;
        this.id = id;
        this.latch = latch;
    }

    @Override
    public void run() {
        int length = 0;
        System.out.println("线程" + id + " 开始下载!!");

        try {
            File file = new File(id + ".txt");
            if (file.exists() && file.length() > 0) {
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(new FileInputStream(file)));
                String saveStartPos = br.readLine();
                if (saveStartPos != null && saveStartPos.length() > 0) {
                    startPos = Integer.parseInt(saveStartPos);
                }
            }
            RandomAccessFile raf = new RandomAccessFile(task.getFilename(), "rwd");// 存储下载文件的随机写入文件
            raf.seek(startPos);// 设置开始下载的位置
            System.out.println("线程" + id + ":" + startPos + "~~"
                    + endPos);
            byte[] b = new byte[1024 * 1024 * 10];
            int len = -1;
            long newPos = startPos;
            while ((len = is.read(b)) != -1) {
                RandomAccessFile rr = new RandomAccessFile(task.gettmpFilename(), "rwd");// 存储下载标记的文件
                raf.write(b, 0, len);
                // 将下载标记存入指定文档
                String savaPoint = String.valueOf(newPos += len);
                rr.write(savaPoint.getBytes());
                rr.close();
            }
            raf.close();
            System.out.println("下载完成");
        } catch (Exception e) {
            e.printStackTrace();
        }




        // try {
        //     System.out.println("线程 " + id + " 长度:---- " + (endPos - startPos));
        //     downloadfile.seek(startPos);
        //     //获取服务器返回的资源流
        //     InputStream is = request.getInputStream();
        //     long count = 0l;
        //     byte[] buf = new byte[1024];
        //
        //     while ((length = is.read(buf)) != -1) {
        //         count += length;
        //         downloadfile.write(buf, 0, length);
        //
        //         //不断更新每个线程下载资源的起始位置，并写入临时文件；为断点续传做准备
        //         startPos += length;
        //         rantmpfile.seek(8 * id + 8);
        //         rantmpfile.writeLong(startPos);
        //     }
        //     System.out.println("线程 " + id
        //             + " 总下载大小: " + count);
        //
        //     //关闭流
        //     downloadfile.close();
        //     rantmpfile.close();
        //     latch.countDown();//计数器自减
        //     System.out.println("线程 " + id + " 下载完成!!");
        // } catch (IOException e) {
        //     e.printStackTrace();
        // }
    }
}