package com.vang.typeservice.query.service.impl;

import com.vang.typeservice.query.model.TypeResponseModel;
import com.vang.typeservice.query.queries.GetAllTypes;
import com.vang.typeservice.query.queries.GetByTypeId;
import com.vang.typeservice.query.service.TypeQueryService;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeQueryServiceImpl implements TypeQueryService {

    private final QueryGateway queryGateway;

    @Autowired
    public TypeQueryServiceImpl(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @Override
    public ResponseEntity<TypeResponseModel> getByTypeId(String typeId) {

        GetByTypeId getByTypeId = new GetByTypeId();
        getByTypeId.setTypeId(typeId);
        TypeResponseModel responseModel = queryGateway.query(getByTypeId, ResponseTypes.instanceOf(TypeResponseModel.class)).join();
        return new ResponseEntity<>(responseModel, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<TypeResponseModel>> getAllTypes() {

        GetAllTypes allTypes = new GetAllTypes();
        List<TypeResponseModel> responseModelList = queryGateway.query(allTypes, ResponseTypes.multipleInstancesOf(TypeResponseModel.class)).join();
        return new ResponseEntity<>(responseModelList, HttpStatus.OK);
    }
}