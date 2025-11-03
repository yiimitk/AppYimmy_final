
package com.yimmy.app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EditarAbogadoActivity extends AppCompatActivity {

    private EditText nombreAbogado;
    private EditText especialidadAbogado;
    private EditText telefonoAbogado;
    private AbogadoDao abogadoDao;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();
    private int abogadoId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_abogado);

        nombreAbogado = findViewById(R.id.nombre_abogado);
        especialidadAbogado = findViewById(R.id.especialidad_abogado);
        telefonoAbogado = findViewById(R.id.telefono_abogado);
        Button botonGuardar = findViewById(R.id.boton_guardar);

        abogadoDao = AppDatabase.getDatabase(this).abogadoDao();

        abogadoId = getIntent().getIntExtra("abogadoId", -1);

        if (abogadoId != -1) {
            cargarDatosAbogado();
        }

        botonGuardar.setOnClickListener(v -> {
            guardarAbogado();
        });
    }

    private void cargarDatosAbogado() {
        executorService.execute(() -> {
            Abogado abogado = abogadoDao.obtenerPorId(abogadoId);
            runOnUiThread(() -> {
                if (abogado != null) {
                    nombreAbogado.setText(abogado.getNombre());
                    especialidadAbogado.setText(abogado.getEspecialidad());
                    telefonoAbogado.setText(abogado.getTelefono());
                }
            });
        });
    }

    private void guardarAbogado() {
        String nombre = nombreAbogado.getText().toString();
        String especialidad = especialidadAbogado.getText().toString();
        String telefono = telefonoAbogado.getText().toString();

        // Por ahora, usamos una imagen por defecto
        Abogado abogado = new Abogado(nombre, especialidad, telefono, android.R.drawable.star_on);

        executorService.execute(() -> {
            if (abogadoId != -1) {
                abogado.id = abogadoId;
                abogadoDao.actualizar(abogado);
            } else {
                abogadoDao.insertar(abogado);
            }
            runOnUiThread(() -> {
                setResult(RESULT_OK);
                finish();
            });
        });
    }
}
