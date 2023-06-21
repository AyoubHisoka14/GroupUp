package com.example.tests;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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


    }

    private void addRequests(String name) {
        final View view = getLayoutInflater().inflate(R.layout.newrequests, null);

        //@SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView nameView = view.findViewById(R.id.namename1);
        TextView nameView = view.findViewById(R.id.name_re);
        nameView.setText(name);


        Button acceptButton = view.findViewById(R.id.accept);
        Button profileButton = view.findViewById(R.id.profile_re);
        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView nameView = view.findViewById(R.id.name_re);
                String nameText = nameView.getText().toString();
                ProfileDialog(nameText);
                //Toast.makeText(AllRequests.this, nameText, Toast.LENGTH_SHORT).show();
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
}
