package com.vang.userservice.grpc.grpc;

import com.google.gson.Gson;
import com.vang.userservice.data.UserRepository;
import com.vang.userservice.data.Users;
import com.vang.userservice.grpc.gen.GetUserByUsernameGrpc;
import com.vang.userservice.grpc.gen.GetUserByUsernameReply;
import com.vang.userservice.grpc.gen.GetUserByUsernameRequest;
import com.vang.userservice.grpc.grpcmodel.UserJsonModel;
import com.vang.userservice.query.model.UserResponseModel;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;

@GrpcService
public class GetUserByUsernameGrpcServerImpl extends GetUserByUsernameGrpc.GetUserByUsernameImplBase {

    private final UserRepository userRepository;

    @Autowired
    public GetUserByUsernameGrpcServerImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void getUserData(GetUserByUsernameRequest request, StreamObserver<GetUserByUsernameReply> responseObserver) {

        Gson gson = new Gson();
        Users users = userRepository.findByUsername(request.getUsername());
        UserJsonModel userJsonModel = new UserJsonModel();
        GetUserByUsernameReply reply;

        if(ObjectUtils.isEmpty(users)) {

            reply = GetUserByUsernameReply.newBuilder().setStatus(false).build();
        } else {

            BeanUtils.copyProperties(users, userJsonModel);
            String convertToJson = gson.toJson(userJsonModel);
            reply = GetUserByUsernameReply.newBuilder().setStatus(true).setResponseJson(convertToJson).build();
        }
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }

}