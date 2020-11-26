package com.thoughtspott.app;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.auth.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@IgnoreExtraProperties
public class Student {
    private String email;
    private String nameFirst;
    private String nameLast;
    private String bio;
    private List<String> courses;
    private String major;
    //private HashMap<String,Object> studentMap;
    
    // default constructor required for database getValue calls
    public Student(){
    }
    // constructor
    public Student(String e, String nF, String nL, String b, List<String> c, String m){
        this.email = e;
        this.nameFirst = nF;
        this.nameLast = nL;
        this.bio = b;
        this.courses = c;
        this.major = m;
    }

    public String getEmail(){
        return this.email;
    }
    public void setEmail(String e){
        this.email = e;
    }
    public List<String> getCourses(){
        return this.courses;
    }
    public void setCourses(List<String> c){
        this.courses = c;
    }
    public String getNameFirst(){
        return this.nameFirst;
    }
    public void setNameFirst(String f){
        this.nameFirst = f;
    }
    public String getNameLast(){
        return this.nameLast;
    }
    public void setNameLast(String l){
        this.nameLast = l;
    }
    public String getBio(){
        return this.bio;
    }
    public void setBio(String b){
        this.bio = b;
    }
    public String getMajor(){
        return this.major;
    }
    public void setMajor(String m){
        this.major = m;
    }


//    public void setStudentFromDB(Map<String, Object> s){
//        setEmail(s.get("Email").toString());
//
//        setCourses((List<String>) s.get("Courses"));
//
//        if(s.get("FirstName") != null)
//            setNameFirst(s.get("FirstName").toString());
//        else
//            setNameFirst("(not set)");
//
//        if(s.get("LastName") != null)
//            setNameLast(s.get("LastName").toString());
//        else
//            setNameLast("(not set)");
//
//        if(s.get("Bio") != null)
//            setBio(s.get("Bio").toString());
//        else
//            setBio("(not set)");
//
//        if(s.get("Major") != null)
//            setMajor(s.get("Major").toString());
//        else
//            setMajor("(not set)");
//    }
    public void findStudent(MyCallback myCallback){
        // new db instance
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        // new collection reference
        CollectionReference studentsRef = db.collection("Students");
        // new query (a bunch of dumb BS)
        studentsRef
                .whereEqualTo("Email", email)
                .limit(1)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            Map<String, Object> data = new HashMap<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                data = document.getData();

                                /*data.put("Email", email);
                                data.put("FirstName", document.get("FirstName"));
                                data.put("LastName", document.get("LastName"));
                                data.put("Courses", document.get("Courses"));
                                data.put("Bio", document.get("Bio"));
                                data.put("Major", document.get("Major"));*/
                                Log.d("Student.findStudent", data.toString());

                            }
                            myCallback.onCallback(data);
                        } else
                            Log.d("Student.findStudent", "Error finding documents");
                    }
                });


    }

}