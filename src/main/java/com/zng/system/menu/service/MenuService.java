package com.zng.system.menu.service;

import com.zng.system.menu.dto.MenuDTO;

import java.util.List;

/**
 * @author John.Zhang
 * @version 1.0
 * @date 2018/8/24
 */
public interface MenuService {

    List<MenuDTO> getByUser();
}
