package com.example.appparcialicm;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText etUsuario = findViewById(R.id.etUsuario);
        EditText etPassword = findViewById(R.id.etPassword);
        Button btnIngresar = findViewById(R.id.btnIngresar);

        etPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

        btnIngresar.setOnClickListener(v -> {
            String usuario = etUsuario.getText().toString();
            String password = etPassword.getText().toString();

            if (usuario.length() > 20) {
                Toast.makeText(this, "El usuario no puede tener más de 20 caracteres", Toast.LENGTH_SHORT).show();
                return;
            }

            if (usuario.equals(password)) {
                // Cambiar RegistroActivity.class cuando la crees
                Intent intent = new Intent(MainActivity.this, RegistroActivity.class);
                startActivity(intent);
            } else {
                new AlertDialog.Builder(this)
                        .setTitle("Error")
                        .setMessage("Credenciales incorrectas")
                        .setPositiveButton("Aceptar", null)
                        .show();
            }
        });
    }
}
