package com.example.projet_e5;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");

        Button button_login = findViewById(R.id.button_pageLogin);
        System.out.println("ID : " + id);
        if (id == null || id.equals("null")){
            button_login.setText("Login");
            go_Login();
        }else{

            try {
                JSONObject json_values = get_user_nom(id);
                button_login.setText(json_values.getString("nom").toString());
                go_user_profil(id);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }

    }


    protected void go_Login(){
        Button button_login = findViewById(R.id.button_pageLogin);
        button_login.setOnClickListener(v ->{
            Intent intent_login = new Intent();
            intent_login.setClass(MainActivity.this,LoginMainActivity.class);
            startActivity(intent_login);
        });
    }

    protected JSONObject get_user_nom(String id) throws InterruptedException {
        Thread_API api = new Thread_API();
        String function_name = "get_By_value",
                token = "Jiojio000608.",
                table_name = "clients",
                values_send = "id," + id;
        ArrayList<String> list_values = new ArrayList<>();
        list_values.add(0,function_name);
        list_values.add(1,token);
        list_values.add(2,table_name);
        list_values.add(3,values_send);
        api.set_array_list(list_values);

        api.start();
        api.join();

        return api.get_Values();
    }

    protected void go_user_profil(String id){
        Button button_login = findViewById(R.id.button_pageLogin);
        button_login.setOnClickListener(v->{
            Intent intent_user_profile = new Intent();
            intent_user_profile.setClass(MainActivity.this,UserActivity.class);
            intent_user_profile.putExtra("id",id);
            intent_user_profile.putExtra("user_name",button_login.getText().toString());
            startActivity(intent_user_profile);
        });
    }

}