package com.kiss.account.utils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Random;

/**
 * 加密工具类，用以加密用户的密码
 */
public class CryptoUtil {

    public static String salt() {

        return String.valueOf((int) ((Math.random() * 9 + 1) * 100000));
    }

    public static String hmacSHA256(String message, String secret) {

        String hash = "";

        try {
            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKeySpec = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
            sha256_HMAC.init(secretKeySpec);
            byte[] bytes = sha256_HMAC.doFinal(message.getBytes());
            hash = byteArrayToHexString(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return hash;
    }

    public static String hmacSHA1(String message, String secret) {

        String hash = "";

        try {
            Mac sha1_HMAC = Mac.getInstance("HmacSHA1");
            SecretKeySpec secretKeySpec = new SecretKeySpec(secret.getBytes(), "HmacSHA1");
            sha1_HMAC.init(secretKeySpec);
            byte[] bytes = sha1_HMAC.doFinal(message.getBytes());
            hash = byteArrayToHexString(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return hash;
    }

    public static String uniqueId() {

        Random random = new Random();
        String id = String.valueOf(random.nextInt(25) + 65);

        do {
            int ascii = random.nextInt(42) + 48;
            if (ascii < 58 || ascii > 64) {
                id = id + String.valueOf(ascii);
            }
        } while (id.length() < 32);

        return id;
    }

    private static String byteArrayToHexString(byte[] bytes) {

        StringBuilder hs = new StringBuilder();
        String stamp;

        for (int n = 0; bytes != null && n < bytes.length; n++) {
            stamp = Integer.toHexString(bytes[n] & 0XFF);
            if (stamp.length() == 1)
                hs.append('0');
            hs.append(stamp);
        }

        return hs.toString().toLowerCase();
    }

    public static String sha1(String msg) {

        String hash = "";

        try {
            byte[] by = msg.getBytes();
            MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
            messageDigest.update(by);
            byte[] b = messageDigest.digest();
            hash = Base64.getEncoder().encodeToString(b);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return hash;
    }
}
