package com.yimmy.app;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "lawyers")
public class Lawyer implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;
    private String specialty;
    private String phone;
    private int imageResId;

    public Lawyer(String name, String specialty, String phone, int imageResId) {
        this.name = name;
        this.specialty = specialty;
        this.phone = phone;
        this.imageResId = imageResId;
    }

    // Getters y Setters necesarios para Room
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getImageResId() {
        return imageResId;
    }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }
}
