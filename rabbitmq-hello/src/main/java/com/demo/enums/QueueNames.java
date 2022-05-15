package com.demo.enums;

/**
 * @description:
 * @date: 2022/3/2 16:56
 * @author: LiHaoHan
 * @program: com.demo.enums
 */
public enum QueueNames {

    WORK_QUEUE_NAME("HELLO WORLD");

    private String name;

    QueueNames(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
