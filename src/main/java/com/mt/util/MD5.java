package com.mt.util;

import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5
{
    public static String MD5Encode(String str , String characterEncoding) throws NoSuchAlgorithmException, UnsupportedEncodingException
    {
        //确定计算方法
        MessageDigest md5=MessageDigest.getInstance("MD5");
        BASE64Encoder base64en = new BASE64Encoder();
        //加密后的字符串
        String newstr=base64en.encode(md5.digest(str.getBytes(characterEncoding)));
        return newstr;
    }
}
