package com.example.talkingkidsapp;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {

    private PaintView paintView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        paintView = findViewById(R.id.paint_view);

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        paintView.init(metrics);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.options_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.pen :
                paintView.pen();
                Toast.makeText(this, "Pincel Activado!", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.eraser :
                paintView.eraser();
                Toast.makeText(this, "Borrador Activado!", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.clear :
                paintView.clear();
                Toast.makeText(this, "Nuevo Dibujo", Toast.LENGTH_SHORT).show();
                return true;
        }


        return super.onOptionsItemSelected(item);
    }
}
