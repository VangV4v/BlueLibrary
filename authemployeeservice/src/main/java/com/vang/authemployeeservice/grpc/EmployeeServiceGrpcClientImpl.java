package com.vang.authemployeeservice.grpc;

import com.vang.authemployeeservice.jwt.grpc.gen.AuthenticateEmployeeGrpc;
import com.vang.authemployeeservice.jwt.grpc.gen.AuthenticateEmployeeReply;
import com.vang.authemployeeservice.jwt.grpc.gen.AuthenticateEmployeeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceGrpcClientImpl {

    private final AuthenticateEmployeeGrpc.AuthenticateEmployeeBlockingStub authenticateEmployeeBlockingStub;

    @Autowired
    public EmployeeServiceGrpcClientImpl(AuthenticateEmployeeGrpc.AuthenticateEmployeeBlockingStub authenticateEmployeeBlockingStub) {
        this.authenticateEmployeeBlockingStub = authenticateEmployeeBlockingStub;
    }

    public String getPassword(String username) {

        AuthenticateEmployeeReply reply = authenticateEmployeeBlockingStub.authenticate(AuthenticateEmployeeRequest.newBuilder().setUsername(username).build());
        return reply.getPassword();
    }

}