package com.emivue.sqlite_login_and_register_app.activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.emivue.sqlite_login_and_register_app.R;


public class AuthSuccessScreen extends AppCompatActivity {
    Handler handler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth_success_screen);
        handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(AuthSuccessScreen.this,UsersActivity.class);
                startActivity(intent);
                finish();
            }
        },5000);
    }

}
