package com.vang.bookservice.grpc.grpc;

import com.google.protobuf.ByteString;
import com.vang.bookservice.grpc.gen.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageClientImpl {

    private final UploadImageGrpc.UploadImageBlockingStub uploadImageBlockingStub;
    private final DeleteImageGrpc.DeleteImageBlockingStub deleteImageBlockingStub;

    @Autowired
    public ImageClientImpl(UploadImageGrpc.UploadImageBlockingStub uploadImageBlockingStub, DeleteImageGrpc.DeleteImageBlockingStub deleteImageBlockingStub) {
        this.uploadImageBlockingStub = uploadImageBlockingStub;
        this.deleteImageBlockingStub = deleteImageBlockingStub;
    }

    public String uploadImage(byte[] image) {

        ByteString bytes = ByteString.copyFrom(image);
        UploadImageReply reply = uploadImageBlockingStub.upload(UploadImageRequest.newBuilder().setType(2).setImage(bytes).build());
        return reply.getUrl();
    }

    public void deleteImage(String image) {

        int first = image.lastIndexOf("/");
        String afterFirst = image.substring(0 ,first);
        int last = afterFirst.lastIndexOf("/");
        String imageKey = image.substring(last+1);
        DeleteImageReply reply = deleteImageBlockingStub.delete(DeleteImageRequest.newBuilder().setImageName(imageKey).build());
    }
}