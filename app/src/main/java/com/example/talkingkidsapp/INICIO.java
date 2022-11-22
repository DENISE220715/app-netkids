package com.example.talkingkidsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class INICIO extends AppCompatActivity {

    Button LOGINBOTON;
    Button bRegistro;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        LOGINBOTON = (Button) findViewById(R.id.LOGINBOTON);
        bRegistro = (Button) findViewById(R.id.bRegistro);

        LOGINBOTON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(INICIO.this, ACCESO.class);
                startActivity(i);
            }
        });
        bRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(INICIO.this, REGISTRO.class);
                startActivity(i);
            }
        });
    }
}