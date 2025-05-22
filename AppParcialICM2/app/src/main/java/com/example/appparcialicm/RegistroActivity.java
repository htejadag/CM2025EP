package com.example.appparcialicm;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

public class RegistroActivity extends AppCompatActivity {

    private EditText etNombre, etCorreo, etCelular, etFecha;
    private RadioGroup rgGenero;
    private CheckBox cbTerminos;
    private Button btnValidar, btnRegresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        etNombre = findViewById(R.id.etNombre);
        etCorreo = findViewById(R.id.etCorreo);
        etCelular = findViewById(R.id.etCelular);
        etFecha = findViewById(R.id.etFecha);
        rgGenero = findViewById(R.id.rgGenero);
        cbTerminos = findViewById(R.id.cbTerminos);
        btnValidar = findViewById(R.id.btnValidar);
        btnRegresar = findViewById(R.id.btnRegresar);

        btnValidar.setEnabled(false);

        cbTerminos.setOnCheckedChangeListener((buttonView, isChecked) -> {
            btnValidar.setEnabled(isChecked);
        });

        btnValidar.setOnClickListener(v -> {
            if (!validarFormulario()) {
                mostrarAlerta("Por favor complete correctamente todos los campos.");
            } else {
                Toast.makeText(this, "Datos validados correctamente", Toast.LENGTH_LONG).show();
            }
        });

        btnRegresar.setOnClickListener(v -> finish()); // Volver a MainActivity
    }

    private boolean validarFormulario() {
        String nombre = etNombre.getText().toString();
        String correo = etCorreo.getText().toString();
        String celular = etCelular.getText().toString();
        String fecha = etFecha.getText().toString();
        int idGenero = rgGenero.getCheckedRadioButtonId();

        if (nombre.isEmpty() || nombre.length() > 15) return false;
        if (!correo.matches("^[\\w.-]+@[\\w.-]+\\.\\w+$")) return false;
        if (!celular.matches("^\\d{9}$")) return false;
        if (fecha.isEmpty()) return false;
        if (idGenero == -1) return false;
        if (!cbTerminos.isChecked()) return false;

        return true;
    }

    private void mostrarAlerta(String mensaje) {
        new AlertDialog.Builder(this)
                .setTitle("Error")
                .setMessage(mensaje)
                .setPositiveButton("OK", null)
                .show();
    }
}
