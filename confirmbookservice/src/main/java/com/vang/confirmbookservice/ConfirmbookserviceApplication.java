package com.vang.confirmbookservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ConfirmbookserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfirmbookserviceApplication.class, args);
	}

}