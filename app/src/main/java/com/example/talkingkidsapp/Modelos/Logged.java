package com.example.talkingkidsapp.Modelos;

import android.util.Log;

public class Logged {
    static boolean theres = false;
    static String nombre;
    static String correo;
    static String contraseña;
    public Logged(){}
    public Logged(boolean Theres, String Nombre, String Correo, String Contraseña)
    {
        this.theres = Theres;
        this.nombre = Nombre;
        this.correo = Correo;
        this.contraseña = Contraseña;
    }

    public static boolean isTheres() {
        return theres;
    }

    public static void setTheres(boolean theres) {
        Logged.theres = theres;
    }

    public static String getNombre() {
        return nombre;
    }

    public static void setNombre(String nombre) {
        Logged.nombre = nombre;
    }

    public static String getCorreo() {
        return correo;
    }

    public static void setCorreo(String correo) {
        Logged.correo = correo;
    }

    public static String getContraseña() {
        return contraseña;
    }

    public static void setContraseña(String contraseña) {
        Logged.contraseña = contraseña;
    }
}

