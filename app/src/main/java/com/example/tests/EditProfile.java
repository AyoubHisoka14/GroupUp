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

import com.example.tests.databinding.EditprofileBinding;

public class EditProfile extends AppCompatActivity {
    private EditprofileBinding binding;
    private UserRepository userRepository=new UserRepository();
    private User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent1 = new Intent(this, AddRequest.class);
        Intent intent2 = new Intent(this, AllRequests.class);
        Intent intent3 = new Intent(this, MyRequests.class);
        Intent intent4 = new Intent(this, Chat.class);
        Intent intent5 = new Intent(this, Profile.class);

        binding = EditprofileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        userRepository=UserRepository.getInstance();
        user=userRepository.getActiveUser();

        binding.editTextName2.setText(user.name);
        binding.editTextEmail2.setText(user.email);
        binding.editTextGeburts2.setText(user.geburstdatum);
        binding.editTextwohn2.setText(user.wohnort);
        binding.editTexthochschule2.setText(user.hochschule);
        binding.editTextStudiengang2.setText(user.studiengang);
        binding.editTextSemester2.setText(user.semester);

        notificationsIcon();

        binding.buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user.geburstdatum=binding.editTextGeburts2.getText().toString();
                user.wohnort=binding.editTextwohn2.getText().toString();
                user.hochschule=binding.editTexthochschule2.getText().toString();
                user.studiengang=binding.editTextStudiengang2.getText().toString();
                user.semester=binding.editTextSemester2.getText().toString();


                startActivity(intent5);
            }
        });

        binding.buttonAdd6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(intent1);
            }
        });
        binding.buttonAll6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(intent2);
            }
        });
        binding.buttonMyrequests6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(intent3);
            }
        });
        binding.buttonChat6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(intent4);
            }
        });
        binding.notif4.setOnClickListener(new View.OnClickListener() {
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
        Intent intent1 = new Intent(this, EditProfile.class);

        builder.setView(dialogView);
        builder.setTitle("Notifications")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        User newUser=userRepository.getActiveUser();
                        user.deleteNotifications();
                        //startActivity(intent1);

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
