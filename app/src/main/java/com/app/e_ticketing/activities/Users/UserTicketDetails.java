package com.app.e_ticketing.activities.Users;

import static com.app.e_ticketing.helpers.common_helper.collection_tickets;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.app.e_ticketing.R;
import com.app.e_ticketing.activities.Officers.CheckTicketStatus;
import com.app.e_ticketing.activities.Officers.MyTicketsOfficerActivity;
import com.app.e_ticketing.helpers.BaseActivity;
import com.app.e_ticketing.models.Ticket;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserTicketDetails extends BaseActivity {
    Ticket ticket = new Ticket();
    TextView tvTicketName,tvUserName,tvLocation,tvDatetime,tvAmount,tvInfo,tvVehicleNo;
    Button btnPay;
    String return_activity,activity;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
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

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference(collection_tickets);

        Intent intent = getIntent();

        if (null != intent) {
            //Null Checking
            String strId= intent.getStringExtra("ticket_id");
            return_activity = intent.getStringExtra("return_activity");
            activity = intent.getStringExtra("activity");

            if(!strId.isEmpty()) {
                getTicketDetails(strId);
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

    boolean check= false;
    private void getTicketDetails(String strId)
    {
        showProgressDialog();
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getChildrenCount() != 0) {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        try {
                            String id = ds.child("id").getValue(String.class);
                            if (strId.equals(id)) {
                                check= true;
                                String user_id = ds.child("user_id").getValue(String.class);
                                String user_name = ds.child("user_name").getValue(String.class);
                                String ticket_name = ds.child("ticket_name").getValue(String.class);
                                String vehicle_reg_no = ds.child("vehicle_reg").getValue(String.class);
                                String datetime = ds.child("datetime").getValue(String.class);
                                String location = ds.child("location").getValue(String.class);
                                String description = ds.child("description").getValue(String.class);
                                String status = ds.child("status").getValue(String.class);
                                double amount = ds.child("amount").getValue(double.class);
                                Boolean payment_status = ds.child("payment_status").getValue(boolean.class);
                                Ticket ticket = new Ticket(id, ticket_name,"",user_name,datetime,description,location,
                                        amount, vehicle_reg_no,payment_status,status);
                                showDetails(ticket);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    if(!check){
                        Toast.makeText(getApplicationContext(), "invalid details!", Toast.LENGTH_SHORT).show();
                        if(return_activity.equals("search"))
                            startActivity(new Intent(getApplicationContext(), MyPendingTicketsActivity.class));
                        else
                            startActivity(new Intent(getApplicationContext(), MyPendingTicketsActivity.class));
                    }
                }
                hideProgressDialog();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
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
        if(ticket.isPayment_status()) {
            btnPay.setText("PAID");
            //   Toast.makeText(this, "Ticket closed!", Toast.LENGTH_SHORT).show();
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
