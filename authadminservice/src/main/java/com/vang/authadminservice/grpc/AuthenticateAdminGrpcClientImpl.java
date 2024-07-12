package com.vang.authadminservice.grpc;

import com.vang.authadminservice.grpc.gen.AuthenticateAdminGrpc;
import com.vang.authadminservice.grpc.gen.AuthenticateAdminReply;
import com.vang.authadminservice.grpc.gen.AuthenticateAdminRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticateAdminGrpcClientImpl {

    private final AuthenticateAdminGrpc.AuthenticateAdminBlockingStub authenticateAdminBlockingStub;

    @Autowired
    public AuthenticateAdminGrpcClientImpl(AuthenticateAdminGrpc.AuthenticateAdminBlockingStub authenticateAdminBlockingStub) {
        this.authenticateAdminBlockingStub = authenticateAdminBlockingStub;
    }

    public String authenticateAdmin(String username) {

        AuthenticateAdminReply reply = authenticateAdminBlockingStub.login(AuthenticateAdminRequest.newBuilder().setUsername(username).build());
        return reply.getStatus() ? reply.getPassword() : null;
    }
}
