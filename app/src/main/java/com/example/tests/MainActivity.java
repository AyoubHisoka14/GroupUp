package com.example.tests;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.tests.databinding.ActivityMainBinding;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private UserRepository userRepository=new UserRepository();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent1 = new Intent(this, LogIn.class);
        Intent intent2 = new Intent(this, SignUp.class);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        userRepository=UserRepository.getInstance();

        SharedPreferences sharedPreferences2 = getSharedPreferences("shared preferences", MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences2.edit();
        //editor.clear();
        //editor.apply();

        Gson gson2 = new Gson();
        String json2= sharedPreferences2.getString("task list", null);
        Type type = new TypeToken<ArrayList<User>>() {}.getType();
        userRepository.allUsers = gson2.fromJson(json2, type);

        if (userRepository.allUsers == null) {
            userRepository.allUsers = new ArrayList<>();
        }

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