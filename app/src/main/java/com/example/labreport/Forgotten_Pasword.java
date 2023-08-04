package com.example.labreport;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Forgotten_Pasword extends AppCompatActivity implements change_password.Listener{

    private Integer registered_id;
    private EditText username , contact;
    private DatabaseManager databaseManager;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotten_pasword);
        first();
    }

    public void first(){
        btn = (Button) findViewById(R.id.search_btn);
        databaseManager = new DatabaseManager(Forgotten_Pasword.this);
        username = (EditText) findViewById(R.id.frgt_pwd_email_box);
        contact = (EditText) findViewById(R.id.frgt_pwd_contact_box);
        String Username = username.getText().toString();
        String Contact = contact.getText().toString();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Cursor result = databaseManager.fetch(Username);
//                if(result.getCount()!=0){
//                    registered_id = result.getInt(0);
//                    String registered_contact = result.getString(3);
//                    if(Contact == registered_contact){
                        openDialog();
//                    }
//                }
            }
        });
    }
    public void openDialog(){
        change_password box = new change_password();
        box.show(getSupportFragmentManager(),"Change Password");
    }

    @Override
    public void applyTexts(String Password) {
        databaseManager.update_password(registered_id,Password);
    }

    @Override
    public void onBackPressed() {
            super.onBackPressed();
        }
    }