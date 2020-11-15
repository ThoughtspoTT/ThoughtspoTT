package com.thoughtspott.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Profile extends MainActivity {
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        //TextView textView = (TextView) findViewById(R.id.textView);
        //textView.setText(String.valueOf(user));

        text = (TextView) findViewById(R.id.textViewTest);
        text.setText("hello");
    }
}