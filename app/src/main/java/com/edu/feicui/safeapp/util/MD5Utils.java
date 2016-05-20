package com.edu.feicui.safeapp.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by 16245 on 2016/05/16.
 */
public class MD5Utils {
    public static String encode(String password){
        try {
            MessageDigest instance=MessageDigest.getInstance("MD5");
            byte[] digest = instance.digest(password.getBytes());
            StringBuffer sb = new StringBuffer();
            for (byte b : digest) {
                int i = b & 0xff;// 获取字节的低八位有效值
                String hexString = Integer.toHexString(i);// 将整数转为16进制

                if (hexString.length() < 2) {
                    hexString = "0" + hexString;// 如果是1位的话,补0
                }

                sb.append(hexString);
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}
