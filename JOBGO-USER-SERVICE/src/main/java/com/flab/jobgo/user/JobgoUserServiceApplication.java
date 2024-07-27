package com.flab.jobgo.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan(basePackages = "com.flab.jobgo")
public class JobgoUserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobgoUserServiceApplication.class, args);
	}

}
