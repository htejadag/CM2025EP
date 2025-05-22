package com.example.examenparcial;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText etNombre, etCorreo, etCelular, etFecha;
    RadioGroup rgGenero;
    CheckBox checkTerminos;
    Button btnValidar, btnRegresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Enlazar vistas del layout
        etNombre = findViewById(R.id.etNombre);
        etCorreo = findViewById(R.id.etCorreo);
        etCelular = findViewById(R.id.etCelular);
        etFecha = findViewById(R.id.etFecha);
        rgGenero = findViewById(R.id.rgGenero);
        checkTerminos = findViewById(R.id.checkTerminos);
        btnValidar = findViewById(R.id.btnValidar);
        btnRegresar = findViewById(R.id.btnRegresar);

        btnValidar.setEnabled(false);

        checkTerminos.setOnCheckedChangeListener((buttonView, isChecked) -> {
            btnValidar.setEnabled(isChecked);
        });


        btnValidar.setOnClickListener(v -> {
            if (!validarFormulario()) {
                Toast.makeText(this, "Corrija los errores", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Datos correctos", Toast.LENGTH_LONG).show();
            }
        });


        btnRegresar.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private boolean validarFormulario() {
        boolean valido = true;

        String nombre = etNombre.getText().toString().trim();
        String correo = etCorreo.getText().toString().trim();
        String celular = etCelular.getText().toString().trim();
        String fecha = etFecha.getText().toString().trim();
        int generoId = rgGenero.getCheckedRadioButtonId();

        if (nombre.isEmpty() || nombre.length() > 15) {
            etNombre.setError("Máximo puedes añadir 15 caracteres");
            valido = false;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(correo).matches()) {
            etCorreo.setError("Correo inválido");
            valido = false;
        }

        if (celular.length() != 9 || !celular.matches("\\d{9}")) {
            etCelular.setError("Debe contener exactamente 9 dígitos");
            valido = false;
        }

        if (fecha.isEmpty()) {
            etFecha.setError("Fecha obligatoria");
            valido = false;
        }

        if (generoId == -1) {
            Toast.makeText(this, "Seleccione un género", Toast.LENGTH_SHORT).show();
            valido = false;
        }

        return valido;
    }
}
