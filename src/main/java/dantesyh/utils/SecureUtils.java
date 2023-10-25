package dantesyh.utils;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * 安全相关工具类
 *
 * @author dante
 * @since 2023/8/31
 */
public class SecureUtils {
    private SecureUtils() {

    }

    /**
     * AES算法名称
     */
    private static final String AES_ALGORITHM = "AES";

    /**
     * AES加密算法使用的填充方案
     */
    private static final String AES_PADDING = "AES/ECB/PKCS5Padding";

    /**
     * RSA算法名称
     */
    private static final String RSA_ALGORITHM = "RSA";

    /**
     * RSA加密算法使用的填充方案
     */
    private static final String RSA_PADDING = "RSA/ECB/PKCS1Padding";

    /**
     * RSA数字签名算法名称
     */
    private static final String RSA_SIGNATURE_ALGORITHM = "MD5withRSA";

    /**
     * RSA密钥的默认长度
     */
    private static final int RSA_KEY_SIZE = 1024;

    /**
     * 公钥的键名，用于从Map中检索RSA公钥
     */
    public static final String PUBLIC_KEY = "RSAPublicKey";

    /**
     * 私钥的键名，用于从Map中检索RSA私钥
     */
    public static final String PRIVATE_KEY = "RSAPrivateKey";


