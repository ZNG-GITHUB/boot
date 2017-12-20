package com.zng.system.user.repository;

import com.zng.common.repository.InitRepository;
import com.zng.system.user.entity.SysUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by John.Zhang on 2017/12/19.
 */
@Repository
public interface SysUserRepository extends InitRepository<SysUser,Long>{

    @Query("select u from #{#entityName} u where u.userCode = ?1")
    SysUser findByUserCode(String userCode);

    @Query("select u from #{#entityName} u where u.isHided = 0 and u.isDeleted = 0")
    List<SysUser> findAllHidely();

}
