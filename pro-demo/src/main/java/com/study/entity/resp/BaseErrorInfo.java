package com.study.entity.resp;

/**
 * @program: BaseErrorInfo
 * @Date: 2022/10/31
 * @Author: LiHaoHan
 * @Description:
 */
public interface BaseErrorInfo {
    /**
     * return code
     * @return code
     */
    Integer getResultCode();

    /**
     * return message
     * @return msg
     */
    String getResultMsg();
}
