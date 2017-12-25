package com.zng.system.user.mapper;

import com.zng.system.user.entity.SysUser;

/**
 * Created by John.Zhang on 2017/12/22.
 */
public interface SysUserMapper {

    SysUser findByUserCode(String userCode);

}
