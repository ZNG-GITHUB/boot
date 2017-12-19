package com.zng.system.user.service;

import com.zng.system.user.entity.SysUser;

/**
 * Created by John.Zhang on 2017/12/19.
 */
public interface SysUserService {
    SysUser findByUserCode(String userCode);
}
