package com.vang.imageservice.service;

import com.amazonaws.services.s3.AmazonS3;
import com.vang.imageservice.common.ServiceCommon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteImageService {

    private final AmazonS3 amazonS3;

    @Autowired
    public DeleteImageService(AmazonS3 amazonS3) {
        this.amazonS3 = amazonS3;
    }

    public void deleteImage(String imageName) {

        amazonS3.deleteObject(ServiceCommon.BUDGET_NAME, imageName);
    }
}