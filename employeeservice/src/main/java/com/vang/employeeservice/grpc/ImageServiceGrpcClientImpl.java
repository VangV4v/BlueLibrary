package com.vang.employeeservice.grpc;

import com.google.protobuf.ByteString;
import com.vang.employeeservice.grpc.gen.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ImageServiceGrpcClientImpl {

    private final UploadImageGrpc.UploadImageBlockingStub uploadImageBlockingStub;
    private final DeleteImageGrpc.DeleteImageBlockingStub deleteImageBlockingStub;

    @Autowired
    public ImageServiceGrpcClientImpl(UploadImageGrpc.UploadImageBlockingStub uploadImageBlockingStub, DeleteImageGrpc.DeleteImageBlockingStub deleteImageBlockingStub) {
        this.uploadImageBlockingStub = uploadImageBlockingStub;
        this.deleteImageBlockingStub = deleteImageBlockingStub;
    }

    public String uploadImage(byte[] image, int type) throws IOException {

        ByteString bytes = ByteString.copyFrom(image);
        UploadImageReply reply = uploadImageBlockingStub.upload(UploadImageRequest.newBuilder().setImage(bytes).setType(1).build());
        if(reply.getStatus()) {

            return reply.getUrl();
        } else {

            return "";
        }
    }

    public void deleteImage(String imageUrl) throws IOException {

        int first = imageUrl.lastIndexOf("/");
        String afterFirst = imageUrl.substring(0 ,first);
        int last = afterFirst.lastIndexOf("/");
        String imageKey = imageUrl.substring(last+1);
        deleteImageBlockingStub.delete(DeleteImageRequest.newBuilder().setImageName(imageKey).build());
    }
}