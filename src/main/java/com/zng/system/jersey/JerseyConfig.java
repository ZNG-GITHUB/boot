package com.zng.system.jersey;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.RequestContextFilter;


/**
 * Created by John.Zhang on 2017/12/14.
 */
@Configuration
public class JerseyConfig extends ResourceConfig{
    public JerseyConfig(){
        packages("com.zng");
        register(RequestContextFilter.class);
    }
}
