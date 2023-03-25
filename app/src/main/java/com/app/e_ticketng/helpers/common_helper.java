package com.app.e_ticketng.helpers;

public class common_helper {
    public static boolean getOfficerLogin(String email, String password){
        return email.equals("officer@gmail.com") && password.equals("officer123");
    }
    public static boolean getUserLogin(String email, String password){
        return email.equals("user@gmail.com") && password.equals("user123");
    }
}
