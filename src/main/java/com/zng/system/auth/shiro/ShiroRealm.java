package com.zng.system.auth.shiro;

import com.zng.system.auth.alias.PermissionFilterView;
import com.zng.system.auth.service.AuthService;
import com.zng.system.user.entity.SysPermission;
import com.zng.system.user.entity.SysRole;
import com.zng.system.user.entity.SysUser;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by John.Zhang on 2017/10/10.
 */
public class ShiroRealm extends AuthorizingRealm {
    private Logger logger = LoggerFactory.getLogger(ShiroRealm.class);

    @Autowired
    private AuthService authService;

    /**
     * 登录认证
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        logger.info("验证当前Subject时获取到token为：" + token.toString());
        //查出是否有此用户
        SysUser hasUser = authService.findByUserCode(token.getUsername());
        if (hasUser != null) {
            if(hasUser.getIsLocked() != null && hasUser.getIsLocked().equals(SysUser.IsLocked.LOCKED)){
                throw new LockedAccountException("用户已被锁定，无法登录");
            }

            List<PermissionFilterView> permissions = authService.findPermissionsByUser(hasUser.getId());

            hasUser.setPermissions(permissions);

            // 若存在，将此用户存放到登录认证info中，无需自己做密码对比，Shiro会为我们进行密码对比校验
            return new SimpleAuthenticationInfo(hasUser, hasUser.getPassword(),ByteSource.Util.bytes(hasUser.getSalt()), getName());
        }else {
            throw new UnknownAccountException("用户不存在");
        }
    }

    /**
     * 权限认证
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SysUser user = (SysUser) principalCollection.getPrimaryPrincipal();

        if (user != null) {
            //权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission)
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

            /*SysUser hasUser = authService.findByUserCode(user.getUserCode());
            Set<SysRole> roles = hasUser.getRoles();
            List<String> roleNames = new ArrayList<>();
            for(SysRole role : roles){
                roleNames.add(role.getName());
            }
            info.addRoles(roleNames);*/
            return info;
        }
        // 返回null的话，就会导致任何用户访问被拦截的请求时，都会自动跳转到unauthorizedUrl指定的地址
        return null;
    }
}
