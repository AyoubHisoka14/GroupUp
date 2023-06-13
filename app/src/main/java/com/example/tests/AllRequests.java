package com.example.tests;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tests.databinding.AllrequestsBinding;

public class AllRequests extends AppCompatActivity {
    private AllrequestsBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent1 = new Intent(this, AddRequest.class);
        Intent intent2 = new Intent(this, Profile.class);
        Intent intent3 = new Intent(this, MyRequests.class);
        Intent intent4 = new Intent(this, Chat.class);
        //Intent intent5 = new Intent(this, Profile.class);

        binding = AllrequestsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

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


    }
}
