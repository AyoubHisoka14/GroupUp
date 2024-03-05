package com.example.tests;

import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.ArrayListMultimap;
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.ListMultimap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MyChat {

    //ListMultimap<String, String> texts = ArrayListMultimap.create();

    Map<String, List<String>> texts = new HashMap<>();
    public List<String> orderList = new ArrayList<>();

    String user1, user2;

    int newTexts=0;

    MyChat(String euser1, String euser2)
    {
        user1=euser1;
        user2=euser2;
        //texts.put("key1", "value1");

    }
    MyChat(){}

    public void addText(String user, String text)
    {
        List<String> values = texts.getOrDefault(user, new ArrayList<>());
        values.add(text);

        if (!texts.containsKey(user)) {
            orderList.add(user);
        }

        texts.put(user, values);

        //texts.put(user, text);
    }


}
