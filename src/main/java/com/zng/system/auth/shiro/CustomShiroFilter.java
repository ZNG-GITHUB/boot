package com.zng.system.auth.shiro;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by John.Zhang on 2018/3/6.
 */
public class CustomShiroFilter extends FormAuthenticationFilter {

    @Override
    protected void redirectToLogin(ServletRequest request, ServletResponse response) throws IOException {
        String loginUrl = super.getLoginUrl();
        HttpServletResponse res = (HttpServletResponse)response;
        HttpServletRequest req = (HttpServletRequest) request;
        res.setStatus(200);
//        res.sendRedirect(loginUrl);
    }



}
