package com.vang.publisherservice.query.projection;

import com.vang.publisherservice.data.PublisherRepository;
import com.vang.publisherservice.data.Publishers;
import com.vang.publisherservice.query.model.PublisherResponseModel;
import com.vang.publisherservice.query.queries.GetAllPublishers;
import com.vang.publisherservice.query.queries.GetByPublisherId;
import org.apache.commons.lang.StringUtils;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PublisherQueryProjection {

    private final PublisherRepository publisherRepository;

    @Autowired
    public PublisherQueryProjection(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    @QueryHandler
    public PublisherResponseModel getPublisherById(GetByPublisherId publisherId) {

        Publishers publishers = publisherRepository.findById(publisherId.getPublisherId()).orElse(new Publishers());
        PublisherResponseModel responseModel = new PublisherResponseModel();
        if(StringUtils.isEmpty(publishers.getPublisherId())) {
            responseModel.initialize();
        }else {
            BeanUtils.copyProperties(publishers, responseModel);
        }
        return responseModel;
    }

    @QueryHandler
    public List<PublisherResponseModel> getAllPublishers(GetAllPublishers allPublishers) {

        List<Publishers> publishers = publisherRepository.findAll();
        List<PublisherResponseModel> responseModels = new ArrayList<>();
        publishers.forEach(e -> {
            PublisherResponseModel model = new PublisherResponseModel();
            BeanUtils.copyProperties(e, model);
            responseModels.add(model);
        });
        return responseModels;
    }

}