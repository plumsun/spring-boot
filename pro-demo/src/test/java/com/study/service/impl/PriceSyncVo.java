package com.study.service.impl;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author LiHaoHan Created on 2024/3/5
 */
@Data
public class PriceSyncVo {

    @JSONField(name = "IV_ZNUM")
    private String IV_ZNUM;

    @JSONField(name = "IV_VKORG")
    private String IV_VKORG;

    @JSONField(name = "IV_VTWEG")
    private String IV_VTWEG;

    @JSONField(name = "IV_ZSOURCE")
    private String IV_ZSOURCE = "1";

    @JSONField(name = "IV_KUNNR")
    private String IV_KUNNR;

    @JSONField(name = "IV_PRSDT")
    private String IV_PRSDT;

    @JSONField(name = "IT_ITEM")
    private List<PriceSyncVo.ITITEMDTO> IT_ITEM;

    @NoArgsConstructor
    @Data
    public static class ITITEMDTO {
        @JSONField(name = "MATNR")
        private String MATNR;
    }
}
