package com.list.index.live.live_index_list.services.passutil;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;

public class PasswordEncrypter {

    public static String encryptPassword(String Password) throws NoSuchAlgorithmException {

        MessageDigest md5 = MessageDigest.getInstance("MD5");

        md5.update(Password.getBytes());
        byte[] digested = md5.digest();

        return HexFormat.of().formatHex(digested);
    }
}
