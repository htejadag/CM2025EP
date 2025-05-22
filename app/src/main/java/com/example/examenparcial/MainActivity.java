package com.example.examenparcial;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText etUsuario, etContrasena;
    Button btnLogin;

    final String USER = "admin";
    final String PASS = "1234";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        etUsuario = findViewById(R.id.nombreIngresar);
        etContrasena = findViewById(R.id.contraIngresar);
        btnLogin = findViewById(R.id.iniciarSesion);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usuario = etUsuario.getText().toString();
                String contrasena = etContrasena.getText().toString();

                if (usuario.isEmpty() || contrasena.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Completa todos los campos", Toast.LENGTH_SHORT).show();
                } else if (usuario.length() > 20) {
                    Toast.makeText(MainActivity.this, "El usuario no puede tener más de 20 caracteres", Toast.LENGTH_SHORT).show();
                } else if (usuario.equals(USER) && contrasena.equals(PASS)) {
                    Intent intent = new Intent(MainActivity.this, registro.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(MainActivity.this, "credenciales incorrectos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}