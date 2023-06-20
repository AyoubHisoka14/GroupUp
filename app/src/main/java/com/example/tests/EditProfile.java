package com.example.tests;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tests.databinding.EditprofileBinding;

public class EditProfile extends AppCompatActivity {
    private EditprofileBinding binding;
    private UserRepository userRepository=new UserRepository();
    private User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent1 = new Intent(this, AddRequest.class);
        Intent intent2 = new Intent(this, AllRequests.class);
        Intent intent3 = new Intent(this, MyRequests.class);
        Intent intent4 = new Intent(this, Chat.class);
        Intent intent5 = new Intent(this, Profile.class);

        binding = EditprofileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        userRepository=UserRepository.getInstance();
        user=userRepository.getActiveUser();

        binding.editTextName2.setText(user.name);
        binding.editTextEmail2.setText(user.email);
        binding.editTextGeburts2.setText(user.geburstdatum);
        binding.editTextwohn2.setText(user.wohnort);
        binding.editTexthochschule2.setText(user.hochschule);
        binding.editTextStudiengang2.setText(user.studiengang);
        binding.editTextSemester2.setText(user.semester);



        binding.buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user.geburstdatum=binding.editTextGeburts2.getText().toString();
                user.wohnort=binding.editTextwohn2.getText().toString();
                user.hochschule=binding.editTexthochschule2.getText().toString();
                user.studiengang=binding.editTextStudiengang2.getText().toString();
                user.semester=binding.editTextSemester2.getText().toString();


                startActivity(intent5);
            }
        });

        binding.buttonAdd6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(intent1);
            }
        });
        binding.buttonAll6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(intent2);
            }
        });
        binding.buttonMyrequests6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(intent3);
            }
        });
        binding.buttonChat6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(intent4);
            }
        });


    }
}
