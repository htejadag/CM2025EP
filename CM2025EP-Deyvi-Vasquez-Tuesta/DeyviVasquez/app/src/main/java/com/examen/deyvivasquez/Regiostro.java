package com.examen.deyvivasquez;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Regiostro extends AppCompatActivity {
    TextView tv1;
    EditText nombre;
    EditText correo;
    EditText celular;
    EditText fecha;
    RadioGroup grupoSexo;
    CheckBox condiciones;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_regiostro);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        nombre = findViewById(R.id.txtNombre);
        correo = findViewById(R.id.txtCorreo);
        celular = findViewById(R.id.txtCelular);
        fecha = findViewById(R.id.txtNacimiento);
        condiciones = findViewById(R.id.txtxPoliticas);
        grupoSexo = findViewById(R.id.radioGroupSexo);


    }

    public void volverPlantilla(View view){
        Toast.makeText(this, "Volviendo a la plantilla anterior  ", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public void validarEntrar(View view) {
        String nombreStr = nombre.getText().toString().trim();
        String correoStr = correo.getText().toString().trim();
        String celularStr = celular.getText().toString().trim();
        String fechaStr = fecha.getText().toString().trim();


        if (nombreStr.isEmpty() || correoStr.isEmpty() || celularStr.isEmpty() || fechaStr.isEmpty()) {
            Toast.makeText(this, "Llena todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }


        if (nombreStr.length() > 15) {
            nombre.setError("Maximo 15 caracteres");
            return;
        }


        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(correoStr).matches()) {
            correo.setError("Correo invalido");
            return;
        }

        if (!celularStr.matches("\\d{1,9}")) {
            celular.setError("Maximo 9 dígitos numericos");
            return;
        }

       if (fechaStr.length() < 6) {
            fecha.setError("Ingresa una fecha valida");
            return;
        }

        int idSeleccionado = grupoSexo.getCheckedRadioButtonId();
        if (idSeleccionado == -1) {
            Toast.makeText(this, "Selecciona un sexo", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!condiciones.isChecked()) {
            Toast.makeText(this, "Debes aceptar las condiciones", Toast.LENGTH_SHORT).show();
            return;
        }

        RadioButton seleccionado = findViewById(idSeleccionado);
        String sexo = seleccionado.getText().toString();

        Toast.makeText(this, "Validacion correcta ", Toast.LENGTH_SHORT).show();

    }


}