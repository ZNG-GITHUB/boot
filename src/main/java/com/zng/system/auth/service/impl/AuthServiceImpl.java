package com.zng.system.auth.service.impl;

import com.zng.common.entity.ResponseCode;
import com.zng.common.entity.ResponseModel;
import com.zng.system.auth.dto.QRLoginModel;
import com.zng.system.auth.entity.UserToken;
import com.zng.system.auth.service.AuthService;
import com.zng.system.auth.shiro.MyAuthToken;
import com.zng.system.auth.shiro.ShiroRealm;
import com.zng.system.user.dto.SysUserDTO;
import com.zng.system.user.entity.SysUser;
import com.zng.system.user.repository.SysUserRepository;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by John.Zhang on 2018/2/27.
 */
@Service(value = "authService")
public class AuthServiceImpl implements AuthService {

    private static HashMap<String,QRLoginModel> qrCodeMap = new HashMap<>();

    @Autowired
    private SysUserRepository userRepository;


    @Override
    public ResponseModel login(UserToken userToken) {
        MyAuthToken token = new MyAuthToken(userToken.getUsername(), userToken.getPassword());
        try {
            SecurityUtils.getSubject().login(token);
        }catch (UnknownAccountException e){
            return new ResponseModel(ResponseCode.Error,"用户名不存在",e.getMessage());
        }catch (IncorrectCredentialsException e) {
            return new ResponseModel(ResponseCode.Error,"密码错误",e.getMessage());
        }catch (LockedAccountException e){
            return new ResponseModel(ResponseCode.Error,"用户已被锁定，无法登录",e.getMessage());
        }catch (Exception e){
            return new ResponseModel(ResponseCode.Error,"其他错误",e.getMessage());
        }
        return new ResponseModel(SecurityUtils.getSubject().getSession().getId());
    }

    @Override
    public SysUser findByUserCode(String usercode) {
        return userRepository.findByUserCode(usercode);
    }

    @Override
    public ResponseModel getLoginUser() {
        SysUser user = (SysUser)SecurityUtils.getSubject().getPrincipal();
        String sessionId = SecurityUtils.getSubject().getSession().getId().toString();
        if(user != null){
            user = userRepository.findByIdSoftly(user.getId());
            user.setDescr(sessionId);
            return new ResponseModel(SysUserDTO.toDTO(user));
        }
        return new ResponseModel(ResponseCode.NoAuth,"未授权");
    }

    @Override
    public ResponseModel logout() {
        SecurityUtils.getSubject().logout();
        return new ResponseModel();
    }

    @Override
    public Boolean isAdmin(SysUser user) {
        if(user.getId().equals(1L)){
            return true;
        }
        return false;
    }

    @Override
    public String buildLoginQRCode() {
        String uuid = UUID.randomUUID().toString();
        qrCodeMap.put(uuid,new QRLoginModel(1,"等待中",null));
        return uuid;
    }

    @Override
    public QRLoginModel queryQRCodeStatus(String key) {
        if(!StringUtils.isEmpty(key) && qrCodeMap.containsKey(key)){
            QRLoginModel model = qrCodeMap.get(key);
            if(model.getCode() == 2){
                try {
                    SysUser user = (SysUser)model.getValue();
                    MyAuthToken token = new MyAuthToken(user.getUserCode(), user.getPassword(),false);
                    SecurityUtils.getSubject().login(token);
                    String sessionId = SecurityUtils.getSubject().getSession().getId().toString();
                    model.setValue(sessionId);
                }catch (Exception e){
                    return new QRLoginModel(3,"登录失败",null);
                }finally {
                    qrCodeMap.remove(key);
                }
            }
            return model;
        }
        return new QRLoginModel(4,"二维码无效",null);
    }

    @Override
    public QRLoginModel QRLogin(String key) {

        SysUser user = (SysUser)SecurityUtils.getSubject().getPrincipal();
        if(user != null){
            QRLoginModel res = new QRLoginModel(2,"登录成功",user);
            qrCodeMap.put(key,res);
            return res;
        }
        return new QRLoginModel(3,"登录失败",null);
    }

}
