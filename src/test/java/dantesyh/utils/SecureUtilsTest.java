package dantesyh.utils;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author dante
 * @since 2023/8/31
 */
class SecureUtilsTest {
    private static final Logger logger = LoggerFactory.getLogger(SecureUtils.class);

    @Test
    void testAES() throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        String secretKey = "myseckeyeyhds";
        String originalText = "Hello, AES Encryption!";

        logger.debug("Original Text: " + originalText);

        String encryptedText = SecureUtils.encryptAES(originalText, secretKey);
        logger.debug("Encrypted Text: " + encryptedText);

        String decryptedText = SecureUtils.decryptAES(encryptedText, secretKey);
        logger.debug("Decrypted Text: " + decryptedText);

        assertEquals(originalText, decryptedText);
    }

    @Test
    void testRSA() throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeySpecException, InvalidKeyException, SignatureException {
        Map<String, String> keyPair = SecureUtils.keyPairMap();
        String publicKey = keyPair.get(SecureUtils.PUBLIC_KEY);
        String privateKey = keyPair.get(SecureUtils.PRIVATE_KEY);

        String originalText = "Hello, RSA!";
        logger.debug("Original Text: " + originalText);

        String encryptedText = SecureUtils.encryptRSA(originalText, publicKey);
        logger.debug("Encrypted Text: " + encryptedText);

        String decryptedText = SecureUtils.decryptRSA(encryptedText, privateKey);
        logger.debug("Decrypted Text: " + decryptedText);

        String signature = SecureUtils.signRSA(originalText, privateKey);
        logger.debug("Signature: " + signature);

        boolean isVerified = SecureUtils.verifyRSA(originalText, publicKey, signature);
        logger.debug("Signature Verification: " + isVerified);

        assertEquals(decryptedText, originalText);
        assertTrue(isVerified);
    }
}