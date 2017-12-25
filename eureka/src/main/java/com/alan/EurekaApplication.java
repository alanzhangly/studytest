package com.alan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Created by Ke Zhang on 2017/12/15.
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaApplication {

    public static void main(String[] args){
        SpringApplication.run(EurekaApplication.class, args);
    }
}
