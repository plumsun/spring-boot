package com.study.utils;

import com.study.exception.ResultBaseException;

import java.lang.reflect.Field;

/**
 * object 工具类
 *
 * @author LiHaoHan
 * @date 2022/11/25
 */
public class ObjectUtils {


    /**
     * 校验当前类中属性值是否符合要求
     *
     * @param test
     */
    public static void validateField(Object test) {
        Field[] fields = test.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Object o = null;
            try {
                o = field.get(test);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            if (cn.hutool.core.util.ObjectUtil.isEmpty(o)) {
                throw new ResultBaseException();
            }
        }
    }
}
