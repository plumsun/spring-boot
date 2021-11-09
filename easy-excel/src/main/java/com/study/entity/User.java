package com.study.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description:
 * @date: 2021/11/4 10:18
 * @author: LiHaoHan
 * @program: com.study.entity
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    private String userName;
    private String password;
    private String age;
    private String addr;
}
