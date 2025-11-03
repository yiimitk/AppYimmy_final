
package com.yimmy.app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DetalleAbogadoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_abogado);

        ImageView imagenAbogado = findViewById(R.id.imagen_abogado);
        TextView nombreAbogado = findViewById(R.id.nombre_abogado);
        TextView especialidadAbogado = findViewById(R.id.especialidad_abogado);
        TextView telefonoAbogado = findViewById(R.id.telefono_abogado);
        Button botonEditar = findViewById(R.id.boton_editar);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String nombre = extras.getString("abogadoNombre");
            String especialidad = extras.getString("abogadoEspecialidad");
            String telefono = extras.getString("abogadoTelefono");
            int idImagen = extras.getInt("abogadoIdImagen");

            nombreAbogado.setText(nombre);
            especialidadAbogado.setText(especialidad);
            telefonoAbogado.setText(telefono);
            imagenAbogado.setImageResource(idImagen);

            botonEditar.setOnClickListener(v -> {
                Intent intent = new Intent(DetalleAbogadoActivity.this, EditarAbogadoActivity.class);
                intent.putExtra("abogadoNombre", nombre);
                intent.putExtra("abogadoEspecialidad", especialidad);
                intent.putExtra("abogadoTelefono", telefono);
                intent.putExtra("abogadoIdImagen", idImagen);
                startActivity(intent);
            });
        }
    }
}
