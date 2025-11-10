package com.yimmy.app;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class LawyerDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        ImageView lawyerImage = findViewById(R.id.lawyer_image);
        TextView lawyerName = findViewById(R.id.lawyer_name);
        TextView lawyerSpecialty = findViewById(R.id.lawyer_specialty);
        TextView lawyerPhone = findViewById(R.id.lawyer_phone);

        // --- LÓGICA ACTUALIZADA PARA RECIBIR EL OBJETO ABOGADO ---
        if (getIntent().hasExtra("LAWYER_DATA")) {
            Abogado abogado = (Abogado) getIntent().getSerializableExtra("LAWYER_DATA");

            if (abogado != null) {
                lawyerName.setText(abogado.getNombre());
                lawyerSpecialty.setText(abogado.getEspecialidad());
                lawyerPhone.setText(abogado.getTelefono());
                lawyerImage.setImageResource(abogado.getIdImagen());
            }
        }
    }
}
