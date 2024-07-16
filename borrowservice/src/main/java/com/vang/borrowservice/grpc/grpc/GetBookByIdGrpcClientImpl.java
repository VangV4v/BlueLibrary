package com.vang.borrowservice.grpc.grpc;

import com.vang.borrowservice.grpc.gen.GetBookByIDGrpc;
import com.vang.borrowservice.grpc.gen.GetBookByIDReply;
import com.vang.borrowservice.grpc.gen.GetBookByIDRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetBookByIdGrpcClientImpl {

    private final GetBookByIDGrpc.GetBookByIDBlockingStub getBookByIDBlockingStub;

    @Autowired
    public GetBookByIdGrpcClientImpl(GetBookByIDGrpc.GetBookByIDBlockingStub getBookByIDBlockingStub) {
        this.getBookByIDBlockingStub = getBookByIDBlockingStub;
    }

    public String getBookByID(String id) {

        GetBookByIDReply reply = getBookByIDBlockingStub.getBookData(GetBookByIDRequest.newBuilder().setBookId(id).build());
        if(reply.getStatus()) {

            return reply.getJsonResponse();
        }
        return null;
    }

}