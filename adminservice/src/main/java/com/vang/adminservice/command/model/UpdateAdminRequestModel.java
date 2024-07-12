package com.vang.adminservice.command.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
public class UpdateAdminRequestModel extends AdminRequestModel {

    private String hdnEmail;
    private String hdnPhone;
}