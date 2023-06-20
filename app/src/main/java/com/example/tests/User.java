package com.example.tests;


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

    User(String ename, String eemail,String epassword)
    {
        name=ename;
        email=eemail;
        password=epassword;
    }
    User()
    {}
}
