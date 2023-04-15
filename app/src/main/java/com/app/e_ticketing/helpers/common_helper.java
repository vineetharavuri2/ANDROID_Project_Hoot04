package com.app.e_ticketing.helpers;

import com.app.e_ticketing.models.CourtHearing;
import com.app.e_ticketing.models.Ticket;
import com.app.e_ticketing.models.User;

import java.util.ArrayList;
import java.util.List;

public class common_helper {
    public static String collection_users = "users";
    public static String collection_tickets = "tickets";
    public static String collection_wallets = "wallets";
    public static boolean getOfficerLogin(String email, String password){
        return email.equals("officer@gmail.com") && password.equals("officer123");
    }
    public static boolean getUserLogin(String email, String password){
        return email.equals("user@gmail.com") && password.equals("user123");
    }

    public static List<CourtHearing> getCourtHearings()
    {
        List<CourtHearing> list = new ArrayList<>();
        list.add(new CourtHearing(
                "1",
                "Over speed",
                "12345",
                "05-3-2023",
                "Crossed Speed limit & Dangerous Driving",
                "Pending"));
        return list;
    }
//    public static getCourtHearings(String userId){
//        CourtHearing courtHearing = new CourtHearing(id, String ticket_id, String datetime, String description,
//                String status) {
//            this.id = id;
//            this.description = description;
//            this.datetime = datetime;
//            this.ticket_id = ticket_id;
//            this.status = status;
//        }
//    }

}
