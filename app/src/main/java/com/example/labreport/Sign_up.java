package com.example.labreport;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.text.DateFormat;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Calendar;

public class Sign_up extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    Button btn,date_picker;
    DatabaseManager dbManager;
    EditText email, user_name,contact,password,date_box;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        first();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void first(){
        date_picker = (Button) findViewById(R.id.calendar);
        btn =(Button) findViewById(R.id.signup_btn);
        user_name = (EditText) findViewById(R.id.sign_username_box);
        email = (EditText) findViewById(R.id.sign_email_box);
        contact = (EditText) findViewById(R.id.sign_contact_box);
        password = (EditText) findViewById(R.id.sign_password_box);
        dbManager = new DatabaseManager(Sign_up.this);
        date_box = (EditText) findViewById(R.id.sign_date_box);

        String User_name = user_name.getText().toString();
        String Email = email.getText().toString();
        String Contact = contact.getText().toString();
        String Password = password.getText().toString();
        byte[] hashed = Password.getBytes(StandardCharsets.UTF_8);
        String Hashed_password = Base64.getEncoder().encodeToString(hashed);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbManager.insert(User_name,Email,Contact,Password);
                Intent back_to_main = new Intent(Sign_up.this,MainActivity.class);
                startActivity(back_to_main);
            }
        });

        date_picker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePicker datePicker = new DatePicker();
                datePicker.show(getSupportFragmentManager(),"Date Pick");
            }
        });
    }

    @Override
    public void onDateSet(android.widget.DatePicker datePicker, int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR,year);
        calendar.set(Calendar.MONTH,month);
        calendar.set(Calendar.DAY_OF_MONTH,day);
        String selectedDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
        int selected_year = calendar.get(Calendar.YEAR);
        int selected_month = calendar.get(Calendar.YEAR);
        int selected_date = calendar.get(Calendar.YEAR);
        date_box.setText(selectedDate);
    }
}