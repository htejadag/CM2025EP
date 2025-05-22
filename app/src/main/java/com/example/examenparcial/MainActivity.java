package com.example.examenparcial;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private static final String USUARIO_CORRECTO = "admin";
    private static final String CONTRASENA_CORRECTA = "1234";
    EditText etUsuario, etContrasena;
    Button btnIngresar;


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

        etUsuario = findViewById(R.id.etUsuario);
        etContrasena = findViewById(R.id.etContrasenia);
        btnIngresar = findViewById(R.id.btnLogin);

        btnIngresar.setOnClickListener(v -> {
            String usuarioIngresado = etUsuario.getText().toString();
            String contrasenaIngresada = etContrasena.getText().toString();

            if (usuarioIngresado.equals(USUARIO_CORRECTO) && contrasenaIngresada.equals(CONTRASENA_CORRECTA)) {
                Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, DatosPersonales.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

