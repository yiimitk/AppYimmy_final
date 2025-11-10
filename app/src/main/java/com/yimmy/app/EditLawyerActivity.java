package com.yimmy.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView;

public class EditLawyerActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private EditText nameEditText, specialtyEditText, phoneEditText;
    private LawyerRepository lawyerRepository;
    private boolean isNewLawyer = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_abogado);

        lawyerRepository = LawyerRepository.getInstance(getApplicationContext());

        Toolbar toolbar = findViewById(R.id.toolbar_edit);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout_edit);
        NavigationView navigationView = findViewById(R.id.nav_view_edit);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        nameEditText = findViewById(R.id.nombre_abogado_edit_text);
        specialtyEditText = findViewById(R.id.especialidad_abogado_edit_text);
        phoneEditText = findViewById(R.id.telefono_abogado_edit_text);
        Button saveButton = findViewById(R.id.guardar_button);

        if (getIntent().hasExtra("LAWYER_DATA")) {
            isNewLawyer = false;
            Abogado abogado = (Abogado) getIntent().getSerializableExtra("LAWYER_DATA");
            populateFields(abogado);
            getSupportActionBar().setTitle("Editar Abogado");
        }

        saveButton.setOnClickListener(v -> saveLawyer());
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.nav_info) {
            Toast.makeText(this, "App de Abogados v1.0", Toast.LENGTH_SHORT).show();
        } else if (itemId == R.id.nav_lawyers) {
            finish();
        } else if (itemId == R.id.nav_logout) {
            logout();
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void logout() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    // --- MÉTODO CORREGIDO ---
    private void populateFields(Abogado abogado) {
        if (abogado != null) {
            // Usar los getters en español de la clase Abogado
            nameEditText.setText(abogado.getNombre());
            specialtyEditText.setText(abogado.getEspecialidad());
            phoneEditText.setText(abogado.getTelefono());
        }
    }

    private void saveLawyer() {
        String name = nameEditText.getText().toString();
        String specialty = specialtyEditText.getText().toString();
        String phone = phoneEditText.getText().toString();

        if (name.isEmpty() || specialty.isEmpty()) {
            Toast.makeText(this, "Nombre y especialidad son obligatorios", Toast.LENGTH_SHORT).show();
            return;
        }

        if (isNewLawyer) {
            Abogado newAbogado = new Abogado(name, specialty, phone, android.R.drawable.star_on);
            lawyerRepository.addAbogado(newAbogado);
            Toast.makeText(this, "Abogado añadido a la BD", Toast.LENGTH_SHORT).show();
        } else {
            // Aquí iría la lógica para actualizar el abogado en la base de datos
            Toast.makeText(this, "Abogado actualizado", Toast.LENGTH_SHORT).show();
        }

        setResult(RESULT_OK);
        finish();
    }
}
