package com.example.talkingkidsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Juegos extends AppCompatActivity {


    Button GATO,DIBUJO,BFIGURAS,BANIMALES,rocket;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juegos);
        GATO=(Button) findViewById(R.id.GATO);

        GATO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Juegos.this, MainActivity.class);
                startActivity(i);
            }
        });
        DIBUJO=(Button) findViewById(R.id.DIBUJO);

        DIBUJO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Juegos.this, MainActivity2.class);
                startActivity(i);
            }
        });
        BFIGURAS=(Button) findViewById(R.id.BFIGURAS);

        BFIGURAS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Juegos.this, FIGURAS.class);
                startActivity(i);
            }
        });
        BANIMALES=(Button) findViewById(R.id.BANIMALES);

        BANIMALES.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Juegos.this, MainActivity4.class);
                startActivity(i);
            }
        });
        rocket=(Button) findViewById(R.id.rocket);

        rocket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Juegos.this, MainActivity5.class);
                startActivity(i);
            }
        });

    }
}