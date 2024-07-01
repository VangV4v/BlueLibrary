package com.vang.userservice.common;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ServiceCommon {

    public static final int ZERO = 0;
    public static final int ONE = 1;
    public static final String ROLE_USER = "ROLE_USER";
    public static final String DEFAULT_IMAGE ="https://bluelibrary.s3.ap-southeast-2.amazonaws.com/users/defaultavatar.png";
    public static final String ADD_USER_SUCCESS = "Add User Success";
    public static final String UPDATE_USER_SUCCESS = "Update User Success";
    public static final String DELETE_USER_SUCCESS = "Delete User Success";
    public static final String ERROR_001 = "Username is already exist";
    public static final String ERROR_002 = "Email is already exist";
    public static final String ERROR_003 = "Phone is already exist";

    public static int getIndexById(String id) {
        for(int i = 0; i < id.length(); ++i) {
            char characterOfPoint = id.charAt(i);
            char characterOfPointNext = id.charAt(i + 1);
            if ((characterOfPoint < 'A' || characterOfPoint > 'Z') && (characterOfPointNext < 'A' || characterOfPointNext > 'Z')) {
                return i;
            }
        }

        return -1;
    }

    public static String getFullCurrentDate() {

        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
