package com.example.talkingkidsapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class FIGURAS extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_figuras);
    }

    public void play(View view){
        Intent intent =new Intent(this,game.class);
        startActivity(intent);
    }
}