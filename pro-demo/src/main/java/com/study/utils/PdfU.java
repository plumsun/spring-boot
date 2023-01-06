package com.study.utils;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
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
     * @param imagePath 水印图片
     * @throws Exception
     */
    public void generatePdfPlus(String htmlStr, OutputStream out, String imagePath) throws Exception {
        Document document = new Document(PageSize.A4, 30, 30, 30, 30);
        PdfWriter writer = PdfWriter.getInstance(document, out);
        document.open();
        //页码
        //document.setPageCount(2);
        XMLWorkerHelper helper = XMLWorkerHelper.getInstance();
        //xml解析
        helper.parseXHtml(
                writer,
                document,
                new ByteArrayInputStream(htmlStr.getBytes(CHARACTER)),
                CHARACTER);
        this.textWaterMark(writer,new WaterMarkEntity(),"");
        this.imgWaterMark(writer,new WaterMarkEntity(),"");
        document.close();
    }

    /**
     * 文本水印
     *
     * @param writer
     * @param builder
     * @param text
     */
    public void textWaterMark(PdfWriter writer, WaterMarkEntity builder, String text) {
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
        content.showTextAligned(Element.ALIGN_CENTER, text, builder.getX(), builder.getY(), builder.getRotation());
        content.endText();
    }

    /**
     * 图片水印
     *
     * @param writer
     * @param builder
     * @param imagePath
     * @throws Exception
     */
    public void imgWaterMark(PdfWriter writer, WaterMarkEntity builder, String imagePath) throws Exception {
        PdfGState state = new PdfGState();
        Image image = Image.getInstance(imagePath);
        ////设置图片的位置
        image.setAbsolutePosition(builder.getX(), builder.getY());
        ////设置图片的大小
        image.scaleAbsolute(240, 140);
        // 设置旋转弧度
        image.setRotation(30);
        // 设置旋转角度
        image.setRotationDegrees(45);
        // 设置等比缩放
        image.scalePercent(90);
        //设置透明度
        state.setFillOpacity(builder.getFillOpacity());

        PdfContentByte content = writer.getDirectContent();
        content.beginText();
        //添加图片
        content.addImage(image);
        content.setGState(state);

        content.endText();
    }
}
