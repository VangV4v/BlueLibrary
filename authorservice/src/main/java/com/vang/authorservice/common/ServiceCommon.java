package com.vang.authorservice.common;

public class ServiceCommon {

    public static final int ZERO = 0;
    public static final String ADD_AUTHOR_SUCCESS = "Add Author Success";
    public static final String UPDATE_AUTHOR_SUCCESS = "Update Author Success";
    public static final String DELETE_AUTHOR_SUCCESS = "Delete Author Success";

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
}
