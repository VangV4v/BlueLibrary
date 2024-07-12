package com.vang.adminservice.query.service.impl;

import com.vang.adminservice.query.model.AdminResponseModel;
import com.vang.adminservice.query.queries.GetAllAdmins;
import com.vang.adminservice.query.queries.GetByAdminId;
import com.vang.adminservice.query.service.AdminQueryService;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AdminQueryServiceImpl implements AdminQueryService {

    private final QueryGateway queryGateway;

    @Autowired
    public AdminQueryServiceImpl(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @Override
    public ResponseEntity<AdminResponseModel> getByAdminId(String adminId) {

        GetByAdminId byAdminId = new GetByAdminId(adminId);
        AdminResponseModel responseModel = queryGateway.query(byAdminId, ResponseTypes.instanceOf(AdminResponseModel.class)).join();
        return new ResponseEntity<>(responseModel, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<AdminResponseModel>> getAllAdmins() {

        GetAllAdmins allAdmins = new GetAllAdmins();
        List<AdminResponseModel> responseModel = queryGateway.query(allAdmins, ResponseTypes.multipleInstancesOf(AdminResponseModel.class)).join();
        return new ResponseEntity<>(responseModel, HttpStatus.OK);
    }

}