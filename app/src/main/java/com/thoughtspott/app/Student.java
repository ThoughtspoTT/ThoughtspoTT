package com.thoughtspott.app;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Student {

    String email;
    String[] courses = new String[6];
    

    public Student(){
        
    }

    public void writeToDB(){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Map<String, String> student = new HashMap<>();
        student.put("Email", email);
        for(int i = 0; i < 6 && courses[i] != null; i++)
            student.put("Courses", courses[i]);

        db.collection("Students")
                .add(student)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("Student.java", "DocumentSnapshot added with ID: " + documentReference.getId());

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("Student.java", "Error adding document to Students", e);
                    }
                });
    }
}
