package com.thoughtspott.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.thoughtspott.app.ui.main.Register2Fragment;

public class Register2 extends AppCompatActivity {


    EditText firstName;
    EditText lastName;
    EditText email;
    EditText password;
    Button register2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register2_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, Register2Fragment.newInstance())
                    .commitNow();
        }
    }
}