package com.zng.system.auth.service;

import com.zng.common.entity.ResponseModel;
import com.zng.system.auth.alias.PermissionFilterView;
import com.zng.system.auth.entity.UserToken;
import com.zng.system.user.entity.SysPermission;
import com.zng.system.user.entity.SysRole;
import com.zng.system.user.entity.SysUser;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * Created by John.Zhang on 2018/2/27.
 */
public interface AuthService {

    ResponseModel login(UserToken userToken);

    SysUser findByUserCode(String username);

    ResponseModel getLoginUser();

    ResponseModel logout();

    List<SysRole> findRolesByUid(Long id);

    List<PermissionFilterView> findPermissionsByUser(Long userId);
}
