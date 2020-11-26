package com.thoughtspott.app;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.applozic.mobicomkit.Applozic;
import com.applozic.mobicomkit.uiwidgets.conversation.ConversationUIService;
import com.applozic.mobicomkit.uiwidgets.conversation.activity.ConversationActivity;

public class launcher extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Applozic.isConnected(launcher.this)) {
            Intent intent = new Intent(this, ConversationActivity.class);
            intent.putExtra("userId", "test"); //change the userId here for other users
            intent.putExtra(ConversationUIService.DISPLAY_NAME, "Jacob"); //This name will be displayed on the toolbar of the chat screen
            startActivity(intent);
            finish();
        } else {
            Intent intent = new Intent(this, messaging.class);
            startActivity(intent);
            finish();
        }
    }
}
