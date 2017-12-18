package com.zng.common.config;

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

        Optional<String> userName = Optional.of("系统");

        return userName;
    }
}
