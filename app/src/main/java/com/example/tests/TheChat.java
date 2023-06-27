package com.example.tests;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tests.databinding.ThechatBinding;
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.ArrayListMultimap;
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.ListMultimap;

import java.util.LinkedHashMap;
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

        ListMultimap<String, String> chat = ArrayListMultimap.create();
        chat =chatRepository.getChat(user1.email, user2.email);



        for (Map.Entry<String, String> entry : chat.entries()) {
            String key = entry.getKey();
            String value = entry.getValue();

            if(key.equals(user1.email))
            {
                addRight(value);
            }
            else if(key.equals(user2.email))
            {
                addLeft(value);
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
