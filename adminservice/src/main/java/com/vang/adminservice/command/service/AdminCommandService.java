package com.vang.adminservice.command.service;

import com.vang.adminservice.command.model.AdminRequestModel;
import com.vang.adminservice.command.model.UpdateAdminRequestModel;
import com.vang.adminservice.common.ResponseCommon;
import org.springframework.http.ResponseEntity;

public interface AdminCommandService {

    ResponseEntity<ResponseCommon> addAdmin(AdminRequestModel requestModel);

    ResponseEntity<ResponseCommon> updateAdmin(UpdateAdminRequestModel requestModel);

    ResponseEntity<ResponseCommon> deleteAdmin(AdminRequestModel requestModel);
}