package com.vang.userservice.configuration;

import com.vang.userservice.grpc.gen.DeleteImageGrpc;
import com.vang.userservice.grpc.gen.UploadImageGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GrpcConfiguration {

    private final static String IP = "192.168.160.1";
    private final static int IMAGE_PORT = 6005;

    @Bean
    public UploadImageGrpc.UploadImageBlockingStub uploadImageBlockingStub() {

        ManagedChannel channel = ManagedChannelBuilder.forAddress(IP, IMAGE_PORT).usePlaintext().build();
        return UploadImageGrpc.newBlockingStub(channel);
    }

    @Bean
    public DeleteImageGrpc.DeleteImageBlockingStub deleteImageBlockingStub() {

        ManagedChannel channel = ManagedChannelBuilder.forAddress(IP, IMAGE_PORT).usePlaintext().build();
        return DeleteImageGrpc.newBlockingStub(channel);
    }

}