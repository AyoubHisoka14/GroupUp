package com.example.tests;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tests.databinding.AddrequestBinding;

public class AddRequest extends AppCompatActivity {

    private AddrequestBinding binding;
    UserRepository userRepository =new UserRepository();
    RequestRepository requestRepository=new RequestRepository();
    User user=new User();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent1 = new Intent(this, AllRequests.class);
        Intent intent2 = new Intent(this, MyRequests.class);
        Intent intent3 = new Intent(this, Chat.class);
        Intent intent4 = new Intent(this, Profile.class);
        //Intent intent5 = new Intent(this, Profile.class);

        binding = AddrequestBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        userRepository=UserRepository.getInstance();
        requestRepository=RequestRepository.getInstance();

        //notificationsIcon();

        binding.buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String editCourse = binding.editTextCourse.getText().toString();
                final String editModul = binding.editTextModul.getText().toString();
                final String editProf = binding.editTextProf.getText().toString();
                final String editBlock = binding.editTextBlock.getText().toString();

                if(!editCourse.isEmpty() && !editModul.isEmpty() && !editProf.isEmpty() && !editBlock.isEmpty())
                {
                    if(editCourse.length()<50 && editModul.length()<50 && editProf.length()<50 && editBlock.length()<50)
                    {
                        user=userRepository.getActiveUser();
                        Request newRequest=new Request(requestRepository.getId(), user, editCourse, editModul, editProf, editBlock);
                        requestRepository.save(newRequest);
                        Toast.makeText(AddRequest.this, "Your request has been successfully added", Toast.LENGTH_SHORT).show();
                        startActivity(intent1);
                    }
                    else {
                        Toast.makeText(AddRequest.this, "The Informations are too long", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(AddRequest.this, "Please Enter All Informations", Toast.LENGTH_SHORT).show();
                }

            }
        });


        binding.buttonAll2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(intent1);
            }
        });
        binding.buttonMyrequests2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(intent2);
            }
        });
        binding.buttonChat2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(intent3);
            }
        });
        binding.buttonProfile2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(intent4);

            }
        });
        binding.notification2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                User user2=userRepository.getActiveUser();
                notifications(user2);
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
    private void notifications(User user2)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogView = getLayoutInflater().inflate(R.layout.notifications, null);
        LinearLayout layoutNotification = dialogView.findViewById(R.id.container3);
        Intent intent1 = new Intent(this, AddRequest.class);

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

        for (String notification : user2.newNotification) {
            View notificationView = getLayoutInflater().inflate(R.layout.notification, null);
            TextView nameView = notificationView.findViewById(R.id.notificationText);
            nameView.setText(notification);
            layoutNotification.addView(notificationView);
        }


    }
}
