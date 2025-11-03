
package com.yimmy.app;

import android.content.Intent;
import android.os.Bundle;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ListaAbogadosActivity extends AppCompatActivity {

    private AbogadoAdapter adapter;
    private AbogadoDao abogadoDao;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    private final ActivityResultLauncher<Intent> editAddLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK) {
                    cargarAbogados();
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_abogados);

        RecyclerView listaAbogados = findViewById(R.id.lista_abogados);
        FloatingActionButton agregarAbogadoFab = findViewById(R.id.fab_agregar_abogado);

        abogadoDao = AppDatabase.getDatabase(this).abogadoDao();

        adapter = new AbogadoAdapter(List.of());
        listaAbogados.setAdapter(adapter);

        adapter.setOnItemClickListener(abogado -> {
            Intent intent = new Intent(ListaAbogadosActivity.this, DetalleAbogadoActivity.class);
            intent.putExtra("abogadoId", abogado.id);
            startActivity(intent);
        });

        agregarAbogadoFab.setOnClickListener(v -> {
            Intent intent = new Intent(ListaAbogadosActivity.this, EditarAbogadoActivity.class);
            editAddLauncher.launch(intent);
        });

        cargarAbogados();
    }

    private void cargarAbogados() {
        executorService.execute(() -> {
            List<Abogado> abogados = abogadoDao.obtenerTodos();
            runOnUiThread(() -> {
                adapter.actualizarDatos(abogados);
            });
        });
    }
}
