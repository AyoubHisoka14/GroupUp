package com.example.tests;

import java.util.HashMap;
import java.util.Map;

public class UserRepository {

    private static UserRepository instance; // Static instance variable
    private Map<String, User> ownersByEmail;

    UserRepository() {
        ownersByEmail = new HashMap<>();
    }

    public static UserRepository getInstance() {
        if (instance == null) {
            instance = new UserRepository();
        }
        return instance;
    }

    public void save(User user) {
        ownersByEmail.put(user.email, user);
    }

    public User find(String email) {
        for (Map.Entry<String, User> entry : ownersByEmail.entrySet()) {
            User user = entry.getValue();

            if (user.email.equals(email)) {
                return user; // Found the user with the matching email
            }
        }
        return null;
    }

    public User findByName(String name)
    {
        for (Map.Entry<String, User> entry : ownersByEmail.entrySet()) {
            User user = entry.getValue();

            if (user.name.equals(name)) {
                return user; // Found the user with the matching email
            }
        }
        return null;
    }

    public User getActiveUser() {
        for (Map.Entry<String, User> entry : ownersByEmail.entrySet()) {
            User user = entry.getValue();

            if (user.isActive) {
                return user; // Found the user with the matching email
            }
        }
        return null;
    }



}
