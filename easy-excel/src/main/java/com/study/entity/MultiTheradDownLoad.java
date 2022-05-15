package com.study.entity;

import java.util.concurrent.CountDownLatch;

public class MultiTheradDownLoad {
    private String filename = null;
    private String tmpFilename = null;

    private int threadNum = 0;

    private CountDownLatch latch = null;//设置一个计数器，代码内主要用来完成对缓存文件的删除

    private long fileLength = 0l;
    private long threadLength = 0l;
    private long[] startPos;//保留每个线程下载数据的起始位置。
    private long[] endPos;//保留每个线程下载数据的截止位置。




    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String gettmpFilename() {
        return tmpFilename;
    }

    public void settmpFilename(String tmpFilename) {
        this.tmpFilename = tmpFilename;
    }

    public int getThreadNum() {
        return threadNum;
    }

    public void setThreadNum(int threadNum) {
        this.threadNum = threadNum;
    }

    public CountDownLatch getLatch() {
        return latch;
    }

    public void setLatch(CountDownLatch latch) {
        this.latch = latch;
    }

    public long getFileLength() {
        return fileLength;
    }

    public void setFileLength(long fileLength) {
        this.fileLength = fileLength;
    }

    public long getThreadLength() {
        return threadLength;
    }

    public void setThreadLength(long threadLength) {
        this.threadLength = threadLength;
    }

    public long[] getStartPos() {
        return startPos;
    }

    public void setStartPos(long[] startPos) {
        this.startPos = startPos;
    }

    public long[] getEndPos() {
        return endPos;
    }

    public void setEndPos(long[] endPos) {
        this.endPos = endPos;
    }




    //有参构造函数，先构造需要的数据
    public MultiTheradDownLoad(int threadNum) {
        this.threadNum = threadNum;
        startPos = new long[this.threadNum];
        endPos = new long[this.threadNum];
        latch = new CountDownLatch(this.threadNum);
    }
}