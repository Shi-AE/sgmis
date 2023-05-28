package com.AE.sgmis.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.util.encoders.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class QRCodeUtil {

    /**
     * 默认宽度
     */
    @Value("${QR.width}")
    private int width;
    /**
     * 默认高度
     */
    @Value("${QR.height}")
    private int height;
    /**
     * 图片格式
     */
    @Value("${QR.image-format}")
    private String imageFormat;
    /**
     * 字符编码
     */
    @Value("${QR.charset}")
    private String charset;
    /**
     * 原生转码前面没有 data:image/png;base64 这些字段，返回给前端是无法被解析
     */
    @Value("${QR.base64-image}")
    private String base64Image;
    /**
     * 系统logo地址
     */
    @Value("${QR.logo-url}")
    private String logoUrl;
    /**
     * logo宽度
     */
    @Value("${QR.logo-width}")
    private int logoWidth;
    /**
     * logo高度
     */
    @Value("${QR.logo-height}")
    private int logoHeight;
    /**
     * 二维码前景色
     */
    @Value("${QR.foreground}")
    private int foreground;
    /**
     * 二维码背景色
     */
    @Value("${QR.background}")
    private int background;

    private Map<EncodeHintType, Object> qrConfig;

    @PostConstruct
    private void init() {
        qrConfig = new HashMap<>();
        //指定字符集
        qrConfig.put(EncodeHintType.CHARACTER_SET, charset);
        //指定二维码的纠错等级为中级
        qrConfig.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        //设置图片的边距
        qrConfig.put(EncodeHintType.MARGIN, 2);
        log.info("QRCodeUtil qrConfig 初始化完成 {}", qrConfig);
    }

    /**
     * 生成默认宽高二维码
     */
    public String createQrCode(String content) {
        return createQrCode(content, width, height);
    }

    /**
     * 根据宽高生成二维码
     */
    public String createQrCode(String content, int width, int height) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        try {
            // 创建 QRCodeWriter 对象
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            // 生成 BitMatrix 对象
            BitMatrix bitMatrix = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, width, height, qrConfig);
            // 创建 BufferedImage 对象
            BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            //设置像素点颜色
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    bufferedImage.setRGB(x, y, bitMatrix.get(x, y) ? foreground : background);
                }
            }
            //插入logo
            insertLogo(bufferedImage, width, height, logoUrl, logoWidth, logoHeight);
            //输出
            ImageIO.write(bufferedImage, imageFormat, output);
        } catch (Exception e) {
            log.error("服务错误， 生成二维码失败", e);
        }
        return String.format(base64Image, new String(Base64.encode(output.toByteArray())));
    }

    private void insertLogo(BufferedImage source,
                            int width, int height,
                            String logoUrl,
                            int logoWidth, int logoHeight) throws Exception {
        Image src = ImageIO.read(new File(logoUrl));
        // 插入LOGO
        Graphics2D graph = source.createGraphics();
        int x = (width - logoWidth) / 2;
        int y = (height - logoHeight) / 2;
        graph.drawImage(src, x, y, logoWidth, logoHeight, null);
        Shape shape = new RoundRectangle2D.Float(x, y, logoWidth, logoHeight, 6, 6);
        graph.setStroke(new BasicStroke(3f));
        graph.draw(shape);
        graph.dispose();
    }
}