package com.example.tests;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ChatRepository {

    private static ChatRepository instance;
    List<MyChat> allChats=new ArrayList<>();

    String secondUser;

    public static ChatRepository getInstance() {
        if (instance == null) {
            instance = new ChatRepository();
        }
        return instance;
    }
    public void newChat(MyChat chat)
    {
        allChats.add(chat);
    }

    public Map<String, String> getChat(String user1, String user2) {
        for (MyChat chat : allChats) {
            if ((chat.user1.equals(user1) || chat.user1.equals(user2)) && (chat.user2.equals(user1) || chat.user2.equals(user2))) {
                return chat.texts;
            }
        }
        return null;
    }

    public void addText(String user1, String user2, String text) {
        for (MyChat chat : allChats) {
            if ((chat.user1.equals(user1) || chat.user1.equals(user2)) && (chat.user2.equals(user1) || chat.user2.equals(user2))) {
                chat.addText(user1, text);
            }
        }

    }


}