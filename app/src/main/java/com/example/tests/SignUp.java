package com.example.tests;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tests.databinding.SignupBinding;

public class SignUp extends AppCompatActivity {
    private SignupBinding binding;
    UserRepository userRepository =new UserRepository();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = new Intent(this, Profile.class);
        Intent intent2 = new Intent(this, MainActivity.class);

        binding= SignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.buttonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String editName = binding.editName.getText().toString();
                final String editEmail = binding.editTextTextEmailAddress3.getText().toString();
                final String editPassword = binding.editTextTextPassword5.getText().toString();
                final String editPassword2 = binding.editTextTextPassword3.getText().toString();

                userRepository=UserRepository.getInstance();

                if(!editName.isEmpty() && !editEmail.isEmpty() && !editPassword.isEmpty() && !editPassword2.isEmpty())
                {
                    if(userRepository.find(editEmail)==null)
                    {
                        if(editPassword.equals(editPassword2))
                        {
                            User newUser=new User(editName, editEmail, editPassword);
                            userRepository.save(newUser);
                            startActivity(intent);
                        }
                        else
                        {
                            Toast.makeText(SignUp.this, "Passwords must be identical", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(SignUp.this, "Please choose another Email", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(SignUp.this, "Please Enter All Informations", Toast.LENGTH_SHORT).show();
                }

            }
        });

        binding.backSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(intent2);
            }
        });



    }
}
