package com.vang.userservice.grpc.grpc;

import com.google.protobuf.ByteString;
import com.vang.userservice.grpc.gen.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageGrpcClientImpl {

    private final UploadImageGrpc.UploadImageBlockingStub uploadImageBlockingStub;
    private final DeleteImageGrpc.DeleteImageBlockingStub deleteImageBlockingStub;

    @Autowired
    public ImageGrpcClientImpl(UploadImageGrpc.UploadImageBlockingStub uploadImageBlockingStub, DeleteImageGrpc.DeleteImageBlockingStub deleteImageBlockingStub) {
        this.uploadImageBlockingStub = uploadImageBlockingStub;
        this.deleteImageBlockingStub = deleteImageBlockingStub;
    }

    public String uploadImage(byte[] image) {

        ByteString bytes = ByteString.copyFrom(image);
        UploadImageReply reply = uploadImageBlockingStub.upload(UploadImageRequest.newBuilder().setType(3).setImage(bytes).build());
        return reply.getUrl();
    }

    public void deleteImage(String imageUrl) {

        int first = imageUrl.lastIndexOf("/");
        String afterFirst = imageUrl.substring(0 ,first);
        int last = afterFirst.lastIndexOf("/");
        String imageKey = imageUrl.substring(last+1);
        deleteImageBlockingStub.delete(DeleteImageRequest.newBuilder().setImageName(imageKey).build());
    }

}