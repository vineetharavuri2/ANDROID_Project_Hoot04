package com.app.e_ticketng.activities.Officers;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.app.e_ticketng.R;
import com.app.e_ticketng.activities.LoginActivity;

public class CreateTicketActivity extends AppCompatActivity implements View.OnClickListener {
    Button CreateTicket;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_ticket);
        CreateTicket = findViewById(R.id.CreateTicket);
        CreateTicket.setOnClickListener(this);
        findViewById(R.id.btnBack).setOnClickListener(view->{
            this.finish();
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.CreateTicket:
                startActivity(new Intent(getApplicationContext(), DashboardOfficerActivity.class));
                break;
            default:
                break;
        }
    }
}