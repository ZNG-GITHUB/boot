package com.zng.system.menu.controller;

import com.zng.common.entity.ResponseModel;
import com.zng.system.menu.dto.MenuDTO;
import com.zng.system.menu.service.MenuService;
import com.zng.system.user.dto.SysUserDTO;
import com.zng.system.user.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping("getByUser")
    public ResponseModel getByUser(){
        List<MenuDTO> menus = menuService.getByUser();
        return new ResponseModel(menus);
    }

}
