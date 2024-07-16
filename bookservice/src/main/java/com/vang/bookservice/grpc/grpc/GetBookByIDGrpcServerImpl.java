package com.vang.bookservice.grpc.grpc;

import com.google.gson.Gson;
import com.vang.bookservice.data.BookRepository;
import com.vang.bookservice.data.Books;
import com.vang.bookservice.grpc.gen.GetBookByIDGrpc;
import com.vang.bookservice.grpc.gen.GetBookByIDReply;
import com.vang.bookservice.grpc.gen.GetBookByIDRequest;
import com.vang.bookservice.grpc.grpcmodel.BookJsonModel;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;

@GrpcService
public class GetBookByIDGrpcServerImpl extends GetBookByIDGrpc.GetBookByIDImplBase {

    private final BookRepository bookRepository;

    @Autowired
    public GetBookByIDGrpcServerImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void getBookData(GetBookByIDRequest request, StreamObserver<GetBookByIDReply> responseObserver) {

        Gson gson = new Gson();
        Books books = bookRepository.findById(request.getBookId()).orElse(null);
        BookJsonModel bookJsonModel = new BookJsonModel();
        GetBookByIDReply reply;
        if(ObjectUtils.isEmpty(books)) {

            reply = GetBookByIDReply.newBuilder().setStatus(false).build();
        } else {

            BeanUtils.copyProperties(books, bookJsonModel);
            String convertToJson = gson.toJson(bookJsonModel);
            reply = GetBookByIDReply.newBuilder().setStatus(true).setJsonResponse(convertToJson).build();
        }
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }

}