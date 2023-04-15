package com.app.e_ticketng.activities.Officers;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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