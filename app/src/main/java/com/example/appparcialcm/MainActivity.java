package com.example.appparcialcm;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    public EditText txtNombre, txtCorreo, txtCel,fecha1;
    public RadioGroup radioGroup2;
    public RadioButton rbtnMasculino1, rbtnFemenino2;
    public Button btnEnviar,btnValidar;

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
        txtNombre = findViewById(R.id.txtNombre);
        txtCorreo = findViewById(R.id.txtCorreo);
        txtCel = findViewById(R.id.txtCel);
        fecha1 = findViewById(R.id.fecha1);
        radioGroup2 = findViewById(R.id.radioGroup2);
        rbtnMasculino1 = findViewById(R.id.rbtnMasculino);
        rbtnFemenino2 = findViewById(R.id.rbtnFemenino);
        btnValidar = findViewById(R.id.btnValidar);


    }



    public void Regresar(View view){
        Intent e = new Intent(this, LoginActivity.class);
        startActivity(e);
    }

    public void mostraradvertencia(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }

    public void Validar(View view) {
        String nombre = txtNombre.getText().toString().trim();
        String correo = txtCorreo.getText().toString().trim();
        String telefono = txtCel.getText().toString().trim();
        int selectedId = radioGroup2.getCheckedRadioButtonId();


        if (nombre.isEmpty()) {
            mostraradvertencia("El nombre es obligatorio.");
            return;
        }


        if (correo.isEmpty()) {
            mostraradvertencia("El correo es obligatorio.");
            return;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(correo).matches()) {
            mostraradvertencia("El correo electrónico no es válido.");
            return;
        }


        if (telefono.isEmpty()) {
            mostraradvertencia("El teléfono es obligatorio.");
            return;
        }


        if (selectedId == -1) {
            mostraradvertencia("Debe seleccionar un género.");
            return;
        }


        mostraradvertencia("Datos ingresados correctamente.");
    }

}