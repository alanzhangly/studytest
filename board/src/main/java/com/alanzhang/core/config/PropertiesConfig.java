package com.alanzhang.core.config;

import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * Created by Ke Zhang on 2017/11/2.
 */
@Configuration
@PropertySource("classpath:application.properties")
public class PropertiesConfig {

//    @Bean
//    public PropertiesFactoryBean propertiesFactoryBean(){
//        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
//        Resource resource = new ClassPathResource("classpath*:application.properties");
//        propertiesFactoryBean.setLocations(resource);
//        return propertiesFactoryBean;
//    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
