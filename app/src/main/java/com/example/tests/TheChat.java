package com.example.tests;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tests.databinding.ThechatBinding;

public class TheChat extends AppCompatActivity {

    private ThechatBinding thechatBinding;
    ChatRepository chatRepository=new ChatRepository();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }
}
