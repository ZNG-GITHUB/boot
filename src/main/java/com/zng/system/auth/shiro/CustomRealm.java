package com.zng.system.auth.shiro;

import com.zng.system.user.entity.SysUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cas.CasAuthenticationException;
import org.apache.shiro.cas.CasToken;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.StringUtils;

import java.io.Serializable;

/**
 * Created by John.Zhang on 2018/3/8.
 */
public class CustomRealm extends AuthorizingRealm {

    protected void onInit() {
        super.onInit();
    }

    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        CasToken casToken = (CasToken)token;
        if(token == null) {
            return null;
        } else {
            String ticket = (String)casToken.getCredentials();
            if(!StringUtils.hasText(ticket)) {
                return null;
            } else {
                Session session = SecurityUtils.getSecurityManager().getSession(new SessionKey() {
                    @Override
                    public Serializable getSessionId() {
                        return ticket;
                    }
                });
                if(session == null){
                    throw new CasAuthenticationException("Unable to validate ticket [" + ticket + "]");
                }
                SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();

                casToken.setUserId(user.getUserCode());
                SimplePrincipalCollection principalCollection = new SimplePrincipalCollection(user, this.getName());
                return new SimpleAuthenticationInfo(principalCollection, ticket);
            }
        }
    }

    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        return simpleAuthorizationInfo;
    }

}
