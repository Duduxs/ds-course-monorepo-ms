package com.edudev.mspayroll;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MsPayrollApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsPayrollApplication.class, args);
	}

}
