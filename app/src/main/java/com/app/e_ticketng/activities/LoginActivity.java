package com.app.e_ticketng.activities;
import static com.app.e_ticketng.helpers.common_helper.getOfficerLogin;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.app.e_ticketng.R;
import com.app.e_ticketng.activities.Officers.DashboardOfficerActivity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnLogin;
    TextView tvCreateAccount;
    EditText etEmail, etPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnLogin = findViewById(R.id.LoginBtn);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        tvCreateAccount = findViewById(R.id.CreateAccount);
        btnLogin.setOnClickListener(this);
        tvCreateAccount.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.LoginBtn:
                String stEmail = etEmail.getText().toString();
                String stPassword = etPassword.getText().toString();
                if(!stEmail.isEmpty() && !stPassword.isEmpty()){
                    if(getOfficerLogin(stEmail,stPassword)) {
                        Toast.makeText(getApplicationContext(),"Login Success!",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), DashboardOfficerActivity.class));
                    }
//                    else if(getUserLogin(stEmail,stPassword)){
//                        Toast.makeText(getApplicationContext(),"Login Success!",Toast.LENGTH_SHORT).show();
//                        startActivity(new Intent(getApplicationContext(), DashboardUserActivity.class));
//                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Invalid credentials!",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(),"email (or) password should not be empty!",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.CreateAccount:
                startActivity(new Intent(getApplicationContext(), CreateAccountActivity.class));
                break;
            default:
                break;
        }
    }
}