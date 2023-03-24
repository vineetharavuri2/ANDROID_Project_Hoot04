package com.app.e_ticketing.activities.Officers;

import static com.app.e_ticketing.helpers.common_helper.getTicketDetails;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.app.e_ticketing.R;
import com.app.e_ticketing.helpers.BaseActivity;
import com.app.e_ticketing.models.Ticket;

public class CheckTicketStatus extends BaseActivity {
    EditText etSearch;
    Button btnSearch;
    TextView tvError;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_status);
        findViewById(R.id.btnBack).setOnClickListener(view->{
            startActivity(new Intent(this,DashboardOfficerActivity.class));
        });
        etSearch = findViewById(R.id.etSearch);
        btnSearch = findViewById(R.id.btnSearch);
        tvError = findViewById(R.id.tvError);
        btnSearch.setOnClickListener(view->{
            if(etSearch.getText().toString().isEmpty()) etSearch.setError("required");
            else{
                searchTicket(etSearch.getText().toString());
            }
        });
    }


    }
}