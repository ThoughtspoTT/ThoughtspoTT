package com.thoughtspott.app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DashboardActivity extends MainActivity {
    //private ImageButton profile, message, map, calendar,addsession, sessionList, logout;
    private TextView nameText;
    private LinearLayout profile, message, map, calendar, addsession,sessionList,logout;
    //public static Student user;
    private String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard1);

        nameText = findViewById(R.id.textView1);
        nameText.setText("Loading...");
        Intent i = getIntent();
        Bundle b = i.getExtras();
        if(b != null) {
            uid = (String) b.get("userID");
        }
        if (user == null)
            findStudentForUser(uid);

        //Map button

        map = (LinearLayout) findViewById(R.id.maplayout4);
        map.setEnabled(false);
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMapActivity();
            }
        });

        //Logout button
        logout = (LinearLayout) findViewById(R.id.logoutsess6);
        logout.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v){
                LogOut();
            }
        });



        //Add new session
        addsession = (LinearLayout) findViewById(R.id.createsesslayout3);
        addsession.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               openCreateSessionActivity();
           }
        });

        //Profile button
        profile = (LinearLayout) findViewById(R.id.profilelaylout1);
        profile.setEnabled(false);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openProfileActivity();
            }
        });

        //Message button
        message = (LinearLayout) findViewById(R.id.messagelayout2);
        message.setEnabled(false);
        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMessageActivity();
            }
        });

        // Session list button
        sessionList = (LinearLayout) findViewById(R.id.mysesslayout5);
        sessionList.setEnabled(false);
        sessionList.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openJoinableSessionsList();
            }
        });
    }

    //Logout button
    public void LogOut(){
        FirebaseAuth.getInstance().signOut();
        Toast.makeText(DashboardActivity.this,"Log-out successful",Toast.LENGTH_LONG).show();

        Intent intent = new Intent(DashboardActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    //Map button
    public void openMapActivity(){
        Intent MapIntent = new Intent(this, MapsActivity.class);
        startActivity(MapIntent);
    }

    // Session list Button
    public void openJoinableSessionsList(){

        Log.d("Dashboard->SessionsList","courses: "+userCourses);
        Intent sesh = new Intent(this, JoinableSessionsList.class);
        sesh.putExtra("user", user);
        //sesh.putExtra("courses", userCourses);
        startActivity(sesh);
    }
    //Add Event button
    //public void openAddEventActivity(){
    //  Intent AddEventIntent = new Intent(packageContent: this, #######.class);
    //  startactivity(AddEventIntent);
    //}

    //Calendar button
    //public void openCalendarActivity(){
    //  Intent CalendarIntent = new Intent(packageContent: this, #######.class);
    //  startactivity(CalendarIntent);
    //}

    //Message button
    public void openMessageActivity(){
        Intent MessageIntent = new Intent(this, messaging.class);
        startActivity(MessageIntent);
    }

    //add event button

    public void openCreateSessionActivity(){
        Intent SessionIntent = new Intent(this, New_session.class);
        startActivity(SessionIntent);
    }

    //Profile button
    public void openProfileActivity(){

        Intent ProfileIntent = new Intent(this, tabbed_profile.class);
        ProfileIntent.putExtra("info",user);
        startActivity(ProfileIntent);
    }

    private void findStudentForUser(String userID){
        DatabaseReference userRef = FirebaseDatabase
                .getInstance()
                .getReference()
                .child("users")
                .child(userID);
        Log.d("findStudentForUser","Starting user retrieval for UID="+userID);

        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    user = snapshot.getValue(Student.class);
                    assert user != null;
                    userCourses = user.getCourses();
                    nameText.setText(String.format("Hello, %s!", user.getNameFirst()));
                    Log.d("findStudentForUser", "User obj updated: "+user.getEmail());
                    Log.d("findStudentForUser", "User courses: "+user.getCourses());
                    map.setEnabled(true);
                    profile.setEnabled(true);
                    message.setEnabled(true);
                    sessionList.setEnabled(true);

                }
                else{
                    Log.d("findStudentForUser", "user data not found");
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("findStudentForUser","Data retrieval canceled: "+error);

            }
        });

    }


}