package com.iceicelee.nppa.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * @author: Yao Shuai
 * @date: 2021/3/30 20:04
 */
public class EncryptUtils {

    /**
     * <p>@title sign</p>
     * <p>@description 签名</p>
     *
     * @param toBeSignStr 待签名字符串
     * @return java.lang.String
     */
    public static String sign(String toBeSignStr) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
        messageDigest.update(toBeSignStr.getBytes(StandardCharsets.UTF_8));
        return byteToHexString(messageDigest.digest());
    }

    /**
     * <p>@title aesGcmEncrypt</p>
     * <p>@description Aes-Gcm加密</p>
     *
     * @param content 待加密文本
     * @param key     密钥
     * @return java.lang.String
     */
    public static String aesGcmEncrypt(String content, byte[] key) {
        try {
            // 根据指定算法ALGORITHM自成密码器
            Cipher cipher = Cipher.getInstance("AES/GCM/PKCS5Padding");
            SecretKeySpec skey = new SecretKeySpec(key, "AES");
            cipher.init(Cipher.ENCRYPT_MODE, skey);
            //获取向量
            byte[] ivb = cipher.getIV();
            byte[] encodedByteArray = cipher.doFinal(content.getBytes(StandardCharsets.UTF_8));
            byte[] message = new byte[ivb.length + encodedByteArray.length];
            System.arraycopy(ivb, 0, message, 0, ivb.length);
            System.arraycopy(encodedByteArray, 0, message, ivb.length, encodedByteArray.length);
            return Base64.getEncoder().encodeToString(message);
        } catch (Exception e) {
            //建议自行调整为日志输出或抛出异常
            return null;
        }
    }

    /**
     * <p>@title aesGcmDecrypt</p>
     * <p>@description Aes-Gcm解密</p>
     *
     * @param content 带解密文本
     * @param key     密钥
     * @return java.lang.String
     */
    public static String aesGcmDecrypt(String content, byte[] key) {
        try {
            // 根据指定算法ALGORITHM自成密码器
            Cipher decryptCipher = Cipher.getInstance("AES/GCM/PKCS5Padding");
            SecretKeySpec skey = new SecretKeySpec(key, "AES");
            byte[] encodedArrayWithIv = Base64.getDecoder().decode(content);
            GCMParameterSpec decryptSpec = new GCMParameterSpec(128, encodedArrayWithIv, 0, 12);
            decryptCipher.init(Cipher.DECRYPT_MODE, skey, decryptSpec);
            byte[] b = decryptCipher.doFinal(encodedArrayWithIv, 12, encodedArrayWithIv.length - 12);
            return new String(b, StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * <p>@title byteToHexString</p>
     * <p>@description byte数组转化为16进制字符串</p>
     *
     * @param bytes byte数组
     * @return java.lang.String
     */
    public static String byteToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte aByte : bytes) {
            String strHex = Integer.toHexString(aByte);
            if (strHex.length() > 3) {
                sb.append(strHex.substring(6));
            } else {
                if (strHex.length() < 2) {
                    sb.append("0").append(strHex);
                } else {
                    sb.append(strHex);
                }
            }
        }
        return sb.toString();
    }

    /**
     * <p>@title hexStringToByte</p>
     * <p>@description 十六进制string转二进制byte[]</p>
     *
     * @param str 十六进制字符串
     * @return byte[]
     */
    public static byte[] hexStringToByte(String str) {
        byte[] baKeyword = new byte[str.length() / 2];
        for (int i = 0; i < baKeyword.length; i++) {
            try {
                baKeyword[i] = (byte) (0xff & Integer.parseInt(str.substring(i * 2, i * 2 + 2), 16));
            } catch (Exception e) {
                //建议自行调整为日志输出或抛出异常
                e.printStackTrace();
            }
        }
        return baKeyword;
    }
}
