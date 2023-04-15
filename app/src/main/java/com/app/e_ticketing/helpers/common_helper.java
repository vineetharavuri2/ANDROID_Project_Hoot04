package com.app.e_ticketing.helpers;

import com.app.e_ticketing.models.CourtHearing;
import com.app.e_ticketing.models.Ticket;
import com.app.e_ticketing.models.User;

import java.util.ArrayList;
import java.util.List;

public class common_helper {
    public static String collection_users = "users";
    public static String collection_tickets = "tickets";
    public static boolean getOfficerLogin(String email, String password){
        return email.equals("officer@gmail.com") && password.equals("officer123");
    }
    public static boolean getUserLogin(String email, String password){
        return email.equals("user@gmail.com") && password.equals("user123");
    }

    public static List<Ticket> getTicketList(){
        List<Ticket> list = new ArrayList<>();
        list.add(new Ticket("12345",
                "Crossed Speed limit & Dangerous Driving",
                "1",
                "Lucas",
                "12-01-2023 15:30",
                "Male",
                "downtown",
                150.00,
                "BEP123",
                false,
                "Pending"
        ));
        list.add(new Ticket("67890",
                "Violation of Traffic Rules",
                "2",
                "Amelia",
                "25-02-2023 12:10",
                "Traffic Rules Violation",
                "maryvile",
                150.00,
                "CAT462",
                false,
                "Pending"
        ));

        return list;
    }

    public static List<Ticket> getUserTicketList(String userId){
        List<Ticket> list = getTicketList();
        List<Ticket> userList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getUser_id().equals(userId))
                userList.add(list.get(i));
        }
        return userList;
    }

    public static Ticket getTicketDetails(String id) {
        List<Ticket> list = getTicketList();
        Ticket ticket = new Ticket();
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getId().equals(id))
                ticket = list.get(i);
        }
        return ticket;
    }

    public static List<Ticket> getPrevTicketList(){
        List<Ticket> list = new ArrayList<>();
        list.add(new Ticket("12345",
                "Crossed Speed limit & Dangerous Driving",
                "1",
                "Lucas",
                "12-01-2023 15:30",
                "Male",
                "downtown",
                150.00,
                "BEP123",
                true,
                "Completed"
        ));
        list.add(new Ticket("67890",
                "Violation of Traffic Rules",
                "2",
                "Amelia",
                "25-02-2023 12:10",
                "Traffic Rules Violation",
                "maryvile",
                150.00,
                "CAT462",
                true,
                "Completed"
        ));

        return list;
    }

    public static List<Ticket> getPrevUserTicketList(String userId){
        List<Ticket> list = getPrevTicketList();
        List<Ticket> userList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getUser_id().equals(userId))
                userList.add(list.get(i));
        }
        return userList;
    }

    public static Ticket getPrevUserTicketDetails(String id) {
        List<Ticket> list = getPrevTicketList();
        Ticket ticket = new Ticket();
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getId().equals(id))
                ticket = list.get(i);
        }
        return ticket;
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
