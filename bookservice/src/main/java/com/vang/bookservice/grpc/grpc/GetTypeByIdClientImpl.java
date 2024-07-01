package com.vang.bookservice.grpc.grpc;

import com.vang.bookservice.grpc.gen.GetTypeByIdGrpc;
import com.vang.bookservice.grpc.gen.GetTypeByIdReply;
import com.vang.bookservice.grpc.gen.GetTypeByIdRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetTypeByIdClientImpl {

    private final GetTypeByIdGrpc.GetTypeByIdBlockingStub getTypeByIdBlockingStub;

    @Autowired
    public GetTypeByIdClientImpl(GetTypeByIdGrpc.GetTypeByIdBlockingStub getTypeByIdBlockingStub) {
        this.getTypeByIdBlockingStub = getTypeByIdBlockingStub;
    }

    public String getTypeById(String id) {

        GetTypeByIdReply reply = getTypeByIdBlockingStub.getData(GetTypeByIdRequest.newBuilder().setTypeId(id).build());
        if(reply.getStatus()) {

            return reply.getResponseJson();
        }
        return null;
    }

}