package com.ratwareid.spring.Helper;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class GeneralHelper {

    public static String generateRandomString(String words) throws NoSuchAlgorithmException {
        String salt = "sadwa81hidasd0j2dajkshda20udjjksda";
        String tmsp = String.valueOf(System.currentTimeMillis());

        return sha256(salt.concat(words).concat(tmsp));
    }

    public static String sha256(String base) {
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(base.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }
}
