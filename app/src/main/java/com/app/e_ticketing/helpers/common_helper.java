package com.app.e_ticketing.helpers;

import com.app.e_ticketing.activities.Officers.TicketDetails;
import com.app.e_ticketing.models.Ticket;
import com.app.e_ticketing.models.User;

import java.util.ArrayList;
import java.util.List;

public class common_helper {
    public static String collection_users = "users";
    public static boolean getOfficerLogin(String email, String password){
        return email.equals("officer@gmail.com") && password.equals("officer123");
    }
    public static boolean getUserLogin(String email, String password){
        return email.equals("user@gmail.com") && password.equals("user123");
    }

    public static List<User> getUsersList(){
        List<User> list = new ArrayList<>();
        list.add(new User("1",
                "Lucas@gmail.com",
                "Lucas",
                "+15479565865",
                "gateway, Maryville, Canada, US",
                "Male",
                "12-08-1990",
                true
        ));
        list.add(new User("2",
                "Amelia@gmail.com",
                "Amelia",
                "+15479565865",
                "gateway, Maryville, Canada, US",
                "Female",
                "14-04-1993",
                true
        ));
        list.add(new User("3",
                "Oliver@gmail.com",
                "Oliver",
                "+15479565865",
                "gateway, Maryville, Canada, US",
                "Male",
                "1-05-1996",
                true
        ));
        return list;
    }

    public static List<Ticket> getTicketList(){
        List<Ticket> list = new ArrayList<>();
        list.add(new Ticket("12345",
                "Crossed Speed limit & Dangerous Driving",
                "Lucas",
                "Lucas",
                "12-01-2023 15:30",
                "Male",
                "downtown",
                150.00,
                "BEP123",
                "Pending"
        ));
        list.add(new Ticket("67890",
                "Violation of Traffic Rules",
                "Amelia",
                "Amelia",
                "25-02-2023 12:10",
                "Traffic Rules Violation",
                "maryvile",
                150.00,
                "CAT462",
                "Pending"
        ));

        return list;
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
}
