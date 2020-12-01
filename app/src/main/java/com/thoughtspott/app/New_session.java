package com.thoughtspott.app;

import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class New_session extends MainActivity {

    private TextView descriptiontextview;
    private Button create_sessionbutton;
    private Spinner course_spinner;
    private TimePicker picker;
    ArrayAdapter<String> adapter;
    String selectedCourse, timeSelected, description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_session);

        descriptiontextview = findViewById(R.id.descriptiontextview);
        course_spinner = findViewById(R.id.spinner4);
        create_sessionbutton = findViewById(R.id.createsessionbutton);
        picker =findViewById(R.id.datePicker1);


        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, userCourses);
        course_spinner.setAdapter(adapter);
        course_spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedCourse = (String) course_spinner.getItemAtPosition(position);
            }
            public void onNothingSelected(AdapterView<?> parent){

            }
        });

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
                timeSelected = hour+":"+minute+ " "+am_pm;

                description = descriptiontextview.getText().toString();
                if (TextUtils.isEmpty(description))
                    description = "No description";





                writeNewSession(selectedCourse,user,timeSelected,description);
                Toast.makeText(New_session.this,"Time set to " + hour + ":" + minute + " "+ am_pm,Toast.LENGTH_LONG).show();

            }
        });

    }

    public void writeNewSession(String c, Student crtr, String t, String d){
        Session newSesh = new Session(c, crtr, null, t, d);
        DatabaseReference db = FirebaseDatabase.getInstance().getReference();
        db.child("sessions").child(c).push().setValue(newSesh);
    }
}