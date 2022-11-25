package com.study.entity;

import lombok.ToString;


/**
 * 状态信息
 *
 * @author User
 */
@ToString
public enum StatusType {

    /**
     * kafka消息状态
     */
    MESSAGE_SENT("2", "3"),
    MESSAGE_TYPEN("N", "NEW_COPACE"),
    MESSAGE_SENTCL("9", "已发送"),
    MESSAGE_ORIGINAL("1", "2"),

    /**
     * 装箱证明书状态
     * 0:暂存 -1：校验失败 1:待发送(保存) 2:已发送(待接收回执) 3:海事受理成功 4:海事受理不通过 5:发送报文内容出错 6：校验中
     */
    TEMP_DATA("0", "暂存"),
    VERIFY_FAILED("-1", "校验失败"),
    UNSEND("1", "待发送"),
    HADSEND("2", "已发送"),
    ACCEPT_SUCCESS("3", "海事受理成功"),
    ACCEPT_FLR("4", "海事受理不通过"),
    MESSAGE_ERROR("5", "发送报文内容出错"),
    CHECKING("6", "校验中");

    private final String code;
    private final String Info;

    StatusType(String code, String Info) {
        this.code = code;
        this.Info = Info;
    }

    public String getCode() {
        return code;
    }

    public String getInfo() {
        return Info;
    }

    public static String getInfoByCode(String code) {
        for (StatusType type : StatusType.values()) {
            if (type.getCode().equals(code)) {
                return type.getInfo();
            }
        }
        return " ";
    }
}
