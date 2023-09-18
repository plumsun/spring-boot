package com.demo.component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * kafka传输dto
 *
 * @author LiHaoHan Created on 2023/9/18
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class KafkaDto {

    private String messageId;
}
