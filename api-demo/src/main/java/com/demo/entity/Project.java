package com.demo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @date: 2023/7/5
 * @author: LiHaoHan
 */
@Data
@TableName("project")
public class Project {

    @TableId
    private Integer id;

    private String name;
}
