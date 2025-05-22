package com.examen.deyvivasquez;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    public EditText eUser;
    public EditText ePassword;

    public static final String extrauser = "user";
    public static final String extraPass = "password";

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
        eUser = findViewById(R.id.txtUsuario);
        ePassword = findViewById(R.id.txtPassword);
    }
    public void ingresarUsuario(View view){
        String nombre = eUser.getText().toString();
        String password = ePassword.getText().toString();


        if (validarUsuario(nombre)==0){
            Toast.makeText(this, "Error: El usuario no puede estar vacio : ", Toast.LENGTH_SHORT).show();
        } else if (validarUsuario(nombre)==1) {
            Toast.makeText(this, "Error: El longitud no debe superar los 20 digitos. : "+nombre, Toast.LENGTH_SHORT).show();
        }
        else if (validarUsuario(nombre)==2){
            if(validarPassword(password)==0){
                Toast.makeText(this, "Error: El password no puede estar vacio : ", Toast.LENGTH_SHORT).show();
            }
            else if(validarPassword(password)==1){
                if(validarCuentaIgual(nombre,password)==false){
                    Toast.makeText(this, "Inicio de sesion denegado : ", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(this, "Bienvenido  "+nombre, Toast.LENGTH_SHORT).show();
//                    Intent i = new Intent(this, Principal.class);
//                    i.putExtra(extrauser2,edad);
//                    i.putExtra(extrauser, nombre);
//                    startActivity(i);
                }
            }
        }

    }

    public int validarUsuario(String name){
        if (name.isEmpty()) {
            System.out.println("Error: El usuario no puede estar vacío.");
            return 0;
        }
        else if(name.length() > 20){
            System.out.println("Error: El longitud no debe superar los 20 digitos.");
            return 1;
        }
        else{
            System.out.println("Valor usuario correcto validado");
            return 2;
        }
    }
    public int validarPassword(String name){
        if (name.isEmpty()) {
            System.out.println("Error: El password no puede estar vacío.");
            return 0;
        }
        else{
            System.out.println("Valor usuario correcto validado");
            return 1;
        }
    }

    public boolean validarCuentaIgual(String user, String password){
        if (user.equals(password)) {
            System.out.println("El usuario y la contraseña son iguales.");
            return true;
        } else {
            System.out.println("El usuario y la contraseña son diferentes.");
            return false;
        }
    }
}