package com.vang.userservice.command.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
public class UpdateUserRequestModel extends UserRequestModel {

    private String hdnOldEmail;
    private String hdnOldPhone;
    private MultipartFile image;
}