package com.thoughtspott.app;

import com.google.type.LatLng;

import java.sql.Timestamp;
import java.util.ArrayList;

public class Session {
    String course;
    Student creator;
    ArrayList<Student> participants = new ArrayList<>();
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

    public void addMember(Student member){
        participants.add(member);
        population++;
    }

    public boolean removeMember(Student member){
        for(int i = 0; i < population; i++){
            if(participants.get(i) == member){
                participants.remove(i);
                population--;
                return true;
            }
        }
        return false;
    }
}
