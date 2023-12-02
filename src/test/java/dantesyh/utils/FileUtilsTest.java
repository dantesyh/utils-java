package dantesyh.utils;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author dante
 * @since 2023/10/26
 */
class FileUtilsTest {

    @Test
    void compressJPG() throws IOException {
        String filePath = "src/test/resources/image.jpg"; // 文件在类路径下的相对路径
        Path path = Paths.get(filePath);
        byte[] fileBytes = Files.readAllBytes(path);
        byte[] bytes = FileUtils.compressJPG(fileBytes, 0.5f);
        Assertions.assertTrue(fileBytes.length > bytes.length);
    }

    @Test
    void compressPNG() throws IOException {
        String filePath = "src/test/resources/image.png"; // 文件在类路径下的相对路径
        Path path = Paths.get(filePath);
        byte[] fileBytes = Files.readAllBytes(path);
        byte[] bytes = FileUtils.compressPNG(fileBytes, 0.5f);
        Assertions.assertTrue(fileBytes.length > bytes.length);
    }

    @Test
    void loadResourceAsBytes() throws IOException {
        String filePath = "image.jpg"; // 文件在类路径下的相对路径
        byte[] bytes = FileUtils.loadResourceAsBytes(filePath, this.getClass());
        Assertions.assertNotNull(bytes);
    }
}