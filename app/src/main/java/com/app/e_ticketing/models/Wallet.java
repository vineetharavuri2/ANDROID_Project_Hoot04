package com.app.e_ticketing.models;

import androidx.annotation.NonNull;

public class Wallet {
    private String id;
    private String uid;
    private String uname;
    private double amount;
    private boolean status;

    public Wallet(){}
    public Wallet(String id, String uid, String uname, double amount,  boolean status) {
        this.id = id;
        this.uid = uid;
        this.uname = uname;
        this.amount = amount;
        this.status = status;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @NonNull
    @Override
    public String toString() {
        return super.toString();
    }
}
