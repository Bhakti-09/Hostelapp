package com.example.a1stexample;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

public class Requested_stud_Activity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private StudentAdapter adapter;
    private ArrayList<HashMap<String, String>> studentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requested_stud);

        recyclerView = findViewById(R.id.recyclerViewRequested);
        studentList = new ArrayList<>();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        fetchRequestedStudents();

        adapter = new StudentAdapter(studentList);
        recyclerView.setAdapter(adapter);
    }

    @SuppressLint("Range")
    private void fetchRequestedStudents() {
        Database1 db = new Database1(getApplicationContext());
        Cursor cursor = db.getAllRoomRequests();

        if (cursor != null && cursor.moveToFirst()) {
            do {
                HashMap<String, String> student = new HashMap<>();
                student.put("username", cursor.getString(cursor.getColumnIndex(Database1.COL_REQUEST_USERNAME)));
                student.put("year", cursor.getString(cursor.getColumnIndex(Database1.COL_REQUEST_YEAR)));
                student.put("branch", cursor.getString(cursor.getColumnIndex(Database1.COL_REQUEST_BRANCH)));
                student.put("email", cursor.getString(cursor.getColumnIndex(Database1.COL_REQUEST_EMAIL)));
                student.put("mobile", cursor.getString(cursor.getColumnIndex(Database1.COL_REQUEST_MOBILE)));
                studentList.add(student);
            } while (cursor.moveToNext());
            cursor.close();
        } else {
            Toast.makeText(getApplicationContext(), "No requested students found", Toast.LENGTH_SHORT).show();
        }
    }
}
