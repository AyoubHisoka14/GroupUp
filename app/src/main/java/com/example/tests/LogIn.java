package com.example.tests;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.tests.databinding.LoginBinding;


public class LogIn extends AppCompatActivity {
    private LoginBinding binding;
    private UserRepository userRepository=new UserRepository();
    private User user=new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = new Intent(this, Profile.class);



        binding = LoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String editEmail = binding.editTextTextEmailAddress2.getText().toString();
                final String editPassword = binding.editTextTextPassword2.getText().toString();

                if (!editEmail.isEmpty() && !editPassword.isEmpty())
                {
                    userRepository=UserRepository.getInstance();
                    user=userRepository.find(editEmail);
                    if(user.password.equals(editPassword))
                    {
                        user.isActive=true;
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(LogIn.this, "Email or Password are false", Toast.LENGTH_SHORT).show();
                    }

                } else {

                    Toast.makeText(LogIn.this, "Email and Password cannot be empty", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

