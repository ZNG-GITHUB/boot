package com.zng.system.user.controller;

import com.zng.system.user.dto.SysUserDTO;
import com.zng.system.user.entity.SysUser;
import com.zng.system.user.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by John.Zhang on 2017/12/19.
 */
@RestController
@RequestMapping("user")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @GetMapping("getUser/{userCode}")
    public SysUserDTO getUserByUserCode(@PathVariable String userCode){

        SysUser user = sysUserService.findByUserCode(userCode);

        return SysUserDTO.toDTO(user);
    }

}
