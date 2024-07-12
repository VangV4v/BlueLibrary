package com.vang.adminservice.command.event;

import com.vang.adminservice.command.model.SendNotificationModel;
import com.vang.adminservice.common.ServiceCommon;
import com.vang.adminservice.data.AdminRepository;
import com.vang.adminservice.data.Admins;
import org.apache.commons.lang.StringUtils;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class AdminEventsHandler {

    private final AdminRepository adminRepository;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    public AdminEventsHandler(AdminRepository adminRepository, KafkaTemplate<String, Object> kafkaTemplate) {
        this.adminRepository = adminRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    @EventHandler
    public void handle(AdminCreatedEvent event) {

        event.setAdminId(generateId());
        Admins admins = new Admins();
        BeanUtils.copyProperties(event, admins);
        adminRepository.save(admins);
        SendNotificationModel notificationModel = new SendNotificationModel();
        notificationModel.setEmail(event.getEmail());
        kafkaTemplate.send("sendNotification", notificationModel);
    }

    @EventHandler
    public void handle(AdminUpdatedEvent event) {

        Admins admins = new Admins();
        BeanUtils.copyProperties(event, admins);
        adminRepository.save(admins);
    }

    @EventHandler
    public void handle(AdminDeletedEvent event) {

        adminRepository.deleteById(event.getAdminId());
    }

    private String generateId() {

        String latestId = adminRepository.getLatestId();
        int id = 0;
        if(StringUtils.isEmpty(latestId)) {

            return "ADMIN0001";
        }
        id = Integer.parseInt(latestId.substring(ServiceCommon.getIndexById(latestId)));
        if(id > 0 && id < 9) {

            return "ADMIN000"+(id + 1);
        } else if(id > 9 && id < 99) {

            return "ADMIN00"+(id + 1);
        } else if(id > 99 && id < 999) {

            return "ADMIN0"+(id + 1);
        } else {

            return "ADMIN"+(id + 1);
        }
    }

}