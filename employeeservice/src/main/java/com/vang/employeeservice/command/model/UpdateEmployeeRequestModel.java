package com.vang.employeeservice.command.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

@Setter
@Getter
public class UpdateEmployeeRequestModel extends EmployeeRequestModel implements Serializable {

    private String hdnUsername;
    private String hdnEmail;
    private String hdnPhone;
    private MultipartFile image;
}