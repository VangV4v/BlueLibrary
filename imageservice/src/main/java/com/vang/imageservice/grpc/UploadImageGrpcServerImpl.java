package com.vang.imageservice.grpc;

import com.vang.imageservice.grpc.gen.UploadImageGrpc;
import com.vang.imageservice.grpc.gen.UploadImageReply;
import com.vang.imageservice.grpc.gen.UploadImageRequest;
import com.vang.imageservice.service.UploadImageService;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

@GrpcService
public class UploadImageGrpcServerImpl extends UploadImageGrpc.UploadImageImplBase {

    private final UploadImageService uploadImageService;

    @Autowired
    public UploadImageGrpcServerImpl(UploadImageService uploadImageService) {
        this.uploadImageService = uploadImageService;
    }

    @Override
    public void upload(UploadImageRequest request, StreamObserver<UploadImageReply> responseObserver) {

        UploadImageReply reply;
        String url = uploadImageService.uploadImage(request);
        if(StringUtils.isEmpty(url)) {

            reply = UploadImageReply.newBuilder().setStatus(false).build();
        } else {

            reply = UploadImageReply.newBuilder().setStatus(true).setUrl(url).build();
        }
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }

}