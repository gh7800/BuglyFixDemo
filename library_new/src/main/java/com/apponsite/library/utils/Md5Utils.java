package com.apponsite.library.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by zhang on 2016/8/15.
 */
public class Md5Utils {

    private static Md5Utils instance;
    private Md5Utils(){}

    public static Md5Utils getInstance() {
        if (instance == null) {
            synchronized (Md5Utils.class) {
                if (instance == null) {
                    instance = new Md5Utils();
                }
            }
        }
        return instance;
    }

    public String getMd5(String string) {
        byte[] hash;
        try {
            hash = MessageDigest.getInstance("MD5").digest(string.getBytes("UTF-8"));
            StringBuffer sb = new StringBuffer(hash.length*2);
            for (Byte b : hash) {
                if ((b & 0xFF) < 0x10) {
                    sb.append(0);
                }
                sb.append(Integer.toHexString(b & 0xFF));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return null;
    }
}
