
package com.yimmy.app;

public class Lawyer {
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

    public String getName() {
        return name;
    }

    public String getSpecialty() {
        return specialty;
    }

    public String getPhone() {
        return phone;
    }

    public int getImageResId() {
        return imageResId;
    }
}
