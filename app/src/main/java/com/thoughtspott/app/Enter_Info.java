package com.thoughtspott.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class Enter_Info extends MainActivity {

    private EditText first_name, last_name, major, biography;
    private Button submit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter__info);

        first_name = findViewById(R.id.editTextFirstName);
        last_name = findViewById(R.id.editTextLastName);
        major = findViewById(R.id.editTextMajor);
        biography = findViewById(R.id.editTextBiography);
        submit = findViewById(R.id.buttonSubmitInfo);


        submit.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){

                enter_info();



            }


        });

    }



    private void enter_info(){
        //ArrayList<String> infoInput = new ArrayList<>();
        String first_name_text = first_name.getText().toString();

        if(!TextUtils.isEmpty(first_name_text)){
            user.setNameFirst(first_name_text);
        }

        String last_name_text = last_name.getText().toString();

        if(!TextUtils.isEmpty(last_name_text)){
            user.setNameLast(last_name_text);
        }

        String major_text = major.getText().toString();

        if(!TextUtils.isEmpty(major_text)){
            user.setMajor(major_text);

        }


        String biography_text = biography.getText().toString();

        if(!TextUtils.isEmpty(biography_text)){
            user.setBio(biography_text);
        }


        user.writeToDB();
        Intent intent = new Intent(Enter_Info.this, DashboardActivity.class);
        startActivity(intent);
        finish();
    }

}