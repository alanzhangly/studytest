package com.alan.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by Ke Zhang on 2017/10/26.
 */
@Configuration
@ComponentScan(basePackages = {"com.alan"},
                excludeFilters = {
                        @ComponentScan.Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class)
                })
public class RootConfig {
}
