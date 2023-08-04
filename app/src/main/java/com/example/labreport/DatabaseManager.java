package com.example.labreport;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;

public class DatabaseManager {
    private DatabaseHelper databaseHelper;
    private Context context;
    private SQLiteDatabase sqLiteDatabase;

    public DatabaseManager(Context c){
        context = c;
    }

    public DatabaseManager open() throws SQLException{
        databaseHelper = new DatabaseHelper(context);
        sqLiteDatabase = databaseHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        databaseHelper.close();
    }

    public void insert(String name ,String email ,  String contact , String Password,String Date_selected){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.user_name,name);
        contentValues.put(DatabaseHelper.email,email);
        contentValues.put(DatabaseHelper.Contact,contact);
        contentValues.put(DatabaseHelper.Password,Password);
        contentValues.put(DatabaseHelper.date,Date_selected);
    }

    public Cursor fetch(String search_entity){
        sqLiteDatabase = databaseHelper.getReadableDatabase();
        String query = "Select * from "+ databaseHelper.Table_Name + "Where " + databaseHelper.user_name + "="+ search_entity+";";
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);
        return cursor;

    }
    public int update_name(long _id,String name){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.user_name,name);
        int i = sqLiteDatabase.update(DatabaseHelper.Table_Name, contentValues,DatabaseHelper.Column_id + " = " + _id, null);
        return i;
    }
    public int update_email(long _id,String email){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.email,email);
        int i = sqLiteDatabase.update(DatabaseHelper.Table_Name, contentValues, DatabaseHelper.Column_id + " = " + _id, null);
        return i;
    }
    public int update_password(long _id,String password){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.Password,password);
        int i = sqLiteDatabase.update(DatabaseHelper.Table_Name, contentValues, DatabaseHelper.Column_id + " = " + _id, null);
        return i;
    }
    public int update_Contact(long _id,int contact){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.Contact,contact);
        int i = sqLiteDatabase.update(DatabaseHelper.Table_Name, contentValues, DatabaseHelper.Column_id + " = " + _id, null);
        return i;
    }

    public  void delete (long _id){
        sqLiteDatabase.delete(DatabaseHelper.Table_Name,DatabaseHelper.Column_id + " = " + _id, null);
    }
}
