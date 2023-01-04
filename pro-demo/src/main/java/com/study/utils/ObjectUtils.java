package com.study.utils;

import com.study.entity.BusinessCode;
import com.study.entity.resp.RestResult;
import com.study.exception.ResultBaseException;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Column;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.groups.Default;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Set;

/**
 * object 工具类
 *
 * @author LiHaoHan
 * @date 2022/11/25
 */
@Slf4j
public class ObjectUtils {


    /**
     * 校验当前类中属性值是否符合要求
     *
     * @param obj
     */
    public static void validateField(Object obj) {
        try {
            Field[] fields = obj.getClass().getDeclaredFields();
            Object value = null;
            for (Field field : fields) {
                Column annotation = field.getAnnotation(Column.class);
                field.setAccessible(true);
                value = field.get(obj);
                if (value == null && !annotation.nullable()) {
                    throw new ResultBaseException(BusinessCode.CODE_500001);
                }
                if (value != null && value.toString().length() > annotation.length()) {
                    throw new ResultBaseException(BusinessCode.CODE_500001);
                }
            }
        } catch (ResultBaseException e) {
            throw e;
        } catch (Exception e) {
            log.error("get field value error", e);
        }
    }


    /**
     * 校验当前类中属性值是否符合要求
     *
     * @param obj
     */
    public static <T> RestResult<?> validate(T obj) {
        try {
            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set<ConstraintViolation<T>> validate = validator.validate(obj, Default.class);
            HashMap<String, String> map = new HashMap<>(validate.size());
            validate.forEach(constraintViolation ->
                    map.put(constraintViolation.getPropertyPath().toString(),
                            constraintViolation.getMessage()));
            return ResultUtils.err(map);
        } catch (ResultBaseException e) {
            throw e;
        } catch (Exception e) {
            log.error("get field value error", e);
        }
        return ResultUtils.success();
    }
}
