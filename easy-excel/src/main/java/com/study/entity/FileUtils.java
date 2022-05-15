package com.study.entity;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description:
 * @date: 2022/4/19 14:43
 * @author: LiHaoHan
 * @program: com.study
 */
public class FileUtils {

    private static int ThreadNum=3;

    /*
     * 组织断点续传功能的方法
     */
    public static void downloadPart(HttpServletResponse response, HttpServletRequest request, MultipartFile multipartFile) throws IOException {
        MultiTheradDownLoad multiTheradDownLoad = new MultiTheradDownLoad(ThreadNum);


        long[] startPos = multiTheradDownLoad.getStartPos();
        long[] endPos = multiTheradDownLoad.getEndPos();


        File file = null;
        File tmpFile = null;

        //从文件链接中获取文件名，此处没考虑文件名为空的情况，此种情况可能需使用UUID来生成一个唯一数来代表文件名。
        String filename = multipartFile.getOriginalFilename();
        String tmpFilename = filename + "_tmp";

        multiTheradDownLoad.setFilename(filename);
        multiTheradDownLoad.settmpFilename(tmpFilename);

        try {
            //获取请求资源的总长度。
            long fileLength = multipartFile.getSize();
            multiTheradDownLoad.setFileLength(fileLength);

            //下载文件和临时文件
            file = new File(filename);//相对目录
            tmpFile = new File(tmpFilename);

            //每个线程需下载的资源大小；由于文件大小不确定，为避免数据丢失
            long threadLength = fileLength % ThreadNum == 0 ? fileLength / ThreadNum : fileLength / ThreadNum + 1;
            //打印下载信息
            System.out.println("fileName: " + filename + " ," + "fileLength= "
                    + fileLength + " the threadLength= " + threadLength);
            multiTheradDownLoad.setThreadLength(threadLength);
            //各个线程在exec线程池中进行，起始位置--结束位置
            if (file.exists() && file.length() == fileLength) {
                System.out.println("文件已存在!!");
                return;
            } else {
                CountDownLatch latch = multiTheradDownLoad.getLatch();
                setBreakPoint(startPos, endPos, tmpFile,multiTheradDownLoad);
                ExecutorService exec = Executors.newCachedThreadPool();
                ServletInputStream inputStream = request.getInputStream();
                DownLoadThread downLoadThread = null;
                for (int i = 0; i < ThreadNum; i++) {
                     downLoadThread = new DownLoadThread(startPos[i], endPos[i],
                            multiTheradDownLoad, i, tmpFile, latch, multipartFile, inputStream);
                    exec.execute(downLoadThread);
                }
                latch.await();//当你的计数器减为0之前，会在此处一直阻塞。
                exec.shutdown();
                inputStream.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //下载完成后，判断文件是否完整，并删除临时文件
        if (file.length() == multiTheradDownLoad.getFileLength()) {
            if (tmpFile.exists()) {
                System.out.println("删除临时文件!!");
                tmpFile.delete();
            }
        }
    }

    /*
     * 断点设置方法，当有临时文件时，直接在临时文件中读取上次下载中断时的断点位置。没有临时文件，即第一次下载时，重新设置断点。
     *
     * RantmpFile.seek()跳转到一个位置的目的是为了让各个断点存储的位置尽量分开。
     *
     * 这是实现断点续传的重要基础。
     */
    private static void setBreakPoint(long[] startPos, long[] endPos, File tmpFile,MultiTheradDownLoad multiTheradDownLoad) {
        RandomAccessFile rantmpFile = null;
        try {
            long threadLength = multiTheradDownLoad.getThreadLength();
            long fileLength = multiTheradDownLoad.getFileLength();
            if (tmpFile.exists()) {
                System.out.println("继续下载!!");
                rantmpFile = new RandomAccessFile(tmpFile, "rw");
                for (int i = 0; i < multiTheradDownLoad.getThreadNum(); i++) {
                    rantmpFile.seek(8 * i + 8); // 8 16
                    startPos[i] = rantmpFile.readLong();

                    rantmpFile.seek(8 * (i + 1000) + 16); // 8016 8024
                    endPos[i] = rantmpFile.readLong();

                    System.out.println("the Array content in the exit file: ");
                    System.out.println("thre thread" + (i + 1) + " startPos:"
                            + startPos[i] + ", endPos: " + endPos[i]);
                }
            } else {
                System.out.println("the tmpFile is not available!!");
                rantmpFile = new RandomAccessFile(tmpFile, "rw");

                //最后一个线程的截止位置大小为请求资源的大小
                for (int i = 0; i < ThreadNum; i++) {
                    startPos[i] = threadLength * i;
                    if (i == ThreadNum - 1) {
                        endPos[i] = fileLength;
                    } else {
                        endPos[i] = threadLength * (i + 1) - 1;
                    }

                    rantmpFile.seek(8 * i + 8);
                    rantmpFile.writeLong(startPos[i]);

                    rantmpFile.seek(8 * (i + 1000) + 16);
                    rantmpFile.writeLong(endPos[i]);

                    System.out.println("the Array content: ");
                    System.out.println("thre thread" + (i + 1) + " startPos:"
                            + startPos[i] + ", endPos: " + endPos[i]);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rantmpFile != null) {
                    rantmpFile.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
