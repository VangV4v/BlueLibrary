package com.vang.userservice.grpc.grpc;

import com.vang.userservice.data.UserRepository;
import com.vang.userservice.grpc.gen.AuthenticateUserGrpc;
import com.vang.userservice.grpc.gen.AuthenticateUserReply;
import com.vang.userservice.grpc.gen.AuthenticateUserRequest;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

@GrpcService
public class AuthenticateGrpcServerImpl extends AuthenticateUserGrpc.AuthenticateUserImplBase {

    private final UserRepository userRepository;

    @Autowired
    public AuthenticateGrpcServerImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void login(AuthenticateUserRequest request, StreamObserver<AuthenticateUserReply> responseObserver) {

        String password = userRepository.getPasswordByUsername(request.getUsername());
        AuthenticateUserReply reply;
        if(StringUtils.isEmpty(password)) {

            reply = AuthenticateUserReply.newBuilder().setStatus(false).build();
        } else {

            reply = AuthenticateUserReply.newBuilder().setStatus(true).setPassword(password).build();
        }
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }

}