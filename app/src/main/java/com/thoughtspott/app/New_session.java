package com.thoughtspott.app;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.thoughtspott.app.DashboardActivity;
import com.thoughtspott.app.MainActivity;
import com.thoughtspott.app.R;
import com.thoughtspott.app.Student;

import java.util.ArrayList;

public class New_session extends MainActivity {

    private EditText first_name, last_name, major, biography;
    private Button submit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_session);

       // first_name = findViewById(R.id.editTextFirstName);
        //last_name = findViewById(R.id.editTextLastName);
        //major = findViewById(R.id.editTextMajor);
        //biography = findViewById(R.id.editTextBiography);
        //submit = findViewById(R.id.buttonSubmitInfo);


    }
}