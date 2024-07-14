package com.vang.typeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class TypeserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TypeserviceApplication.class, args);
	}

}