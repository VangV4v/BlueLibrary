package com.vang.borrowservice.common;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ServiceCommon {

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
