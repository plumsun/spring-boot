package com.study.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @program: TestDemo
 * @Date: 2022/10/11
 * @Author: LiHaoHan
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Test_Demo")
@EntityListeners(AuditingEntityListener.class)
public class TestDemo {
    @Id
    private String id;
    private String name;
    private Object obj;
}
