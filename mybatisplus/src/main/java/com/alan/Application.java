package com.alan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by Ke Zhang on 2017/9/13.
 */
@ImportResource(locations={"classpath*:**/config/**/*.xml"})
@EnableTransactionManagement
@EnableScheduling
@SpringBootApplication
public class Application {

    public static void main(String[] args){
        ApplicationContext context = SpringApplication.run(Application.class, args);

    }

}
