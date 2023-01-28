package com.study.utils;

import cn.hutool.core.util.StrUtil;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfGState;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.study.entity.pdf.WaterMarkEntity;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.annotation.Resource;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Slf4j
@Component
public class PdfU {

    private static final Charset CHARACTER = StandardCharsets.UTF_8;

    @Resource
    FreeMarkerConfigurer configurer;

    private PdfU() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * 根据模版绑定数据后生成html字符串
     *
     * @param templateName
     * @param paramMap
     * @return
     * @throws Exception
     */
    public String generateHtml(String templateName, Map<String, Object> paramMap) throws Exception {
        Template template = configurer.getConfiguration().getTemplate(templateName);
        StringWriter stringWriter = new StringWriter();
        BufferedWriter writer = new BufferedWriter(stringWriter);
        template.setEncoding("UFT-8");
        template.process(paramMap, writer);
        String htmlStr = stringWriter.toString();
        writer.flush();
        writer.close();
        return htmlStr;
    }

    /**
     * 绑定html内容并解析生成pdf
     *
     * @param htmlStr   html
     * @param out       输出流
     * @throws Exception
     */
    public void generatePdfPlus(String htmlStr, OutputStream out) throws Exception {
        Document document = new Document(PageSize.A4, 30, 30, 30, 30);
        PdfWriter writer = PdfWriter.getInstance(document, out);
        document.open();
        //页码
        //document.setPageCount(2);
        XMLWorkerHelper helper = XMLWorkerHelper.getInstance();

        /*//若是需要采用特殊字体，需要手动注册
        XMLWorkerFontProvider fontProvider = new XMLWorkerFontProvider();
        //注册字体
        fontProvider.register("*.ttf");
        Set<String> fonts = fontProvider.getRegisteredFonts();
        log.info("默认注册的字体：{}",fonts);*/

        //注册外部css文件
        //helper.getDefaultCssResolver(true).addCssFile("*.css",true);

        //xml解析
        helper.parseXHtml(
                writer,
                document,
                new ByteArrayInputStream(htmlStr.getBytes(CHARACTER)),
                CHARACTER);
        WaterMarkEntity waterMark = buildWaterMark(BaseFont.createFont(), BaseColor.RED, "text", null);
        //this.textWaterMark(writer, waterMark);
        //this.imgWaterMark(writer, waterMark);
        document.close();
    }

    /**
     * 文本水印
     *
     * @param writer
     * @param builder
     */
    public void textWaterMark(PdfWriter writer, WaterMarkEntity builder) {
        //文本水印
        PdfGState state = new PdfGState();
        ////设置透明度
        state.setFillOpacity(builder.getFillOpacity());
        PdfContentByte content = writer.getDirectContent();
        content.beginText();
        content.setGState(state);
        ////水印颜色
        content.setColorFill(builder.getColor());
        ////文本水印位置
        content.setFontAndSize(builder.getFont(), builder.getFontSize());
        content.showTextAligned(Element.ALIGN_CENTER, builder.getText(), builder.getX(), builder.getY(), builder.getRotation());
        log.info("添加文字水印");
        content.endText();
    }

    /**
     * 图片水印
     *
     * @param writer
     * @param builder
     * @throws Exception
     */
    public void imgWaterMark(PdfWriter writer, WaterMarkEntity builder) throws Exception {
        PdfGState state = new PdfGState();
        //设置透明度
        state.setFillOpacity(builder.getFillOpacity());
        PdfContentByte content = writer.getDirectContent();
        content.beginText();
        //添加图片
        content.addImage(builder.getImage());
        content.setGState(state);
        log.info("添加图片水印");
        content.endText();
    }

    /**
     * 构建水印对象
     *
     * @param font      字体
     * @param color     颜色
     * @param text      文本
     * @param imagePath 图片路径
     * @return
     * @throws Exception
     */
    public WaterMarkEntity buildWaterMark(BaseFont font, BaseColor color, String text, String imagePath) throws Exception {
        WaterMarkEntity waterMark = new WaterMarkEntity();
        waterMark.setFillOpacity(0.4f);
        waterMark.setX(200);
        waterMark.setY(300);
        waterMark.setRotation(10);
        waterMark.setFontSize(20);
        waterMark.setText(text);
        waterMark.setFont(font);
        waterMark.setColor(color);
        Image image = null;
        if (StrUtil.isNotEmpty(imagePath)) {
            image = Image.getInstance(imagePath);
            //设置图片的位置
            image.setAbsolutePosition(waterMark.getX(), waterMark.getY());
            //设置图片的大小
            image.scaleAbsolute(240, 140);
            // 设置旋转弧度
            image.setRotation(waterMark.getRotation());
            // 设置旋转角度
            image.setRotationDegrees(45);
            // 设置等比缩放
            image.scalePercent(90);
        }
        waterMark.setImage(image);
        return waterMark;
    }


}
