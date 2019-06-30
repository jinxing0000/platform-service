package com.bettem.common.utils;

import com.bettem.common.exception.RRException;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 山西百得科技开发股份有限公司 版权所有 © Copyright 2018<br>
 *
 * @Description: 图片处理工具类
 * @Project: bettem-security
 * @CreateDate: Created in 2019/1/21 15:31 <br>
 * @Author: 颜金星
 */
public class ImageUtils {
    /**
     * @Param [in, type]
     * @Return: byte[]
     * @Decription:  谷歌图片压缩
     * @CreateDate: Created in 2019/1/21 16:45
     * @Author: 颜金星
     */
    public static byte[] imageCompress(InputStream in,String type){
        byte[] byteArray = null;
        try {
            //保持纵横比，质量降低为原来的42%
            Thumbnails.Builder<? extends InputStream> builder=Thumbnails.of(in).scale(1.00f).outputQuality(0.42f);
            BufferedImage bufferedImage = builder.asBufferedImage();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, type, baos);
            byteArray = baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RRException("压缩文件失败，请重新上传！！");
        }
        return byteArray;
    }

    public static void main(String[] args) {
//      Thumbnails.of(file.getInputStream()).scale(1.00f).outputQuality(0.25f).toOutputStream(outputStream);
        try {
            //保持纵横比，质量降低为原来的42%
            Thumbnails.of("E:/FTP/1.jpg").scale(1.00f).outputQuality(0.42f).toFile("E:/FTP/4.jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
