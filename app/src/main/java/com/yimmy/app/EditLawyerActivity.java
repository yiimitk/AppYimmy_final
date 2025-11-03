
package com.yimmy.app;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class EditLawyerActivity extends AppCompatActivity {

    private EditText lawyerName;
    private EditText lawyerSpecialty;
    private EditText lawyerPhone;
    private ImageView lawyerImage;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_lawyer);

        lawyerName = findViewById(R.id.lawyer_name);
        lawyerSpecialty = findViewById(R.id.lawyer_specialty);
        lawyerPhone = findViewById(R.id.lawyer_phone);
        lawyerImage = findViewById(R.id.lawyer_image);
        saveButton = findViewById(R.id.save_button);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            lawyerName.setText(extras.getString("lawyerName"));
            lawyerSpecialty.setText(extras.getString("lawyerSpecialty"));
            lawyerPhone.setText(extras.getString("lawyerPhone"));
            // In a real app, you'd load the image from a URI or URL
            lawyerImage.setImageResource(extras.getInt("lawyerImageResId"));
        }

        saveButton.setOnClickListener(v -> {
            // Here you would save the new or updated lawyer information
            finish(); // Go back to the previous screen
        });
    }
}
