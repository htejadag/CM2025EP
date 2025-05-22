package com.example.examenparcial;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
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

import com.google.android.material.snackbar.Snackbar;

public class Registro extends AppCompatActivity {

    public EditText eNombre, eFecha, eCelular, eCorreo;
    public CheckBox cTermino;
    public RadioGroup rGenero;
    public Button bRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registro);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        eNombre = findViewById(R.id.txtNombre);
        eFecha = findViewById(R.id.txtFecha);
        eCelular = findViewById(R.id.txtTelefono);
        eCorreo = findViewById(R.id.txtCorreo);
        cTermino = findViewById(R.id.chkTerminos);
        rGenero = findViewById(R.id.grGenero);
        bRegistrar = findViewById(R.id.btnRegistrar);

        bRegistrar.setEnabled(false);
        cTermino.setOnCheckedChangeListener((buttonView, isChecked) -> {
            bRegistrar.setEnabled(isChecked);
            bRegistrar.setAlpha(isChecked ? 1f : 0.5f);
        });
    }

    public void registrar(View view){
        if(validar(view)){
            String nombre = eNombre.getText().toString();
            Toast.makeText(this, "Te registraste exitosamente  " + nombre, Toast.LENGTH_SHORT).show();

            Intent i = new Intent(this, Inicio.class);
            startActivity(i);
        }
    }
    public Boolean validar(View view){
        String nombre = eNombre.getText().toString();
        String fecha = String.valueOf(eFecha.getText());
        String celular = eCelular.getText().toString();
        String correo = eCorreo.getText().toString();
        boolean acepto = cTermino.isChecked();
        if(acepto){
            if(nombre.isBlank() || fecha.isBlank() || celular.isBlank() || correo.isBlank() || getGenero(view) == null) return mostrarError(view,"Debes llenar todos los campos");
            if(nombre.length() > 15) return mostrarError(view, "El nombre no debe pasar los 15 caracteres");
            if(!Patterns.EMAIL_ADDRESS.matcher(correo).matches()) return mostrarError(view, "El formato de correo debe ser valido");
            if(celular.length() != 9) return mostrarError(view, "El celular debe tener 9 digitos.");
        }else{
            mostrarError(view, "Debes aceptar lso terminos y condiciones");
            return false;
        }

        return true;
    }

    private Character getGenero(View view){
        int opc = rGenero.getCheckedRadioButtonId();
        if (opc == -1){
            mostrarError(view, "Debes seleccionar un genero");
            return null;
        }
        return (opc == R.id.radioMasculino) ? 'm' : 'f';
    }

    private boolean mostrarError(View view, String msg){
        Snackbar.make(view,msg,Snackbar.LENGTH_SHORT).show();
        return false;
    }

    public void toLogin(View view){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}