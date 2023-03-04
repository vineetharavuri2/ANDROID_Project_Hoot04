package com.app.e_ticketng.activities.Officers;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.app.e_ticketng.R;
import com.app.e_ticketng.activities.LoginActivity;

public class DashboardOfficerActivity extends AppCompatActivity {
    CardView cardMyTickets, createTickets, ticketsAttention, ticketStatus;
    ImageView imgLogout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_officer_dashboard);
        cardMyTickets = findViewById(R.id.cardMyTickets);
        createTickets = findViewById(R.id.createTickets);
        ticketStatus = findViewById(R.id.ticketStatus);
        ticketsAttention = findViewById(R.id.ticketsAttention);
        imgLogout=findViewById(R.id.imgLogout);
        imgLogout.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });
        cardMyTickets.setOnClickListener(view->{
            Toast.makeText(this,"My tickets!",Toast.LENGTH_SHORT).show();
        });
        createTickets.setOnClickListener(view->{
            Toast.makeText(this,"createTickets!",Toast.LENGTH_SHORT).show();
        });
        ticketStatus.setOnClickListener(view->{
            Toast.makeText(this,"ticketStatus!",Toast.LENGTH_SHORT).show();
        });
        ticketsAttention.setOnClickListener(view->{
            Toast.makeText(this,"ticketsAttention!",Toast.LENGTH_SHORT).show();
        });
    }
}
