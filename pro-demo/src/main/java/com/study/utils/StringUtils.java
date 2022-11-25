package com.study.utils;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

/**
 * @author LiHaoHan
 * @date 2022/11/25
 */
public class StringUtils {


    /**
     * 如果字节数大于1，是汉字以这种方式区别英文字母和中文汉字并不是十分严谨，但在这个题目中，这样判断已经足够了
     *
     * @param c
     * @return
     * @throws UnsupportedEncodingException
     */
    public static boolean isChineseChar(char c)
            throws UnsupportedEncodingException {
        byte[] bytes = String.valueOf(c).getBytes("UTF-8");
        return bytes.length > 1;

    }

    /**
     * 截取字符串（过滤中文字符）
     *
     * @param orignal
     * @param count
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String substring(String orignal, int count)
            throws UnsupportedEncodingException {

        if (orignal != null && !"".equals(orignal)) {
            orignal = new String(orignal.getBytes(), StandardCharsets.UTF_8);

            if (count > 0 && count < orignal.getBytes(StandardCharsets.UTF_8).length) {
                StringBuilder buff = new StringBuilder();
                char c;
                for (int i = 0; i < count; i++) {
                    c = orignal.charAt(i);
                    buff.append(c);
                    if (isChineseChar(c)) {
                        count -= 2;
                    }
                }
                return buff.toString();
            }
        }
        return orignal;
    }
}
