package com.example.examenparcial;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etUsername, etPassword;
    private Button btnLogin;

    // Usuario y contraseña simulados
    private static final String USER = "admin";
    private static final String PASS = "1234";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Enlazar vistas
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);

        // Configurar evento del botón
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    // Método de validación del login
    private void login() {
        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        // Validar campos vacíos
        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show();
            return;
        }

        // Validar longitud del usuario
        if (username.length() > 20) {
            Toast.makeText(this, "El usuario no debe exceder los 20 caracteres", Toast.LENGTH_SHORT).show();
            return;
        }

        // Validar que usuario y contraseña no sean iguales
        if (username.equals(password)) {
            Toast.makeText(this, "El usuario y la contraseña no pueden ser iguales", Toast.LENGTH_SHORT).show();
            return;
        }

        // Validación final de credenciales
        if (username.equals(USER) && password.equals(PASS)) {
            Toast.makeText(this, "Login exitoso", Toast.LENGTH_SHORT).show();
            // Aquí podrías pasar a otra actividad, por ejemplo:
            // startActivity(new Intent(this, RegistroActivity.class));
        } else {
            Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
        }
    }
}
