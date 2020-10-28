package com.thoughtspott.app;

import com.google.type.LatLng;

import java.sql.Timestamp;

public class Session {
    String course;
    Student creator;
    String[] participants;
    int population;
    LatLng location;
    Timestamp timeStart;
    boolean isPrivate;

    public Session(String c, Student crtr, LatLng loc, Timestamp time, boolean priv){
        course = c;
        creator = crtr;
        population = 1;
        location = loc;
        timeStart = time;
        isPrivate = priv;
    }
}
