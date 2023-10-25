package dantesyh.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.logging.Logger;

/**
 * @author dante
 * @since 2023/8/31
 */
class RandomUtilsTest {
    private static final Logger logger = Logger.getLogger(SecureUtils.class.getName());

    @Test
    void testGenerator() {
        int passwordLength = 10;
        int codeLength = 6;

        String randomUUID = RandomUtils.genUUID();
        logger.info("Random UUID: " + randomUUID);

        String randomPassword = RandomUtils.generateRandomPassword(passwordLength);
        logger.info("Random Password: " + randomPassword);

        String randomCode = RandomUtils.genRandomCode(codeLength);
        logger.info("Random Code: " + randomCode);

        String randomUpperCaseCode = RandomUtils.genRandomCodeUpperCase(codeLength);
        logger.info("Random Upper Case Code: " + randomUpperCaseCode);

        String randomLowerCaseCode = RandomUtils.genRandomCodeLowerCase(codeLength);
        logger.info("Random Lower Case Code: " + randomLowerCaseCode);

        Assertions.assertEquals(randomCode.length(), codeLength);
    }

}