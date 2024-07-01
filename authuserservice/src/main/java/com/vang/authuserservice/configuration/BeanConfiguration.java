package com.vang.authuserservice.configuration;

import com.vang.authuserservice.grpc.gen.AuthenticateUserGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    private static final String IP = "192.168.160.1";
    private static final int PORT = 6006;

    @Bean
    public AuthenticateUserGrpc.AuthenticateUserBlockingStub authenticateUserBlockingStub() {

        ManagedChannel channel = ManagedChannelBuilder.forAddress(IP, PORT).usePlaintext().build();
        return AuthenticateUserGrpc.newBlockingStub(channel);
    }

}