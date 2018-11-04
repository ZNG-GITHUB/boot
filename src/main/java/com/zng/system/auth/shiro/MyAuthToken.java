package com.zng.system.auth.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

public class MyAuthToken extends UsernamePasswordToken {

    private boolean hasSalt = true;

    public MyAuthToken(String username, String password){
        super(username,password);
    }

    public MyAuthToken(String username, String password,boolean hasSalt){
        super(username,password);
        this.hasSalt = hasSalt;
    }

    public boolean isHasSalt() {
        return hasSalt;
    }

    public void setHasSalt(boolean hasSalt) {
        this.hasSalt = hasSalt;
    }
}
