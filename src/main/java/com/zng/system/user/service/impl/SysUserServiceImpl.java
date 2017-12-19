package com.zng.system.user.service.impl;

import com.zng.system.user.entity.SysUser;
import com.zng.system.user.repository.SysUserRepository;
import com.zng.system.user.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by John.Zhang on 2017/12/19.
 */
@Service(value = "sysUserService")
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserRepository sysUserRepository;

    @Override
    public SysUser findByUserCode(String userCode) {
        return sysUserRepository.findByUserCode(userCode);
    }
}
