package com.zng.system.user.controller;

import com.zng.common.entity.ResponseCode;
import com.zng.common.entity.ResponseModel;
import com.zng.system.user.dto.SysUserDTO;
import com.zng.system.user.entity.SysPermission;
import com.zng.system.user.entity.SysUser;
import com.zng.system.user.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by John.Zhang on 2017/12/19.
 */
@RestController
@RequestMapping("user")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @GetMapping("getUser/{userCode}")
    public ResponseModel getUserByUserCode(@PathVariable String userCode){
        SysUser user = sysUserService.findByUserCode(userCode);
        return new ResponseModel(SysUserDTO.toDTO(user));
    }

    @GetMapping("getUsers")
    public ResponseModel getAllUsersSoftly(){
//        List<SysUser> users = sysUserService.findAllUsersSoftly();
        List<SysUser> users = sysUserService.findAllUsers();
        List<SysUserDTO> userDTOs = new ArrayList<>();
        if(!CollectionUtils.isEmpty(users)){
            for(SysUser user : users){
                SysUserDTO dto = SysUserDTO.toDTO(user);
                if(dto != null){
                    userDTOs.add(dto);
                }
            }
        }
        return new ResponseModel(userDTOs);
    }

    @GetMapping("deleteUser/{id}")
    public ResponseModel deleteUser(@PathVariable Long id){
        Integer result = sysUserService.deleteUserById(id);
        return new ResponseModel(ResponseCode.Sucess,"删除成功！");
    }

    @GetMapping("saveUser")
    public ResponseModel saveUser(){
        SysUser sysUser = sysUserService.saveUser();
        return new ResponseModel(ResponseCode.Sucess,"创建成功！");
    }

}
