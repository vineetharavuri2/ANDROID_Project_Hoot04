package com.app.e_ticketing.activities.Officers;

import static android.content.ContentValues.TAG;
import static com.app.e_ticketing.helpers.common_helper.collection_tickets;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.e_ticketing.R;
import com.app.e_ticketing.adapters.TicketsAdapter;
import com.app.e_ticketing.helpers.BaseActivity;
import com.app.e_ticketing.models.Ticket;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MyTicketsOfficerActivity extends BaseActivity {
    RecyclerView recyclerView;
    TicketsAdapter adapter;
    List<Ticket> list = new ArrayList<>();
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_tickets);
        findViewById(R.id.btnBack).setOnClickListener(view->{
            this.finish();
        });
        firebaseDatabase = FirebaseDatabase.getInstance();
        mDatabase = firebaseDatabase.getReference(collection_tickets);
        setUpRecyclerView();
    }

    public void setUpRecyclerView(){
        recyclerView = findViewById(R.id.ListLabour);
        adapter = new TicketsAdapter(list, getApplicationContext(),"all");
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        getTickets();
    }

    private void  getTickets(){
        if(list.size() != 0) list.clear();
        showProgressDialog();
        mDatabase.addListenerForSingleValueEvent( new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot){
                if(dataSnapshot.getChildrenCount() != 0){
                    for (DataSnapshot ds : dataSnapshot.getChildren()){
                        try {
                            String id = ds.child("id").getValue(String.class);
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
                            list.add(new Ticket(id, ticket_name,"",user_name,datetime,description,location,
                                    amount, vehicle_reg_no,payment_status,status));
                            adapter.notifyDataSetChanged();
                        }catch(Exception e){
                            e.printStackTrace();
                            Log.e(TAG, "onDataChange: "+e.getMessage() );
                        }
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "List is empty!", Toast.LENGTH_SHORT).show();
                }
                hideProgressDialog();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}
