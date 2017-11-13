package com.alanzhang.core.config;

import com.alibaba.druid.support.http.StatViewServlet;
//import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * Created by lxq on 15-11-19.
 */

@Configuration
public class DruidServletConfig {
	
//	@Bean
//	@Order
//	public ServletRegistrationBean statViewServlet() {
//		StatViewServlet servlet = new StatViewServlet();
//		ServletRegistrationBean bean = new ServletRegistrationBean(servlet, "/druid/*");
//		bean.addInitParameter("loginUsername", "druid");
//		bean.addInitParameter("loginPassword", "pinche");
//
//		return bean;
//	}

//	@Override
//	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//		System.out.println("开始加载数据源");
//		return application.sources(DruidServletConfig.class);
//	}
}