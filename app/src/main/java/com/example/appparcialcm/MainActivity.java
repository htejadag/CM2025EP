package com.example.appparcialcm;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    EditText et1;
    EditText et2;
    EditText et3;
    EditText et4;

    private RadioButton rbtnMasculino;
    private RadioButton rbtnFemenino;

    private CheckBox cbTerminos;
    private Button btnValidar;


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

        et1 = findViewById(R.id.txtNombre);
        et2 = findViewById(R.id.txtCorreo);
        et3 = findViewById(R.id.txtCelular);
        et4 = findViewById(R.id.txtFecha);

        rbtnMasculino = findViewById(R.id.rbtnMasculino);
        rbtnFemenino = findViewById(R.id.rbtnFemenino);

        cbTerminos = findViewById(R.id.cbTerminos);
        btnValidar = findViewById(R.id.btnValidar);
        btnValidar.setEnabled(false);

        cbTerminos.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                btnValidar.setEnabled(isChecked);
            }
        });


    }


    public void validar(View view) {


        String nombre = et1.getText().toString();
        String correo = et2.getText().toString();
        String StrCelular = et3.getText().toString();
        String fecha = et4.getText().toString();

        if (nombre.length() > 15) {
            Toast.makeText(this, "EL NOMBRE ES MAXIMO 15 CARACTERES", Toast.LENGTH_SHORT).show();
        } else if (!Patterns.EMAIL_ADDRESS.matcher(correo).matches()) {
            Toast.makeText(this, "CORREO INVALIDO", Toast.LENGTH_SHORT).show();
        } else if (StrCelular.length() > 9) {
            Toast.makeText(this, "NUMERO DE CELULAR DEMASIADO LARGO", Toast.LENGTH_SHORT).show();
        } else if (!esFechaValida(fecha)) {
            Toast.makeText(this, "LA FECHA ES INVALIDA (FORMATO dd/MM/YYYY)", Toast.LENGTH_SHORT).show();
        } else if (rbtnMasculino.isChecked() && rbtnFemenino.isChecked()) {
            Toast.makeText(this, "SOLO PUEDES SELECCIONAR UN GENERO", Toast.LENGTH_SHORT).show();

        } else if (!cbTerminos.isChecked()) {
            Toast.makeText(this, "ERROR, ACEPTAR TERMINOS Y CONDICIONES", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "DATOS INGRESADOS CORRECTAMENTE", Toast.LENGTH_SHORT).show();
        }


    }

    public boolean esFechaValida(String fecha) {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        sdf.setLenient(false);

        try {

            Date d = sdf.parse(fecha);
            return true;
        } catch (ParseException e) {

            return false;
        }
    }


    public void regresar(View view) {

        Intent i = new Intent(this, LoginActivity.class);

        startActivity(i);


    }

}