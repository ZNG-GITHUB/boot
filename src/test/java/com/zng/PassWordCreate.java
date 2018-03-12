package com.zng;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.StringUtils;
import org.junit.*;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by John.Zhang on 2018/2/27.
 */
public class PassWordCreate {

    private String encodingAlgorithm = "MD5";

    private String characterEncoding;

    public String getNewPassword(){
        String newPassword = new SimpleHash(
                "MD5",           //加密算法
                "123",      //密码
                ByteSource.Util.bytes("10086"),  //salt盐   username + salt
                2   //迭代次数
        ).toHex();
        System.out.print(newPassword);
        return newPassword;
    }
    @org.junit.Test
    public void getMD5Password(){
        String newPassword = new SimpleHash(
                "MD5",           //加密算法
                "123"
        ).toHex();
        System.out.print(newPassword);
    }


}
