package com.vang.authorservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class AuthorserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthorserviceApplication.class, args);
	}

}