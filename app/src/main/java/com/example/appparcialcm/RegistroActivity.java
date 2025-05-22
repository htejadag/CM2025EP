package com.example.appparcialcm;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.examenparcial.R;

public class RegistroActivity extends AppCompatActivity {

    EditText etNombre, etCorreo, etCelular, etFechaNacimiento;
    RadioGroup rgGenero;
    CheckBox cbTerminos;
    Button btnValidar, btnRegresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        etNombre = findViewById(R.id.etNombre);
        etCorreo = findViewById(R.id.etCorreo);
        etCelular = findViewById(R.id.etCelular);
        etFechaNacimiento = findViewById(R.id.etFechaNacimiento);
        rgGenero = findViewById(R.id.rgGenero);
        cbTerminos = findViewById(R.id.cbTerminos);
        btnValidar = findViewById(R.id.btnValidar);
        btnRegresar = findViewById(R.id.btnRegresar);

        btnRegresar.setOnClickListener(v -> finish());

        btnValidar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!cbTerminos.isChecked()) {
                    showAlert("Debe aceptar los términos y condiciones");
                    return;
                }

                String nombre = etNombre.getText().toString();
                String correo = etCorreo.getText().toString();
                String celular = etCelular.getText().toString();
                String fecha = etFechaNacimiento.getText().toString();
                int generoId = rgGenero.getCheckedRadioButtonId();

                if (nombre.isEmpty() || nombre.length() > 15) {
                    showAlert("Nombre inválido (máx 15 caracteres)");
                } else if (!correo.contains("@") || !correo.contains(".")) {
                    showAlert("Correo inválido");
                } else if (celular.length() != 9) {
                    showAlert("Número de celular debe tener 9 dígitos");
                } else if (fecha.isEmpty()) {
                    showAlert("Fecha de nacimiento inválida");
                } else if (generoId == -1) {
                    showAlert("Debe seleccionar un género");
                } else {
                    showAlert("Registro válido. Todos los datos son correctos.");
                }
            }
        });
    }

    private void showAlert(String mensaje) {
        new AlertDialog.Builder(RegistroActivity.this)
            .setTitle("Alerta")
            .setMessage(mensaje)
            .setPositiveButton("OK", null)
            .show();
    }
}