package com.example.projet_e5;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class LoginMainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);

        back_Home();
    }

    protected void back_Home(){
        ImageButton button_home = findViewById(R.id.buttonhome);

        button_home.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setClass(LoginMainActivity.this,MainActivity.class);
            startActivity(intent);
        });
    }


}