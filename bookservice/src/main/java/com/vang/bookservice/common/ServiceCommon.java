package com.vang.bookservice.common;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ServiceCommon {

    public static final int ZERO = 0;
    public static final int ONE = 1;
    public static final String ADD_BOOK_SUCCESS = "Add Book Success";
    public static final String UPDATE_BOOK_SUCCESS = "Update Book Success";
    public static final String DELETE_BOOK_SUCCESS = "Delete Book Success";

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
