package com.example.tests;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.tests.databinding.LoginBinding;


public class LogIn extends AppCompatActivity {
    private  LoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= LoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());




    }
}
