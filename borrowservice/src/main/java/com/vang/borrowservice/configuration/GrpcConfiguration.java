package com.vang.borrowservice.configuration;

import com.vang.borrowservice.grpc.gen.GetAuthUserGrpc;
import com.vang.borrowservice.grpc.gen.GetBookByIDGrpc;
import com.vang.borrowservice.grpc.gen.GetUserByUsernameGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GrpcConfiguration {

    private static final String IP = "192.168.160.1";
    private static final int AUTH_USER_PORT = 5002;
    private static final int BOOK_PORT = 6006;
    private static final int USER_PORT = 6007;

    @Bean
    public GetAuthUserGrpc.GetAuthUserBlockingStub getAuthUserBlockingStub() {

        ManagedChannel channel = ManagedChannelBuilder.forAddress(IP, AUTH_USER_PORT).usePlaintext().build();
        return GetAuthUserGrpc.newBlockingStub(channel);
    }

    @Bean
    public GetUserByUsernameGrpc.GetUserByUsernameBlockingStub getUserByUsernameBlockingStub() {

        ManagedChannel channel = ManagedChannelBuilder.forAddress(IP, USER_PORT).usePlaintext().build();
        return GetUserByUsernameGrpc.newBlockingStub(channel);
    }

    @Bean
    public GetBookByIDGrpc.GetBookByIDBlockingStub getBookByIDBlockingStub() {

        ManagedChannel channel = ManagedChannelBuilder.forAddress(IP, BOOK_PORT).usePlaintext().build();
        return GetBookByIDGrpc.newBlockingStub(channel);
    }
}