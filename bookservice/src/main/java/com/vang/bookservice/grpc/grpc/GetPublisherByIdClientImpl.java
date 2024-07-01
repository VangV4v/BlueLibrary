package com.vang.bookservice.grpc.grpc;

import com.vang.bookservice.grpc.gen.GetPublisherByIdGrpc;
import com.vang.bookservice.grpc.gen.GetPublisherByIdReply;
import com.vang.bookservice.grpc.gen.GetPublisherByIdRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetPublisherByIdClientImpl {

    private final GetPublisherByIdGrpc.GetPublisherByIdBlockingStub getPublisherByIdBlockingStub;

    @Autowired
    public GetPublisherByIdClientImpl(GetPublisherByIdGrpc.GetPublisherByIdBlockingStub getPublisherByIdBlockingStub) {
        this.getPublisherByIdBlockingStub = getPublisherByIdBlockingStub;
    }

    public String getPublisherById(String id) {

        GetPublisherByIdReply reply = getPublisherByIdBlockingStub.getData(GetPublisherByIdRequest.newBuilder().setPublisherId(id).build());
        if(reply.getStatus()) {

            return reply.getResponseJson();
        }
        return null;
    }

}