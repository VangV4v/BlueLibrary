package com.vang.imageservice.grpc;

import com.vang.imageservice.grpc.gen.DeleteImageGrpc;
import com.vang.imageservice.grpc.gen.DeleteImageReply;
import com.vang.imageservice.grpc.gen.DeleteImageRequest;
import com.vang.imageservice.service.DeleteImageService;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

@GrpcService
public class DeleteImageGrpcServerImpl extends DeleteImageGrpc.DeleteImageImplBase {

    private final DeleteImageService deleteImageService;

    @Autowired
    public DeleteImageGrpcServerImpl(DeleteImageService deleteImageService) {
        this.deleteImageService = deleteImageService;
    }

    @Override
    public void delete(DeleteImageRequest request, StreamObserver<DeleteImageReply> responseObserver) {

        deleteImageService.deleteImage(request.getImageName());
        DeleteImageReply reply = DeleteImageReply.newBuilder().setStatus(true).build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }
}