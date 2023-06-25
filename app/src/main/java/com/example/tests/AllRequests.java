package com.example.tests;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tests.databinding.AllrequestsBinding;
import com.example.tests.databinding.RequestBinding;
import com.example.tests.databinding.NotificationsBinding;

import java.util.Collection;
import java.util.List;

public class AllRequests extends AppCompatActivity {
    private AllrequestsBinding binding;
    private RequestBinding requestBinding;

    private NotificationsBinding notificationsBinding;

    UserRepository userRepository =new UserRepository();
    RequestRepository requestRepository=new RequestRepository();
    User user=new User();

    SearchView searchView;
    //RecyclerView recyclerView;


    LinearLayout layout, layout_Notification;
    AlertDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent1 = new Intent(this, AddRequest.class);
        Intent intent2 = new Intent(this, Profile.class);
        Intent intent3 = new Intent(this, MyRequests.class);
        Intent intent4 = new Intent(this, Chat.class);
        //Intent intent5 = new Intent(this, AllRequests.class);



        binding = AllrequestsBinding.inflate(getLayoutInflater());
        notificationsBinding=NotificationsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        requestBinding = RequestBinding.inflate(getLayoutInflater());
        requestRepository=RequestRepository.getInstance();
        userRepository=UserRepository.getInstance();

        layout = binding.container;
        layout_Notification=notificationsBinding.container3;
        user=userRepository.getActiveUser();

        notificationsIcon();

        List<Request> allRequests=requestRepository.getAllRequests();
        for(Request request : allRequests)
        {
            if(user.checkSentRequest(request) && user.checkPartner(request))
            {
                addRequests(request.user.name, request.id, request.modul);
            }

        }

        binding.buttonAdd4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(intent1);
            }
        });
        binding.buttonProfile4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(intent2);
            }
        });

        binding.buttonMyrequests4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(intent3);
            }
        });
        binding.buttonChat4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(intent4);
            }
        });
        binding.ButtonNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                user=userRepository.getActiveUser();
                notifications(user);
            }
        });

        searchView=findViewById(R.id.searchView1);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                //Toast.makeText(AllRequests.this, "Search query submitted: " + s, Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String text) {
                //Toast.makeText(AllRequests.this, "Search query changed: " + s, Toast.LENGTH_SHORT).show();
                layout.removeAllViews();

                List<Request> allRequests=requestRepository.getAllRequests();
                for(Request request : allRequests)
                {
                    if(user.checkSentRequest(request) && user.checkPartner(request))
                    {
                        if(request.modul.toUpperCase().contains(text.toUpperCase()))
                        {
                            addRequests(request.user.name, request.id, request.modul);
                        }
                    }
                }
                return false;
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

    private void addRequests(String name, Integer id, String modul) {
        final View view = getLayoutInflater().inflate(R.layout.request, null);

        //@SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView nameView = view.findViewById(R.id.namename1);
        TextView nameView = view.findViewById(R.id.namename1);
        nameView.setText(name);

        TextView idView = view.findViewById(R.id.textViewId);
        idView.setText(id.toString());

        TextView modulView = view.findViewById(R.id.modul_all);
        modulView.setText(modul);


        Button addButton = view.findViewById(R.id.add);
        Button viewButton = view.findViewById(R.id.view);
        Button profileButton = view.findViewById(R.id.profile);
        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView nameView = view.findViewById(R.id.namename1);
                String nameText = nameView.getText().toString();
                ProfileDialog(nameText);
                //Toast.makeText(AllRequests.this, nameText, Toast.LENGTH_SHORT).show();
            }
        });

        viewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView id = view.findViewById(R.id.textViewId);
                String nameText = id.getText().toString();
                ViewDialog(nameText);

            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView nameView = view.findViewById(R.id.namename1);
                String nameText = nameView.getText().toString();
                Add(nameText);

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

    private void ViewDialog(String id)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.view, null);

        Integer Id=Integer.valueOf(id);

        Request request=requestRepository.getById(Id);

        TextView studiengang = view.findViewById(R.id.studiengang_view);
        studiengang.setText(request.course);

        TextView modul = view.findViewById(R.id.modul_view);
        modul.setText(request.modul);

        TextView prof = view.findViewById(R.id.prof_view);
        prof.setText(request.professor);

        TextView block = view.findViewById(R.id.block_view);
        block.setText(request.block);

        builder.setView(view);
        builder.setTitle("Request")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        dialog = builder.create();
        dialog.show();

    }

    private void Add(String name)
    {
        user=userRepository.getActiveUser();
        User user2=userRepository.findByName(name);

        if(user.email.equals(user2.email))
        {
            Toast.makeText(AllRequests.this, "You can not add your own Request", Toast.LENGTH_SHORT).show();
        }
        else
        {
            user2.addRequest(user);
            user.setSentRequests(user2);
            Toast.makeText(AllRequests.this, "Your request has been successfully sent", Toast.LENGTH_SHORT).show();
            Intent intent5 = new Intent(this, AllRequests.class);
            startActivity(intent5);
        }
    }

    private void notifications(User user)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogView = getLayoutInflater().inflate(R.layout.notifications, null);
        LinearLayout layoutNotification = dialogView.findViewById(R.id.container3);
        Intent intent1 = new Intent(this, AllRequests.class);

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

