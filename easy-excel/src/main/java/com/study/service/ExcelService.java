package com.study.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.study.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @description:
 * @date: 2021/11/4 9:44
 * @author: LiHaoHan
 * @program: com.study.service
 */
@Service
public interface ExcelService extends IService<User> {

    String analyse(MultipartFile file);
}
