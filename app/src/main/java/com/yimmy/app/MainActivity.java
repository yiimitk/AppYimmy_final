package com.yimmy.app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Botones del layout
        Button btnCamara = findViewById(R.id.btnCamara);
        Button btnHome = findViewById(R.id.btnHome);
        Button btnPerfil = findViewById(R.id.btnPerfil);

        // Ir a Cámara
        btnCamara.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CamaraActivity.class);
            startActivity(intent);
        });

        // Ir a Inicio
        btnHome.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(intent);
        });

        // Ir a Perfil
        btnPerfil.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, PerfilActivity.class);
            startActivity(intent);
        });
    }
}
