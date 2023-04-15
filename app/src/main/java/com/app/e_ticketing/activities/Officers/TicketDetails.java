package com.app.e_ticketing.activities.Officers;

import static com.app.e_ticketing.helpers.common_helper.collection_tickets;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.app.e_ticketing.R;
import com.app.e_ticketing.helpers.BaseActivity;
import com.app.e_ticketing.models.Ticket;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class TicketDetails extends BaseActivity {
    Ticket ticket = new Ticket();
    TextView tvTicketName,tvUserName,tvLocation,tvDatetime,tvAmount,tvInfo,tvVehicleNo,tvCloseMsg;
    Button btnClose,btnEdit;
    String return_activity;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
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
        tvCloseMsg = findViewById(R.id.tvCloseMsg);
        tvVehicleNo = findViewById(R.id.tvVehicleNo);
        btnEdit = findViewById(R.id.btnEdit);
        btnClose = findViewById(R.id.btnClose);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference(collection_tickets);

        Intent intent = getIntent();

        if (null != intent) {
            //Null Checking
            String strId= intent.getStringExtra("ticket_id");
            return_activity= intent.getStringExtra("return_activity");
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
                            startActivity(new Intent(getApplicationContext(), CheckTicketStatus.class));
                        else
                            startActivity(new Intent(getApplicationContext(), MyTicketsOfficerActivity.class));
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

        if(ticket.getStatus().equals("inactive")){
            btnClose.setVisibility(View.GONE);
            tvCloseMsg.setVisibility(View.VISIBLE);
            tvCloseMsg.setText("Closed!");
        }
        btnClose.setOnClickListener(view -> {
            ticket.setStatus("inactive");
            CloseTicket(ticket);
        });
    }

    private void CloseTicket(Ticket ticket){
        FirebaseDatabase.getInstance().getReference().child(collection_tickets).child(ticket.getId()).setValue(ticket)
                .addOnSuccessListener(command -> {
                    Toast.makeText(this, "Ticket has been closed!", Toast.LENGTH_SHORT).show();
                    if(return_activity.equals("search"))
                        startActivity(new Intent(getApplicationContext(), CheckTicketStatus.class));
                    else
                        startActivity(new Intent(getApplicationContext(), MyTicketsOfficerActivity.class));
                })
                .addOnFailureListener(command ->{
                    Toast.makeText(this, "failed!", Toast.LENGTH_SHORT).show();
                    if(return_activity.equals("search"))
                        startActivity(new Intent(getApplicationContext(), CheckTicketStatus.class));
                    else
                        startActivity(new Intent(getApplicationContext(), MyTicketsOfficerActivity.class));
                });
    }

}
