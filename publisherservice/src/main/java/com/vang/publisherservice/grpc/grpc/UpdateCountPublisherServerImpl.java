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

        UpdateCountPublisherReply reply;
        int statusResponse = 0;
        if(request.getTypeUpdate() == 1) {

            statusResponse = publisherRepository.updateIncrementByPublisherId(request.getPublisherId());
        } else if(request.getTypeUpdate() == 2) {

            statusResponse = publisherRepository.updateDecrementByPublisherId(request.getPublisherId());
        }
        if(statusResponse > 0) {

            reply = UpdateCountPublisherReply.newBuilder().setStatus(true).build();
        } else {

            reply = UpdateCountPublisherReply.newBuilder().setStatus(false).build();
        }
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }

}