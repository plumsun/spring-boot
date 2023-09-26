package com.study.entity;


import java.util.function.Consumer;

/**
 * excel导入实体扩展
 * 方便在数据解析完成后针对每种实体做不同业务操作
 *
 * @author LiHaoHan on 2023/6/9
 */
public interface ExcelEntityTactics extends Consumer<Object> {
}
