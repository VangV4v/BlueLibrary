package com.vang.employeeservice.common;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class ServiceCommon {

    public static final int ZERO = 0;
    public static final int ONE = 1;
    public static final String ROLE_EMPLOYEE = "ROLE_EMPLOYEE";
    public static final String DEFAULT_IMAGE ="https://bluelibrary.s3.ap-southeast-2.amazonaws.com/employees/defaultavatar.png";
    public static final String ADD_EMPLOYEE_SUCCESS = "Add Employee Success";
    public static final String UPDATE_EMPLOYEE_SUCCESS = "Update Employee Success";
    public static final String DELETE_EMPLOYEE_SUCCESS = "Delete Employee Success";
    public static final String ERROR_001 = "Username is exist";
    public static final String ERROR_002 = "Email is exist";
    public static final String ERROR_003 = "Phone is exist";

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
