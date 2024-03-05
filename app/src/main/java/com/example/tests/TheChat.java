package com.example.tests;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tests.databinding.ThechatBinding;
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.ArrayListMultimap;
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.ListMultimap;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TheChat extends AppCompatActivity {

    private ThechatBinding binding;
    ChatRepository chatRepository=new ChatRepository();
    UserRepository userRepository=new UserRepository();
    LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ThechatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        chatRepository=ChatRepository.getInstance();
        userRepository=UserRepository.getInstance();

        Intent intent1 = new Intent(this, TheChat.class);
        Intent intent2 = new Intent(this, Chat.class);

        layout=binding.scrollView2;

        String userName=chatRepository.secondUser;

        binding.textViewContact.setText(userName);

        User user1=new User();
        User user2=new User();
        user1=userRepository.getActiveUser();
        user2=userRepository.findByName(userName);

        Map<String, List<String>> chat = new HashMap<>();
        chat =chatRepository.getChat(user1.email, user2.email);

        List<String> orderList = new ArrayList<>();
        orderList=chatRepository.getChat2(user1.email, user2.email).orderList;


        for (String key : orderList) {
            List<String> values = chat.get(key);

            if(key.equals(user1.email))
            {
                for (String value : values) {
                    addRight(value);
                }
            }
            else if(key.equals(user2.email))
            {
                for (String value : values) {
                    addLeft(value);
                }
            }

        }


        User finalUser = user1;
        User finalUser1 = user2;
        binding.buttonSendText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TextView nameView = view.findViewById(R.id.writeText);
                String nameText = binding.writeText.getText().toString();
                if(!nameText.isEmpty())
                {
                    chatRepository.addText(finalUser.email, finalUser1.email, nameText);
                    //finalUser.newNotification.add("New Text from "+finalUser1.name);
                    finalUser1.addNotificationText(finalUser.name);

                    startActivity(intent1);
                }

            }
        });

        binding.backChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startActivity(intent2);

            }
        });

    }

    private void addLeft(String text) {
        final View view = getLayoutInflater().inflate(R.layout.leftchat, null);

        //@SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView nameView = view.findViewById(R.id.namename1);
        TextView nameView = view.findViewById(R.id.contactName);
        nameView.setText(text);

        layout.addView(view);
    }

    private void addRight(String text) {
        final View view = getLayoutInflater().inflate(R.layout.rightchat, null);

        //@SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView nameView = view.findViewById(R.id.namename1);
        TextView nameView = view.findViewById(R.id.contactName2);
        nameView.setText(text);

        layout.addView(view);
    }
}
