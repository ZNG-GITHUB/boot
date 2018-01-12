package com.zng.system.user.service;

import com.zng.system.user.entity.SysUser;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by John.Zhang on 2017/12/19.
 */
public interface SysUserService {

    SysUser findByUserCode(String userCode);

    List<SysUser> findAllUsersSoftly();

    List<SysUser> findAllUsers();

    Integer deleteUserById(Long id);

    SysUser saveUser();
}
