package dantesyh.utils;

import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * 图像处理工具类
 *
 * @author dante
 * @since 2023/10/26
 */
public class ImageUtils {
    private ImageUtils() {
    }

    private static final List<String> IMAGE_FORMAT = List.of("jpg", "jpeg", "png", "gif");
    private static final Long TARGET_SIZE = 10 * 1024 * 1024L; // 10MB

    /**
     * 从 MultipartFile 读取并处理图像文件。
     *
     * @param multipartFile MultipartFile 对象，包含上传的图像文件
     * @param allowedFormat 允许的图像格式列表，可以为 null
     * @param targetSize    目标文件大小，可以为 null
     * @return 处理后的图像数据的字节数组
     * @throws IOException              如果发生输入/输出错误
     * @throws IllegalArgumentException 如果图像格式不合法或未知
     */
    public static byte[] readImage(MultipartFile multipartFile, List<String> allowedFormat, Long targetSize) throws IOException, IllegalArgumentException {
        String contentType = multipartFile.getContentType();
        if (contentType == null || !contentType.startsWith("image/")) {
            throw new IllegalArgumentException("未知的图片格式"); // 获取content type
        }
        String fileExtension = contentType.substring("image/".length());
        if (allowedFormat != null && !allowedFormat.contains(fileExtension)) {
            throw new IllegalArgumentException("图片格式不符合要求"); // 获取图片格式
        }
        long fileSize = multipartFile.getSize();
        if (targetSize != null) {
            if (fileSize < targetSize) {
                return multipartFile.getBytes(); // 小于目标大小则输出
            }
            BufferedImage originalImage = ImageIO.read(multipartFile.getInputStream());
            while (fileSize > targetSize) {
                try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
                    originalImage = compressImage(originalImage);
                    ImageIO.write(originalImage, fileExtension, outputStream); // 将压缩后的图像写入 ByteArrayOutputStream
                    fileSize = outputStream.size(); // 更新文件大小
                }
            }
            // 返回压缩后的图像数据
            try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
                ImageIO.write(originalImage, fileExtension, outputStream);
                return outputStream.toByteArray();
            }
        } else {
            return multipartFile.getBytes();
        }
    }

    /**
     * 从 MultipartFile 读取并处理图像文件。
     *
     * @param multipartFile MultipartFile 对象，包含上传的图像文件
     * @return 处理后的图像数据的字节数组
     * @throws IOException              如果发生输入/输出错误
     * @throws IllegalArgumentException 如果图像格式不合法或未知
     */
    public static byte[] readImage(MultipartFile multipartFile) throws IOException, IllegalArgumentException {
        return readImage(multipartFile, IMAGE_FORMAT, TARGET_SIZE);
    }

    /**
     * 从 MultipartFile 读取并处理图像文件。
     *
     * @param multipartFile MultipartFile 对象，包含上传的图像文件
     * @return 处理后的图像数据的字节数组
     * @throws IOException              如果发生输入/输出错误
     * @throws IllegalArgumentException 如果图像格式不合法或未知
     */
    public static byte[] readImageNoLimit(MultipartFile multipartFile) throws IOException, IllegalArgumentException {
        return readImage(multipartFile, IMAGE_FORMAT, null);
    }

    /**
     * 压缩图像至一半的宽度和高度。
     *
     * @param originalImage 原始图像
     * @return 压缩后的图像
     */
    private static BufferedImage compressImage(BufferedImage originalImage) {
        int newWidth = originalImage.getWidth() / 2;
        int newHeight = originalImage.getHeight() / 2;

        BufferedImage compressedImage = new BufferedImage(newWidth, newHeight, originalImage.getType());
        Graphics2D g = compressedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, newWidth, newHeight, null);
        g.dispose();
        return compressedImage;
    }


}
