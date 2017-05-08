package com.dealmoon.ucenter;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.dealmoon.ucenter.util.MyFilter;

@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@Configuration
@ComponentScan
@EnableDiscoveryClient
public class UCenterServiceApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(UCenterServiceApplication.class).web(true).run(args);
    }
    
    @Bean
    public MyFilter myFilter(){
    	return new MyFilter();
    }
}
