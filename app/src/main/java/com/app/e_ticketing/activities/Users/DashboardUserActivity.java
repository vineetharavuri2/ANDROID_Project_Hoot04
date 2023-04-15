package com.app.e_ticketing.activities.Users;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import com.app.e_ticketing.R;
import com.app.e_ticketing.activities.LoginActivity;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class DashboardUserActivity extends AppCompatActivity {
    CardView cardMyTickets, myPrevTickets, eWallet, courtHearing;
    ImageView imgLogout;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dashboard);
        cardMyTickets = findViewById(R.id.cardMyTickets);
        myPrevTickets = findViewById(R.id.myPrevTickets);
        courtHearing = findViewById(R.id.courtHearing);
        eWallet = findViewById(R.id.eWallet);
        imgLogout=findViewById(R.id.imgLogout);
        FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance();
        imgLogout.setOnClickListener(v -> {
            mAuth.signOut();
            Toast.makeText(this, "Good bye!", Toast.LENGTH_SHORT).show();
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
//            Toast.makeText(this,"you have 350coins in your wallet!",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getApplicationContext(), UserWalletDetails.class));
        });
    }
}
