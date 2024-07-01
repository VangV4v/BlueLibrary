package com.vang.userservice.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    NewTopic sendNotification() {

        return new NewTopic("sendNotification", 1, (short)1);
    }

}