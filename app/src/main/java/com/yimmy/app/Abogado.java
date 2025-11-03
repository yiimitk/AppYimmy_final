
package com.yimmy.app;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "abogados")
public class Abogado {
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
