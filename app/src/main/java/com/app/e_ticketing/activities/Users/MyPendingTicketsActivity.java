package com.app.e_ticketing.activities.Users;

import static com.app.e_ticketing.helpers.common_helper.getUserTicketList;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.e_ticketing.R;
import com.app.e_ticketing.adapters.UserTicketsAdapter;
import com.app.e_ticketing.models.Ticket;

import java.util.ArrayList;
import java.util.List;

public class MyPendingTicketsActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    UserTicketsAdapter adapter;
    List<Ticket> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_pening_tickets);
        findViewById(R.id.btnBack).setOnClickListener(view->{
            startActivity(new Intent(getApplicationContext(), DashboardUserActivity.class));
        });
        setUpRecyclerView();

    }
    public void setUpRecyclerView(){
        list = getUserTicketList("1");
        recyclerView = findViewById(R.id.ListLabour);
        adapter = new UserTicketsAdapter(list, getApplicationContext(),"new");
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}
