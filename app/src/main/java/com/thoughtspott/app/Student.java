package com.thoughtspott.app;

import android.app.Application;
import android.os.Parcel;
import android.os.Parcelable;
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
public class Student implements Parcelable {
    private String email;
    private String nameFirst;
    private String nameLast;
    private String bio;
    private List<String> courses = new ArrayList<String>() {
    };
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
    // GETTERS & SETTERS             ///////////////////////////////////
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

    public Student(int nothing){
        this.email = "[email not found]";
        this.nameFirst = "[nameFirst not set]";
        this.nameLast = "[nameLast not set]";
        this.major = "[major not set]";
        this.bio = "[bio not set]";
        this.courses.add("[courses not set]");
    }
    //      PARCELABLE METHODS        //////////////////////////////////
    public static final Creator<Student> CREATOR
            = new Creator<Student>(){
        public Student createFromParcel(Parcel in){
            return new Student(in);
        }
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };

    public Student(Parcel in){
        this();
        readFromParcel(in);
    }

    private void readFromParcel(Parcel in){
        this.email = in.readString();
        this.nameFirst = in.readString();
        this.nameLast = in.readString();
        this.bio = in.readString();
        in.readStringList(courses);
        this.major = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(email);
        dest.writeString(nameFirst);
        dest.writeString(nameLast);
        dest.writeString(bio);
        dest.writeString(major);
        dest.writeStringList(courses);

    }
// saving just in case
//    public void findStudent(MyCallback myCallback){
//        // new db instance
//        FirebaseFirestore db = FirebaseFirestore.getInstance();
//        // new collection reference
//        CollectionReference studentsRef = db.collection("Students");
//        // new query (a bunch of dumb BS)
//        studentsRef
//                .whereEqualTo("Email", email)
//                .limit(1)
//                .get()
//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        if(task.isSuccessful()){
//                            Map<String, Object> data = new HashMap<>();
//                            for (QueryDocumentSnapshot document : task.getResult()) {
//                                data = document.getData();
//
//                                /*data.put("Email", email);
//                                data.put("FirstName", document.get("FirstName"));
//                                data.put("LastName", document.get("LastName"));
//                                data.put("Courses", document.get("Courses"));
//                                data.put("Bio", document.get("Bio"));
//                                data.put("Major", document.get("Major"));*/
//                                Log.d("Student.findStudent", data.toString());
//
//                            }
//                            myCallback.onCallback(data);
//                        } else
//                            Log.d("Student.findStudent", "Error finding documents");
//                    }
//                });
//
//
//    }
}