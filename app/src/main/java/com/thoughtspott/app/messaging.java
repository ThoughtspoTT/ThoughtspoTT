package com.thoughtspott.app;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.applozic.mobicomkit.Applozic;
import com.applozic.mobicomkit.api.account.register.RegistrationResponse;
import com.applozic.mobicomkit.api.account.user.User;
import com.applozic.mobicomkit.listners.AlLoginHandler;
import com.applozic.mobicomkit.uiwidgets.conversation.activity.ConversationActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class messaging extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messaging);

        User user = new User();
        user.setUserId("User"); //userId it can be any unique user identifier NOTE : +,*,? are not allowed chars in user

        Applozic.connectUser(this, user, new AlLoginHandler() {

            @Override

            public void onSuccess(RegistrationResponse registrationResponse, Context context) {

                // After successful registration with Applozic server the callback will come here

                Intent mainIntent = new Intent(messaging.this, launcher.class);
                startActivity(mainIntent);
                finish();

            }

            @Override

            public void onFailure(RegistrationResponse registrationResponse, Exception exception) {

                // If any failure in registration the callback  will come here

                Toast.makeText(messaging.this, "Error : " + registrationResponse, Toast.LENGTH_SHORT).show();

            }

        });
    }
}