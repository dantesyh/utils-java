package dantesyh.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;

import javax.swing.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author dante
 * @since 2023/10/26
 */
class ImageUtilsTest {
    private static final Logger logger = Logger.getLogger(ImageUtils.class.getName());

    @Test
    void testReadImage() throws IOException {
        String filePath = "src/test/resources/image.jpg"; // 文件在类路径下的相对路径
        Path path = Paths.get(filePath);
        byte[] fileBytes = Files.readAllBytes(path);
        MockMultipartFile file = new MockMultipartFile("图像1", "图像1", "image/jpg", fileBytes);
        byte[] bytes = ImageUtils.readImage(file);
        byte[] bytes2 = ImageUtils.readImageNoLimit(file);
        byte[] bytes3 = ImageUtils.readImage(file, List.of("jpg"), 5 * 1024L);
        JFrame frame = new JFrame("显示图像");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        Assertions.assertArrayEquals(fileBytes, bytes);
        Assertions.assertArrayEquals(fileBytes, bytes2);
        logger.info(String.valueOf(bytes2.length));
        logger.info(String.valueOf(bytes3.length));
    }
}