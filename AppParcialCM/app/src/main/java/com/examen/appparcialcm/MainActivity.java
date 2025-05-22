package com.examen.appparcialcm;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    public EditText eUsuario;
    public EditText eContrasena;


    public static final String extrauser = "userNombre";
    public static final String extrauser2 = "userEdad";




    @SuppressLint("MissingInflatedId")
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

        eUsuario = findViewById(R.id.txtUsuario);
        eContrasena = findViewById(R.id.txtContrasena);




    }
    public void ingresarSaludar(View view){
        String usuario = eUsuario.getText().toString();
        String contrasena = eContrasena.getText().toString();


        if(validarNombre(usuario)==true){
            if(validarNombre(contrasena)==3){
                Toast.makeText(this, "Error: La edad no puede estar vacio : "+contrasena, Toast.LENGTH_SHORT).show();
            }
            else if(validarNombre(contrasena)==0){
                Toast.makeText(this, "Error: La edad debe ser un número entero : "+contrasena, Toast.LENGTH_SHORT).show();
            }


            }
        }
        else{
            Toast.makeText(this, "Error: El nombre no puede estar vacío : "+eUsuario, Toast.LENGTH_SHORT).show();
        }

    }

    public boolean validarNombre(String name){
        if (name.isEmpty()) {
            System.out.println("Error: El nombre no puede estar vacío.");
            return false;
        }
        else{
            System.out.println("Valor nombre correcto validado");
            return true;
        }
    }



    public int validarCelular(String cel){
        if (cel.isEmpty()) {
            System.out.println("Error: El celular no puede estar vacio ");
            return 0;
        }
        else if (!cel.matches("\\d+")){
            System.out.println("Error: el celular debe ser un numero entero");
            return 1;
        }
        else if (!cel.matches("\\d{9}")) {
            System.out.println("Error: El celular debe contener exactamente 9 dígitos numéricos.");
            return 2;
        }
        else{
            System.out.println("Valor celular correcto validado");
            return 3;
        }
    }

}



