package com.vang.borrowservice.service.impl;

import com.google.gson.Gson;
import com.vang.borrowservice.common.ServiceCommon;
import com.vang.borrowservice.data.BorrowRepository;
import com.vang.borrowservice.data.Borrows;
import com.vang.borrowservice.grpc.grpc.GetAuthUserGrpcClientImpl;
import com.vang.borrowservice.grpc.grpc.GetBookByIdGrpcClientImpl;
import com.vang.borrowservice.grpc.grpc.GetUserByUsernameClientGrpcImpl;
import com.vang.borrowservice.grpc.grpcmodel.UserJsonModel;
import com.vang.borrowservice.model.BorrowRequestModel;
import com.vang.borrowservice.model.BorrowResponseModel;
import com.vang.borrowservice.service.BorrowService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class BorrowServiceImpl implements BorrowService {

    private final GetAuthUserGrpcClientImpl getAuthUserGrpcClient;
    private final GetUserByUsernameClientGrpcImpl getUserByUsernameClient;
    private final GetBookByIdGrpcClientImpl getBookByIdGrpcClient;
    private final BorrowRepository borrowRepository;

    @Autowired
    public BorrowServiceImpl(GetAuthUserGrpcClientImpl getAuthUserGrpcClient, GetUserByUsernameClientGrpcImpl getUserByUsernameClient, GetBookByIdGrpcClientImpl getBookByIdGrpcClient, BorrowRepository borrowRepository) {
        this.getAuthUserGrpcClient = getAuthUserGrpcClient;
        this.getUserByUsernameClient = getUserByUsernameClient;
        this.getBookByIdGrpcClient = getBookByIdGrpcClient;
        this.borrowRepository = borrowRepository;
    }

    @Override
    public ResponseEntity<String> addBorrow(BorrowRequestModel requestModel) {

        Gson gson = new Gson();
        String username = getAuthUserGrpcClient.getAuthInfo();
        String userResponse = getUserByUsernameClient.getUserByUsername(username);
        String bookResponse = getBookByIdGrpcClient.getBookByID(requestModel.getBookId());
        UserJsonModel userJsonModel = gson.fromJson(userResponse, UserJsonModel.class);
        Borrows borrows = new Borrows();
        LocalDateTime dateAfterPlusDay = LocalDateTime.now().plusDays(requestModel.getBorrowTime());
        String borrowDate = dateAfterPlusDay.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        requestModel.setUserId(userJsonModel.getUserId());
        requestModel.setUserDetail(userResponse);
        requestModel.setCreatedDate(ServiceCommon.getFullCurrentDate());
        requestModel.setReturnDate(borrowDate);
        requestModel.setBookDetail(bookResponse);
        BeanUtils.copyProperties(requestModel, borrows);
        requestModel.setConfirmStatus(0);
        borrowRepository.save(borrows);
        return null;
    }

    @Override
    public ResponseEntity<List<BorrowResponseModel>> getBorrowByUserId() {

        Gson gson = new Gson();
        String username = getAuthUserGrpcClient.getAuthInfo();
        String userResponse = getUserByUsernameClient.getUserByUsername(username);
        UserJsonModel userJsonModel = gson.fromJson(userResponse, UserJsonModel.class);
        List<Borrows> borrows = borrowRepository.findAllByUserid(userJsonModel.getUserId());
        List<BorrowResponseModel> models = new ArrayList<>();
        borrows.forEach(e -> {

            BorrowResponseModel borrowResponseModel = new BorrowResponseModel();
            BeanUtils.copyProperties(e, borrowResponseModel);
            models.add(borrowResponseModel);
        });
        return new ResponseEntity<>(models, HttpStatus.OK);
    }

}