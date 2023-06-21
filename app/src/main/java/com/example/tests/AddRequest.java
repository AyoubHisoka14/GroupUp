package com.example.tests;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tests.databinding.AddrequestBinding;

public class AddRequest extends AppCompatActivity {

    private AddrequestBinding binding;
    UserRepository userRepository =new UserRepository();
    RequestRepository requestRepository=new RequestRepository();
    User user=new User();



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


        binding.buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String editCourse = binding.editTextCourse.getText().toString();
                final String editModul = binding.editTextModul.getText().toString();
                final String editProf = binding.editTextProf.getText().toString();
                final String editBlock = binding.editTextBlock.getText().toString();

                userRepository=UserRepository.getInstance();
                requestRepository=RequestRepository.getInstance();

                if(!editCourse.isEmpty() && !editModul.isEmpty() && !editProf.isEmpty() && !editBlock.isEmpty())
                {
                    user=userRepository.getActiveUser();
                    Request newRequest=new Request(requestRepository.getId(), user, editCourse, editModul, editProf, editBlock);
                    requestRepository.save(newRequest);
                    Toast.makeText(AddRequest.this, "Your request has been successfully added", Toast.LENGTH_SHORT).show();
                    startActivity(intent1);
                }
                else
                {
                    Toast.makeText(AddRequest.this, "Please Enter All Informations", Toast.LENGTH_SHORT).show();
                }

            }
        });


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
