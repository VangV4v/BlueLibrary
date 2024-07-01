package com.vang.authuserservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class AuthuserserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthuserserviceApplication.class, args);
	}

}
