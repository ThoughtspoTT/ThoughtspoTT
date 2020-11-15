package com.thoughtspott.app;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        Map<String, Object> student = new HashMap<>();     // create HashMap for easy db writing
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
    public void setStudentFromDB(Map<String, Object> s){
        email = s.get("email").toString();
        nameFirst = s.get("FirstName").toString();
        nameLast = s.get("LastName").toString();
        courses = (ArrayList<String>) s.get("Courses");
        bio = s.get("bio").toString();
        major = s.get("major").toString();
    }
    public void findStudent(String emailToFind){
        // new db instance
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        // new collection reference
        CollectionReference studentsRef = db.collection("Students");
        // new query (a bunch of dumb BS)
        Query studentQuery = studentsRef.whereEqualTo("email", emailToFind).limit(1);
        Task<QuerySnapshot> task = studentQuery.get();
        QuerySnapshot results = task.getResult();
        List<DocumentSnapshot> doc = results.getDocuments();
        // found student put in doc
        Map<String, Object> studentFound = doc.get(0).getData();
        // call function to set all info
        setStudentFromDB(studentFound);
        ///////////////////////////////////////////////////////////////////////////////////////////
        /*studentsRef
            .whereEqualTo("email", emailToFind)
            .limit(1)
            .get()
            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            Log.d("findStudent", document.getId() + " => " + document.getData());

                        }
                    } else {
                        Log.d("findStudent", "Error getting documents: ", task.getException());
                    }
                }
            });*/
    }
}