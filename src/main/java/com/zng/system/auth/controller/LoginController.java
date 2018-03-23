package com.zng.system.auth.controller;

import com.zng.common.entity.ResponseModel;
import com.zng.system.auth.entity.UserToken;
import com.zng.system.auth.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping(value = "getLoginUser")
    public ResponseModel getLoginUser(){
        return authService.getLoginUser();
    }

}
