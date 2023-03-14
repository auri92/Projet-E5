package com.example.projet_e5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Mdpoublie extends AppCompatActivity {

    private String id = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mdpoublie);

        set_changeElement(false);

        Button button_check_email = findViewById(R.id.button_change);
        button_check_email.setOnClickListener(v->{
            try {
                check_email();
            } catch (InterruptedException e) {
                Show_notification("error");
            } catch (JSONException e) {
                Show_notification("error");
            }
        });

        Button button_change = findViewById(R.id.button_confirm);
        button_change.setOnClickListener(v->{
            try {
                set_password();
            } catch (InterruptedException e) {
                Show_notification("error");
            } catch (JSONException e) {
                Show_notification("error");
            }
        });

        back_Home();
    }

    protected void check_email() throws InterruptedException, JSONException {

        EditText ed_email = findViewById(R.id.ed_email_mdp);
        if (ed_email.getText() != null){
            Thread_API api = new Thread_API();
            String function_name = "get_By_value",
                    token = "Jiojio000608.",
                    table_name = "clients",
                    values_send = "email," + ed_email.getText();
            ;

            ArrayList<String> list_values = new ArrayList<String>();
            list_values.add(0,function_name);
            list_values.add(1,token);
            list_values.add(2,table_name);
            list_values.add(3,values_send);
            api.set_array_list(list_values);


            api.start();
            api.join();
            JSONObject res = api.get_Values();
            try{
                if (res.getString("id") != null || res.getString("id") != ""){
                    id = res.getString("id");
                    Show_notification("Utilisateurs sélectionnés : " + ed_email.getText());
                    set_changeElement(true);
                    Button button_check = findViewById(R.id.button_change);
                    button_check.setEnabled(false);
                    EditText edittext_email = findViewById(R.id.ed_email_mdp);
                    edittext_email.setEnabled(false);
                }else {
                    set_changeElement(false);
                    Show_notification("L'adresse e-mail n'existe pas");
                }
            }catch (Exception e){
                set_changeElement(false);
                Show_notification("L'adresse e-mail n'existe pas");
            }


        }else{
            Show_notification("L'adresse e-mail est vide !");
        }
    }

    protected void set_password() throws InterruptedException, JSONException {
        EditText password = findViewById(R.id.ed_password_mdp);
        EditText password_confirme = findViewById(R.id.ed_passwordConfirm_mdp);
        String password_text = String.valueOf(password.getText());
        String passwordConfirm_text = String.valueOf(password_confirme.getText());

        Thread_API api = new Thread_API();
        String function_name = "change_password",
                token = "Jiojio000608.",
                table_name = "clients",
                values_send = "password," + password_text + "," + "id," + id;
        ;

        ArrayList<String> list_values = new ArrayList<String>();
        list_values.add(0,function_name);
        list_values.add(1,token);
        list_values.add(2,table_name);
        list_values.add(3,values_send);
        api.set_array_list(list_values);


        api.start();
        api.join();
        JSONObject res = api.get_Values();
        if (res.getString("res").equals("1")){
            Show_notification("Changement réussi");
            Intent intent = new Intent();
            intent.setClass(Mdpoublie.this,LoginMainActivity.class);
            startActivity(intent);
        }else {
            Show_notification("erreur inconnue");
        }
    }

    protected void Show_notification(String info){
        Context context = getApplicationContext();
        CharSequence text = info;
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context,text,duration);
        toast.show();
    }

    protected void set_changeElement(Boolean value){
        Button button_confirm = findViewById(R.id.button_confirm);
        button_confirm.setEnabled(value);
        EditText ed_password = findViewById(R.id.ed_password_mdp);
        ed_password.setEnabled(value);
        EditText ed_password_confirme = findViewById(R.id.ed_passwordConfirm_mdp);
        ed_password_confirme.setEnabled(value);
    }

    protected void back_Home(){
        ImageButton button_home = findViewById(R.id.buttonhome_mdp);

        button_home.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setClass(Mdpoublie.this,MainActivity.class);
            startActivity(intent);
        });
    }
}