package com.example.examenparcial;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class registropersona extends AppCompatActivity {
public EditText txt_nombre;
    public EditText txt_correo;
    public EditText txt_numero;
    public RadioGroup sex_grupo;
    public CheckBox chk_terminos;
    public Button btn_validar;
    public Button btn_retornar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registropersona);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        txt_nombre = findViewById(R.id.txtnombre);
        txt_numero = findViewById(R.id.txtnumero);
        txt_correo = findViewById(R.id.txtcorreo);
        sex_grupo = findViewById(R.id.sexoGroup);
        chk_terminos = findViewById(R.id.chkTerminos);
        btn_retornar = findViewById((R.id.btnretornar));
        btn_retornar.setOnClickListener(v -> {
            Intent intent = new Intent(registropersona.this, MainActivity.class);
            startActivity(intent);
        });
        btn_validar = findViewById((R.id.btnValidar));

        btn_validar.setOnClickListener(v -> {
            validarFormulario();
        });


    }
    private void validarFormulario(){
        String nombre = txt_nombre.getText().toString();
        String correo = txt_correo.getText().toString();
        String numero = txt_numero.getText().toString();
        if (nombre.isEmpty()) {
            Toast.makeText(this, "Por favor ingrese su nombre", Toast.LENGTH_SHORT).show();
            return;

        }
        if (correo.isEmpty() || !correo.contains("@")) {
            Toast.makeText(this, "Por favor ingrese un correo válido", Toast.LENGTH_SHORT).show();
            return;
        }
        if (numero.length() != 9) {
            Toast.makeText(this, "Por favor ingrese un número válido (9 dígitos)", Toast.LENGTH_SHORT).show();
            return;
        }
        int selectedSexoId = sex_grupo.getCheckedRadioButtonId();
        if (selectedSexoId == -1) {
            Toast.makeText(this, "Por favor seleccione su sexo", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!chk_terminos.isChecked()) {
            Toast.makeText(this, "Debe aceptar los términos y condiciones", Toast.LENGTH_SHORT).show();
            return;
        }
        Toast.makeText(this, "Formulario válido, puedes continuar", Toast.LENGTH_SHORT).show();

    }

}