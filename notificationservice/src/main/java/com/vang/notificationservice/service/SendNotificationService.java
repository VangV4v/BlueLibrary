package com.vang.notificationservice.service;

import com.vang.notificationservice.model.SendNotificationModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class SendNotificationService {

    private final MailService mailService;

    @Autowired
    public SendNotificationService(MailService mailService) {
        this.mailService = mailService;
    }

    @KafkaListener(groupId = "sendNotification", topics = "sendNotification")
    public void sendNotification(SendNotificationModel model) {

        System.out.println(model.toString());
        mailService.sendEmail(model.getEmail());
    }
}