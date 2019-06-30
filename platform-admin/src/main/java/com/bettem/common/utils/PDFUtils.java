package com.bettem.common.utils;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.itextpdf.tool.xml.html.CssAppliers;
import com.itextpdf.tool.xml.html.CssAppliersImpl;
import com.itextpdf.tool.xml.html.Tags;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipelineContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.Charset;

/**
 * 山西百得科技开发股份有限公司 版权所有 © Copyright 2018<br>
 *
 * @Description: 生成PDF文件工具类
 * @Project: nongbu-service
 * @CreateDate: Created in 2019/5/6 14:31 <br>
 * @Author: 颜金星
 */
public class PDFUtils {

    // 本地异常日志记录对象
    private static final Logger logger = LoggerFactory
            .getLogger(PDFUtils.class);


    public static class AsianFontProvider extends XMLWorkerFontProvider {

        @Override
        public Font getFont(final String fontname, String encoding, float size, final int style) {
            String fntname = fontname;
            /*使用的windows里的宋体，可将其文件放资源文件中引入*/
            String classpath = PDFUtils.class.getClassLoader().getResource("").getPath();
            fntname = classpath + "fonts/simsun.ttf";
            if (size == 0) {
                size = 4;
            }
            return super.getFont(fntname, encoding, size, style);
        }
    }

    /**
     * @description PDF文件生成
     */
    public static void convertToPDF(String htmlStr, OutputStream os) {
        //横向
        Rectangle pageSize = new Rectangle(PageSize.A4.getHeight(), PageSize.A4.getWidth());
        pageSize.rotate();
//        document.setPageSize(pageSize);
//        Document document = new Document(PageSize.A4);
        Document document = new Document(pageSize);
        try {
            String classpath = PDFUtils.class.getClassLoader().getResource("").getPath();
            String fntname = classpath + "fonts/simsun.ttf";
            // 设置字体对象为Windows系统默认的字体
//            BaseFont bfChinese = BaseFont.createFont(fntname + "simsun.ttc,0", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
            BaseFont bfChinese = BaseFont.createFont(fntname, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
            PdfWriter writer = PdfWriter.getInstance(document, os);
            // 页码
            setFooter(writer, bfChinese, 8, PageSize.A4);
            document.open();
            XMLWorkerHelper.getInstance().parseXHtml(writer, document, new ByteArrayInputStream(htmlStr.getBytes("UTF-8")), null, Charset.forName("UTF-8"), new AsianFontProvider());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            document.close();
        }
    }

    public static void setFooter(PdfWriter writer, BaseFont bf, int presentFontSize, Rectangle pageSize) {
        ItextPdfHeaderFooter headerFooter = new ItextPdfHeaderFooter(bf, presentFontSize, pageSize);
        writer.setPageEvent(headerFooter);
    }
}
