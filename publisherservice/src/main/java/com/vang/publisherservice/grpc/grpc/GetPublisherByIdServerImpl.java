package com.vang.publisherservice.grpc.grpc;

import com.google.gson.Gson;
import com.vang.publisherservice.data.PublisherRepository;
import com.vang.publisherservice.data.Publishers;
import com.vang.publisherservice.grpc.gen.GetPublisherByIdGrpc;
import com.vang.publisherservice.grpc.gen.GetPublisherByIdReply;
import com.vang.publisherservice.grpc.gen.GetPublisherByIdRequest;
import com.vang.publisherservice.grpc.grpcmodel.PublisherJsonModel;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;

@GrpcService
public class GetPublisherByIdServerImpl extends GetPublisherByIdGrpc.GetPublisherByIdImplBase {

    private final PublisherRepository publisherRepository;

    @Autowired
    public GetPublisherByIdServerImpl(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void getData(GetPublisherByIdRequest request, StreamObserver<GetPublisherByIdReply> responseObserver) {

        Publishers publishers = publisherRepository.findById(request.getPublisherId()).orElse(null);
        GetPublisherByIdReply reply;
        if(ObjectUtils.isEmpty(publishers)) {

            reply = GetPublisherByIdReply.newBuilder().setStatus(false).build();
        } else {

            Gson gson = new Gson();
            PublisherJsonModel publisherJsonModel = new PublisherJsonModel();
            BeanUtils.copyProperties(publishers, publisherJsonModel);
            String jsonResponse = gson.toJson(publisherJsonModel);
            reply = GetPublisherByIdReply.newBuilder().setStatus(true).setResponseJson(jsonResponse).build();
        }
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }
}