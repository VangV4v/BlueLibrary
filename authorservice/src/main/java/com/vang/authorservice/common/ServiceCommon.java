package com.vang.authorservice.common;

public class ServiceCommon {

    public static final int ZERO = 0;
    public static final String ADD_PUBLISHER_SUCCESS = "Add Publisher Success";
    public static final String UPDATE_PUBLISHER_SUCCESS = "Update Publisher Success";
    public static final String DELETE_PUBLISHER_SUCCESS = "Delete Publisher Success";

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
