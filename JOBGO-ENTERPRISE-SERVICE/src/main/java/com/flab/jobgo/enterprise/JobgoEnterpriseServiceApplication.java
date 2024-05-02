package com.flab.jobgo.enterprise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
@EntityScan(basePackages = {"com.flab.jobgo"}) // 공통 라이브러리 Entity를 스캔하기 위함
public class JobgoEnterpriseServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobgoEnterpriseServiceApplication.class, args);
	}

}
