package com.app.e_ticketing.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.app.e_ticketing.R;
import com.app.e_ticketing.activities.Officers.DashboardOfficerActivity;
import com.app.e_ticketing.activities.Officers.TicketDetails;
import com.app.e_ticketing.models.Ticket;

import java.util.Collections;
import java.util.List;

public class TicketsAdapter extends RecyclerView.Adapter<TicketsAdapter.MyTicket> {
    List<Ticket> list = Collections.emptyList();
    Context mContext;
    String type;
    public TicketsAdapter(List<Ticket> list, Context context, String type){
        this.list = list;
        this.mContext = context;
        this.type = type;
    }

    @NonNull
    @Override
    public MyTicket onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_ticket_item, parent, false);
        return new MyTicket(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyTicket holder, int position) {
        Ticket ticket = list.get(position);
        holder.tvUserName.setText(ticket.getUser_name());
        holder.tvTicketName.setText(ticket.getTicket_name());
        holder.tvTicketIssueDate.setText(ticket.getDatetime());
        holder.tvStatus.setText(ticket.getStatus());
        holder.ticketItemCard.setOnClickListener(view->{
            Intent intent = new Intent(view.getContext(), TicketDetails.class);
            Bundle extras = new Bundle();
            extras.putString("ticket_id",ticket.getId());
            extras.putString("return_activity","mytickets");
            intent.putExtras(extras);
            view.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    static class MyTicket extends RecyclerView.ViewHolder{
        // Here we hold the MyDoctorItems
        TextView tvUserName,tvTicketIssueDate,tvTicketName,tvStatus;
        CardView ticketItemCard;
        ImageView ticket_item_image;
        public MyTicket(@NonNull View itemView) {
            super(itemView);
            tvUserName = itemView.findViewById(R.id.tvUserName);
            tvTicketIssueDate = itemView.findViewById(R.id.tvTicketIssueDate);
            tvTicketName = itemView.findViewById(R.id.tvTicketName);
            ticketItemCard = itemView.findViewById(R.id.ticketItemCard);
            tvStatus = itemView.findViewById(R.id.tvStatus);
            ticket_item_image = itemView.findViewById(R.id.ticket_item_image);
        }
    }
}
