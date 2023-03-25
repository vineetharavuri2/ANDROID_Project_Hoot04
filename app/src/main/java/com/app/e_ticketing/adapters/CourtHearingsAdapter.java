package com.app.e_ticketing.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.app.e_ticketing.R;
import com.app.e_ticketing.models.CourtHearing;
import com.app.e_ticketing.activities.Users.UserTicketDetails;

import java.util.Collections;
import java.util.List;

public class CourtHearingsAdapter extends RecyclerView.Adapter<CourtHearingsAdapter.MyTicket> {
    List<CourtHearing> list = Collections.emptyList();
    Context mContext;
    String type;
    public CourtHearingsAdapter(List<CourtHearing> list, Context context, String type){
        this.list = list;
        this.mContext = context;
        this.type = type;
    }
    @NonNull
    @Override
    public MyTicket onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_court_hearing_item, parent, false);
        return new MyTicket(v);
    }
    @Override
    public void onBindViewHolder(@NonNull MyTicket holder, int position) {
        CourtHearing ticket = list.get(position);
        holder.courtSchedule.setText(ticket.getDatetime());
        holder.caseName.setText(ticket.getCase_name());
        holder.ticketCategory.setText(ticket.getDescription());
        holder.courtSchedule.setText(ticket.getDatetime());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    static class MyTicket extends RecyclerView.ViewHolder{
        // Here we hold the MyDoctorItems
        TextView courtSchedule,caseName,ticketCategory;
        CardView courtItemCard;
        public MyTicket(@NonNull View itemView) {
            super(itemView);
            courtSchedule = itemView.findViewById(R.id.courtSchedule);
            caseName = itemView.findViewById(R.id.caseName);
            ticketCategory = itemView.findViewById(R.id.ticketCategory);
            courtItemCard = itemView.findViewById(R.id.courtItemCard);
        }
    }
}
