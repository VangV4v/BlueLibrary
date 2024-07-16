package com.vang.authorservice.grpc.gprc;

import com.vang.authorservice.UpdateCountPublisherGrpc;
import com.vang.authorservice.UpdateCountPublisherReply;
import com.vang.authorservice.UpdateCountPublisherRequest;
import com.vang.authorservice.data.AuthorRepository;
import io.grpc.stub.StreamObserver;
import jakarta.transaction.Transactional;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

@GrpcService
public class UpdateCountAuthorServerImpl extends UpdateCountPublisherGrpc.UpdateCountPublisherImplBase {

    private final AuthorRepository authorRepository;

    @Autowired
    public UpdateCountAuthorServerImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Transactional
    @Override
    public void updateCount(UpdateCountPublisherRequest request, StreamObserver<UpdateCountPublisherReply> responseObserver) {

        int rowUpdated = authorRepository.updateByAuthorId(request.getAuthorId());
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