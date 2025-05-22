package com.example.examenparcial;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText etUsuario, etPassword;
    Button btnIngresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUsuario = findViewById(R.id.etUsuario);
        etPassword = findViewById(R.id.etPassword);
        btnIngresar = findViewById(R.id.btnIngresar);


        btnIngresar.setOnClickListener(v -> {
            String user = etUsuario.getText().toString();
            String pass = etPassword.getText().toString();

            if (user.isBlank() || pass.isBlank()) {
                Toast.makeText(this, "Rellene ambos campos", Toast.LENGTH_SHORT).show();
            } else if (user.equals(pass)) {
                Intent i = new Intent(MainActivity.this, activity_registro.class);
                startActivity(i);
            } else {
                Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
            }
        });
    }
}