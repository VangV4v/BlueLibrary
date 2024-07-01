package com.vang.authuserservice.grpc;

import com.vang.authuserservice.grpc.gen.AuthenticateUserGrpc;
import com.vang.authuserservice.grpc.gen.AuthenticateUserReply;
import com.vang.authuserservice.grpc.gen.AuthenticateUserRequest;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticateUserGrpcClientImpl {

    private final AuthenticateUserGrpc.AuthenticateUserBlockingStub authenticateUserBlockingStub;

    @Autowired
    public AuthenticateUserGrpcClientImpl(AuthenticateUserGrpc.AuthenticateUserBlockingStub authenticateUserBlockingStub) {
        this.authenticateUserBlockingStub = authenticateUserBlockingStub;
    }

    public String authenticateUser(String username) {

        AuthenticateUserReply reply = authenticateUserBlockingStub.login(AuthenticateUserRequest.newBuilder().setUsername(username).build());
        if(reply.getStatus()) {

            return reply.getPassword();
        }
        return null;
    }

}