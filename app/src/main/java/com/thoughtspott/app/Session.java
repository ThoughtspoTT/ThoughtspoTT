package com.thoughtspott.app;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
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
        writeToDB();
    }

    // true if removed successfully
    public boolean removeMember(Student member){
        for(int i = 0; i < population; i++){
            if(participants.get(i) == member){
                participants.remove(i);
                population--;
                writeToDB();
                return true;
            }
        }
        return false;
    }
    public void removeSession(){

    }

    public void writeToDB(){
        HashMap<String, Object> session = new HashMap<>();
        session.put("Course", course);
        session.put("Creator", creator);
        session.put("Participants", participants);
        session.put("Population", population);
        session.put("Location", location);
        session.put("TimeStart", timeStart);
        session.put("Description", description);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Sessions").document(course+" ("+creator+")")
                .set(session)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("Session.java", "DocumentSnapshot written successfully");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("Session.java", "Error adding document to Sessions", e);
                    }
                });
    }
}
