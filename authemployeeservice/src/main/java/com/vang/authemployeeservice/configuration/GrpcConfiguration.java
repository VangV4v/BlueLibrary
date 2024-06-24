package com.vang.authemployeeservice.configuration;

import com.vang.authemployeeservice.jwt.grpc.gen.AuthenticateEmployeeGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import net.devh.boot.grpc.server.security.authentication.BasicGrpcAuthenticationReader;
import net.devh.boot.grpc.server.security.authentication.GrpcAuthenticationReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GrpcConfiguration {

    private static final String IP = "192.168.160.1";
    private static final int EMPLOYEE_PORT = 6004;

    @Bean
    public AuthenticateEmployeeGrpc.AuthenticateEmployeeBlockingStub authenticateEmployeeBlockingStub() {

        ManagedChannel channel = ManagedChannelBuilder.forAddress(IP, EMPLOYEE_PORT).usePlaintext().build();
        return AuthenticateEmployeeGrpc.newBlockingStub(channel);
    }

    @Bean
    public GrpcAuthenticationReader authenticationReader() {

        return new BasicGrpcAuthenticationReader();
    }

}