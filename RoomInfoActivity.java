package com.example.a1stexample;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class RoomInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_info);

        ListView listView = findViewById(R.id.listViewRoomInfo);
        Database1 db = new Database1(getApplicationContext());

        // Fetch the list of allocated students
        List<Student> students = db.getAllocatedStudents();

        if (students.isEmpty()) {
            Toast.makeText(getApplicationContext(), "No students allocated yet.", Toast.LENGTH_SHORT).show();
        } else {
            // Set up the adapter to display the students
            RoomInfoAdapter adapter = new RoomInfoAdapter(this, students);
            listView.setAdapter(adapter);
        }
    }
}
