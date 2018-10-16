package com.kiss.account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
//@EnableDiscoveryClient
@EnableAutoConfiguration
@EnableEurekaClient
public class KissAccountApplication {

    public static void main(String[] args) {
        SpringApplication.run(KissAccountApplication.class, args);
    }
}