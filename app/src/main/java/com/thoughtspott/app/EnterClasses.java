package com.thoughtspott.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class EnterClasses extends MainActivity {

    Spinner spinner;
    DatabaseReference databaseReference;
    Button enter_class_button;
    List<String> names;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_classes);

        spinner = findViewById(R.id.spinner);
        names = new ArrayList<>();

        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("spinner").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot chilSnap:snapshot.getChildren()) {
                    String spinnerName =  chilSnap.child("name")!= null ? chilSnap.child("name").getValue(String.class) :null;
                    if(spinnerName != null) {
                        names.add(spinnerName);
                    }
                }

                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(EnterClasses.this, android.R.layout.simple_spinner_item,names);
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
                spinner.setAdapter(arrayAdapter);
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        enter_class_button = findViewById(R.id.button_submit);


        enter_class_button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){

                enter_classes();

            }


        });

    }

    private void enter_classes(){

        ArrayList<String> classInput = new ArrayList<>();

        //course 1
        String class1 = spinner.getSelectedItem().toString();
        classInput.add(class1);


        user.setCourses(classInput);
        Intent intent = new Intent(EnterClasses.this, Enter_Info.class);
        startActivity(intent);
        finish();

    }
}