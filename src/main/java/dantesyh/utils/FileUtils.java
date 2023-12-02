package dantesyh.utils;

import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

/**
 * 图像处理工具类
 *
 * @author dante
 * @since 2023/10/26
 */
public class FileUtils {
    private FileUtils() {
    }

    /**
     * 压缩JPG/JPEG图像
     *
     * @param src     图像源
     * @param quality 质量
     * @return 压缩后图像
     * @throws IOException 读取错误
     */
    public static byte[] compressJPG(byte[] src, float quality) throws IOException {
        return compressImage(src, quality, "jpg");
    }

    /**
     * 压缩PNG图像
     *
     * @param src     图像源
     * @param quality 质量
     * @return 压缩后图像
     * @throws IOException 读取错误
     */
    public static byte[] compressPNG(byte[] src, float quality) throws IOException {
        return compressImage(src, quality, "png");
    }


    private static byte[] compressImage(byte[] src, float quality, String format) throws IOException {

        BufferedImage image = ImageIO.read(new ByteArrayInputStream(src));

        Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName(format);

        if (!writers.hasNext()) {
            throw new IllegalStateException("No writers found");
        }

        ImageWriter writer = writers.next();
        ImageWriteParam param = writer.getDefaultWriteParam();

        param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
        param.setCompressionQuality(quality);

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            writer.setOutput(ImageIO.createImageOutputStream(outputStream));
            writer.write(null, new javax.imageio.IIOImage(image, null, null), param);
            return outputStream.toByteArray();
        } finally {
            writer.dispose();
        }
    }

    /**
     * 从resource读取
     *
     * @param resourcePath 相对路径
     * @param clazz        当前类
     * @return byte数据
     * @throws IOException 找不到文件
     */
    public static byte[] loadResourceAsBytes(String resourcePath, Class<?> clazz) throws IOException {
        try (InputStream inputStream = clazz.getClassLoader().getResourceAsStream(resourcePath)) {
            if (inputStream == null) {
                throw new IOException("Resource not found: " + resourcePath);
            }
            try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
                return outputStream.toByteArray();
            }
        }
    }

}
