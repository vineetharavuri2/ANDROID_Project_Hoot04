package com.app.e_ticketing.activities.Officers;

import static com.app.e_ticketing.helpers.common_helper.getTicketDetails;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.app.e_ticketing.R;
import com.app.e_ticketing.helpers.BaseActivity;
import com.app.e_ticketing.models.Ticket;

public class TicketDetails extends BaseActivity {
    Ticket ticket = new Ticket();
    TextView tvTicketName,tvUserName,tvLocation,tvDatetime,tvAmount,tvInfo,tvVehicleNo;
    Button btnClose,btnEdit;
    String return_activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_details);
        findViewById(R.id.btnBack).setOnClickListener(view->{
            startActivity(new Intent(getApplicationContext(), DashboardOfficerActivity.class));
        });
        tvTicketName = findViewById(R.id.tvTicketName);
        tvUserName = findViewById(R.id.tvUserName);
        tvLocation = findViewById(R.id.tvLocation);
        tvDatetime = findViewById(R.id.tvDatetime);
        tvInfo = findViewById(R.id.tvInfo);
        tvAmount = findViewById(R.id.tvAmount);
        tvVehicleNo = findViewById(R.id.tvVehicleNo);
        btnEdit = findViewById(R.id.btnEdit);
        btnClose = findViewById(R.id.btnClose);

        Intent intent = getIntent();

        if (null != intent) {
            //Null Checking
            String strId= intent.getStringExtra("ticket_id");
            return_activity= intent.getStringExtra("return_activity");
            if(!strId.isEmpty()) {
                ticket = getTicketDetails(strId);
                try {
                    if (ticket.getTicket_name().isEmpty()) {
                        Toast.makeText(this, "invalid!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), CheckTicketStatus.class));
                    }
                    showDetails(ticket);
                }catch (Exception ex){
                    ex.printStackTrace();
                    Toast.makeText(this, "invalid!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), CheckTicketStatus.class));
                }
            }
            else {
                Toast.makeText(this, "invalid!", Toast.LENGTH_SHORT).show();
                if(return_activity.equals("search"))
                    startActivity(new Intent(getApplicationContext(), CheckTicketStatus.class));
                else
                    startActivity(new Intent(getApplicationContext(), MyTicketsOfficerActivity.class));
            }
        }

        btnClose.setOnClickListener(view -> {
            Toast.makeText(this, "This ticket is closed!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getApplicationContext(), MyTicketsOfficerActivity.class));
        });
    }

    private void showDetails(Ticket ticket){
        tvTicketName.setText(ticket.getTicket_name());
        tvUserName.setText(ticket.getUser_name());
        tvLocation.setText(ticket.getLocation());
        tvDatetime.setText(ticket.getDatetime());
        tvInfo.setText(ticket.getDescription());
        tvVehicleNo.setText(ticket.getVehicle_reg());
        tvAmount.setText(ticket.getAmount()+"");
    }
}
