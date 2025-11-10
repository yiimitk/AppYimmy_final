package com.yimmy.app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameEditText = findViewById(R.id.username_edit_text);
        passwordEditText = findViewById(R.id.password_edit_text);
        loginButton = findViewById(R.id.login_button);

        loginButton.setOnClickListener(v -> {
            String username = usernameEditText.getText().toString();
            String password = passwordEditText.getText().toString();

            if (isValidCredentials(username, password)) {
                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                intent.putExtra("USER_ROLE", "ADMIN");
                startActivity(intent);
                finish(); // Cierra la pantalla de login para que no se pueda volver
            } else {
                Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean isValidCredentials(String username, String password) {
        // Credenciales actualizadas
        return "admin".equals(username) && "1234".equals(password);
    }
}
