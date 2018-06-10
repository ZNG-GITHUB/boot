package com.zng.system.auth.filter;

import com.zng.system.auth.alias.PermissionFilterView;
import com.zng.system.user.entity.SysUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by John.Zhang on 2018/3/22.
 */
public class PermissionFilter extends AccessControlFilter{
    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object mappedValue) throws Exception {

        //获得请求地址
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        String url = request.getRequestURI();
        String urlType = request.getMethod();

        //获得当前登录用户拥有的权限
        boolean isAuthenticated = SecurityUtils.getSubject().isAuthenticated();
        if(isAuthenticated){
            SysUser user = (SysUser)SecurityUtils.getSubject().getPrincipal();
            List<PermissionFilterView> pers = user.getPermissions();
            boolean hasPer = checkUrlPermission(pers,url,urlType);
            if(hasPer || user.getUserCode().equals("admin")){
                return true;
            }
        }
        response.setStatus(401);
        return false;
    }

    private boolean checkUrlPermission(List<PermissionFilterView> pers, String url, String urlType) {
        for(PermissionFilterView view : pers){
            String hasUrl = view.getUrl();
            String type = view.getUrlType();
            Pattern p = Pattern.compile(hasUrl);
            Matcher m = p.matcher(url);
            boolean urlMactch = m.matches();
            boolean typeMactch = matchRequestType(type,urlType);
            if(urlMactch && typeMactch){
                return true;
            }
        }
        return false;
    }

    private boolean matchRequestType(String type, String urlType) {
        if(StringUtils.isEmpty(type) || type.toLowerCase().equals(urlType.toLowerCase())){
            return true;
        }
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        return false;
    }
}
