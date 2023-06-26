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

    List<String> myRequests=new ArrayList<>();
    List<String> sentRequests=new ArrayList<>();
    List<String> myPartners=new ArrayList<>();
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
        myRequests.add(user.email);
    }

    public void setSentRequests(User user)
    {
        sentRequests.add(user.email);
    }

    public Boolean checkSentRequest(Request request)
    {
        for(String user : sentRequests)
        {
            if(user.equals(request.user.email))
            {
                return false;
            }
        }
        return true;
    }

    public void newPartner(User user)
    {
        for(String user1 : myRequests)
        {
            if(user.email.equals(user1))
            {
                myRequests.remove(user1);
                break;
            }
        }
        newNotification.add("New Partner: "+user.name);
        myPartners.add(user.email);
    }

    public void deleteRequest(User user)
    {
        for(String user1 : myRequests)
        {
            if(user1.equals(user.email))
            {
                myRequests.remove(user1);
                break;
            }
        }
    }

    public Boolean checkPartner(Request request)
    {
        for(String user : myPartners)
        {
            if(user.equals(request.user.email))
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
