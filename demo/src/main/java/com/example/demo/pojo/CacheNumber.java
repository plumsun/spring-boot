package com.example.demo.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description:
 * @date: 2022/8/2 18:57
 * @author: LiHaoHan
 * @program: com.example.demo.pojo
 */
@TableName("cache_number")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CacheNumber {
    private String redisKey;
    private Integer number;
}
