package com.app.e_ticketing.activities.Users;

import static android.content.ContentValues.TAG;
import static com.app.e_ticketing.helpers.common_helper.collection_tickets;
import static com.app.e_ticketing.helpers.common_helper.collection_users;
import static com.app.e_ticketing.helpers.common_helper.getCourtHearings;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.e_ticketing.R;
import com.app.e_ticketing.activities.LoginActivity;
import com.app.e_ticketing.activities.Officers.AttentionTickets;
import com.app.e_ticketing.adapters.CourtHearingsAdapter;
import com.app.e_ticketing.adapters.TicketsAdapter;
import com.app.e_ticketing.helpers.BaseActivity;
import com.app.e_ticketing.models.Ticket;
import com.app.e_ticketing.models.User;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class CourtHearing extends BaseActivity {
    RecyclerView recyclerView;
    CourtHearingsAdapter adapter;
    List<com.app.e_ticketing.models.CourtHearing> list = new ArrayList<>();
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference mDatabase;
    TextView tvMsg;
    private FirebaseAuth mAuth;
    private User user = new User();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_court_hearing);
        findViewById(R.id.btnBack).setOnClickListener(view->{
            this.finish();
        });
        FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        mDatabase = firebaseDatabase.getReference(collection_users);
        mDatabase.child(Objects.requireNonNull(mAuth.getUid()))
                .get().addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        try {
                            user =  task.getResult().getValue(User.class);
                            assert user != null;
                        } catch (Exception ex) {
                            ex.getMessage();
                            signout();
                        }
                    }
                });
        setUpRecyclerView();
    }
    public void setUpRecyclerView(){
        recyclerView = findViewById(R.id.ListLabour);
        tvMsg = findViewById(R.id.tvMsg);
        adapter = new CourtHearingsAdapter(list, getApplicationContext(),"new");
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        getTickets();
    }

    private void getTickets(){
        if(list.size() != 0) list.clear();
        showProgressDialog();
        firebaseDatabase = FirebaseDatabase.getInstance();
        mDatabase = firebaseDatabase.getReference(collection_tickets);
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
                            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                            Date strDate = null;
                            try {
                                strDate = sdf.parse(datetime);
                                Date dueDate = addDay(strDate,30);
                                long time_difference = new Date().getTime() - dueDate.getTime();
                                long days_difference = (time_difference / (1000*60*60*24)) % 365;
                                if(days_difference > 0 && !payment_status &&
                                        user.getVehicleRegNo().equals(vehicle_reg_no)){
                                    list.add(new com.app.e_ticketing.models.CourtHearing(id, ticket_name,id, datetime,description,status));
                                }
                            }catch (Exception e) {
                                e.printStackTrace();
                            }
                            adapter.notifyDataSetChanged();
                        }catch(Exception e){
                            e.printStackTrace();
                            Log.e(TAG, "onDataChange: "+e.getMessage() );
                        }
                    }
                    if(list.size() == 0 ) {
                        tvMsg.setVisibility(View.VISIBLE);
                        recyclerView.setVisibility(View.GONE);
                        Toast.makeText(getApplicationContext(), "no tickets found!", Toast.LENGTH_SHORT).show();
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

    public static Date addDay(Date date, int i) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_YEAR, i);
        return cal.getTime();
    }

    private void signout(){
        mAuth.signOut();
        Toast.makeText(this, "Good bye!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
