package com.thoughtspott.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

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



    private void enter_info() {
        //ArrayList<String> infoInput = new ArrayList<>();
        String first_name_text = first_name.getText().toString();

        String first = null;
        if (!TextUtils.isEmpty(first_name_text)) {
            first = first_name_text;
        }

        String last_name_text = last_name.getText().toString();

        String last = null;
        if (!TextUtils.isEmpty(last_name_text)) {
            last = last_name_text;
        }

        String major_text = major.getText().toString();

        String maj = null;
        if (!TextUtils.isEmpty(major_text)) {
            maj = major_text;

        }


        String biography_text = biography.getText().toString();

        String bio = null;
        if (!TextUtils.isEmpty(biography_text)) {
            bio = biography_text;
        }
        Intent ii = getIntent();
        Bundle bundle = ii.getExtras();
        String uid = (String) bundle.get("userID");
        String email = (String) bundle.get("userEmail");
        List<String> courses = (List<String>) bundle.get("courses");
        Log.d("Enter_Info:enter_info()","UID = "+uid);

        writeNewUser(uid, email, first, last, bio, courses, maj);
        Intent intent = new Intent(Enter_Info.this, DashboardActivity.class);
        startActivity(intent);
        finish();
    }
    private void writeNewUser(String id,String eml,String fst,String lst,String bio,List<String> crs,String mjr){
        /*Map<String, Object> user = new HashMap<>();     // create HashMap for easy db writing
        //List clist = Arrays.asList(courses);
        // put all info in map
        user.put("Email", email);
        user.put("FirstName", nameFirst);
        user.put("LastName", nameLast);
        user.put("Courses", courses);
        user.put("Bio", bio);
        user.put("Major", major);*/

        Student user = new Student(eml,fst,lst,bio,crs,mjr);

        DatabaseReference db = FirebaseDatabase.getInstance().getReference();
        db.child("users").child(id).setValue(user);


    }

}