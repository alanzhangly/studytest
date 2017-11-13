//package com.alan.config;
//
//import com.baomidou.mybatisplus.spring.MybatisSqlSessionFactoryBean;
//import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
//
//import javax.sql.DataSource;
//import java.io.IOException;
//
///**
// * Created by Ke Zhang on 2017/9/13.
// */
//@Configuration
//@MapperScan("com.alan.**.mapper")
//public class MybatisPlusConfig {
//
//    @Autowired
//    private DataSource dataSource;
//
//    @Bean
//    public MybatisSqlSessionFactoryBean sqlSessionFactory(){
//        MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();
//        //配置数据源
//        sqlSessionFactory.setDataSource(dataSource);
//        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
//        try {
//            //自动扫描xml文件位置
//            sqlSessionFactory.setMapperLocations(resolver.getResources("classpath*:com/alan/**/mapper/*.xml"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
//}
