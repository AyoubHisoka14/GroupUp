package com.example.tests;      //kindasus

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tests.databinding.ChatBinding;
import com.example.tests.databinding.MyrequestsBinding;

public class Chat extends AppCompatActivity {

    private ChatBinding binding;
    UserRepository userRepository=new UserRepository();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent1 = new Intent(this, AddRequest.class);
        Intent intent2 = new Intent(this, Profile.class);
        Intent intent3 = new Intent(this, AllRequests.class);
        Intent intent4 = new Intent(this, MyRequests.class);
        //Intent intent5 = new Intent(this, Profile.class);
        userRepository=UserRepository.getInstance();

        binding = ChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //notificationsIcon();

        binding.buttonAdd3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(intent1);
            }
        });
        binding.buttonProfile3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(intent2);
            }
        });

        binding.buttonAll3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(intent3);
            }
        });
        binding.buttonMyrequests3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(intent4);
            }
        });
        binding.notif3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                User user=userRepository.getActiveUser();
                notifications(user);
            }
        });

    }
    private void notificationsIcon() {
        User newUser=userRepository.getActiveUser();

        if(newUser.newNotification.size()>0)
        {
            ImageButton myButton = findViewById(R.id.ButtonNotification);
            ColorStateList colorStateList = ColorStateList.valueOf(getResources().getColor(R.color.red));
            myButton.setBackgroundTintList(colorStateList);
        }
    }
    private void notifications(User user)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogView = getLayoutInflater().inflate(R.layout.notifications, null);
        LinearLayout layoutNotification = dialogView.findViewById(R.id.container3);
        Intent intent1 = new Intent(this, Chat.class);

        builder.setView(dialogView);
        builder.setTitle("Notifications")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        User newUser=userRepository.getActiveUser();
                        user.deleteNotifications();
                        startActivity(intent1);
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();

        for (String notification : user.newNotification) {
            View notificationView = getLayoutInflater().inflate(R.layout.notification, null);
            TextView nameView = notificationView.findViewById(R.id.notificationText);
            nameView.setText(notification);
            layoutNotification.addView(notificationView);
        }


    }
}
