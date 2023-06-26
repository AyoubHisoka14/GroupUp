package com.example.tests;

import java.util.LinkedHashMap;
import java.util.Map;

public class MyChat {

    Map<String, String> texts = new LinkedHashMap<>();

    String user1, user2;

    MyChat(String euser1, String euser2)
    {
        user1=euser1;
        user2=euser2;
    }

    public void addText(String user, String text)
    {
        texts.put(user, text);
    }


}
