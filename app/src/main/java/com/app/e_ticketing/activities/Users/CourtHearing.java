package com.app.e_ticketing.activities.Users;

import static com.app.e_ticketing.helpers.common_helper.getCourtHearings;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.e_ticketing.R;
import com.app.e_ticketing.adapters.CourtHearingsAdapter;

import java.util.ArrayList;
import java.util.List;

public class CourtHearing extends AppCompatActivity {
    RecyclerView recyclerView;
    CourtHearingsAdapter adapter;
    List<com.app.e_ticketing.models.CourtHearing> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_court_hearing);
        findViewById(R.id.btnBack).setOnClickListener(view->{
            this.finish();
        });
        setUpRecyclerView();
    }


    public void setUpRecyclerView(){
        list = getCourtHearings();
        recyclerView = findViewById(R.id.ListLabour);
        adapter = new CourtHearingsAdapter(list, getApplicationContext(),"new");
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
