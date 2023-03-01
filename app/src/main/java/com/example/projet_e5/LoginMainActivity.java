package com.example.projet_e5;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import androidx.appcompat.app.AppCompatActivity;

public class LoginMainActivity extends AppCompatActivity {
    private String Username,Password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);

        back_Home();
        get_login();


        Button button_login = findViewById(R.id.button5);
        button_login.setOnClickListener(v ->{
            System.out.println(check_Login());
        });
    }

    protected void back_Home(){
        ImageButton button_home = findViewById(R.id.buttonhome);

        button_home.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setClass(LoginMainActivity.this,MainActivity.class);
            startActivity(intent);
        });
    }

    protected void get_login(){
        Button button_login = findViewById(R.id.button_login);
        TextView text_id,text_password;
        text_id = findViewById(R.id.text_id);
        text_password = findViewById(R.id.text_password);

        button_login.setOnClickListener(v -> {
            this.Username = text_id.getText().toString();
            this.Password = text_password.getText().toString();
        });
    }

    protected String getUsername(){
        return this.Username;
    }

    protected String getPassword(){
        return this.Password;
    }

    protected boolean check_Login(){
        String User = this.getUsername();
        String Password = this.getPassword();
        System.out.println(User.length() + " / " + Password.length());
        if (User.length() > 0 && Password.length() > 0){
            String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
            Pattern regex = Pattern.compile(check);
            Matcher matcher = regex.matcher(User.toString());
            boolean isMatched = matcher.matches();
            if (isMatched){
                String Password_Origin = "e0c42d61855381f30d670f6cdebc7f13";
                String Password_md5 = getMD5Str(Password);
                System.out.println(Password_md5);
                if (Password_md5.equals(Password_Origin)){
                    return true;
                }else{
                    return false;
                }
            }else{
                return false;
            }
        }else{
            return false;
        }
    }

    protected String getMD5Str(String str) {
        byte[] digest = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance("md5");
            digest = md5.digest(str.getBytes("utf-8"));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String md5Str = new BigInteger(1, digest).toString(16);
        return md5Str;
    }

}