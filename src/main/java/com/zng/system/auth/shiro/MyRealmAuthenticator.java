package com.zng.system.auth.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.realm.AuthorizingRealm;

import java.util.Map;

public class MyRealmAuthenticator extends ModularRealmAuthenticator {

    /**
     * 存放realm
     */
    private Map<String, AuthorizingRealm> definedRealms;

    public void setDefinedRealms(Map<String, AuthorizingRealm> definedRealms) {
        this.definedRealms = definedRealms;
    }

    @Override
    protected AuthenticationInfo doAuthenticate(AuthenticationToken authenticationToken)
            throws AuthenticationException {
        super.assertRealmsConfigured();
        AuthorizingRealm realm = null;
        // 使用自定义Token
        MyAuthToken token = (MyAuthToken) authenticationToken;
        // 判断用户类型
        if (token.isHasSalt()) {
            realm = this.definedRealms.get("shiroRealm");
        } else {
            realm = this.definedRealms.get("noSaltShiroRealm");
        }
        return this.doSingleRealmAuthentication(realm, authenticationToken);
    }
}
