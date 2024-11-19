package com.example.a1stexample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class Database1 extends SQLiteOpenHelper {
    private Context context;
    public static final String Hdata="HostelData.db";
    public Database1(@Nullable Context context) {
        super(context, Hdata, null, 1);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query="create table users(username text,email text,password text);";
        db.execSQL(query);
        Log.d("DatabaseStatus","Database and table created successfully");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS users ");
        onCreate(db);
    }
    public void register(String username,String email,String password)
    {
        ContentValues cv = new ContentValues();
        cv.put("username",username);
        cv.put("email",email);
        cv.put("password",password);
        SQLiteDatabase db = getWritableDatabase();
        db.insert("users",null,cv);
        db.close();

    }
    public int login(String username,String password)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor c= db.rawQuery("select * from users where username=? and password=?",new String[]{username,password});
        if(c.getCount()>0)
        {
            c.close();
            return 1;
        }
        else {
            c.close();
            return 0;
        }

    }
}
