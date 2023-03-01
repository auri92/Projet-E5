package com.example.projet_e5;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        go_Login();

    }

    protected void go_Login(){
        Button button_login = findViewById(R.id.button_pageLogin);
        button_login.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setClass(MainActivity.this,LoginMainActivity.class);
            startActivity(intent);
        });
    }


}