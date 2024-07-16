package com.vang.publisherservice.grpc.grpc;

import com.vang.publisherservice.data.PublisherRepository;
import com.vang.publisherservice.grpc.gen.UpdateCountPublisherGrpc;
import com.vang.publisherservice.grpc.gen.UpdateCountPublisherReply;
import com.vang.publisherservice.grpc.gen.UpdateCountPublisherRequest;
import io.grpc.stub.StreamObserver;
import jakarta.transaction.Transactional;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

@GrpcService
public class UpdateCountPublisherServerImpl extends UpdateCountPublisherGrpc.UpdateCountPublisherImplBase {

    private final PublisherRepository publisherRepository;

    @Autowired
    public UpdateCountPublisherServerImpl(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    @Transactional
    @Override
    public void updateCount(UpdateCountPublisherRequest request, StreamObserver<UpdateCountPublisherReply> responseObserver) {

        int rowUpdated = publisherRepository.updateByPublisherId(request.getPublisherId());
        UpdateCountPublisherReply reply;
        if(rowUpdated > 0) {

            reply = UpdateCountPublisherReply.newBuilder().setStatus(true).build();
        } else {

            reply = UpdateCountPublisherReply.newBuilder().setStatus(false).build();
        }
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }

}