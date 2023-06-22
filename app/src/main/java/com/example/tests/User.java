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
    List<User> myPartners=new ArrayList<>();
    List<String> newNotification=new ArrayList<>();



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
        newNotification.add("New Request from "+user.name);
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

    public void newPartner(User user)
    {
        for(User user1 : myRequests)
        {
            if(user.email.equals(user1.email))
            {
                myRequests.remove(user1);
                break;
            }
        }
        newNotification.add("New Partner: "+user.name);
        myPartners.add(user);
    }

    public void deleteRequest(User user)
    {
        for(User user1 : myRequests)
        {
            if(user1.email.equals(user.email))
            {
                myRequests.remove(user1);
                break;
            }
        }
    }

    public Boolean checkPartner(Request request)
    {
        for(User user : myPartners)
        {
            if(user.email.equals(request.user.email))
            {
                return false;
            }
        }
        return true;
    }

    public void deleteNotifications()
    {
        newNotification.clear();
    }


}
