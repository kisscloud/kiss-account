package com.kiss.account.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class LdapUtil {

    public static String ssha(String password,String salt) {

        byte[] saltBytes = salt.getBytes();

        try {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(password.getBytes());
            crypt.update(saltBytes);
            byte[] hash = crypt.digest();

            byte[] hashPlusSalt = new byte[hash.length + saltBytes.length];
            System.arraycopy(hash, 0, hashPlusSalt, 0, hash.length);
            System.arraycopy(saltBytes, 0, hashPlusSalt, hash.length, saltBytes.length);

            return "{ssha}" + Base64.getEncoder().encodeToString(hashPlusSalt);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return password;
        }
    }
}
