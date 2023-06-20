package com.example.tests;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

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


    }
}
