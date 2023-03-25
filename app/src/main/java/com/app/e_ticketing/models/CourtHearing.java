package com.app.e_ticketing.models;

import androidx.annotation.NonNull;

public class CourtHearing {
    private String id;
    private String ticket_id;




    private String case_name;
    private String datetime;
    private String description;
    private String status;

    public CourtHearing(){}
    public CourtHearing(String id, String case_name, String ticket_id, String datetime, String description,
                         String status) {
        this.id = id;
        this.description = description;
        this.case_name = case_name;
        this.datetime = datetime;
        this.ticket_id = ticket_id;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTicket_id() {
        return ticket_id;
    }

    public void setTicket_id(String ticket_id) {
        this.ticket_id = ticket_id;
    }

    public String getCase_name() {
        return case_name;
    }

    public void setCase_name(String case_name) {
        this.case_name = case_name;
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
