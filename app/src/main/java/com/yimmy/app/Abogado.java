package com.yimmy.app;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import java.io.Serializable; // 1. IMPORTAR LA INTERFAZ

@Entity(tableName = "abogados")
public class Abogado implements Serializable { // 2. IMPLEMENTAR LA INTERFAZ
    @PrimaryKey(autoGenerate = true)
    public int id;

    private String nombre;
    private String especialidad;
    private String telefono;
    private int idImagen;

    public Abogado(String nombre, String especialidad, String telefono, int idImagen) {
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.telefono = telefono;
        this.idImagen = idImagen;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public String getTelefono() {
        return telefono;
    }

    public int getIdImagen() {
        return idImagen;
    }
}
