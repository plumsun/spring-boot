package com.example.demo.service;

import cn.hutool.core.collection.CollUtil;
import com.example.demo.entity.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author LiHaoHan Created on 2023/11/2
 */
@Service
public class BService {

    @Resource
    private AService aService;

    public List<User> get(List<Integer> ids){
        if(ids.isEmpty()){
            return Collections.emptyList();
        }
        List<List<Integer>> idList = CollUtil.split(ids, 10);
        return idList.parallelStream().map(list -> this.aService.get(list))
                .filter(CollUtil::isNotEmpty)
                .flatMap(Collection::stream).collect(Collectors.toList());
    }
}
