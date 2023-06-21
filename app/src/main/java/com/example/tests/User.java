package com.example.tests;


import java.util.ArrayList;
import java.util.List;

public class User {
    String name;
    String email;
    String password;

    boolean isActive=true;

    String geburstdatum="";
    String wohnort="";
    String hochschule="";
    String studiengang="";
    String semester="";
    String berufsErfahrungen="";
    String lerntyp="";

    List<User> myRequests=new ArrayList<>();
    List<User> sentRequests=new ArrayList<>();

    User(String ename, String eemail,String epassword)
    {
        name=ename;
        email=eemail;
        password=epassword;
    }
    User()
    {}

    public void addRequest(User user)
    {

        myRequests.add(user);
    }

    public void setSentRequests(User user)
    {
        sentRequests.add(user);
    }

    public Boolean checkSentRequest(Request request)
    {
        for(User user : sentRequests)
        {
            if(user.email.equals(request.user.email))
            {
                return false;
            }
        }
        return true;
    }
}
