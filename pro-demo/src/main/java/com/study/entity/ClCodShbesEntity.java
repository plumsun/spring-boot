package com.study.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Entity
// @Table(name = "CL_COD_SHBES")
@EntityListeners(AuditingEntityListener.class)
public class ClCodShbesEntity implements Serializable {

    /**
     * ID号，主键
     * nullable : false
     * default  : null
     */
    @Id
    @SequenceGenerator(name = "SEQ_CLCODSHBES_PKCLCODSHBES", sequenceName = "SEQ_CLCODSHBES_PKCLCODSHBES", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CLCODSHBES_PKCLCODSHBES")
    @NotNull
    @Column(name = "PK_CLCODSHIPBUS", nullable = true, length = 16)
    private Long pkClcodshipbus;

    /**
     * 航次序号
     * nullable : true
     * default  : null
     */
    @Transient
    private Long voyageId;

    /**
     * 船舶标识号
     * nullable : true
     * default  : null
     */
    @Column(name = "SHIP_ID", nullable = true, length = 35)
    private String shipId;

    /**
     * 航次
     * nullable : true
     * default  : null
     */
    @Column(name = "VOYAGE", nullable = true, length = 10)
    private String voyage;

    /**
     * 船舶进出港标记
     * nullable : true
     * default  : null
     */
    @Column(name = "IN_OUT_FLAG", nullable = true, length = 2)
    private String inOutFlag;

    /**
     * 作业地点编号
     * nullable : true
     * default  : null
     */
    @Column(name = "OPER_PLACE_ID", nullable = true, length = 35)
    private String operPlaceId;

    /**
     * 航线性质
     * nullable : true
     * default  : null
     */
    @Column(name = "TRADE_FLAG", nullable = true, length = 2)
    private String tradeFlag;

    /**
     * 中文船名
     * nullable : true
     * default  : null
     */
    @Column(name = "SHIP_NAME_CN", nullable = true, length = 255)
    private String shipNameCn;

    /**
     * 英文船名
     * nullable : true
     * default  : null
     */
    @Column(name = "SHIP_NAME_EN", nullable = true, length = 255)
    private String shipNameEn;

    /**
     * 船舶呼号
     * nullable : true
     * default  : null
     */
    @Column(name = "VESSEL_CALL", nullable = true, length = 20)
    private String vesselCall;

    /**
     * IMO编号
     * nullable : true
     * default  : null
     */
    @Column(name = "IMO_NO", nullable = true, length = 25)
    private String imoNo;

    /**
     * 离港时间
     * nullable : true
     * default  : null
     */
    @Column(name = "SAILLING_TIME", nullable = true)
    private java.util.Date saillingTime;

    /**
     * 抵港时间
     * nullable : true
     * default  : null
     */
    @Column(name = "ARRIVED_TIME", nullable = true)
    private java.util.Date arrivedTime;

    /**
     * 船代EDI编号
     * nullable : true
     * default  : null
     */
    @Column(name = "AGENCY_EDI_CODE", nullable = true, length = 13)
    private String agencyEdiCode;

    /**
     * 船舶初始登记号
     * nullable : true
     * default  : null
     */
    @Column(name = "SHIP_REGISTER", nullable = true, length = 20)
    private String shipRegister;

    /**
     * 船期性质
     * nullable : true
     * default  : null
     */
    @Column(name = "SAILING_DATE_TYPE", nullable = true, length = 2)
    private String sailingDateType;

    /**
     * 修改时间
     * nullable : true
     * default  : null
     */
    @Column(name = "UPDATE_TIME", nullable = true)
    @LastModifiedDate
    private java.util.Date updateTime;

    /**
     * 企业编号
     * nullable : true
     * default  : null
     */
    @Column(name = "COP_NO", nullable = true, length = 20)
    private String copNo;

    /**
     * 用户编号
     * nullable : true
     * default  : null
     */
    @Column(name = "USER_NO", nullable = true, length = 20)
    private String userNo;

    /**
     * 创建时间
     * nullable : true
     * default  : sysdate
     */
    @Column(name = "CREATE_TIME", nullable = true)
    @CreatedDate
    private java.util.Date createTime;

    /**
     * 作业地点名称
     * nullable : true
     * default  : null
     */
    @Column(name = "OPER_PLACE_NAME", nullable = true, length = 70)
    private String operPlaceName;

    /**
     * uumm中用户组织代码
     * nullable : true
     * default  : null
     */
    @Column(name = "ORG_CODE", nullable = true, length = 255)
    private String orgCode;


    /**
     * 是否为运维添加
     * nullable : true
     * default  : null
     */
    @Column(name = "OPERATIONS_ADD", nullable = true, length = 20)
    private String operationsAdd;


    @Transient
    private String orgName;
}
