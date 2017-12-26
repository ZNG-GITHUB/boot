package com.zng.system.user.mapper;

import com.zng.system.user.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by John.Zhang on 2017/12/22.
 */
@Mapper
public interface SysUserMapper {

    SysUser findByUserCode(String userCode);

}
