package com.vang.typeservice.grpc.grpc;

import com.vang.typeservice.data.TypeRepository;
import com.vang.typeservice.grpc.gen.UpdateCountTypeGrpc;
import com.vang.typeservice.grpc.gen.UpdateCountTypeReply;
import com.vang.typeservice.grpc.gen.UpdateCountTypeRequest;
import io.grpc.stub.StreamObserver;
import jakarta.transaction.Transactional;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

@GrpcService
public class UpdateCountTypeServerImpl extends UpdateCountTypeGrpc.UpdateCountTypeImplBase {

    private final TypeRepository typeRepository;

    @Autowired
    public UpdateCountTypeServerImpl(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }

    @Transactional
    @Override
    public void updateCount(UpdateCountTypeRequest request, StreamObserver<UpdateCountTypeReply> responseObserver) {

        UpdateCountTypeReply reply;
        int statusResponse = 0;
        if(request.getTypeUpdate() == 1) {

            statusResponse = typeRepository.updateIncrementByTypeId(request.getTypeId());
        } else if(request.getTypeUpdate() == 2) {

            statusResponse = typeRepository.updateDecrementByTypeId(request.getTypeId());
        }
        if(statusResponse > 0) {

            reply = UpdateCountTypeReply.newBuilder().setStatus(true).build();
        } else {

            reply = UpdateCountTypeReply.newBuilder().setStatus(false).build();
        }
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }

}