package com.thoughtspott.app;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.thoughtspott.app.DashboardActivity;
import com.thoughtspott.app.MainActivity;
import com.thoughtspott.app.R;
import com.thoughtspott.app.Student;

import java.util.ArrayList;

public class New_session extends MainActivity {

    private TextView descriptiontextview;
    private Button create_sessionbutton;
    private Spinner course_spinner;
    private TimePicker picker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_session);

       descriptiontextview = findViewById(R.id.descriptiontextview);
       course_spinner = findViewById(R.id.spinner4);
       create_sessionbutton = findViewById(R.id.createsessionbutton);
       picker =findViewById(R.id.datePicker1);



        create_sessionbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int hour, minute;
                String am_pm;
                if (Build.VERSION.SDK_INT >= 23 ){
                    hour = picker.getHour();
                    minute = picker.getMinute();
                }
                else{
                    hour = picker.getCurrentHour();
                    minute = picker.getCurrentMinute();
                }
                if(hour > 12) {
                    am_pm = "PM";
                    hour = hour - 12;
                }
                else
                {
                    am_pm="AM";
                }

                Toast.makeText(New_session.this,"Time set to " + hour + ":" + minute + " "+ am_pm,Toast.LENGTH_LONG).show();

            }
        });

    }
}