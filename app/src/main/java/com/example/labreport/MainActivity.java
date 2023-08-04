package com.example.labreport;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    ToggleButton pwdTogglebutton;
    Button sign_up, Get_started,Google,Facebook,sign_in;
    EditText user_email, pwd;

    TextView forgot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        executing();
    }

    @SuppressLint("WrongViewCast")
    public void executing() {

        //Password Toggle Button
        pwdTogglebutton = findViewById(R.id.passwordToggleButton);
        pwdTogglebutton.setTextOff("");
        pwdTogglebutton.setTextOn("");

        //Password Forget Button
        forgot= findViewById(R.id.frgt_text);
        sign_in = (Button) findViewById(R.id.signin);

        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent start = new Intent(MainActivity.this,Homepage.class);
                startActivity(start);
            }
        });

        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 Intent forgot = new Intent(MainActivity.this,Forgotten_Pasword.class);
                 startActivity(forgot);
            }
        });

        //Username and Password field
        pwd = findViewById(R.id.password_box);
        user_email = findViewById(R.id.email_box);

        //ToggleButton Action

        pwdTogglebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pwdTogglebutton.isChecked()){
                    pwd.setInputType(InputType.TYPE_CLASS_TEXT |InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else {
                    pwd.setInputType( InputType.TYPE_CLASS_TEXT |InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
                pwd.setSelection(pwd.getText().length());
                }
        });

        //Sign_up Action
        sign_up = findViewById(R.id.sign_up);

        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sign = new Intent(MainActivity.this,Sign_up.class);
                startActivity(sign);
            }
        });
    }
}