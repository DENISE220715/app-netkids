package com.example.talkingkidsapp;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.talkingkidsapp.Modelos.Usuarios;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class REGISTRO extends AppCompatActivity {

    TextView entradaNombre;
    TextView entradaCorreo;
    TextView entradaContraseña;
    Button buttonRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        entradaNombre = findViewById(R.id.entradaNombre);
        entradaCorreo = findViewById(R.id.entradaCorreo);
        entradaContraseña = findViewById(R.id.entradaContraseña);
        buttonRegistrar = findViewById(R.id.buttonRegistrar);
        buttonRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Nombre = entradaNombre.getText().toString();
                String Correo = entradaCorreo.getText().toString();
                String Contraseña = entradaContraseña.getText().toString();
                if(Nombre.isEmpty() || Correo.isEmpty() || Contraseña.isEmpty())
                { Toast.makeText(REGISTRO.this, "Los campos no pueden estar vacíos", Toast.LENGTH_LONG).show(); }
                else
                {
                    if(validarEntradas(Nombre, Correo))
                    {
                        Usuarios User = new Usuarios(Nombre, Correo, Contraseña);
                        FirebaseFirestore db = FirebaseFirestore.getInstance();
                        try {
                            CollectionReference Coleccion = db.collection("users");
                            Map<String, Object> user = new HashMap<>();
                            user.put("nombre", Nombre);
                            user.put("correo", Correo);
                            user.put("contraseña", Contraseña);
                            Coleccion.document(Nombre).set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(REGISTRO.this, "Bienvenido " + Nombre + " te has registrado con éxito", Toast.LENGTH_LONG).show();
                                    borrarEntradas();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(REGISTRO.this, "Error crítico al comunicarse con la BD", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }catch (Exception ex)
                        { Toast.makeText(REGISTRO.this, "Error crítico: " + ex, Toast.LENGTH_SHORT).show(); }
                    }
                }
            }
        });
    }
    private boolean validarEntradas(String Nombre, String Correo)
    {
        boolean Bandera = true;
        if ((Nombre.matches(".*\\d"))) { Toast.makeText(REGISTRO.this, "El nombre no puede contener números", Toast.LENGTH_LONG).show(); return false; }
        if(!Patterns.EMAIL_ADDRESS.matcher(Correo).matches()) { Toast.makeText(REGISTRO.this, "El correo debe ser válido", Toast.LENGTH_LONG).show(); return false; }
        return Bandera;
    }
    private void borrarEntradas()
    {
        entradaNombre.setText("");
        entradaCorreo.setText("");
        entradaContraseña.setText("");
    }
}