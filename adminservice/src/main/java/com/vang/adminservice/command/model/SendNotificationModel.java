package com.vang.adminservice.command.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class SendNotificationModel implements Serializable {

    private String fullName;
    private String email;
}