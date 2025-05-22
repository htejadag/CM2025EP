package com.example.examenparcial.Entities;

import java.io.Serializable;

public class Person implements Serializable {

    private String nombre;
    private String correo;
    private String numero;
    private String fecha;
    private String genero;

    public Person() {
    }

    public Person(String nombre, String correo, String numero, String fecha, String genero) {
        this.nombre = nombre;
        this.correo = correo;
        this.numero = numero;
        this.fecha = fecha;
        this.genero = genero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    @Override
    public String toString() {
        return "Person{" +
                "nombre='" + nombre + '\'' +
                ", correo='" + correo + '\'' +
                ", numero='" + numero + '\'' +
                ", fecha='" + fecha + '\'' +
                ", genero='" + genero + '\'' +
                '}';
    }
}
