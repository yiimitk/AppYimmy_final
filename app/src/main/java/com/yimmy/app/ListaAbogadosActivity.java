package com.yimmy.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

public class ListaAbogadosActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    // 1. La variable ahora es del tipo correcto LawyerAdapter
    private LawyerAdapter adapter;
    private AbogadoViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        viewModel = new ViewModelProvider(this).get(AbogadoViewModel.class);

        setupViews();
        setupListeners();
    }

    private void setupViews() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        RecyclerView lawyerList = findViewById(R.id.lawyer_list);
        FloatingActionButton addLawyerFab = findViewById(R.id.add_lawyer_fab);

        String userRole = getIntent().getStringExtra("USER_ROLE");
        boolean isAdmin = "ADMIN".equals(userRole);

        addLawyerFab.setVisibility(isAdmin ? View.VISIBLE : View.GONE);

        // 2. Crear una instancia del LawyerAdapter corregido
        adapter = new LawyerAdapter(isAdmin);
        lawyerList.setAdapter(adapter);

        viewModel.getAllAbogados().observe(this, abogados -> {
            adapter.submitList(abogados);
        });
    }

    private void setupListeners() {
        adapter.setOnItemClickListener(abogado -> {
            Intent intent = new Intent(ListaAbogadosActivity.this, PerfilActivity.class);
            intent.putExtra("LAWYER_DATA", abogado);
            startActivity(intent);
        });

        adapter.setOnAdminOptionsClickListener(new LawyerAdapter.OnAdminOptionsClickListener() {
            @Override
            public void onEditClick(Abogado abogado) {
                Intent intent = new Intent(ListaAbogadosActivity.this, EditLawyerActivity.class);
                intent.putExtra("LAWYER_DATA", abogado);
                startActivity(intent);
            }

            @Override
            public void onDeleteClick(Abogado abogado) {
                viewModel.removeAbogado(abogado);
                Toast.makeText(ListaAbogadosActivity.this, "Abogado eliminado", Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.add_lawyer_fab).setOnClickListener(v -> {
            Intent intent = new Intent(ListaAbogadosActivity.this, EditLawyerActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.nav_info) {
            Toast.makeText(this, "App de Abogados v1.0", Toast.LENGTH_SHORT).show();
        } else if (itemId == R.id.nav_lawyers) {
            // No hacemos nada
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
}
