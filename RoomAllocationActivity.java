package com.example.a1stexample;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RoomAllocationActivity extends AppCompatActivity {

    private View btnroominfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_room_allocation);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    EditText edUsername, edRoomNumber;
    Button btnAllocate;
    Database1 db;

        edUsername = findViewById(R.id.editTextUsername);
        edRoomNumber = findViewById(R.id.editTextRoomNumber);
        btnAllocate = findViewById(R.id.buttonAllocate);
        btnroominfo = findViewById(R.id.buttonViewRoomInfo);
        db = new Database1(getApplicationContext());

        btnAllocate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edUsername.getText().toString().trim();
                String roomNumber = edRoomNumber.getText().toString().trim();

                if (username.isEmpty() || roomNumber.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please fill all fields", Toast.LENGTH_SHORT).show();
                } else {
                    boolean isAllocated = db.allocateRoom(username, roomNumber);
                    if (isAllocated) {
                        Toast.makeText(getApplicationContext(), "Room allocated successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Failed to allocate room. Check username.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

       btnroominfo.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               startActivity(new Intent(RoomAllocationActivity.this,RoomInfoActivity.class));
           }
       });

    }
}
