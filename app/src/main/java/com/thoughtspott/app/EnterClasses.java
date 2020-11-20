package com.thoughtspott.app;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class EnterClasses extends MainActivity {

    Spinner spinner, spinner2;
    DatabaseReference databaseReference;
    Button enter_class_button,add_course_button;
    List<String> names;
    ArrayList<String> classInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_classes);

        spinner = findViewById(R.id.spinner);
        spinner2 = findViewById(R.id.spinner2);
        names = new ArrayList<>();
        classInput = new ArrayList<>();


//INITIAL SPINNERS
        databaseReference = FirebaseDatabase.getInstance().getReference();
        //1st spinner
        makeSpinnerData("spinner", spinner);

        //2nd spinner: Uses the 1st spinner to choose the 2nd spinner's choices
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String course_chosen = spinner.getSelectedItem().toString();
                makeSpinnerData(course_chosen,spinner2);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
//END OF INITIAL SPINNERS



        //Button for adding classes to database
        enter_class_button = findViewById(R.id.button_submit);
        enter_class_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                enter_classes();

                //Displays error text in the case that the user did not choose a course prefix and/or number
                TextView errorText = (TextView)spinner.getSelectedView();
                TextView errorText2 = (TextView)spinner2.getSelectedView();
                errorText2.setError("Please select a Course Number");
                errorText.setError("Please select a Course Prefix");
            }
        });



        
        
        
        //Button to add another course input
        add_course_button = findViewById(R.id.button_submit2);
        add_course_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){

            }
        });
    
        
        //ADD ANOTHER BUTTON TO ADD NEW CLASSES TO DATABASE
    }



    private void enter_classes(){

        String prefix = spinner.getSelectedItem().toString();
        String number = spinner2.getSelectedItem().toString();
        String course = prefix + " " + number;

        if (prefix.equals("Choose a Prefix") )
        {
        }else if(number.equals("Choose a Course")){
        }else {
            classInput.add(course);
            user.setCourses(classInput);
            Intent intent = new Intent(EnterClasses.this, Enter_Info.class);
            startActivity(intent);
            finish();
        }

    }





    private void makeSpinnerData(String s, Spinner s2){
        databaseReference.child(s).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                names = new ArrayList<>();
                for(DataSnapshot chilSnap:snapshot.getChildren()) {
                    String spinnerName =  chilSnap.child("name")!= null ? chilSnap.child("name").getValue(String.class) :null;
                    if(spinnerName != null) {
                        names.add(spinnerName);
                    }
                }

                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(EnterClasses.this, android.R.layout.simple_spinner_item,names);
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
                s2.setAdapter(arrayAdapter);
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}