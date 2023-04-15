package com.app.e_ticketing.activities.Officers;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.app.e_ticketing.R;
import com.app.e_ticketing.helpers.BaseActivity;

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

    private void searchTicket(String ticket_id){
            Intent intent = new Intent(this, TicketDetails.class);
            Bundle extras = new Bundle();
            extras.putString("ticket_id",ticket_id);
            extras.putString("return_activity","search");
            intent.putExtras(extras);
            startActivity(intent);
    }
}