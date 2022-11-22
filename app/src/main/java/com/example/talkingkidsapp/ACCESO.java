package com.example.talkingkidsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.talkingkidsapp.Modelos.Logged;
import com.example.talkingkidsapp.Modelos.Usuarios;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class ACCESO extends AppCompatActivity {

    TextView entradaCorreo;
    TextView entradaContraseña;
    Button buttonAcceder;
    Button buttonRecuperar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceso);
        entradaCorreo = findViewById(R.id.entradaCorreoLogin);
        entradaContraseña = findViewById(R.id.entradaContraseñaLogin);
        buttonAcceder = findViewById(R.id.buttonIngresar);
        buttonAcceder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Correo = entradaCorreo.getText().toString();
                String Contraseña = entradaContraseña.getText().toString();
                if(Correo.isEmpty() || Contraseña.isEmpty())
                { Toast.makeText(ACCESO.this, "No pueden haber campos vacíos", Toast.LENGTH_SHORT).show(); }
                else
                {
                    if(!Patterns.EMAIL_ADDRESS.matcher(Correo).matches())
                    { Toast.makeText(ACCESO.this, "El correo no es válido", Toast.LENGTH_SHORT).show();  }
                    else
                    {
                        FirebaseFirestore db = FirebaseFirestore.getInstance();
                        db.collection("users").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if(task.isSuccessful())
                                {
                                    for (QueryDocumentSnapshot document : task.getResult())
                                    {
                                        if(document.get("correo").toString().matches(Correo) && document.get("contraseña").toString().matches(Contraseña))
                                        {
                                            Logged Generado = new Logged(true, document.get("nombre").toString(), document.get("correo").toString(), document.get("contraseña").toString());
                                        }
                                    }
                                    Logged Generado = new Logged();
                                    if (!Logged.isTheres())
                                    { Toast.makeText(ACCESO.this, "Correo o contraseña incorrectos", Toast.LENGTH_SHORT).show(); }
                                    else { Intent i = new Intent(ACCESO.this, MENU.class); startActivity(i); }
                                }
                                else
                                { Toast.makeText(ACCESO.this, "Error al buscar en BD", Toast.LENGTH_SHORT).show(); }
                            }
                        });
                    }
                }
            }
        });
        buttonRecuperar = findViewById(R.id.buttonRecuperar);
        buttonRecuperar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ACCESO.this, RECUPERAR.class);
                startActivity(i);
            }
        });
    }
}