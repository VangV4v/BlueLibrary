package com.vang.publisherservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class PublisherserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PublisherserviceApplication.class, args);
	}

}