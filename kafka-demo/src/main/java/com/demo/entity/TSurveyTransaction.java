package com.demo.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * The type T survey transaction.
 *
 * @author LiHaoHan Created on 2024-03-16
 */
@TableName(value = "t_survey_transaction")
@Data
public class TSurveyTransaction implements Serializable {

    /**
     * The constant serialVersionUID.
     */
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * The Id.
     */
    @TableId(value = "ID", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 厂房
     */
    @TableField(value = "CompanyCode", updateStrategy = FieldStrategy.IGNORED)
    private String companyCode;

    /**
     * 活动ID
     */
    @TableField(value = "ParentId", updateStrategy = FieldStrategy.IGNORED)
    private String parentId;

    /**
     * 问卷ID
     */
    @TableField(value = "SurveyTaskID", updateStrategy = FieldStrategy.IGNORED)
    private String surveyTaskID;

    /**
     * 问卷明细ID
     */
    @TableField(value = "SurveyDetailId", updateStrategy = FieldStrategy.IGNORED)
    private String surveyDetailId;

    /**
     * The Tracking id.
     */
    @TableField(value = "TrackingId", updateStrategy = FieldStrategy.IGNORED)
    private String trackingId;

    /**
     * 门店编号
     */
    @TableField(value = "StoreCode", updateStrategy = FieldStrategy.IGNORED)
    private String storeCode;

    /**
     * 拜访ID
     */
    @TableField(value = "VisitID", updateStrategy = FieldStrategy.IGNORED)
    private String visitID;

    /**
     * 线路id
     */
    @TableField(value = "RoueNo", updateStrategy = FieldStrategy.IGNORED)
    private String routeNo;

    /**
     * 门店id
     */
    @TableField(value = "StoreID", updateStrategy = FieldStrategy.IGNORED)
    private String storeID;

    /**
     * 执行时间
     */
    @TableField(value = "TrackTime", updateStrategy = FieldStrategy.IGNORED)
    private Date trackTime;

    /**
     * 执行类型registration | execution
     */
    @TableField(value = "Type", updateStrategy = FieldStrategy.IGNORED)
    private String type;

    /**
     * 执行内容
     */
    @TableField(value = "Content", updateStrategy = FieldStrategy.IGNORED)
    private String content;

    /**
     * 备注
     */
    @TableField(value = "Remark", updateStrategy = FieldStrategy.IGNORED)
    private String remark;

    /**
     * 是否删除
     */
    @TableField(value = "IsDeleted")
    private String isDeleted;

    /**
     * 创建人
     */
    @TableField(value = "CreateBy")
    private String createBy;

    /**
     * 创建时间
     */
    @TableField(value = "CreateTime", fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 修改人
     */
    @TableField(value = "LastModifiedBy")
    private String lastModifiedBy;

    /**
     * 修改时间
     */
    @TableField(value = "LastModifiedTime", fill = FieldFill.INSERT_UPDATE)
    private Date lastModifiedTime;

    /**
     * 流程id
     */
    @TableField(value = "ProcessID", updateStrategy = FieldStrategy.IGNORED)
    private String processID;

    @TableField(exist = false)
    private String answerType;

    @TableField(exist = false)
    private String questionTitle;
}
