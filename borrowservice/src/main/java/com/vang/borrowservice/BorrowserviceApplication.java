package com.vang.borrowservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class BorrowserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BorrowserviceApplication.class, args);
	}

}