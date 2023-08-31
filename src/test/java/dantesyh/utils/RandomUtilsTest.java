package dantesyh.utils;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author dante
 * @since 2023/8/31
 */
class RandomUtilsTest {
    private static final Logger logger = LoggerFactory.getLogger(SecureUtils.class);

    @Test
    void testGenerator(){
        int passwordLength = 10;
        int codeLength = 6;

        String randomUUID = RandomUtils.genUUID();
        logger.debug("Random UUID: " + randomUUID);

        String randomPassword = RandomUtils.generateRandomPassword(passwordLength);
        logger.debug("Random Password: " + randomPassword);

        String randomCode = RandomUtils.genRandomCode(codeLength);
        logger.debug("Random Code: " + randomCode);

        String randomUpperCaseCode = RandomUtils.genRandomCodeUpperCase(codeLength);
        logger.debug("Random Upper Case Code: " + randomUpperCaseCode);

        String randomLowerCaseCode = RandomUtils.genRandomCodeLowerCase(codeLength);
        logger.debug("Random Lower Case Code: " + randomLowerCaseCode);
    }

}