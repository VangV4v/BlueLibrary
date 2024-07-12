package com.vang.adminservice.query.service;

import com.vang.adminservice.query.model.AdminResponseModel;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AdminQueryService {

    ResponseEntity<AdminResponseModel> getByAdminId(String adminId);

    ResponseEntity<List<AdminResponseModel>> getAllAdmins();
}