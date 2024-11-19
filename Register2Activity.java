package com.example.a1stexample;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Register2Activity extends AppCompatActivity {

    EditText edRegUsername, edPassword ;
    EditText edConfirmPassword,edEmail ;
    Button btn ;
   TextView tv ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        edRegUsername=findViewById(R.id.editTextTextRegUsername);
        edPassword=findViewById(R.id.editTextTextPassword2);
        edConfirmPassword=findViewById(R.id.editTextTextConfirmPassword3);
        btn=findViewById(R.id.buttonRegister);
        tv=findViewById(R.id.textView4Back);
        edEmail=findViewById(R.id.editTextTextEmailAddress);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Register2Activity.this, LoginActivity.class));
            }
        });
        btn.setOnClickListener(v -> {
            String username = edRegUsername.getText().toString();
            String password = edPassword.getText().toString();
            String email = edEmail.getText().toString();
            String confirm = edConfirmPassword.getText().toString();
            Database1 db=new Database1(getApplicationContext());
            if (username.length() == 0 || password.length() == 0 || confirm.length()==0) {
                Toast.makeText(getApplicationContext(), "Please fill all details correctly", Toast.LENGTH_SHORT).show();
            } else {
                if (password.compareTo(confirm) == 0)
                {
                    if(isValid(password))
                    {
                        db.register(username,email,password);
                        Toast.makeText(getApplicationContext(),"Record Inserted",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Register2Activity.this,LoginActivity.class));
                    }

                    else
                        Toast.makeText(getApplicationContext(), "password must contain at least 8 char", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getApplicationContext(), "passwords should be same", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public static boolean isValid(String passwordhere){
        int f1=0,f2=0,f3=0;
        if(passwordhere.length()<8)
        {
            return false;
        }
        else {
            for(int p=0;p<passwordhere.length();p++)
            {
                if(Character.isLetter(passwordhere.charAt(p)))
                {
                    f1=1;
                }
            }
            for(int r=0;r<passwordhere.length();r++)
            {
                if(Character.isDigit(passwordhere.charAt(r)))
                {f2=1;}

            }
            int s;
            for(s=0; s<passwordhere.length(); s++)
            {
                char c=passwordhere.charAt(s);
                if(c>=33 && c<=46 || c==64)
                { f3=1;  }
            }
            if(f1==1 && f2==1 && f3==1)
                return true;
            return false;
        }

    }
}