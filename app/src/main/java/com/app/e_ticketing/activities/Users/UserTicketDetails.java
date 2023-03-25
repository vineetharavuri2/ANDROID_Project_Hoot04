package com.app.e_ticketing.activities.Users;

import static com.app.e_ticketing.helpers.common_helper.getPrevUserTicketDetails;
import static com.app.e_ticketing.helpers.common_helper.getPrevUserTicketList;
import static com.app.e_ticketing.helpers.common_helper.getTicketDetails;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.app.e_ticketing.R;
import com.app.e_ticketing.activities.Officers.CheckTicketStatus;
import com.app.e_ticketing.activities.Officers.DashboardOfficerActivity;
import com.app.e_ticketing.activities.Officers.MyTicketsOfficerActivity;
import com.app.e_ticketing.helpers.BaseActivity;
import com.app.e_ticketing.models.Ticket;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class UserTicketDetails extends BaseActivity {
    Ticket ticket = new Ticket();
    TextView tvTicketName,tvUserName,tvLocation,tvDatetime,tvAmount,tvInfo,tvVehicleNo;
    Button btnPay;
    String return_activity,activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_ticket_details);
        findViewById(R.id.btnBack).setOnClickListener(view->{
            startActivity(new Intent(getApplicationContext(), DashboardUserActivity.class));
        });
        tvTicketName = findViewById(R.id.tvTicketName);
        tvUserName = findViewById(R.id.tvUserName);
        tvLocation = findViewById(R.id.tvLocation);
        tvDatetime = findViewById(R.id.tvDatetime);
        tvInfo = findViewById(R.id.tvInfo);
        tvAmount = findViewById(R.id.tvAmount);
        tvVehicleNo = findViewById(R.id.tvVehicleNo);
        btnPay = findViewById(R.id.btnPay);

        Intent intent = getIntent();

        if (null != intent) {
            //Null Checking
            String strId= intent.getStringExtra("ticket_id");
            return_activity = intent.getStringExtra("return_activity");
            activity = intent.getStringExtra("activity");

            if(!strId.isEmpty()) {
                if(activity.equals("history"))
                    ticket = getPrevUserTicketDetails(strId);
                else
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
    }

    private void showDetails(Ticket ticket){
        tvTicketName.setText(ticket.getTicket_name());
        tvUserName.setText(ticket.getUser_name());
        tvLocation.setText(ticket.getLocation());
        tvDatetime.setText(ticket.getDatetime());
        tvInfo.setText(ticket.getDescription());
        tvVehicleNo.setText(ticket.getVehicle_reg());
        tvAmount.setText(ticket.getAmount()+"");
        if(ticket.isPayment_status()) {
            btnPay.setText("PAID");
//            Toast.makeText(this, "Ticket closed!", Toast.LENGTH_SHORT).show();
        }
        else{
            btnPay.setOnClickListener(view->{
                //show bottom nav for payment
                paymentWindow(ticket);
            });
        }
    }
    private  void paymentWindow(Ticket ticket){
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(R.layout.activity_add_payment);
        bottomSheetDialog.show();
        TextView tvAmount_pay;
        Button btnProceed;
        tvAmount_pay = bottomSheetDialog.findViewById(R.id.tvAmount_pay);
        btnProceed = bottomSheetDialog.findViewById(R.id.btnProceed);
        tvAmount_pay.setText(""+ticket.getAmount());
        btnProceed.setOnClickListener(view->{
            Toast.makeText(this, "Payment processed!", Toast.LENGTH_SHORT).show();
            hideProgressDialog();
            bottomSheetDialog.dismiss();
        });
    }
}
