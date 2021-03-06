package com.alanzhang.core.springmvc;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by Ke Zhang on 2017/10/26.
 */
@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = {"com.alanzhang"},
                excludeFilters = {
                        @ComponentScan.Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class)
                })
public class RootConfig {
}
