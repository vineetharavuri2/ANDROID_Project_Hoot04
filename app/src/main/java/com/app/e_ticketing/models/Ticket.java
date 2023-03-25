package com.app.e_ticketing.models;

import androidx.annotation.NonNull;

public class Ticket {




    private String id;


    private String user_id;



    private String ticket_name;
    private String user_name;
    private String datetime;
    private String description;
    private String location;
    private Double amount;
    private String vehicle_reg;
    private boolean payment_status;
    private String status;

    public Ticket(){}
    public Ticket(String id, String ticket_name,String user_id, String user_name, String datetime, String description,
                  String location, Double amount, String vehicle_reg, boolean payment_status, String status) {
        this.id = id;
        this.ticket_name = ticket_name;
        this.user_id = user_id;
        this.user_name = user_name;
        this.description = description;
        this.datetime = datetime;
        this.vehicle_reg = vehicle_reg;
        this.location = location;
        this.amount = amount;
        this.payment_status = payment_status;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getTicket_name() {
        return ticket_name;
    }

    public void setTicket_name(String ticket_name) {
        this.ticket_name = ticket_name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean isPayment_status() {
        return payment_status;
    }

    public void setPayment_status(boolean payment_status) {
        this.payment_status = payment_status;
    }

    public String getVehicle_reg() {
        return vehicle_reg;
    }




    public void setVehicle_reg(String vehicle_reg) {
        this.vehicle_reg = vehicle_reg;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }






    @NonNull
    @Override
    public String toString() {
        return super.toString();
    }
}