    /**
     * 使用AES加密算法对明文进行加密。
     *
     * @param plainText 要加密的明文
     * @param secretKey 加密密钥，长度必须小于16个字符
     * @return 经过AES加密后的Base64编码的密文
     * @throws NoSuchPaddingException    如果填充方案不可用
     * @throws NoSuchAlgorithmException  如果加密算法不可用
     * @throws InvalidKeyException       如果提供的密钥无效
     * @throws IllegalBlockSizeException 如果加密数据块大小不合法
     * @throws BadPaddingException       如果填充数据不合法
     */
    public static String encryptAES(String plainText, String secretKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        String secretKeyPadded = paddingKey(secretKey);
        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKeyPadded.getBytes(StandardCharsets.UTF_8), AES_ALGORITHM);
        Cipher cipher = Cipher.getInstance(AES_PADDING);
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);

        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    /**
     * 解密AES加密的文本
     *
     * @param encryptedText 要解密的AES加密文本
     * @param secretKey     用于解密的密钥
     * @return 解密后的原始文本
     * @throws NoSuchPaddingException    如果填充方案不可用
     * @throws NoSuchAlgorithmException  如果加密算法不可用
     * @throws InvalidKeyException       如果提供的密钥无效
     * @throws IllegalBlockSizeException 如果加密数据块大小不合法
     * @throws BadPaddingException       如果填充数据不合法
     */
    public static String decryptAES(String encryptedText, String secretKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        String secretKeyPadded = paddingKey(secretKey);
        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKeyPadded.getBytes(StandardCharsets.UTF_8), AES_ALGORITHM);
        Cipher cipher = Cipher.getInstance(AES_PADDING);
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);

        byte[] encryptedBytes = Base64.getDecoder().decode(encryptedText);
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }

    /**
     * 在密钥长度不足16个字符时，使用零填充对密钥进行填充。
     *
     * @param secretKey 要填充的密钥
     * @return 填充后的密钥，总长度为16个字符
     */
    private static String paddingKey(String secretKey) {
        if (secretKey.length() < 16) {
            int length = 16 - secretKey.length();
            secretKey += "0".repeat(length);
        }
        return secretKey;
    }

    /**
     * 生成默认大小的RSA密钥对。
     *
     * @return 生成的RSA密钥对
     * @throws NoSuchAlgorithmException 如果生成密钥对时发生算法不可用异常
     */
    public static KeyPair keyPair() throws NoSuchAlgorithmException {
        return keyPair(RSA_KEY_SIZE);
    }

    /**
     * 生成指定大小的RSA密钥对。
     *
     * @param keySize 生成密钥对的位数
     * @return 生成的RSA密钥对
     * @throws NoSuchAlgorithmException 如果生成密钥对时发生算法不可用异常
     */
    public static KeyPair keyPair(int keySize) throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(RSA_ALGORITHM);
        keyPairGenerator.initialize(keySize);
        return keyPairGenerator.generateKeyPair();
    }

    /**
     * 生成RSA密钥对并将公钥和私钥存储在一个Map中，使用默认的Map键。
     *
     * @return 包含公钥和私钥的Map
     * @throws NoSuchAlgorithmException 如果生成密钥对时发生算法不可用异常
     */
    public static Map<String, String> keyPairMap() throws NoSuchAlgorithmException {
        return keyPairMap(PUBLIC_KEY, PRIVATE_KEY);
    }

    /**
     * 生成RSA密钥对并将公钥和私钥存储在一个Map中，使用自定义的Map键。
     *
     * @param publicMapKey  存储公钥的Map键
     * @param privateMapKey 存储私钥的Map键
     * @return 包含公钥和私钥的Map
     * @throws NoSuchAlgorithmException 如果生成密钥对时发生算法不可用异常
     */
    public static Map<String, String> keyPairMap(String publicMapKey, String privateMapKey) throws NoSuchAlgorithmException {
        KeyPair keyPair = keyPair();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();

        Map<String, String> keyMap = new HashMap<>(2);
        keyMap.put(publicMapKey, getPublicKeyString(publicKey));
        keyMap.put(privateMapKey, getPrivateKeyString(privateKey));
        return keyMap;
    }

    /**
     * 将RSA公钥转换为Base64编码的字符串。
     *
     * @param publicKey 要转换的公钥
     * @return Base64编码的公钥字符串
     */
    public static String getPublicKeyString(PublicKey publicKey) {
        byte[] publicKeyBytes = publicKey.getEncoded();
        return Base64.getEncoder().encodeToString(publicKeyBytes);
    }

    /**
     * 将RSA私钥转换为Base64编码的字符串。
     *
     * @param privateKey 要转换的私钥
     * @return Base64编码的私钥字符串
     */
    public static String getPrivateKeyString(PrivateKey privateKey) {
        byte[] privateKeyBytes = privateKey.getEncoded();
        return Base64.getEncoder().encodeToString(privateKeyBytes);
    }

    /**
     * 从Base64编码的公钥字符串恢复RSA公钥。
     *
     * @param publicKey Base64编码的公钥字符串
     * @return 恢复的RSA公钥
     * @throws NoSuchAlgorithmException 如果生成密钥时发生算法不可用异常
     * @throws InvalidKeySpecException  如果提供的密钥规范无效
     */
    public static PublicKey getPublicKeyFromString(String publicKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] publicKeyBytes = Base64.getDecoder().decode(publicKey);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
        return keyFactory.generatePublic(keySpec);
    }

    /**
     * 从Base64编码的私钥字符串恢复RSA私钥。
     *
     * @param privateKey Base64编码的私钥字符串
     * @return 恢复的RSA私钥
     * @throws InvalidKeySpecException  如果提供的密钥规范无效
     * @throws NoSuchAlgorithmException 如果生成密钥时发生算法不可用异常
     */
    public static PrivateKey getPrivateKeyFromString(String privateKey) throws InvalidKeySpecException, NoSuchAlgorithmException {
        byte[] privateKeyBytes = Base64.getDecoder().decode(privateKey);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
        return keyFactory.generatePrivate(keySpec);
    }


    /**
     * 使用RSA公钥对明文进行加密。
     *
     * @param plainText 要加密的明文
     * @param publicKey RSA公钥
     * @return 经过RSA加密后的Base64编码的密文
     * @throws NoSuchPaddingException    如果填充方案不可用
     * @throws NoSuchAlgorithmException  如果加密算法不可用
     * @throws InvalidKeyException       如果提供的公钥无效
     * @throws IllegalBlockSizeException 如果加密数据块大小不合法
     * @throws BadPaddingException       如果填充数据不合法
     */
    public static String encryptRSA(String plainText, PublicKey publicKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance(RSA_PADDING);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);

        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    /**
     * 使用RSA公钥对明文进行加密。
     *
     * @param plainText 要加密的明文
     * @param publicKey RSA公钥的Base64编码字符串
     * @return 经过RSA加密后的Base64编码的密文
     * @throws NoSuchPaddingException    如果填充方案不可用
     * @throws NoSuchAlgorithmException  如果加密算法不可用
     * @throws InvalidKeyException       如果提供的公钥无效
     * @throws IllegalBlockSizeException 如果加密数据块大小不合法
     * @throws BadPaddingException       如果填充数据不合法
     * @throws InvalidKeySpecException   如果提供的公钥字符串无效
     */
    public static String encryptRSA(String plainText, String publicKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidKeySpecException {
        return encryptRSA(plainText, getPublicKeyFromString(publicKey));
    }

    /**
     * 使用RSA私钥对密文进行解密。
     *
     * @param encryptedText RSA加密的密文
     * @param privateKey    RSA私钥
     * @return 解密后的原始明文
     * @throws NoSuchPaddingException    如果填充方案不可用
     * @throws NoSuchAlgorithmException  如果加密算法不可用
     * @throws InvalidKeyException       如果提供的私钥无效
     * @throws IllegalBlockSizeException 如果解密数据块大小不合法
     * @throws BadPaddingException       如果填充数据不合法
     */
    public static String decryptRSA(String encryptedText, PrivateKey privateKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance(RSA_PADDING);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);

        byte[] encryptedBytes = Base64.getDecoder().decode(encryptedText);
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }

    /**
     * 使用RSA私钥对密文进行解密。
     *
     * @param plainText  RSA加密的密文
     * @param privateKey RSA私钥的Base64编码字符串
     * @return 解密后的原始明文
     * @throws NoSuchPaddingException    如果填充方案不可用
     * @throws NoSuchAlgorithmException  如果加密算法不可用
     * @throws InvalidKeyException       如果提供的私钥无效
     * @throws IllegalBlockSizeException 如果解密数据块大小不合法
     * @throws BadPaddingException       如果填充数据不合法
     * @throws InvalidKeySpecException   如果提供的私钥字符串无效
     */
    public static String decryptRSA(String plainText, String privateKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidKeySpecException {
        return decryptRSA(plainText, getPrivateKeyFromString(privateKey));
    }

    /**
     * 使用RSA私钥对数据进行数字签名。
     *
     * @param data       要签名的数据的字节数组
     * @param privateKey RSA私钥
     * @return Base64编码的数字签名
     * @throws NoSuchAlgorithmException 如果生成签名时发生算法不可用异常
     * @throws InvalidKeyException      如果提供的私钥无效
     * @throws SignatureException       如果生成签名时发生签名异常
     */
    public static String signRSA(byte[] data, PrivateKey privateKey) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        Signature signature = Signature.getInstance(RSA_SIGNATURE_ALGORITHM);
        signature.initSign(privateKey);
        signature.update(data);
        return Base64.getEncoder().encodeToString(signature.sign());
    }

    /**
     * 使用RSA私钥对数据进行数字签名。
     *
     * @param data       要签名的数据的字符串形式
     * @param privateKey RSA私钥的Base64编码字符串
     * @return Base64编码的数字签名
     * @throws NoSuchAlgorithmException 如果生成签名时发生算法不可用异常
     * @throws InvalidKeyException      如果提供的私钥无效
     * @throws SignatureException       如果生成签名时发生签名异常
     * @throws InvalidKeySpecException  如果提供的私钥字符串无效
     */
    public static String signRSA(String data, String privateKey) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException, InvalidKeySpecException {
        return signRSA(data.getBytes(StandardCharsets.UTF_8), getPrivateKeyFromString(privateKey));
    }

    /**
     * 验证RSA数字签名。
     *
     * @param data      要验证签名的数据的字节数组
     * @param publicKey RSA公钥
     * @param sign      Base64编码的数字签名
     * @return 如果签名有效则返回true，否则返回false
     * @throws NoSuchAlgorithmException 如果验证签名时发生算法不可用异常
     * @throws InvalidKeyException      如果提供的公钥无效
     * @throws SignatureException       如果验证签名时发生签名异常
     */
    public static boolean verifyRSA(byte[] data, PublicKey publicKey, String sign) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        Signature signature = Signature.getInstance(RSA_SIGNATURE_ALGORITHM);
        signature.initVerify(publicKey);
        signature.update(data);
        return signature.verify(Base64.getDecoder().decode(sign));
    }

    /**
     * 验证RSA数字签名。
     *
     * @param data      要验证签名的数据的字符串形式
     * @param publicKey RSA公钥的Base64编码字符串
     * @param sign      Base64编码的数字签名
     * @return 如果签名有效则返回true，否则返回false
     * @throws NoSuchAlgorithmException 如果验证签名时发生算法不可用异常
     * @throws InvalidKeyException      如果提供的公钥无效
     * @throws SignatureException       如果验证签名时发生签名异常
     * @throws InvalidKeySpecException  如果提供的公钥字符串无效
     */
    public static boolean verifyRSA(String data, String publicKey, String sign) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException, InvalidKeySpecException {
        return verifyRSA(data.getBytes(StandardCharsets.UTF_8), getPublicKeyFromString(publicKey), sign);
    }
}
