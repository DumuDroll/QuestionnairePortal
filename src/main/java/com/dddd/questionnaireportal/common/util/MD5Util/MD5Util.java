package com.dddd.questionnaireportal.common.util.MD5Util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;

public class MD5Util {

    private final static String MD5 = "MD5";
    private final static String SHA1PRNG = "SHA1PRNG";
    private final static String SUN = "SUN";

    public static String getSecurePassword(String passwordToHash, byte[] salt){
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance(MD5);
            md.update(salt);
            byte[] bytes = md.digest(passwordToHash.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte aByte : bytes) {
                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }

    public static byte[] getSalt() {
        try {
            SecureRandom sr = SecureRandom.getInstance(SHA1PRNG, SUN);
            byte[] salt = new byte[16];
            sr.nextBytes(salt);
            return salt;
        } catch (NoSuchAlgorithmException | NoSuchProviderException exception){
            exception.printStackTrace();
            return null;
        }
    }
}
