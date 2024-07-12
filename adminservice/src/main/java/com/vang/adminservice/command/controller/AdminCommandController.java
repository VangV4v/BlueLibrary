package com.vang.adminservice.command.controller;

import com.vang.adminservice.command.model.AdminRequestModel;
import com.vang.adminservice.command.model.UpdateAdminRequestModel;
import com.vang.adminservice.command.service.AdminCommandService;
import com.vang.adminservice.common.ResponseCommon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admins/")
public class AdminCommandController {

    private final AdminCommandService adminCommandService;

    @Autowired
    public AdminCommandController(AdminCommandService adminCommandService) {
        this.adminCommandService = adminCommandService;
    }

    @PostMapping
    public ResponseEntity<ResponseCommon> addAdmin(@RequestBody AdminRequestModel requestModel) {

        return adminCommandService.addAdmin(requestModel);
    }

    @PutMapping
    public ResponseEntity<ResponseCommon> updateAdmin(@RequestBody UpdateAdminRequestModel requestModel) {

        return adminCommandService.updateAdmin(requestModel);
    }

    @DeleteMapping
    public ResponseEntity<ResponseCommon> deleteAdmin(@RequestBody AdminRequestModel requestModel) {

        return adminCommandService.deleteAdmin(requestModel);
    }

}