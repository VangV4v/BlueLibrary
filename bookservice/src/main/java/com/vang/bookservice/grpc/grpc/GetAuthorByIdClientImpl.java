package com.vang.bookservice.grpc.grpc;


import com.vang.bookservice.grpc.gen.GetAuthorByIdGrpc;
import com.vang.bookservice.grpc.gen.GetAuthorByIdReply;
import com.vang.bookservice.grpc.gen.GetAuthorByIdRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetAuthorByIdClientImpl {

    private final GetAuthorByIdGrpc.GetAuthorByIdBlockingStub getAuthorByIdBlockingStub;

    @Autowired
    public GetAuthorByIdClientImpl(GetAuthorByIdGrpc.GetAuthorByIdBlockingStub getAuthorByIdBlockingStub) {
        this.getAuthorByIdBlockingStub = getAuthorByIdBlockingStub;
    }

    public String getAuthorById(String id) {

        GetAuthorByIdReply reply = getAuthorByIdBlockingStub.getData(GetAuthorByIdRequest.newBuilder().setAuthorId(id).build());
        if(reply.getStatus()) {

            return reply.getResponseJson();
        }
        return null;
    }

}