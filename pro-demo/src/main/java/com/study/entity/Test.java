package com.study.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @description: 测试实体类
 * @date: 2022/9/3 14:57
 * @author: LiHaoHan
 * @program: com.study.entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Test {
    @NotNull
    String id;
    String name;
}
