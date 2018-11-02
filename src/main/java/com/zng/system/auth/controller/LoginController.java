package com.zng.system.auth.controller;

import com.zng.common.entity.ResponseCode;
import com.zng.common.entity.ResponseModel;
import com.zng.system.auth.entity.UserToken;
import com.zng.system.auth.service.AuthService;
import com.zng.system.user.entity.SysUser;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(value = "getLoginUser")
    public ResponseModel getLoginUser(){
        return authService.getLoginUser();
    }

    @GetMapping(value = "token")
    public ResponseModel getToken(){
        return new ResponseModel(SecurityUtils.getSubject().getSession().getId());
    }

    /**
     *生成登录二维码
     */
    @GetMapping(value = "api/loginQRCode")
    public ResponseModel getloginQRCode(){
        return new ResponseModel(authService.buildLoginQRCode());
    }

    /**
     * 查询二维码状态
     */
    @GetMapping(value = "api/queryQRCodeStatus/{key}")
    public ResponseModel queryQRCodeStatus(@PathVariable String key){
        return new ResponseModel(authService.queryQRCodeStatus(key));
    }

    /**
     * 二维码登录
     * @param key
     * @return
     */
    @GetMapping(value = "loginByQR/{key}")
    public ResponseModel QRLogin(@PathVariable String key){
        return new ResponseModel(authService.QRLogin(key));
    }

}
