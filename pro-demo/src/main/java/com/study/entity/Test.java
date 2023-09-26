package com.study.entity;

import com.alibaba.excel.annotation.format.DateTimeFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

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
    @DateTimeFormat("yyyy-MM-dd")
    LocalDate date;
}
