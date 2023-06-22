package com.example.tests;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tests.databinding.MyrequestsBinding;

import java.util.List;

public class MyRequests extends AppCompatActivity {
    private MyrequestsBinding binding;

    UserRepository userRepository =new UserRepository();
    RequestRepository requestRepository=new RequestRepository();
    User user=new User();


    LinearLayout layout;
    AlertDialog dialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent1 = new Intent(this, AddRequest.class);
        Intent intent2 = new Intent(this, Profile.class);
        Intent intent3 = new Intent(this, AllRequests.class);
        Intent intent4 = new Intent(this, Chat.class);
        //Intent intent5 = new Intent(this, Profile.class);

        binding = MyrequestsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        requestRepository=RequestRepository.getInstance();
        userRepository=UserRepository.getInstance();

        notificationsIcon();

        layout = binding.container2;

        user=userRepository.getActiveUser();
        List<User> allRequests=user.myRequests;
        for(User user : allRequests)
        {
            addRequests(user.name);
        }

        binding.buttonAdd5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(intent1);
            }
        });
        binding.buttonProfile5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(intent2);
            }
        });

        binding.buttonAll5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(intent3);
            }
        });
        binding.buttonChat5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(intent4);
            }
        });
        binding.notif6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                user=userRepository.getActiveUser();
                notifications(user);
            }
        });


    }

    private void addRequests(String name) {
        final View view = getLayoutInflater().inflate(R.layout.newrequests, null);
        Intent intent1 = new Intent(this, Chat.class);
        Intent intent2 = new Intent(this, MyRequests.class);

        //@SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView nameView = view.findViewById(R.id.namename1);
        TextView nameView = view.findViewById(R.id.name_re);
        nameView.setText(name);


        Button acceptButton = view.findViewById(R.id.accept);
        Button profileButton = view.findViewById(R.id.profile_re);
        Button deleteButton = view.findViewById(R.id.delete);
        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView nameView = view.findViewById(R.id.name_re);
                String nameText = nameView.getText().toString();
                ProfileDialog(nameText);

            }
        });

        acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TextView nameView = view.findViewById(R.id.name_re);
                String nameText = nameView.getText().toString();

                user=userRepository.getActiveUser();
                User user2 = userRepository.findByName(nameText);

                user.newPartner(user2);
                user2.newPartner(user);
                startActivity(intent1);
            }
        });
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView nameView = view.findViewById(R.id.name_re);
                String nameText = nameView.getText().toString();
                User user1=userRepository.findByName(nameText);
                user=userRepository.getActiveUser();
                user.deleteRequest(user1);
                startActivity(intent2);

            }
        });

        layout.addView(view);
    }

    private void ProfileDialog(String name) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.userprofile, null);

        user=userRepository.findByName(name);
        TextView nameView = view.findViewById(R.id.nameEdit_user);
        nameView.setText(user.name);

        TextView geburts = view.findViewById(R.id.geburts_user);
        geburts.setText(user.geburstdatum);

        TextView wohn = view.findViewById(R.id.wohn_user);
        wohn.setText(user.wohnort);

        TextView hochschule = view.findViewById(R.id.hochschule_user);
        hochschule.setText(user.hochschule);

        TextView studiengang = view.findViewById(R.id.studiengang_user);
        studiengang.setText(user.studiengang);

        TextView semester = view.findViewById(R.id.semester_user);
        semester.setText(user.semester);

        builder.setView(view);
        builder.setTitle("Student's Profile")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        dialog = builder.create();
        dialog.show();
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
        Intent intent1 = new Intent(this, MyRequests.class);

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
