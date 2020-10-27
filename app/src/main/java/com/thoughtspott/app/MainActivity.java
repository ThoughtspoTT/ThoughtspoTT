package com.thoughtspott.app;

// qwertyuiop


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {

    private EditText email_SI, password_SI;
    private Button SignUp_SI, LogIn_SI;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in);
        firebaseAuth = FirebaseAuth.getInstance();
        email_SI = findViewById(R.id.editTextEmailLI);
        password_SI = findViewById(R.id.editTextPasswordLI);
        LogIn_SI = findViewById(R.id.buttonLogInLI);
        progressDialog = new ProgressDialog(this);
        SignUp_SI= findViewById(R.id.buttonRegisterLI);



        LogIn_SI.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){

                Login();

            }


        });

        SignUp_SI.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, SignupActivity.class);
                startActivity(intent);
                finish();
            }



        });

    }


    private void Login(){
        String email = email_SI.getText().toString();
        String password = password_SI.getText().toString();

        if(TextUtils.isEmpty(email)){
            email_SI.setError("Enter Your Email");
            return;
        }
        else if(TextUtils.isEmpty(password)){
            password_SI.setError("Enter Your Password");
            return;
        }


        progressDialog.setMessage("Please Wait...");
        progressDialog.show();
        progressDialog.setCanceledOnTouchOutside(false);
        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(MainActivity.this,"Successful Log In",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(MainActivity.this, DashboardActivity.class);
                    startActivity(intent);
                    finish();

                }
                else{
                    Toast.makeText(MainActivity.this,"Log In fail!",Toast.LENGTH_LONG).show();
                }
                progressDialog.dismiss();
            }
        });

    }



}



