package com.example.talkingkidsapp.Modelos;

public class Usuarios {
    private String Nombre;
    private String Correo;
    private String Contraseña;
    public Usuarios()
    {}
    public Usuarios(String nombre, String correo, String contraseña)
    {
        this.Nombre = nombre;
        this.Correo = correo;
        this.Contraseña = contraseña;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String correo) {
        Correo = correo;
    }

    public String getContraseña() {
        return Contraseña;
    }

    public void setContraseña(String contraseña) {
        Contraseña = contraseña;
    }
}
