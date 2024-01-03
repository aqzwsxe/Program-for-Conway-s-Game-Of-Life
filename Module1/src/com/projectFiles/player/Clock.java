package com.projectFiles.player;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Clock {
    private static  Clock clockContainer;

    private Clock() {
    }

    public static Clock getInstanceClock(){
        if (clockContainer == null) {
            clockContainer = new Clock();
        }
        return clockContainer;
    }

    public String CurrentTime(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy, MM, dd HH:mm");
        return sdf.format(date);
    }
}
