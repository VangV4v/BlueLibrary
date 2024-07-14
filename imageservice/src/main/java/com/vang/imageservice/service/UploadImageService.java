package com.vang.imageservice.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.vang.imageservice.common.ServiceCommon;
import com.vang.imageservice.grpc.gen.UploadImageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

@Service
public class UploadImageService {

    private final AmazonS3 amazonS3;

    @Autowired
    public UploadImageService(AmazonS3 amazonS3) {
        this.amazonS3 = amazonS3;
    }

    public String uploadImage(UploadImageRequest request) {

        String url;
        String imageName = getBaseUrl(request.getType())+ "/" +System.currentTimeMillis()+request.getName().replace(" ", "_");
        InputStream inputStream = new ByteArrayInputStream(request.getImage().toByteArray());
        PutObjectRequest objectRequest = new PutObjectRequest(ServiceCommon.BUDGET_NAME, imageName, inputStream, null).withCannedAcl(CannedAccessControlList.PublicRead);
        amazonS3.putObject(objectRequest);
            url = ServiceCommon.PRE_URL_IMAGE+imageName;
        return url;
    }

    private String getBaseUrl(int type) {

        if(type == 1) {
            return "employees";
        } else if(type == 2) {
            return "books";
        } else if(type == 3) {
            return "users";
        }
        return "";
    }
}