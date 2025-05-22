package com.example.examenparcial;

import android.os.Bundle;
import android.view.View;
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

public class MainActivity extends AppCompatActivity {

    EditText etNombre;
    EditText etCorreo;
    EditText etCelular;
    EditText etFechNac;
    Button btnValidar;
    RadioGroup etG;
    EditText etGM;
    EditText etGF;
    CheckBox etTyC;
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

        etNombre = findViewById(R.id.txtNombre);
        etCorreo = findViewById(R.id.txtCorreo);
        etCelular = findViewById(R.id.txtCelular);
        etFechNac = findViewById(R.id.txtFechNac);
        btnValidar = findViewById(R.id.btnValidar);
       // etG = findViewById(R.id.rbtGrupoGenero);
//        etGM = findViewById(R.id.rbtnH);
//        etGF = findViewById(R.id.rBtnM);
        etTyC = findViewById(R.id.checkTyC);
        etTyC.setOnCheckedChangeListener((buttonView, isChecked) -> btnValidar.setEnabled(isChecked));

    }

    public void Validar(View view){
        String nombre = etNombre.getText().toString();
        String correo = etCorreo.getText().toString();
        String celular = etCelular.getText().toString();
        String fechNac = etFechNac.getText().toString();

        if(nombre.compareTo("")==0){
            Toast.makeText(this, "ingrese un nombre", Toast.LENGTH_LONG).show();
        }
        if(correo.compareTo("")==0){
            Toast.makeText(this, "ingrese su correo", Toast.LENGTH_LONG).show();
        }
        if(celular.compareTo("")==0){
            Toast.makeText(this, "ingrese un numero de celular", Toast.LENGTH_LONG).show();
        }
        if(fechNac.compareTo("")==0){
            Toast.makeText(this, "ingrese una fecha de nacimiento", Toast.LENGTH_LONG).show();
        }
        


    }


}