package com.app.e_ticketing.activities.Officers;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.app.e_ticketing.R;

public class CheckTicketStatus extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_status);
        findViewById(R.id.btnBack).setOnClickListener(view->{
            this.finish();
        });
    }
}