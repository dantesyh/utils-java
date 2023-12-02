package dantesyh.utils;

import org.junit.jupiter.api.Test;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.util.Map;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author dante
 * @since 2023/8/31
 */
class SecureUtilsTest {
    private static final Logger logger = Logger.getLogger(SecureUtils.class.getName());

    @Test
    void testAES() throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        String secretKey = "myseckeyeyhds";
        String originalText = "Hello, AES Encryption!";

        logger.info("Original Text: " + originalText);

        String encryptedText = SecureUtils.encryptAES(originalText, secretKey);
        logger.info("Encrypted Text: " + encryptedText);

        String decryptedText = SecureUtils.decryptAES(encryptedText, secretKey);
        logger.info("Decrypted Text: " + decryptedText);

        assertEquals(originalText, decryptedText);
    }

    @Test
    void testRSA() throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeySpecException, InvalidKeyException, SignatureException {
        Map<String, String> keyPair = SecureUtils.keyPairMap();
        String publicKey = keyPair.get(SecureUtils.PUBLIC_KEY);
        String privateKey = keyPair.get(SecureUtils.PRIVATE_KEY);

        String originalText = "Hello, RSA!";
        logger.info("Original Text: " + originalText);

        String encryptedText = SecureUtils.encryptRSA(originalText, publicKey);
        logger.info("Encrypted Text: " + encryptedText);

        String decryptedText = SecureUtils.decryptRSA(encryptedText, privateKey);
        logger.info("Decrypted Text: " + decryptedText);

        String signature = SecureUtils.signRSA(originalText, privateKey);
        logger.info("Signature: " + signature);

        boolean isVerified = SecureUtils.verifyRSA(originalText, publicKey, signature);
        logger.info("Signature Verification: " + isVerified);

        assertEquals(decryptedText, originalText);
        assertTrue(isVerified);
    }

    @Test
    void maskPhoneNumber() {
        String phone = "18806608995";
        String masked = SecureUtils.maskPhoneNumber(phone);
        assertNotEquals(phone, masked);
        logger.info(masked);
    }

    @Test
    void maskEmail() {
        String email = "dantesyh123@gmail.com";
        String masked = SecureUtils.maskEmail(email);
        assertNotEquals(email, masked);
        logger.info(masked);
    }
}