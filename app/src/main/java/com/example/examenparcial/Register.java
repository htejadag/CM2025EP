package com.example.examenparcial;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.examenparcial.Entities.Person;

public class Register extends AppCompatActivity {

    String[] generos = {"Masculino", "Femenino", "Otro", "Prefiero no decir"};

    EditText etr1,etr2,etr3,etr4;
    Spinner spiner1;
    CheckBox checkBox1;
    Button btnvalidar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        etr1 = findViewById(R.id.etr1);
        etr2 = findViewById(R.id.etr2);
        etr3 = findViewById(R.id.etr3);
        etr4 = findViewById(R.id.etr4);
        spiner1 = findViewById(R.id.spinner1);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, generos);
        spiner1.setAdapter(adapter);
        checkBox1 = findViewById(R.id.checkBox1);
        btnvalidar = findViewById(R.id.button3);
        btnvalidar.setEnabled(false);

    }


    public boolean validar(String nom,String corr,String num,String fech){

        if(nom.isEmpty() && corr.isEmpty() && num.isEmpty() && fech.isEmpty()){
            return false;
        }else{
            if(nom.length() > 15){
                etr1.setError("Nombre requerido y máximo 15 caracteres");
                return false;
            }
            if(!android.util.Patterns.EMAIL_ADDRESS.matcher(corr).matches()){
                etr2.setError("Correo electrónico inválido");
                return false;

            }
            if(!num.matches("\\d{9}")){
                etr3.setError("Celular debe tener exactamente 9 dígitos");
                return false;
            }
            if(spiner1.getSelectedItem()==null){
                Toast.makeText(this, "debe elegir un genero", Toast.LENGTH_SHORT).show();
                return false;
            }
            if(!checkBox1.isChecked()){
                checkBox1.setError("Debe aceptar los terminos y condiciones");
                return false;
            }
            else{return true;}
        }
    }

    public void volver(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        clear();
    }

    public void Registrar(View view){
        String nombre = etr1.getText().toString();
        String correo = etr2.getText().toString();
        String numero = etr3.getText().toString();
        String fecha = etr4.getText().toString();
        String genero = spiner1.getSelectedItem().toString();
        if(validar(nombre,correo,numero,fecha)){
            btnvalidar.setEnabled(true);
            Person p = new Person(nombre,correo,numero,fecha,genero);
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("user2",p);
            startActivity(intent);
            clear();
        }else{
            Toast.makeText(this, "error en registro", Toast.LENGTH_SHORT).show();
        }
    }

    public void Validado(){
        Toast.makeText(this, "Se ha validado todos los datos correctamente", Toast.LENGTH_SHORT).show();
    }

    public void clear(){
        etr1.setText("");
        etr2.setText("");
        etr3.setText("");
        etr4.setText("");
        spiner1.setSelection(0);
        checkBox1.setSelected(false);
    }

}