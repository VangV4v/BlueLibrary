package com.vang.adminservice.query.controller;

import com.vang.adminservice.query.model.AdminResponseModel;
import com.vang.adminservice.query.service.AdminQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admins/")
public class AdminQueryController {

    private final AdminQueryService adminQueryService;

    @Autowired
    public AdminQueryController(AdminQueryService adminQueryService) {
        this.adminQueryService = adminQueryService;
    }

    @GetMapping("{id}")
    public ResponseEntity<AdminResponseModel> getAdminById(@PathVariable("id") String id) {

        return adminQueryService.getByAdminId(id);
    }

    @GetMapping
    public ResponseEntity<List<AdminResponseModel>> getAllAdmins() {

        return adminQueryService.getAllAdmins();
    }

}