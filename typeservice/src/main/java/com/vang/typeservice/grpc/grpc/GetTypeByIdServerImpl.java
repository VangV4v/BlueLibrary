package com.vang.typeservice.grpc.grpc;

import com.google.gson.Gson;
import com.vang.typeservice.data.TypeRepository;
import com.vang.typeservice.data.Types;
import com.vang.typeservice.grpc.gen.GetTypeByIdGrpc;
import com.vang.typeservice.grpc.gen.GetTypeByIdReply;
import com.vang.typeservice.grpc.gen.GetTypeByIdRequest;
import com.vang.typeservice.grpc.grpcmodel.TypeJsonModel;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;

@GrpcService
public class GetTypeByIdServerImpl extends GetTypeByIdGrpc.GetTypeByIdImplBase {

    private final TypeRepository typeRepository;

    @Autowired
    public GetTypeByIdServerImpl(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }

    @Override
    public void getData(GetTypeByIdRequest request, StreamObserver<GetTypeByIdReply> responseObserver) {

        Types types = typeRepository.findById(request.getTypeId()).orElse(null);
        GetTypeByIdReply reply;
        if(ObjectUtils.isEmpty(types)) {

            reply = GetTypeByIdReply.newBuilder().setStatus(false).build();
        } else {

            Gson gson= new Gson();
            TypeJsonModel jsonModel = new TypeJsonModel();
            BeanUtils.copyProperties(types, jsonModel);
            String json = gson.toJson(jsonModel);
            reply = GetTypeByIdReply.newBuilder().setStatus(true).setResponseJson(json).build();
        }
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }

}