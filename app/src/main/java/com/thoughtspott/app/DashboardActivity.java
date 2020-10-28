package com.thoughtspott.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;

public class DashboardActivity extends Activity {
    private ImageButton profile, message, map, calendar,addevent, logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);

        //Map button
        map = (ImageButton) findViewById(R.id.map);
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMapActivity();
            }
        });

        //Logout button
        logout = (ImageButton) findViewById(R.id.logoutbutton);
        logout.setOnClickListener(new OnClickListener(){
        @Override
            public void onClick(View v){
                LogOut();
            }
        });

        //Calendar button
        //calendar = (ImageButton) findViewById(R.id.calendarbutton);
        //calendar.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View v) {
        //        openCalendarActivity();
        //    }
        //});

        //Add event button
        //addevent = (ImageButton) findViewById(R.id.addevent);
        //addevent.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View v) {
        //        openAddEventActivity();
        //    }
        //});

        //Profile button
        //profile = (ImageButton) findViewById(R.id.profilebutton);
        //profile.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View v) {
        //        openProfileActivity();
        //    }
        //});

        //Message button
        //message = (ImageButton) findViewById(R.id.messagebutton);
        //message.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View v) {
        //        openMessageActivity();
        //    }
        //});
    }

    //Logout button
    public void LogOut(){
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(DashboardActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    //Map button
    public void openMapActivity(){
        Intent MapIntent = new Intent(this, MapsActivity.class);
        startActivity(MapIntent);
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
    //public void openMessageActivity(){
    //  Intent MessageIntent = new Intent(packageContent: this, #######.class);
    //  startactivity(MessageIntent);
    //}

    //Profile button
    //public void openProfileActivity(){
    //  Intent ProfileIntent = new Intent(packageContent: this, #######.class);
    //  startactivity(ProfileIntent);
    //}
}
