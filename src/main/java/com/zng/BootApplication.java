package com.zng;

import com.zng.common.repository.MyRepositoryFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableJpaAuditing
@EnableJpaRepositories(basePackages = "com.zng.**.repository",repositoryFactoryBeanClass = MyRepositoryFactoryBean.class)
@EnableSpringDataWebSupport
public class BootApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootApplication.class, args);
	}

}
