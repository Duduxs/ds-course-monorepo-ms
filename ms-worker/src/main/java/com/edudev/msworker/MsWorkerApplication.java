package com.edudev.msworker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MsWorkerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsWorkerApplication.class, args);
	}

}
