package com.vang.userservice.query.projection;

import com.vang.userservice.data.UserRepository;
import com.vang.userservice.data.Users;
import com.vang.userservice.query.model.UserResponseModel;
import com.vang.userservice.query.queries.GetAllUsers;
import com.vang.userservice.query.queries.GetByUserId;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserQueryProjection {

    private final UserRepository userRepository;

    @Autowired
    public UserQueryProjection(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @QueryHandler
    public UserResponseModel getUserById(GetByUserId byUserId) {

        Users users = userRepository.findById(byUserId.getUserId()).orElse(null);
        UserResponseModel userResponseModel = new UserResponseModel();
        if(ObjectUtils.isEmpty(users)) {

            userResponseModel.initialize();
        } else {

            BeanUtils.copyProperties(users, userResponseModel);
        }
        return userResponseModel;
    }

    @QueryHandler
    public List<UserResponseModel> getAllUsers(GetAllUsers allUsers) {

        List<Users> users = userRepository.findAll();
        List<UserResponseModel> userResponseModels = new ArrayList<UserResponseModel>();
        users.forEach(e -> {
            UserResponseModel userResponseModel = new UserResponseModel();
            BeanUtils.copyProperties(e, userResponseModel);
            userResponseModels.add(userResponseModel);
        });
        return userResponseModels;
    }

}