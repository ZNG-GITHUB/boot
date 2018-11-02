package com.zng.system.auth.service;

import com.zng.common.entity.ResponseModel;
import com.zng.system.auth.dto.QRLoginModel;
import com.zng.system.auth.entity.UserToken;
import com.zng.system.user.entity.SysPermission;
import com.zng.system.user.entity.SysRole;
import com.zng.system.user.entity.SysUser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by John.Zhang on 2018/2/27.
 */
public interface AuthService {

    ResponseModel login(UserToken userToken);

    SysUser findByUserCode(String username);

    ResponseModel getLoginUser();

    ResponseModel logout();

    Boolean isAdmin(SysUser user);

    String buildLoginQRCode();

    QRLoginModel queryQRCodeStatus(String key);

    QRLoginModel QRLogin(String key);
}
