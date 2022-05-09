package com.edudev.mspayroll.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableFeignClients
public class AppConfig {

    @Bean
    public RestTemplate create() {
        return new RestTemplate();
    }

}
