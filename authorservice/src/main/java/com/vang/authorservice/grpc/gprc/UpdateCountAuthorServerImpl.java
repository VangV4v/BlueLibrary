package com.vang.authorservice.grpc.gprc;

import com.vang.authorservice.UpdateCountAuthorGrpc;
import com.vang.authorservice.UpdateCountAuthorReply;
import com.vang.authorservice.UpdateCountAuthorRequest;
import com.vang.authorservice.data.AuthorRepository;
import io.grpc.stub.StreamObserver;
import jakarta.transaction.Transactional;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

@GrpcService
public class UpdateCountAuthorServerImpl extends UpdateCountAuthorGrpc.UpdateCountAuthorImplBase {

    private final AuthorRepository authorRepository;

    @Autowired
    public UpdateCountAuthorServerImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Transactional
    @Override
    public void updateCount(UpdateCountAuthorRequest request, StreamObserver<UpdateCountAuthorReply> responseObserver) {

        UpdateCountAuthorReply reply;
        int statusResponse = 0;
        if(request.getTypeUpdate() == 1) {

            statusResponse = authorRepository.updateIncrementByAuthorId(request.getAuthorId());
        } else if(request.getTypeUpdate() == 2) {

            statusResponse = authorRepository.updateDecrementByAuthorId(request.getAuthorId());
        }
        if(statusResponse > 0) {

            reply = UpdateCountAuthorReply.newBuilder().setStatus(true).build();
        } else {

            reply = UpdateCountAuthorReply.newBuilder().setStatus(false).build();
        }
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }

}