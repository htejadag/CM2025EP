package com.examen.appparcialcm;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.regex.Pattern;

public class RegistroCM extends AppCompatActivity {

    public EditText eNombre;
    public EditText eCorreo;
    public EditText eCelular;
    public EditText eFechaNac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registro_cm);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        eNombre = findViewById(R.id.txtNombre);
        eCorreo = findViewById(R.id.txtCorreo);
        eCelular = findViewById(R.id.txtCelular);
        eFechaNac = findViewById(R.id.txtFechaNac);
    }

    public void ingresarRegistro(View view) {
        String nombre = eNombre.getText().toString().trim();
        String fechanac = eFechaNac.getText().toString().trim();
        String celular = eCelular.getText().toString().trim();
        String correo = eCorreo.getText().toString().trim();

        if (!validarNombre(nombre)) {
            Toast.makeText(this, "Nombre inválido", Toast.LENGTH_SHORT).show();
            return;
        }

        int resultadoCelular = validarCelular(celular);
        if (resultadoCelular == 0) {
            Toast.makeText(this, "El celular no puede estar vacío", Toast.LENGTH_SHORT).show();
            return;
        } else if (resultadoCelular == 1) {
            Toast.makeText(this, "El celular debe ser numérico", Toast.LENGTH_SHORT).show();
            return;
        } else if (resultadoCelular == 2) {
            Toast.makeText(this, "El celular debe tener 9 dígitos", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!validarCorreo(correo)) {
            Toast.makeText(this, "Correo inválido", Toast.LENGTH_SHORT).show();
            return;
        }

        Toast.makeText(this, "Registro exitoso", Toast.LENGTH_LONG).show();
    }

    public boolean validarNombre(String name) {
        return !name.isEmpty();
    }

    public int validarCelular(String celular) {
        if (celular.isEmpty()) {
            return 0;
        } else if (!celular.matches("\\d+")) {
            return 1;
        } else if (!celular.matches("\\d{9}")) {
            return 2;
        } else {
            return 3;
        }
    }

    public boolean validarCorreo(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return Pattern.matches(emailRegex, email);
    }
}
