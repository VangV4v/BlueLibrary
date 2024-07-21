package com.vang.bookservice.configuration;

import com.vang.bookservice.grpc.gen.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GrpcConfiguration {

    private static final String IP = "192.168.160.1";
    private static final int TYPE_PORT = 6001;
    private static final int PUBLISHER_PORT = 6002;
    private static final int AUTHOR_PORT = 6003;
    private static final int IMAGE_PORT = 6005;

    @Bean
    public GetAuthorByIdGrpc.GetAuthorByIdBlockingStub getAuthorByIdBlockingStub() {

        ManagedChannel channel = ManagedChannelBuilder.forAddress(IP, AUTHOR_PORT).usePlaintext().build();
        return GetAuthorByIdGrpc.newBlockingStub(channel);
    }

    @Bean
    public UpdateCountAuthorGrpc.UpdateCountAuthorBlockingStub updateCountAuthorBlockingStub() {

        ManagedChannel channel = ManagedChannelBuilder.forAddress(IP, AUTHOR_PORT).usePlaintext().build();
        return UpdateCountAuthorGrpc.newBlockingStub(channel);
    }

    @Bean
    public GetTypeByIdGrpc.GetTypeByIdBlockingStub getTypeByIdBlockingStub() {

        ManagedChannel channel = ManagedChannelBuilder.forAddress(IP, TYPE_PORT).usePlaintext().build();
        return GetTypeByIdGrpc.newBlockingStub(channel);
    }

    @Bean
    public UpdateCountTypeGrpc.UpdateCountTypeBlockingStub updateCountTypeBlockingStub() {

        ManagedChannel channel = ManagedChannelBuilder.forAddress(IP, TYPE_PORT).usePlaintext().build();
        return UpdateCountTypeGrpc.newBlockingStub(channel);
    }

    @Bean
    public GetPublisherByIdGrpc.GetPublisherByIdBlockingStub getPublisherByIdBlockingStub() {

        ManagedChannel channel = ManagedChannelBuilder.forAddress(IP, PUBLISHER_PORT).usePlaintext().build();
        return GetPublisherByIdGrpc.newBlockingStub(channel);
    }

    @Bean
    public UpdateCountPublisherGrpc.UpdateCountPublisherBlockingStub updateCountPublisherBlockingStub() {

        ManagedChannel channel = ManagedChannelBuilder.forAddress(IP, PUBLISHER_PORT).usePlaintext().build();
        return UpdateCountPublisherGrpc.newBlockingStub(channel);
    }

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