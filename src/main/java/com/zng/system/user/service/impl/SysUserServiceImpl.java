package com.zng.system.user.service.impl;

import com.zng.system.user.entity.SysPermission;
import com.zng.system.user.entity.SysRole;
import com.zng.system.user.entity.SysUser;
import com.zng.system.user.mapper.SysUserMapper;
import com.zng.system.user.repository.SysPermissionRepository;
import com.zng.system.user.repository.SysRoleRepository;
import com.zng.system.user.repository.SysUserRepository;
import com.zng.system.user.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by John.Zhang on 2017/12/19.
 */
@Service(value = "sysUserService")
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserRepository sysUserRepository;
    @Autowired
    private SysPermissionRepository sysPermissionRepository;
    @Autowired
    private SysRoleRepository sysRoleRepository;
//    @Autowired
//    private SysUserMapper sysUserMapper;

    @Override
    public SysUser findByUserCode(String userCode) {
        return sysUserRepository.findByUserCode(userCode);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SysUser> findAllUsersSoftly() {
        return sysUserRepository.findAllSoftly();
    }

    @Override
    @Transactional(readOnly = true)
    public List<SysUser> findAllUsers() {
        List<SysUser> users = sysUserRepository.findAll(new Specification<SysUser>() {
            @Override
            public Predicate toPredicate(Root<SysUser> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

                List<Predicate> predicateList = new ArrayList<>();
                predicateList.add(criteriaBuilder.isFalse(root.get("isDeleted")));
                query.distinct(true);
                query.where(predicateList.toArray(new Predicate[predicateList.size()]));

                query.orderBy(criteriaBuilder.desc(criteriaBuilder.selectCase().when(criteriaBuilder.equal(root.get("address"),"112"),1).otherwise(0)));

//                query.orderBy(criteriaBuilder.desc(root.get("createDate")));
                return query.getRestriction();
            }
        });
        return users;
    }

    @Override
    public Integer deleteUserById(Long id) {
        sysPermissionRepository.deleteById(id);
//        sysRoleRepository.deleteById(id);
//        sysUserRepository.deleteById(id);
        return 1;
    }

    @Override
    @Transactional
    public SysUser saveUser() {

        SysPermission permission = new SysPermission();
        permission.setName("一级菜单");
        permission.setPerType(SysPermission.PermissionType.MenuGroup);
        permission.setUrl("/001");
        permission.setUrlType("GET");
        permission = sysPermissionRepository.save(permission);
        SysPermission permission2 = new SysPermission();
        permission2.setName("二级菜单");
        permission2.setPerType(SysPermission.PermissionType.Menu);
        permission2.setUrl("/001/002");
        permission2.setUrlType("GET");
        permission2.setParentPermission(permission);
        permission2 = sysPermissionRepository.save(permission2);

        Set<SysPermission> permissions = new HashSet<>();
        permissions.add(permission);

        SysRole role2 = new SysRole();
        role2.setName("二号管理员");
        role2.setType(SysRole.RoleType.Normal);
        role2.setPermissions(permissions);
        role2 = sysRoleRepository.save(role2);

        permissions = new HashSet<>();
        permissions.add(permission2);
        permissions.add(permission);
        SysRole role = new SysRole();
        role.setName("一号管理员");
        role.setType(SysRole.RoleType.Admin);
        role.setPermissions(permissions);
        role = sysRoleRepository.save(role);

        Set<SysRole> roles = new HashSet<>();

        roles.add(role);
        SysUser user = new SysUser();
        user.setUserCode("zng");
        user.setUserName("测试二号");
        user.setPassword("123");
        user.setRoles(roles);
        sysUserRepository.save(user);

        roles = new HashSet<>();
        roles.add(role2);
        roles.add(role);
        SysUser user2 = sysUserRepository.findByUserCode("admin");
        user2.setRoles(roles);
        sysUserRepository.save(user2);

        return null;
    }
}
