package dantesyh.utils;

import java.security.SecureRandom;
import java.util.Random;
import java.util.UUID;

/**
 * 生成类工具
 *
 * @author dante
 * @since 2023/8/31
 */
public class RandomUtils {
    private RandomUtils() {
    }

    private static final Random random = new Random();
    private static final char[] UPPERCASE = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',};
    private static final char[] LOWERCASE = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',};
    private static final char[] DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',};
    private static final char[] SPECIAL_CHARACTERS = {'!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '_', '-', '+', '=', '<', '>', '?', '"'};

    /**
     * 生成一个UUID（Universally Unique Identifier）字符串。
     *
     * @return UUID字符串
     */
    public static String genUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 生成指定长度的随机密码，包含大写字母、小写字母、数字和特殊字符。
     *
     * @param length 密码的长度
     * @return 生成的随机密码
     */
    public static String generateRandomPassword(int length) {
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder(length);

        // 创建字符集，包含大写字母、小写字母、数字和特殊字符
        char[] characters = concatArrays(UPPERCASE, LOWERCASE, DIGITS, DIGITS, SPECIAL_CHARACTERS);

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(characters.length);
            char randomChar = characters[randomIndex];
            password.append(randomChar);
        }

        return password.toString();
    }

    /**
     * 生成指定长度的随机代码，使用指定的字符集。
     *
     * @param length  代码的长度
     * @param charset 包含允许字符的字符集
     * @return 生成的随机代码
     */
    public static String genRandomCode(int length, char[] charset) {
        StringBuilder productCode = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(charset.length);
            char character = charset[index];
            productCode.append(character);
        }
        return productCode.toString();
    }

    /**
     * 生成指定长度的随机代码，包含大写字母、小写字母和数字。
     *
     * @param length 代码的长度
     * @return 生成的随机代码
     */
    public static String genRandomCode(int length) {
        char[] charset = concatArrays(UPPERCASE, LOWERCASE, DIGITS, DIGITS);
        return genRandomCode(length, charset);
    }

    /**
     * 生成指定长度的随机代码，使用字符集包含大写字母、数字。
     *
     * @param length 代码的长度
     * @return 生成的随机代码
     */
    public static String genRandomCodeUpperCase(int length) {
        char[] charset = concatArrays(UPPERCASE, DIGITS);
        return genRandomCode(length, charset);
    }

    /**
     * 生成指定长度的随机代码，使用字符集包含小写字母、数字。
     *
     * @param length 代码的长度
     * @return 生成的随机代码
     */
    public static String genRandomCodeLowerCase(int length) {
        char[] charset = concatArrays(LOWERCASE, DIGITS);
        return genRandomCode(length, charset);
    }

    /**
     * 合并多个字符数组为一个字符数组。
     *
     * @param arrays 要合并的字符数组
     * @return 合并后的字符数组
     */
    private static char[] concatArrays(char[]... arrays) {
        int totalLength = 0;
        for (char[] array : arrays) {
            totalLength += array.length; // 获取总长度
        }
        char[] result = new char[totalLength];
        int currentIndex = 0;
        for (char[] array : arrays) {
            System.arraycopy(array, 0, result, currentIndex, array.length);
            currentIndex += array.length; // 根据 index 填充数组到结果
        }
        return result;
    }

}
