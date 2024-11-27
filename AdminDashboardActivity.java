package com.example.a1stexample;

import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class AdminDashboardActivity extends AppCompatActivity {

    Button btnViewUsers, btnAllocateRoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        btnViewUsers = findViewById(R.id.viewUsers);
        btnAllocateRoom = findViewById(R.id.AllocateRoom);

        btnViewUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to User List Activity
                startActivity(new Intent(AdminDashboardActivity.this, UserListActivity.class));
            }
        });

        btnAllocateRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to Room Allocation Activity
                startActivity(new Intent(AdminDashboardActivity.this, RoomAllocationActivity.class));
            }
        });

        btnViewUsers.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminDashboardActivity.this,Requested_stud_Activity.class));
            }
        }));
    }
}
