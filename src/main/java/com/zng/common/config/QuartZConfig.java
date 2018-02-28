package com.zng.common.config;

import org.quartz.Scheduler;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by John.Zhang on 2018/2/24.
 */
@Configuration
@ConfigurationProperties(prefix = "quartz.config")
public class QuartZConfig {

    /**
     * 配置文件路径
     */
    private String propertiesPath;

    /**
     * 是否启动
     */
    private Boolean start;

    @Bean //将一个方法产生为Bean并交给Spring容器管理(@Bean只能用在方法上)
    public SchedulerFactoryBean schedulerFactoryBean(DataSource dataSource) throws IOException {
        //Spring提供SchedulerFactoryBean为Scheduler提供配置信息,并被Spring容器管理其生命周期
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        //启动时更新己存在的Job，这样就不用每次修改targetObject后删除qrtz_job_details表对应记录了
        factory.setOverwriteExistingJobs(true);
        // 延时启动(秒)
        //factory.setStartupDelay(20);
        //设置quartz的配置文件
        Properties QuartzPropertie = quartzProperties();
        factory.setQuartzProperties(QuartzPropertie);
        //设置数据源(使用系统的主数据源，覆盖propertis文件的dataSource配置)
        factory.setDataSource(dataSource);
        factory.setAutoStartup(start);
        return factory;
    }

    @Bean
    public Properties quartzProperties() throws IOException {
        //使用Spring的PropertiesFactoryBean对属性配置文件进行管理
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation(new ClassPathResource(propertiesPath));
        //重要：保证其初始化
        propertiesFactoryBean.afterPropertiesSet();
        return propertiesFactoryBean.getObject();
    }

    public String getPropertiesPath() {
        return propertiesPath;
    }

    public void setPropertiesPath(String propertiesPath) {
        this.propertiesPath = propertiesPath;
    }

    public Boolean getStart() {
        return start;
    }

    public void setStart(Boolean start) {
        this.start = start;
    }
}
