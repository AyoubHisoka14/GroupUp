package com.example.tests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserRepository {

    private static UserRepository instance; // Static instance variable
    private List<User> allUsers=new ArrayList<>();



    public static UserRepository getInstance() {
        if (instance == null) {
            instance = new UserRepository();
        }
        return instance;
    }

    public void save(User user) {
        allUsers.add(user);
    }

    public User find(String email) {
        for (User user : allUsers) {

            if (user.email.equals(email)) {
                return user; // Found the user with the matching email
            }
        }
        return null;
    }

    public User findByName(String name)
    {
        for (User user : allUsers) {
            if (user.name.equals(name)) {
                return user;
            }
        }
        return null;
    }

    public User getActiveUser() {
        for (User user : allUsers) {
            if (user.isActive) {
                return user;
            }
        }
        return null;
    }



}
