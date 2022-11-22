package com.example.talkingkidsapp;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
    }
    public void MensajeVaca(View view){
        MediaPlayer mp = MediaPlayer.create(this, R.raw.vaca);
        mp.start();
        Toast.makeText(this,"Vaca", Toast.LENGTH_SHORT).show();
    }
    public void MensajePero(View view){
        MediaPlayer mp = MediaPlayer.create(this, R.raw.perro);
        mp.start();
        Toast.makeText(this,"Perro", Toast.LENGTH_SHORT).show();
    }
    public void MensajePato(View view){
        MediaPlayer mp = MediaPlayer.create(this, R.raw.pato);
        mp.start();
        Toast.makeText(this,"Pato", Toast.LENGTH_SHORT).show();
    }
    public void MensajeGato(View view){
        MediaPlayer mp = MediaPlayer.create(this, R.raw.gato);
        mp.start();
        Toast.makeText(this,"Gato", Toast.LENGTH_SHORT).show();
    }
    public void MensajeCerdo(View view){
        MediaPlayer mp = MediaPlayer.create(this, R.raw.cerdo);
        mp.start();
        Toast.makeText(this,"Cerdo", Toast.LENGTH_SHORT).show();
    }
    public void MensajeCaballo(View view){
        MediaPlayer mp = MediaPlayer.create(this, R.raw.caballo);
        mp.start();
        Toast.makeText(this,"Caballo", Toast.LENGTH_SHORT).show();
    }
}