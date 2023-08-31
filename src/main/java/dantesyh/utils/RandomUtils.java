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

    public static String genUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static String generateRandomPassword(int length) {
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder(length);

        char[] characters = concatArrays(UPPERCASE, LOWERCASE, DIGITS, DIGITS, SPECIAL_CHARACTERS);

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(characters.length);
            char randomChar = characters[randomIndex];
            password.append(randomChar);
        }

        return password.toString();
    }

    public static String genRandomCode(int length) {
        char[] charset = concatArrays(UPPERCASE, LOWERCASE, DIGITS, DIGITS);
        return genRandomCode(length, charset);
    }

    public static String genRandomCodeUpperCase(int length) {
        char[] charset = concatArrays(UPPERCASE, DIGITS);
        return genRandomCode(length, charset);
    }

    public static String genRandomCodeLowerCase(int length) {
        char[] charset = concatArrays(LOWERCASE, DIGITS);
        return genRandomCode(length, charset);
    }


    public static String genRandomCode(int length, char[] charset) {
        StringBuilder productCode = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(charset.length);
            char character = charset[index];
            productCode.append(character);
        }
        return productCode.toString();
    }

    private static char[] concatArrays(char[]... arrays) {
        int totalLength = 0;
        for (char[] array : arrays) {
            totalLength += array.length;
        }

        char[] result = new char[totalLength];
        int currentIndex = 0;

        for (char[] array : arrays) {
            System.arraycopy(array, 0, result, currentIndex, array.length);
            currentIndex += array.length;
        }

        return result;
    }
}
