package com.vang.bookservice.grpc.grpc;

import com.vang.bookservice.grpc.gen.UpdateCountPublisherGrpc;
import com.vang.bookservice.grpc.gen.UpdateCountPublisherReply;
import com.vang.bookservice.grpc.gen.UpdateCountPublisherRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateCountPublisherClientImpl {

    private final UpdateCountPublisherGrpc.UpdateCountPublisherBlockingStub updateCountPublisherBlockingStub;

    @Autowired
    public UpdateCountPublisherClientImpl(UpdateCountPublisherGrpc.UpdateCountPublisherBlockingStub updateCountPublisherBlockingStub) {
        this.updateCountPublisherBlockingStub = updateCountPublisherBlockingStub;
    }

    public boolean updateCountPublisher(String publisherId, int typeUpdate) {

        UpdateCountPublisherReply reply = updateCountPublisherBlockingStub.updateCount(UpdateCountPublisherRequest.newBuilder().setPublisherId(publisherId).setTypeUpdate(typeUpdate).build());
        return reply.getStatus();
    }
}