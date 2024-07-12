package com.vang.authadminservice.configuration;

import com.vang.authadminservice.grpc.gen.AuthenticateAdminGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import net.devh.boot.grpc.server.security.authentication.BasicGrpcAuthenticationReader;
import net.devh.boot.grpc.server.security.authentication.GrpcAuthenticationReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GrpcConfiguration {

    private static final String IP = "192.168.160.1";
    private static final int ADMIN_PORT = 6008;

    @Bean
    public AuthenticateAdminGrpc.AuthenticateAdminBlockingStub authenticateAdminBlockingStub() {

        ManagedChannel channel = ManagedChannelBuilder.forAddress(IP, ADMIN_PORT).usePlaintext().build();
        return AuthenticateAdminGrpc.newBlockingStub(channel);
    }

    @Bean
    public GrpcAuthenticationReader authenticationReader() {

        return new BasicGrpcAuthenticationReader();
    }
}