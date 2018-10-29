package com.byk.rong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class RongApplication {

	public static void main(String[] args) {
		SpringApplication.run(RongApplication.class, args);
	}
}
