package com.vang.borrowservice.grpc.grpc;

import com.vang.borrowservice.grpc.gen.GetUserByUsernameGrpc;
import com.vang.borrowservice.grpc.gen.GetUserByUsernameReply;
import com.vang.borrowservice.grpc.gen.GetUserByUsernameRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetUserByUsernameClientGrpcImpl {

    private final GetUserByUsernameGrpc.GetUserByUsernameBlockingStub getUserByUsernameBlockingStub;

    @Autowired
    public GetUserByUsernameClientGrpcImpl(GetUserByUsernameGrpc.GetUserByUsernameBlockingStub getUserByUsernameBlockingStub) {
        this.getUserByUsernameBlockingStub = getUserByUsernameBlockingStub;
    }

    public String getUserByUsername(String username) {

        GetUserByUsernameReply reply = getUserByUsernameBlockingStub.getUserData(GetUserByUsernameRequest.newBuilder().setUsername(username).build());
        if(reply.getStatus()) {

            return reply.getResponseJson();
        }
        return null;
    }

}