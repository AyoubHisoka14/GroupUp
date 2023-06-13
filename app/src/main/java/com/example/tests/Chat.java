package com.example.tests;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tests.databinding.ChatBinding;
import com.example.tests.databinding.MyrequestsBinding;

public class Chat extends AppCompatActivity {

    private ChatBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent1 = new Intent(this, AddRequest.class);
        Intent intent2 = new Intent(this, Profile.class);
        Intent intent3 = new Intent(this, AllRequests.class);
        Intent intent4 = new Intent(this, MyRequests.class);
        //Intent intent5 = new Intent(this, Profile.class);

        binding = ChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

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

    }
}
