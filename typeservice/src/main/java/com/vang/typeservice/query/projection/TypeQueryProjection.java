package com.vang.typeservice.query.projection;

import com.vang.typeservice.data.TypeRepository;
import com.vang.typeservice.data.Types;
import com.vang.typeservice.query.model.TypeResponseModel;
import com.vang.typeservice.query.queries.GetAllTypes;
import com.vang.typeservice.query.queries.GetByTypeId;
import org.apache.commons.lang.StringUtils;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TypeQueryProjection {

    private final TypeRepository typeRepository;

    @Autowired
    public TypeQueryProjection(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }

    @QueryHandler
    public List<TypeResponseModel> getAllTypes(GetAllTypes allTypes) {

        List<TypeResponseModel> listModels = new ArrayList<>();
        List<Types> listTypes = typeRepository.findAll();
        listTypes.forEach(e -> {
            TypeResponseModel model = new TypeResponseModel();
            BeanUtils.copyProperties(e, model);
            listModels.add(model);
        });
        return listModels;
    }

    @QueryHandler
    public TypeResponseModel getByTypeId(GetByTypeId typeId) {

        Types types = typeRepository.findById(typeId.getTypeId()).get();
        TypeResponseModel typeResponseModel = new TypeResponseModel();
        if(StringUtils.isEmpty(types.getTypeId())) {
            typeResponseModel.initialize();
        } else {
            BeanUtils.copyProperties(types, typeResponseModel);
        }
        return typeResponseModel;
    }
}