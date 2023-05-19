package com.example.ai_club;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Screensaver extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screensaver);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                // if(currentUser==null){
                //  startActivity(new Intent(Screensaver.this,LoginActivity.class));
                // }else{
                startActivity(new Intent(Screensaver.this,CreateAccountActivity.class));
            }
            // finish();
        },1500);

    }
}