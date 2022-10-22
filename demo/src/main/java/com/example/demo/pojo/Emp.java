package com.example.demo.pojo;

import lombok.*;

import java.sql.Date;
import java.util.List;

/**
 * @description:
 * @date: 2022/7/22 15:06
 * @author: LiHaoHan
 * @program: com.example.demo.pojo
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Emp {
    private Integer empno;
    private String ename;
    private String job;
    private Integer mgr;
    private Date hiredate;
    private Integer sal;
    private Integer comm;
    private List deptno;
}
