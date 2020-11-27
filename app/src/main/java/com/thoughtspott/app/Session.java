package com.thoughtspott.app;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.android.gms.maps.model.LatLng;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;

public class Session {
    String course;
    Student creator;
    ArrayList<Student> participants = new ArrayList<>();
    int population;
    LatLng location;
    Timestamp timeStart;
    String description;

    public Session(String c, Student crtr, LatLng loc, Timestamp time, String desc){
        course = c;
        creator = crtr;
        participants.add(creator);
        population = 1;
        location = loc;
        timeStart = time;
        description = desc;
    }

    public void addMember(Student member){
        participants.add(member);
        population++;
        //update database here!
    }

    // true if removed successfully
    public boolean removeMember(Student member){
        for(int i = 0; i < population; i++){
            if(participants.get(i) == member){
                participants.remove(i);
                population--;
                // update database here!
                return true;
            }
        }
        return false;
    }
    public void removeSession(){
        // update database here!
    }



}
