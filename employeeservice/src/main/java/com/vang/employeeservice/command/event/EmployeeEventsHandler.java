package com.vang.employeeservice.command.event;

import com.vang.employeeservice.command.model.SendNotificationModel;
import com.vang.employeeservice.common.ServiceCommon;
import com.vang.employeeservice.data.EmployeeRepository;
import com.vang.employeeservice.data.Employees;
import com.vang.employeeservice.grpc.ImageServiceGrpcClientImpl;
import org.apache.commons.lang.StringUtils;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.io.IOException;

@Component
public class EmployeeEventsHandler {

    private final EmployeeRepository employeeRepository;
    private final ImageServiceGrpcClientImpl imageServiceGrpcClient;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    public EmployeeEventsHandler(EmployeeRepository employeeRepository, ImageServiceGrpcClientImpl imageServiceGrpcClient, KafkaTemplate<String, Object> kafkaTemplate) {
        this.employeeRepository = employeeRepository;
        this.imageServiceGrpcClient = imageServiceGrpcClient;
        this.kafkaTemplate = kafkaTemplate;
    }

    @EventHandler
    public void handle(EmployeeCreatedEvent event) {

        event.setEmployeeId(generateId());
        SendNotificationModel model = new SendNotificationModel();
        Employees employees = new Employees();
        BeanUtils.copyProperties(event, employees);
        employeeRepository.save(employees);
        model.setEmail(employees.getEmail());
        kafkaTemplate.send("sendNotification", model);

    }

    @EventHandler
    public void handle(EmployeeUpdatedEvent event) {

        Employees employees = new Employees();
        BeanUtils.copyProperties(event, employees);
        employeeRepository.save(employees);
        if(!ObjectUtils.isEmpty(event.getImage())) {

            try {
                String oldImage = event.getAvatar();
                String url = imageServiceGrpcClient.uploadImage(event.getImage(), 1, event.getImageName());
                employees.setAvatar(url);
                employeeRepository.save(employees);
                if(!StringUtils.isEmpty(oldImage) && !oldImage.equals(ServiceCommon.DEFAULT_IMAGE)) {

                    imageServiceGrpcClient.deleteImage(oldImage);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @EventHandler
    public void handle(EmployeeDeletedEvent event) {

        employeeRepository.deleteById(event.getEmployeeId());
        if(!StringUtils.isEmpty(event.getAvatar()) && !event.getAvatar().equals(ServiceCommon.DEFAULT_IMAGE)) {

            try {

                imageServiceGrpcClient.deleteImage(event.getAvatar());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private String generateId() {

        String latestId = employeeRepository.getLatestEmployeeId();
        int id = 0;
        if(StringUtils.isEmpty(latestId)) {

            return "EMPLOYEE0001";
        }
        id = Integer.parseInt(latestId.substring(ServiceCommon.getIndexById(latestId)));
        if(id > 0 && id < 9) {
            return "EMPLOYEE000"+(id + 1);
        } else if(id > 9 && id < 99) {
            return "EMPLOYEE00"+(id + 1);
        } else if(id > 99 && id < 999) {
            return "EMPLOYEE0"+(id + 1);
        } else {
            return "EMPLOYEE"+(id + 1);
        }
    }
}