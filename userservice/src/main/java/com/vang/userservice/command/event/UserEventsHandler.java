package com.vang.userservice.command.event;

import com.vang.userservice.command.model.SendNotificationModel;
import com.vang.userservice.common.ServiceCommon;
import com.vang.userservice.data.UserRepository;
import com.vang.userservice.data.Users;
import com.vang.userservice.grpc.grpc.ImageGrpcClientImpl;
import org.apache.commons.lang.StringUtils;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

@Component
public class UserEventsHandler {

    private final UserRepository userRepository;
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final ImageGrpcClientImpl imageGrpcClient;

    @Autowired
    public UserEventsHandler(UserRepository userRepository, KafkaTemplate<String, Object> kafkaTemplate, ImageGrpcClientImpl imageGrpcClient) {
        this.userRepository = userRepository;
        this.kafkaTemplate = kafkaTemplate;
        this.imageGrpcClient = imageGrpcClient;
    }

    @EventHandler
    public void handle(UserCreatedEvent event) {

        event.setUserId(generateId());
        SendNotificationModel model = new SendNotificationModel();
        model.setEmail(event.getEmail());
        Users users = new Users();
        BeanUtils.copyProperties(event, users);
        userRepository.save(users);
        kafkaTemplate.send("sendNotification", model);
    }

    @EventHandler
    public void handle(UserUpdatedEvent event) {

        Users users = new Users();
        BeanUtils.copyProperties(event, users);
        userRepository.save(users);
        if(!ObjectUtils.isEmpty(event.getAvatar())) {

            String urlImage = imageGrpcClient.uploadImage(event.getImageData());
            users.setAvatar(urlImage);
            userRepository.save(users);
            if(!event.getAvatar().equals(ServiceCommon.DEFAULT_IMAGE)) {

                imageGrpcClient.deleteImage(event.getAvatar());
            }
        }
    }

    @EventHandler
    public void handle(UserDeletedEvent event) {

        userRepository.deleteById(event.getUserId());
        if(!event.getAvatar().equals(ServiceCommon.DEFAULT_IMAGE)) {

            imageGrpcClient.deleteImage(event.getAvatar());
        }
    }

    private String generateId() {

        String latestId = userRepository.getLatestId();
        int id = 0;
        if(StringUtils.isEmpty(latestId)) {

            return "USER0001";
        }
        id = Integer.parseInt(latestId.substring(ServiceCommon.getIndexById(latestId)));
        if(id > 0 && id < 9) {
            return "USER000"+(id + 1);
        } else if(id > 9 && id < 99) {
            return "USER00"+(id + 1);
        } else if(id > 99 && id < 999) {
            return "USER0"+(id + 1);
        } else {
            return "USER"+(id + 1);
        }
    }

}