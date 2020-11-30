package com.thoughtspott.app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
public class JoinableSessionsList extends AppCompatActivity {
    ListView joinableList;
    //Student user = new Student(0);


    ArrayList<Session> sessions;
    ArrayList<String> sessionsStrings;

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
        ArrayList<String> userCourses = user.getCourses();


        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                sessionsStrings );

        joinableList.setAdapter(arrayAdapter);

        //updateSessionList(referencesOfJoinableSessions(userCourses));

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
                    Session newSession = snapshot.getValue(Session.class);

                    String string = newSession.sessionToFormattedString();
                    sessionsStrings.add(string);
                    Log.d("UpdateSessions", "Session added");
                }
                @Override
                public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                }
                @Override
                public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                    Session byeSession = snapshot.getValue(Session.class);
                    String string = byeSession.sessionToFormattedString();
                    sessionsStrings.remove(string);
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