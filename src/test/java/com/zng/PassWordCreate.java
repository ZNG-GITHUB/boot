package com.zng;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.junit.Test;

/**
 * Created by John.Zhang on 2018/2/27.
 */
public class PassWordCreate {

    @Test
    public void getNewPassword(){
        String newPassword = new SimpleHash(
                "MD5",           //加密算法
                "123",      //密码
                ByteSource.Util.bytes("10086"),  //salt盐   username + salt
                2   //迭代次数
        ).toHex();
        System.out.print(newPassword);
    }
}
