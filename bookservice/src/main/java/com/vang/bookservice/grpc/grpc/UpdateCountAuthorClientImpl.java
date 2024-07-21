package com.vang.bookservice.grpc.grpc;

import com.vang.bookservice.grpc.gen.UpdateCountAuthorGrpc;
import com.vang.bookservice.grpc.gen.UpdateCountAuthorReply;
import com.vang.bookservice.grpc.gen.UpdateCountAuthorRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateCountAuthorClientImpl {

    private final UpdateCountAuthorGrpc.UpdateCountAuthorBlockingStub updateCountAuthorBlockingStub;

    @Autowired
    public UpdateCountAuthorClientImpl(UpdateCountAuthorGrpc.UpdateCountAuthorBlockingStub updateCountAuthorBlockingStub) {
        this.updateCountAuthorBlockingStub = updateCountAuthorBlockingStub;
    }

    public boolean updateCountAuthor(String authorId, int typeUpdate) {

        UpdateCountAuthorReply reply = updateCountAuthorBlockingStub.updateCount(UpdateCountAuthorRequest.newBuilder().setAuthorId(authorId).setTypeUpdate(typeUpdate).build());
        return reply.getStatus();
    }

}