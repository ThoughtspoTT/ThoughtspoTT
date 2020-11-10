package com.thoughtspott.app;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Student extends Application {
    private String email;
    private String nameFirst;
    private String nameLast;
    private String bio;
    private ArrayList<String> courses = new ArrayList<>();
    private String major;

    public String getEmail(){
        return email;
    }
    public void setEmail(String e){
        email = e;
    }
    public ArrayList<String> getCourses(){
        return courses;
    }
    public void setCourses(ArrayList<String> c){
        courses = c;
    }
    public String getNameFirst(){
        return nameFirst;
    }
    public void setNameFirst(String f){
        nameFirst = f;
    }
    public String getNameLast(){
        return nameLast;
    }
    public void setNameLast(String l){
        nameLast = l;
    }
    public String getBio(){
        return bio;
    }
    public void setBio(String b){
        bio = b;
    }
    public String getMajor(){
        return major;
    }
    public void setMajor(String m){
        major = m;
    }
    public void writeToDB(){
        HashMap<String, Object> student = new HashMap<>();     // create HashMap for easy db writing
        //List clist = Arrays.asList(courses);
        // put all info in map
        student.put("Email", email);
        student.put("FirstName", nameFirst);
        student.put("LastName", nameLast);
        student.put("Courses", courses);
        student.put("Bio", bio);
        student.put("Major", major);

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