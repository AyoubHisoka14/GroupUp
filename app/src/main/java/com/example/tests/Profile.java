package com.example.tests;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tests.databinding.ProfileBinding;

public class Profile extends AppCompatActivity {
    private ProfileBinding binding;
    private UserRepository userRepository=new UserRepository();
    private User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent1 = new Intent(this, AddRequest.class);
        Intent intent2 = new Intent(this, AllRequests.class);
        Intent intent3 = new Intent(this, MyRequests.class);
        Intent intent4 = new Intent(this, Chat.class);
        Intent intent5 = new Intent(this, MainActivity.class);
        Intent intent6 = new Intent(this, EditProfile.class);

        binding = ProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        userRepository=UserRepository.getInstance();
        user=userRepository.getActiveUser();

        binding.editTextName.setText(user.name);
        binding.editTextEmail.setText(user.email);
        binding.editTextGeburts.setText(user.geburstdatum);
        binding.editTextwohn.setText(user.wohnort);
        binding.editTexthochschule.setText(user.hochschule);
        binding.editTextStudiengang.setText(user.studiengang);
        binding.editTextSemester.setText(user.semester);

        notificationsIcon();

        binding.buttonLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user=userRepository.getActiveUser();
                user.isActive=false;
                startActivity(intent5);
            }
        });

       binding.buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(intent1);
            }
        });
        binding.buttonAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(intent2);
            }
        });
        binding.buttonMyrequests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(intent3);
            }
        });
        binding.buttonChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(intent4);
            }
        });

        binding.buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(intent6);
            }
        });
        binding.notif7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                user=userRepository.getActiveUser();
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
        Intent intent1 = new Intent(this, Profile.class);

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
