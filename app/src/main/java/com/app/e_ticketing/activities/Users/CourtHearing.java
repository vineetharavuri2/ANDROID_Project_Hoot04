package com.app.e_ticketing.activities.Users;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.app.e_ticketing.R;

public class CourtHearing extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_court_hearing);
        findViewById(R.id.btnBack).setOnClickListener(view->{
            this.finish();
        });
    }
}
