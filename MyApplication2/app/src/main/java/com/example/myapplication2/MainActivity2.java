package com.example.myapplication2;

import android.os.Bundle;
import android.view.View;
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
import android.widget.CheckBox;

public class MainActivity2 extends AppCompatActivity {
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
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        nombre = findViewById(R.id.txt11);
        correo = findViewById(R.id.txt12);
        celular = findViewById(R.id.txt13);
        fecha = findViewById(R.id.txt14);
        condiciones = findViewById(R.id.checkBox);
        grupoSexo = findViewById(R.id.radioGroupSexo);



    }


    public void regresar(View view)
    {
        finish();

    }
    public void validar(View view) {
        String nombreStr = nombre.getText().toString().trim();
        String correoStr = correo.getText().toString().trim();
        String celularStr = celular.getText().toString().trim();
        String fechaStr = fecha.getText().toString().trim();

        // Validar campos vacíos
        if (nombreStr.isEmpty() || correoStr.isEmpty() || celularStr.isEmpty() || fechaStr.isEmpty()) {
            Toast.makeText(this, "Por favor llena todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        // Validar longitud del nombre
        if (nombreStr.length() > 15) {
            nombre.setError("Máximo 15 caracteres");
            return;
        }

        // Validar correo
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(correoStr).matches()) {
            correo.setError("Correo inválido");
            return;
        }

        // Validar celular: solo dígitos y hasta 9 caracteres
        if (!celularStr.matches("\\d{1,9}")) {
            celular.setError("Máximo 9 dígitos numéricos");
            return;
        }

        // Validar fecha mínima (solo que tenga longitud válida, puedes agregar DatePicker luego)
        if (fechaStr.length() < 6) {
            fecha.setError("Ingresa una fecha válida");
            return;
        }

        // Validar que un sexo esté seleccionado
        int idSeleccionado = grupoSexo.getCheckedRadioButtonId();
        if (idSeleccionado == -1) {
            Toast.makeText(this, "Selecciona un sexo", Toast.LENGTH_SHORT).show();
            return;
        }

        // Validar condiciones
        if (!condiciones.isChecked()) {
            Toast.makeText(this, "Debes aceptar las condiciones", Toast.LENGTH_SHORT).show();
            return;
        }

        // Si todo es válido
        RadioButton seleccionado = findViewById(idSeleccionado);
        String sexo = seleccionado.getText().toString();

        Toast.makeText(this, "Validación correcta. Sexo: " + sexo, Toast.LENGTH_SHORT).show();

        // Aquí podrías continuar a otra actividad si lo deseas
    }


}