package com.study.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @description:
 * @date: 2022/9/3 14:57
 * @author: LiHaoHan
 * @program: com.study.entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Test implements Serializable {
    String id;
    String name;
}
