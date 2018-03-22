package com.zng.system.auth.mapper;

import com.zng.system.auth.alias.PermissionFilterView;
import com.zng.system.user.entity.SysPermission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by John.Zhang on 2018/3/22.
 */
@Mapper
public interface SysPermissionMapper {

    List<PermissionFilterView> findPermissionsByUser(Long userId);
}
