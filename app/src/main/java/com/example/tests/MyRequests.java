package com.example.tests;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tests.databinding.MyrequestsBinding;

public class MyRequests extends AppCompatActivity {
    private MyrequestsBinding binding;


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
}
