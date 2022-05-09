package com.edudev.mspayroll.config;

import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadBalanceServerInstanceConfiguration {


    @Bean
    ServiceInstanceListSupplier serviceInstanceListSupplier() {
        return new LoadBalanceInstanceSupplier("ms-worker");
    }

}
