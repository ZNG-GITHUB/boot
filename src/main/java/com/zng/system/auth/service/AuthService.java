package com.zng.system.auth.service;

import com.zng.common.entity.ResponseModel;
import com.zng.system.auth.entity.UserToken;
import com.zng.system.user.entity.SysUser;

/**
 * Created by John.Zhang on 2018/2/27.
 */
public interface AuthService {

    ResponseModel login(UserToken userToken);

    SysUser findByUserCode(String username);

    ResponseModel getLoginUser();

    ResponseModel logout();
}
