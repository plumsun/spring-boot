package com.study.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;


/**
 * @author user
 * @date 2022-02-11 12:55:00
 */
@Data
@Entity
@Table(name = "CL_BIZ_APPLY")
//@SQLDelete(sql = "update CL_BIZ_APPLY set DELETE_FLAG = 0 where PK_CLBIZAPPLY = ?")
//@SQLDeleteAll(sql = "update CL_BIZ_APPLY set DELETE_FLAG = 0 where PK_CLBIZAPPLY = ?")
//@Where(clause = "DELETE_FLAG = 1")
@EntityListeners(AuditingEntityListener.class)
public class ClBizApplyEntity implements Serializable {

    /**
     * ID号，主键
     * nullable : false
     * default  : null
     */
    @Id
    @SequenceGenerator(name = "SEQ_CLBIZAPPLY_PKCLBIZAPPLY", sequenceName = "SEQ_CLBIZAPPLY_PKCLBIZAPPLY", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CLBIZAPPLY_PKCLBIZAPPLY")
    @Column(name = "PK_CLBIZAPPLY", nullable = true, length = 16)
    private Long pkClbizapply;


    /**
     * 删除标记
     * nullable : true
     * default  : null`
     * 0：已删除 1：未删除 2:彻底删除
     */
    @Column(name = "DELETE_FLAG", nullable = true, length = 2)
    private String deleteFlag;


    /**
     * 修改时间
     * nullable : true
     * default  : null
     */
    @Column(name = "CONTAIN_DATE", nullable = true)
    private java.util.Date containDate;

    /**
     * 创建时间
     * nullable : true
     * default  : sysdate
     */
    @Column(name = "CREATE_TIME", nullable = true,updatable = true)
    @CreatedDate
    private java.util.Date createTime;



    /**
     * 修改时间
     * nullable : true
     * default  : null
     */
    @Column(name = "UPDATE_TIME", nullable = true)
    @LastModifiedDate
    private java.util.Date updateTime;

}
