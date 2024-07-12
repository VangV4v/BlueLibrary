package com.vang.adminservice.grpc.grpc;

import com.vang.adminservice.data.AdminRepository;
import com.vang.adminservice.grpc.gen.AuthenticateAdminGrpc;
import com.vang.adminservice.grpc.gen.AuthenticateAdminReply;
import com.vang.adminservice.grpc.gen.AuthenticateAdminRequest;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

@GrpcService
public class AuthenticateAdminGrpcServerImpl extends AuthenticateAdminGrpc.AuthenticateAdminImplBase {

    private final AdminRepository adminRepository;

    @Autowired
    public AuthenticateAdminGrpcServerImpl(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public void login(AuthenticateAdminRequest request, StreamObserver<AuthenticateAdminReply> responseObserver) {

        String password = adminRepository.getPasswordByUsername(request.getUsername());
        AuthenticateAdminReply reply;
        if(StringUtils.isEmpty(password)) {

            reply = AuthenticateAdminReply.newBuilder().setStatus(false).build();
        } else {

            reply = AuthenticateAdminReply.newBuilder().setStatus(true).setPassword(password).build();
        }
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }

}