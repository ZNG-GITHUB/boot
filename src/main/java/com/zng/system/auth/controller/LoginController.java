package com.zng.system.auth.controller;

import com.zng.common.entity.ResponseModel;
import com.zng.system.auth.entity.UserToken;
import com.zng.system.auth.service.AuthService;
import com.zng.system.user.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by John.Zhang on 2017/10/9.
 */
@RestController
public class LoginController {

    @Autowired
    private AuthService authService;

    @PostMapping(value = "login")
    public ResponseModel login(@RequestBody UserToken userToken){
        return authService.login(userToken);
    }

    @GetMapping(value = "logout")
    public void logout(HttpServletResponse response){
        authService.logout();
        try {
            response.sendRedirect("http://192.168.6.68:8089/");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PostMapping(value = "getLoginUser")
    public ResponseModel getLoginUser(){
        return authService.getLoginUser();
    }

}
