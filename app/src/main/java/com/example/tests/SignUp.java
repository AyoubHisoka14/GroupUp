package com.example.tests;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tests.databinding.SignupBinding;

public class SignUp extends AppCompatActivity {
    private SignupBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = new Intent(this, Profile.class);

        binding= SignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.buttonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(intent);
            }
        });




    }
}
