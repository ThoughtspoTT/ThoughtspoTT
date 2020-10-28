package com.thoughtspott.app;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Student {
    String authID;
    String email;
    String courses[] = new String[]{"course0", "course1", "course2", "course3", "course4", "course5"};
    

    public Student(String studentEmail){
        email = studentEmail;
    }

    public void writeToDB(){
        HashMap<String, Object> student = new HashMap<>();     // create HashMap for easy db writing
        List clist = Arrays.asList(courses);
        // put all info in map
        student.put("Email", email);
        //for(int i = 0; i < 6 && !courses[i].equals(""); i++)
        student.put("Courses", clist);

        // write the map to Students collection in the Firestore db:
        FirebaseFirestore db = FirebaseFirestore.getInstance(); // new instance of db
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
