package com.app.e_ticketng.activities.Officers;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.app.e_ticketng.R;

public class AttentionTickets extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attention_tickets);
        findViewById(R.id.btnBack).setOnClickListener(view->{
            this.finish();
        });
    }
}