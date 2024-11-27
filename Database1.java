package com.example.a1stexample;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Database1 extends SQLiteOpenHelper {

    private static final String Hdata = "HostelData.db";
    private static final int DATABASE_VERSION = 2;

    // Users table (for registration)
    private static final String USERS_TABLE = "users";
    private static final String COL_USERNAME = "username";
    private static final String COL_EMAIL = "email";
    private static final String COL_PASSWORD = "password";
    private static final String COL_ROOM_NUMBER = "room_number";
    private static final String COL_BRANCH = "branch";
    private static final String COL_YEAR = "year";

    // Requests table (for room requests)
//
//    private static final String RoomRequestdata = "RoomRequest.db";
//    private static final int DATABASE_VERSION1 = 2;
    static final String REQUESTS_TABLE = "requests";
    static final String COL_REQUEST_ID = "request_id";
    static final String COL_REQUEST_USERNAME = "username";
    static final String COL_REQUEST_YEAR = "year";
    static final String COL_REQUEST_BRANCH = "branch";
    static final String COL_REQUEST_EMAIL = "email";
    static final String COL_REQUEST_MOBILE = "mobile";

    public Database1(@Nullable Context context) {
        super(context, Hdata, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create users table
        String createUsersTable = "CREATE TABLE IF NOT EXISTS " + USERS_TABLE + " (" +
                COL_USERNAME + " TEXT PRIMARY KEY, " +
                COL_EMAIL + " TEXT, " +
                COL_PASSWORD + " TEXT, " +
                COL_ROOM_NUMBER + " TEXT, " +
                COL_BRANCH + " TEXT, " +
                COL_YEAR + " TEXT);";
        db.execSQL(createUsersTable);

        // Create requests table for storing room requests
        String createRequestsTable = "CREATE TABLE IF NOT EXISTS " + REQUESTS_TABLE + " (" +
                COL_REQUEST_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_REQUEST_USERNAME + " TEXT, " +
                COL_REQUEST_YEAR + " TEXT, " +
                COL_REQUEST_BRANCH + " TEXT, " +
                COL_REQUEST_EMAIL + " TEXT, " +
                COL_REQUEST_MOBILE + " TEXT);";
        db.execSQL(createRequestsTable);

        Log.d("DatabaseStatus", "Database and tables created successfully");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 2) {
            // Alter table or handle migrations
            db.execSQL("ALTER TABLE " + USERS_TABLE + " ADD COLUMN " + COL_ROOM_NUMBER + " TEXT;");
            Log.d("DatabaseStatus", "room_number column added to users table");
        }
    }

    // Method to insert a room request
    public boolean insertStudentRequest(String username, String year, String branch, String email, String mobile) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COL_REQUEST_USERNAME, username);
        values.put(COL_REQUEST_YEAR, year);
        values.put(COL_REQUEST_BRANCH, branch);
        values.put(COL_REQUEST_EMAIL, email);
        values.put(COL_REQUEST_MOBILE, mobile);

        long result = db.insert(REQUESTS_TABLE, null, values);
        db.close();

        if (result == -1) {
            Log.e("DatabaseError", "Failed to insert request for " + username);
            return false;
        } else {
            Log.d("DatabaseStatus", "Request inserted successfully for " + username);
            return true;
        }
    }

    // Method to fetch all room requests (for display in Requested_stud_activity)
    public Cursor getAllRoomRequests() {
        SQLiteDatabase db = this.getReadableDatabase();
        // Fetch all the data from the 'requests' table
        return db.rawQuery("SELECT * FROM " + REQUESTS_TABLE, null);
    }

    // Register method to add users
    public boolean register(String username, String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_USERNAME, username);
        values.put(COL_EMAIL, email);
        values.put(COL_PASSWORD, password);

        // Insert new user into the users table
        long result = db.insert(USERS_TABLE, null, values);
        db.close();

        // Return true if insert was successful (result > 0), false if failed
        return result != -1;
    }

    // Method to fetch all users
    public Cursor getAllUsers() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + USERS_TABLE, null);
    }

    // Method to allocate room to user
    public boolean allocateRoom(String username, String roomNumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COL_USERNAME,username);
        values.put(COL_ROOM_NUMBER, roomNumber);

        // Update room_number for the user
        int rowsUpdated = db.update(USERS_TABLE, values, COL_USERNAME + "=?", new String[]{username});
        db.close();
        return rowsUpdated > 0;
    }

    // Fetch all allocated students
    public List<Student> getAllocatedStudents() {
        List<Student> students = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        // Query to get users who have a room number allocated
        Cursor cursor = db.rawQuery("SELECT * FROM " + USERS_TABLE + " WHERE " + COL_ROOM_NUMBER + " IS NOT NULL", null);

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String username = cursor.getString(cursor.getColumnIndex(COL_USERNAME));
                @SuppressLint("Range") String email = cursor.getString(cursor.getColumnIndex(COL_EMAIL));
                @SuppressLint("Range") String roomNumber = cursor.getString(cursor.getColumnIndex(COL_ROOM_NUMBER));
                @SuppressLint("Range") String branch = cursor.getString(cursor.getColumnIndex(COL_BRANCH));
                @SuppressLint("Range") String year = cursor.getString(cursor.getColumnIndex(COL_YEAR));

                // Add student to the list
                Student student = new Student(username, email, roomNumber, branch, year);
                students.add(student);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return students;
    }

    // Method for user login
    public boolean login(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + USERS_TABLE + " WHERE " + COL_USERNAME + " = ? AND " + COL_PASSWORD + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{username, password});

        if (cursor != null && cursor.moveToFirst()) {
            cursor.close();
            db.close();
            return true; // Valid login
        }

        cursor.close();
        db.close();
        return false; // Invalid login
    }

    // Method to check if a username already exists
    public boolean checkUsernameExists(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + USERS_TABLE + " WHERE " + COL_USERNAME + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{username});

        if (cursor != null && cursor.moveToFirst()) {
            cursor.close();
            db.close();
            return true; // Username exists
        }

        cursor.close();
        db.close();
        return false; // Username does not exist
    }
}
