package com.zng.system.menu.service.impl;

import com.zng.system.auth.service.AuthService;
import com.zng.system.menu.dto.MenuDTO;
import com.zng.system.menu.entity.Menu;
import com.zng.system.menu.repository.MenuRepository;
import com.zng.system.menu.service.MenuService;
import com.zng.system.user.entity.SysUser;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author John.Zhang
 * @version 1.0
 * @date 2018/8/24
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private AuthService authService;

    @Autowired
    private MenuRepository menuRepository;

    @Override
    public List<MenuDTO> getByUser() {

        //获取当前登录用户
        SysUser login = (SysUser) SecurityUtils.getSubject().getPrincipal();

        //判断是否是admin
        boolean isAdmin = authService.isAdmin(login);
        List<Menu> menus = new ArrayList<>();
        if(isAdmin){
            menus = menuRepository.findShowMenuAllSoftly();
        }
        
        return null;
    }
}
