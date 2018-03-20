package com.zng.common.config;

import com.zng.system.user.entity.SysUser;
import org.apache.shiro.SecurityUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

/**
 * Created By Zng on 2017/12/18
 */
@Configuration
public class UserAuditorConfig implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
        Optional<String> name = Optional.of("系统管理员");
        if(user != null && user.getUserName() != null){
            name = Optional.of(user.getUserName());
        }
        return name;
    }
}
