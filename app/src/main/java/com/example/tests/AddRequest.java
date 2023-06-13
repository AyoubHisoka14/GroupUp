package com.example.tests;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tests.databinding.AddrequestBinding;

public class AddRequest extends AppCompatActivity {
    private AddrequestBinding binding;



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
    }
}
