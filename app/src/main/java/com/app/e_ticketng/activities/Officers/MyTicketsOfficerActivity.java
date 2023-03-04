package com.app.e_ticketng.activities.Officers;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.app.e_ticketng.R;
import com.app.e_ticketng.activities.LoginActivity;

public class MyTicketsOfficerActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_tickets);
        findViewById(R.id.btnBack).setOnClickListener(view->{
            this.finish();
        });
    }
}
