package com.example.talkingkidsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Locale;

public class RECUPERAR extends AppCompatActivity {

    TextView entradaCorreo;
    TextView vistaContraseña;
    Button buttonRecuperar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar);
        entradaCorreo = findViewById(R.id.entradaCorreoRecuperacion);
        vistaContraseña = findViewById(R.id.vistaContraseña);
        buttonRecuperar = findViewById(R.id.buttonRegenerar);
        buttonRecuperar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Correo = entradaCorreo.getText().toString();
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                db.collection("users").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(!task.isSuccessful())
                        {   Toast.makeText(RECUPERAR.this, "Error crítico al leer la BD", Toast.LENGTH_SHORT).show(); }
                        else
                        {
                            boolean Bandera = false;
                            String Contraseña = "";
                            for (QueryDocumentSnapshot document : task.getResult())
                            {
                                String CorreoBD = document.get("correo").toString().toLowerCase(Locale.ROOT);
                                if(CorreoBD.matches(Correo)) { Contraseña = document.get("contraseña").toString();Bandera = true; break; }
                            }
                            if(!Bandera)
                            { Toast.makeText(RECUPERAR.this, "El correo no fue registrado", Toast.LENGTH_SHORT).show(); }
                            else
                            {  vistaContraseña.setVisibility(View.VISIBLE); vistaContraseña.setText("Tu contraseña es: " + Contraseña); entradaCorreo.setText("");}
                        }
                    }
                });
            }
        });
    }
}