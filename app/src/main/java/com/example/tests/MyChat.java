package com.example.tests;

import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.ArrayListMultimap;
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.ListMultimap;

import java.util.LinkedHashMap;
import java.util.Map;

public class MyChat {

    ListMultimap<String, String> texts = ArrayListMultimap.create();

    String user1, user2;

    MyChat(String euser1, String euser2)
    {
        user1=euser1;
        user2=euser2;
        //texts.put("key1", "value1");

    }

    public void addText(String user, String text)
    {
        texts.put(user, text);
    }


}
