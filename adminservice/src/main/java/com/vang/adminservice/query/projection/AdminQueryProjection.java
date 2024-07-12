package com.vang.adminservice.query.projection;

import com.vang.adminservice.data.AdminRepository;
import com.vang.adminservice.data.Admins;
import com.vang.adminservice.query.model.AdminResponseModel;
import com.vang.adminservice.query.queries.GetAllAdmins;
import com.vang.adminservice.query.queries.GetByAdminId;
import io.axoniq.axonserver.grpc.admin.Admin;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Component
public class AdminQueryProjection {

    private final AdminRepository adminRepository;

    @Autowired
    public AdminQueryProjection(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @QueryHandler
    public AdminResponseModel getByAdminId(GetByAdminId byAdminId) {

        Admins admins = adminRepository.findById(byAdminId.getAdminId()).orElse(null);
        AdminResponseModel responseModel = new AdminResponseModel();
        if(ObjectUtils.isEmpty(admins)) {

            responseModel.initialize();
        } else {

            BeanUtils.copyProperties(admins, responseModel);
        }
        return responseModel;
    }

    @QueryHandler
    public List<AdminResponseModel> getAllAdmins(GetAllAdmins allAdmins) {

        List<Admins> admins = adminRepository.findAll();
        List<AdminResponseModel> responseModels = new ArrayList<>();
        admins.forEach(e -> {
            AdminResponseModel adminResponseModel = new AdminResponseModel();
            BeanUtils.copyProperties(e, adminResponseModel);
            responseModels.add(adminResponseModel);
        });
        return responseModels;
    }

}