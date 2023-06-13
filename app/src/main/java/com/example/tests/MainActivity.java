package com.example.tests;
import android.content.Intent;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.tests.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent1 = new Intent(this, LogIn.class);
        Intent intent2 = new Intent(this, SignUp.class);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Add click listener to button
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(intent1);
            }
        });

        binding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(intent2);
            }
        });







    }
}