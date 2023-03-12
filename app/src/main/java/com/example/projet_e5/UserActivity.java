package com.example.projet_e5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class UserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String user_name = intent.getStringExtra("user_name");


        TextView t_user_name = findViewById(R.id.user_name);
        TextView t_user_id = findViewById(R.id.user_id);
        t_user_name.setText("User Name : " + user_name);
        t_user_id.setText("User ID : " + id);

        Button button_log_out = findViewById(R.id.button_deconnection);
        button_log_out.setOnClickListener(v->{
            log_out();
        });

    }

    protected void log_out(){
        Intent intent_main = new Intent();
        intent_main.setClass(UserActivity.this,MainActivity.class);
        intent_main.putExtra("id","null");

        startActivity(intent_main);
    }
}