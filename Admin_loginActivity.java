package com.example.a1stexample;

import android.os.Bundle;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Admin_loginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_admin_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    EditText edAdminUsername, edAdminPassword;
    Button btnAdminLogin;

    edAdminUsername = findViewById(R.id.editTextAdminUsername);
    edAdminPassword = findViewById(R.id.editTextAdminPassword);
    btnAdminLogin = findViewById(R.id.buttonAdminLogin);

    btnAdminLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String adminUsername = edAdminUsername.getText().toString().trim();
                String adminPassword = edAdminPassword.getText().toString().trim();

                if (adminUsername.equals("admin") && adminPassword.equals("admin123")) {
                    // Save admin session
                    SharedPreferences sharedPreferences = getSharedPreferences("shared_pref", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("role", "admin");
                    editor.apply();

                    Toast.makeText(getApplicationContext(), "Admin Login Successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Admin_loginActivity.this, AdminDashboardActivity.class));
                } else {
                    Toast.makeText(getApplicationContext(), "Invalid Admin Credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
