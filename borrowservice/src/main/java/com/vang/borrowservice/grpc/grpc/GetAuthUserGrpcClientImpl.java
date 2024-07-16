package com.vang.borrowservice.grpc.grpc;

import com.vang.borrowservice.grpc.gen.GetAuthUserGrpc;
import com.vang.borrowservice.grpc.gen.GetAuthUserReply;
import com.vang.borrowservice.grpc.gen.GetAuthUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetAuthUserGrpcClientImpl {

    private final GetAuthUserGrpc.GetAuthUserBlockingStub getAuthUserBlockingStub;

    @Autowired
    public GetAuthUserGrpcClientImpl(GetAuthUserGrpc.GetAuthUserBlockingStub getAuthUserBlockingStub) {
        this.getAuthUserBlockingStub = getAuthUserBlockingStub;
    }

    public String getAuthInfo() {

        GetAuthUserReply reply = getAuthUserBlockingStub.getAutInfo(GetAuthUserRequest.newBuilder().setNumber(0).build());
        if(reply.getStatus()) {

            return reply.getUsername();
        }
        return null;
    }

}