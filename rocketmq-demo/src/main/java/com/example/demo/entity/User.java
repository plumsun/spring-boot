package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/**
 * @author LiHaoHan Created on 2023/11/2
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "user")
@EntityListeners(AuditingEntityListener.class)
public class User {

    @Id
    @Column(name = "id", length = 6)
    private Integer id;

    @Column(name = "password", length = 10)
    private String password;

    @Column(name = "username", length = 10)
    private String username;
}
