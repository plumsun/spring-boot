package com.study.entity;


import com.study.entity.resp.CodeI;

/**
 * 错误状态信息
 */
public enum BusinessCode implements CodeI {

    CODE_500001(500001 , "传入参数有误或缺失"),
    CODE_500002(500002 , "更新时未找到业务主键"),
    CODE_500003(500003 , "未找到对应的操作，请检查action参数"),
    CODE_500004(500004 , "空指针啦，快去杀虫吧"),
    CODE_500005(500 , "记录不存在"),
    CODE_500006(500 , "当前状态不可做任何申报"),
    CODE_500007(500 , "发送海事局失败"),
    SUCCESS(200 , "成功"),
    ;

    private int code;
    private String message;

    BusinessCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
