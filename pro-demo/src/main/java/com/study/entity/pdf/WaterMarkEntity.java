package com.study.entity.pdf;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.BaseFont;
import lombok.Data;

/**
 * @author LiHaoHan
 * @date 2023/1/6
 */
@Data
public class WaterMarkEntity {
    //透明度
    private float fillOpacity;
    //x坐标
    private float x;
    //y坐标
    private float y;
    //旋转度
    private float rotation;

    /*-----------------------------------------文本-------------------------------------------------*/
    //字体
    private BaseFont font;
    //字体大小
    private float fontSize;
    //文本内容
    private String text;
    //颜色
    private BaseColor color;

    /*-----------------------------------------图片-------------------------------------------------*/
    private Image image;
}
