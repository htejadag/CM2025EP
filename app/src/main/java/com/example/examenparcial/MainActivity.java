package com.example.examenparcial;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;


public class MainActivity extends AppCompatActivity {

    EditText editUsuario, editContrasena;
    Button btnIngresar;

    // Puedes cambiar estos valores por los que tú necesites
    final String USUARIO_CORRECTO = "lino";
    final String CONTRASENA_CORRECTA = "12";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editUsuario = findViewById(R.id.editUsuario);
        editContrasena = findViewById(R.id.editContrasena);
        btnIngresar = findViewById(R.id.btnIngresar);

        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usuarioIngresado = editUsuario.getText().toString().trim();
                String contrasenaIngresada = editContrasena.getText().toString().trim();

                if (usuarioIngresado.equals(USUARIO_CORRECTO) && contrasenaIngresada.equals(CONTRASENA_CORRECTA)) {
                    Intent intent = new Intent(MainActivity.this, FormularioActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
