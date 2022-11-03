package com.study.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: TestDemo
 * @Date: 2022/10/11
 * @Author: LiHaoHan
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestDemo {
    private String id;
    private String name;
    private Object obj;
}
