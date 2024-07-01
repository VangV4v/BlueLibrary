package com.vang.authorservice.grpc.gprc;

import com.google.gson.Gson;
import com.vang.authorservice.GetAuthorByIdGrpc;
import com.vang.authorservice.GetAuthorByIdReply;
import com.vang.authorservice.GetAuthorByIdRequest;
import com.vang.authorservice.data.AuthorRepository;
import com.vang.authorservice.data.Authors;
import com.vang.authorservice.grpc.grpcmodel.AuthorJsonModel;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;

@GrpcService
public class GetAuthorByIdImpl extends GetAuthorByIdGrpc.GetAuthorByIdImplBase {

    private final AuthorRepository authorRepository;

    @Autowired
    public GetAuthorByIdImpl(AuthorRepository authorRepository) {

        this.authorRepository = authorRepository;
    }

    @Override
    public void getData(GetAuthorByIdRequest request, StreamObserver<GetAuthorByIdReply> responseObserver) {

        Authors authors = authorRepository.findById(request.getAuthorId()).orElse(null);
        Gson gson = new Gson();
        AuthorJsonModel jsonModel = new AuthorJsonModel();
        GetAuthorByIdReply reply;
        if(ObjectUtils.isEmpty(authors)) {

            reply = GetAuthorByIdReply.newBuilder().setStatus(false).build();
        } else {

            BeanUtils.copyProperties(authors, jsonModel);
            String convertToJson = gson.toJson(jsonModel);
            reply = GetAuthorByIdReply.newBuilder().setStatus(true).setResponseJson(convertToJson).build();
        }
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }
}