package com.app.e_ticketng.activities.Users;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.app.e_ticketng.R;

public class MyPreviousTicketsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_previous_tickets);
        findViewById(R.id.btnBack).setOnClickListener(view->{
            this.finish();
        });
    }
}
