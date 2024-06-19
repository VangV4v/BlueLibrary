package com.vang.typeservice.common;

public class TypeServiceCommon {

    public static final int ZERO = 0;
    public static final String ADD_TYPE_SUCCESS = "Add Type Success";
    public static final String UPDATE_TYPE_SUCCESS = "Update Type Success";
    public static final String DELETE_TYPE_SUCCESS = "Delete Type Success";

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
