package com.thoughtspott.app;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

public class JoinableSessionsList extends AppCompatActivity {
    ListView joinableList;
    Student user = new Student(0);


    ArrayList<Session> sessions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joinable_sessions_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        joinableList = findViewById(R.id.sesh_list);

        Intent i = getIntent();
        Bundle b = i.getExtras();
        Student user = (Student) b.get("user");
        ArrayList<String> userCourses = (ArrayList<String>) user.getCourses();
        Log.d("SessionsListCreate","One of user's courses is: "+userCourses.get(0));

        updateSessionList(referencesOfJoinableSessions(userCourses));

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }




    // pass in list of student's courses and ret the DB references of the sessions for their courses
    private ArrayList<DatabaseReference> referencesOfJoinableSessions(ArrayList<String> courses){
        ArrayList<DatabaseReference> references = new ArrayList<>();
        for(String course : courses){
            DatabaseReference seshRef = FirebaseDatabase
                    .getInstance()
                    .getReference()
                    .child("sessions")
                    .child(course);
            references.add(seshRef);
            Log.d("JoinableSessions", "Reference found for course: "+course);
        }
        Log.d("JoinableSessions","Done finding references");
        return references;
    }



    public void updateSessionList(ArrayList<DatabaseReference> refs){

        for(DatabaseReference ref : refs){
            ref.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                    sessions.add(snapshot.getValue(Session.class));
                    Log.d("UpdateSessions", "Session added");
                }
                @Override
                public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                }
                @Override
                public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                    sessions.remove(snapshot.getValue(Session.class));
                    Log.d("UpdateSessions", "Session removed");
                }
                @Override
                public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Log.d("UpdateSessions", "Cancelled");
                }
            });
        }




    }

}