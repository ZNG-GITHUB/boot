package com.zng.system.auth.service.impl;

import com.zng.common.entity.ResponseCode;
import com.zng.common.entity.ResponseModel;
import com.zng.system.auth.entity.UserToken;
import com.zng.system.auth.service.AuthService;
import com.zng.system.user.dto.SysUserDTO;
import com.zng.system.user.entity.SysUser;
import com.zng.system.user.repository.SysUserRepository;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by John.Zhang on 2018/2/27.
 */
@Service(value = "authService")
public class AuthServiceImpl implements AuthService {

    @Autowired
    private SysUserRepository userRepository;


    @Override
    public ResponseModel login(UserToken userToken) {
        UsernamePasswordToken token = new UsernamePasswordToken(userToken.getUsername(), userToken.getPassword());
        try {
            SecurityUtils.getSubject().login(token);
        }catch (UnknownAccountException e){
            return new ResponseModel(ResponseCode.Error,"用户名不存在",e);
        }catch (IncorrectCredentialsException e) {
            return new ResponseModel(ResponseCode.Error,"密码错误",e);
        }catch (LockedAccountException e){
            return new ResponseModel(ResponseCode.Error,"用户已被锁定，无法登录",e);
        }catch (Exception e){
            return new ResponseModel(ResponseCode.Error,"其他错误",e);
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

}
