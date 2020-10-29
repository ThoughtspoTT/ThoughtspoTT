package com.thoughtspott.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EnterClasses extends AppCompatActivity {


    private EditText text_class1, text_class2, text_class3, text_class4;
    private Button enter_class_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_classes);

        text_class1 = findViewById(R.id.editTextClass1);
        text_class2 = findViewById(R.id.editTextClass2);
        text_class3 = findViewById(R.id.editTextClass3);
        text_class4 = findViewById(R.id.editTextClass4);
        enter_class_button = findViewById(R.id.button_submit);


        enter_class_button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){

                enter_classes();

            }


        });

    }

    private void enter_classes(){


        String class1 = text_class1.getText().toString();

        if(!TextUtils.isEmpty(class1)){
            // add to database

        }


        String class2 = text_class2.getText().toString();

        if(!TextUtils.isEmpty(class2)){
            // add to database

        }

        String class3 = text_class3.getText().toString();

        if(!TextUtils.isEmpty(class3)){
            // add to database

        }

        String class4 = text_class4.getText().toString();

        if(!TextUtils.isEmpty(class4)){
            // add to database

        }


        Intent intent = new Intent(EnterClasses.this, DashboardActivity.class);
        startActivity(intent);
        finish();

    }
}