package com.zng.system.auth.shiro;

import com.zng.common.config.ShiroConfig;
import com.zng.system.auth.service.AuthService;
import com.zng.system.user.entity.SysUser;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cas.CasRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

/**
 * Created by John.Zhang on 2018/3/7.
 */
public class ShiroCasRealm extends CasRealm {

    @Autowired
    private AuthService authService;

    @PostConstruct
    public void initProperty(){
//      setDefaultRoles("ROLE_USER");
        setCasServerUrlPrefix(ShiroConfig.casServerUrlPrefix);
        // 客户端回调地址
        setCasService(ShiroConfig.shiroServerUrlPrefix + ShiroConfig.casFilterUrlPattern);
    }


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String loginName = (String)super.getAvailablePrincipal(principalCollection);

        SysUser hasUser = authService.findByUserCode(loginName);
        if(hasUser!=null){
            return new SimpleAuthorizationInfo();
        }
        return null;
    }

}
