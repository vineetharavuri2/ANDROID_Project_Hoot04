package com.app.e_ticketing.activities.Officers;

import static com.app.e_ticketing.helpers.common_helper.getTicketList;
import static com.app.e_ticketing.helpers.common_helper.getUsersList;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.e_ticketing.R;
import com.app.e_ticketing.adapters.TicketsAdapter;
import com.app.e_ticketing.models.Ticket;
import com.app.e_ticketing.models.User;

import java.util.ArrayList;
import java.util.List;

public class MyTicketsOfficerActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    TicketsAdapter adapter;
    List<Ticket> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_tickets);
        findViewById(R.id.btnBack).setOnClickListener(view->{
            this.finish();
        });
        setUpRecyclerView();
    }

    public void setUpRecyclerView(){
        list = getTicketList();
        recyclerView = findViewById(R.id.ListLabour);
        adapter = new TicketsAdapter(list, getApplicationContext(),"all");
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
