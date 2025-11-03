
package com.yimmy.app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
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
        Button editButton = findViewById(R.id.edit_button);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String name = extras.getString("lawyerName");
            String specialty = extras.getString("lawyerSpecialty");
            String phone = extras.getString("lawyerPhone");
            int imageResId = extras.getInt("lawyerImageResId");

            lawyerName.setText(name);
            lawyerSpecialty.setText(specialty);
            lawyerPhone.setText(phone);
            lawyerImage.setImageResource(imageResId);

            editButton.setOnClickListener(v -> {
                Intent intent = new Intent(LawyerDetailActivity.this, EditLawyerActivity.class);
                intent.putExtra("lawyerName", name);
                intent.putExtra("lawyerSpecialty", specialty);
                intent.putExtra("lawyerPhone", phone);
                intent.putExtra("lawyerImageResId", imageResId);
                startActivity(intent);
            });
        }
    }
}
