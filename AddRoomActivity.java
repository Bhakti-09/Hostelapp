package com.example.a1stexample;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddRoomActivity extends AppCompatActivity {
    EditText edUsername, year, branch, mail, mobile;
    Button btn;
    ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_room);

        edUsername = findViewById(R.id.entername);
        year = findViewById(R.id.enteryear);
        branch = findViewById(R.id.enterbranch);
        mail = findViewById(R.id.enteremail);
        mobile = findViewById(R.id.entermobileno);
        btn = findViewById(R.id.button);
        back = findViewById(R.id.navigateback);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edUsername.getText().toString().trim();
                String yr = year.getText().toString().trim();
                String trade = branch.getText().toString().trim();
                String email = mail.getText().toString().trim();
                String mobileNo = mobile.getText().toString().trim();

                // Validate the inputs
                if (username.isEmpty() || yr.isEmpty() || trade.isEmpty() || email.isEmpty() || mobileNo.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please fill all the fields", Toast.LENGTH_SHORT).show();
                } else if (mobileNo.length() != 10) {
                    Toast.makeText(getApplicationContext(), "Please fill mobile number correctly", Toast.LENGTH_SHORT).show();
                } else {
                    Database1 db = new Database1(getApplicationContext());
                    boolean isInserted = db.insertStudentRequest(username, yr, trade, email, mobileNo);
                    if (isInserted) {
                        Toast.makeText(getApplicationContext(), "Request Done", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Failed to submit request", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddRoomActivity.this, mainHomeActivity.class));
            }
        });
    }
}
