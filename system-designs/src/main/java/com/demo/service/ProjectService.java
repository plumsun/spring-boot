package com.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.entity.Project;
import com.demo.entity.ResultVo;

/**
 * @date: 2023/7/5
 * @author: LiHaoHan
 */
public interface ProjectService extends IService<Project> {

    ResultVo getData();

    String getPrepareSql();
}
