package com.app.e_ticketng.activities.Users;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.app.e_ticketng.R;
import com.app.e_ticketng.activities.LoginActivity;
import com.app.e_ticketng.activities.Officers.CheckTicketStatus;
import com.app.e_ticketng.activities.Officers.CreateTicketActivity;
import com.app.e_ticketng.activities.Officers.MyTicketsOfficerActivity;

public class DashboardUserActivity extends AppCompatActivity {
    CardView cardMyTickets, myPrevTickets, eWallet, courtHearing;
    ImageView imgLogout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dashboard);
        cardMyTickets = findViewById(R.id.cardMyTickets);
        myPrevTickets = findViewById(R.id.myPrevTickets);
        courtHearing = findViewById(R.id.courtHearing);
        eWallet = findViewById(R.id.eWallet);
        imgLogout=findViewById(R.id.imgLogout);
        imgLogout.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });
        cardMyTickets.setOnClickListener(view->{
            startActivity(new Intent(getApplicationContext(), MyPendingTicketsActivity.class));
        });
        myPrevTickets.setOnClickListener(view->{
            startActivity(new Intent(getApplicationContext(), MyPreviousTicketsActivity.class));
        });
        courtHearing.setOnClickListener(view->{
            startActivity(new Intent(getApplicationContext(), CourtHearing.class));
        });
        eWallet.setOnClickListener(view->{
            Toast.makeText(this,"you have 350coins in your wallet!",Toast.LENGTH_SHORT).show();
        });
    }
}
