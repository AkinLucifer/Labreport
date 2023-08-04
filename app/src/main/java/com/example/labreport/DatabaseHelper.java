package com.example.labreport;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Date;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String Database_Name = "mydb";
    public static final int Database_Version = 1;

    public static final String Table_Name = "my_tb";
    public static final String Column_id = "id";
    public static final String user_name = "Username";
    public static final String date = "Date";
    public static final String email = "email";
    public static final String Contact = "Contact";
    public static final String Password = "Password";

    private static final String create_table = new StringBuilder().append(" Create table ").
            append(Table_Name).append("(").append(Column_id).append("Integer primary key autoincrement").
            append(user_name).append(" Text not null,").
            append(email).append("Text not null,").
            append(date).append("text not null,").
            append(Contact).append("Text not null,").
            append(Password).append("Text not null);").toString();

    public DatabaseHelper(Context context){
        super(context, Database_Name, null, Database_Version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(create_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(String.format("Drop %sif exists%s", Table_Name, Database_Name));
        onCreate(db);
    }
}

