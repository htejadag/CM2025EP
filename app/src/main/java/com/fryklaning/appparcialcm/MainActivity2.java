package com.fryklaning.appparcialcm;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.widget.RadioButton;

public class MainActivity2 extends AppCompatActivity {

    public EditText etNombre;
    public EditText etCorreo;
    public EditText etNumeroCelular;
    public EditText etFechaNacimiento;
    public RadioButton r1,r2,r3;


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

        etNombre = findViewById(R.id.txtNombre);
        etCorreo = findViewById(R.id.txtCorreo);
        etNumeroCelular = findViewById(R.id.txtNumCel);
        etFechaNacimiento = findViewById(R.id.txtFechaNacimiento);
        r1=findViewById(R.id.rbtMasculino1);
        r2=findViewById(R.id.rbtFemenino1);
        r3=findViewById(R.id.rbtTyC);
        Button btnValidar = findViewById(R.id.btnValidar);

        r3.setOnCheckedChangeListener((buttonView, isChecked) -> {
            btnValidar.setEnabled(isChecked);
        });

    }

    public void Retroceder(View view){

        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);

    }

    public void Validar(View view){

        String Nombre = etNombre.getText().toString();
        String Correo = etCorreo.getText().toString();
        String NumeroCelular = etNumeroCelular.getText().toString();
        String FechaNacimiento = etFechaNacimiento.getText().toString();

        if (Patterns.EMAIL_ADDRESS.matcher(Correo).matches()) {
            Toast.makeText(this, "Correo valido", Toast.LENGTH_SHORT).show();
        } else {

            Toast.makeText(this, "Correo invalido", Toast.LENGTH_SHORT).show();
        }

        if (r1.isChecked()==true) {
            String Genero = "Masculino";
        } else
        if (r2.isChecked()==true) {
            String Genero = "Femenino";
        }

        if (r3.isChecked()==true) {
            Toast.makeText(this, "Se aceptaron terminos y condiciones", Toast.LENGTH_SHORT).show();
        }

        Toast.makeText(this, "Datos registrados", Toast.LENGTH_SHORT).show();

    }
}