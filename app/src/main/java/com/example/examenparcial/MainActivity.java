package com.example.examenparcial;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.examenparcial.R;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    EditText etNombre, etCorreo, etCelular, etFechaNacimiento;
    RadioGroup rgGenero;
    CheckBox cbTerminos;
    Button btnValidar, btnRegresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNombre = findViewById(R.id.etNombre);
        etCorreo = findViewById(R.id.etCorreo);
        etCelular = findViewById(R.id.etCelular);
        etFechaNacimiento = findViewById(R.id.etFechaNacimiento);
        rgGenero = findViewById(R.id.rgGenero);
        cbTerminos = findViewById(R.id.cbTerminos);
        btnValidar = findViewById(R.id.btnValidar);
        btnRegresar = findViewById(R.id.btnRegresar);

        etNombre.setFilters(new InputFilter[] {new InputFilter.LengthFilter(15)});
        etCelular.setFilters(new InputFilter[] {new InputFilter.LengthFilter(9)});

        etFechaNacimiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dpd = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int y, int m, int d) {
                        String fecha = String.format("%02d/%02d/%04d", d, m+1, y);
                        etFechaNacimiento.setText(fecha);
                    }
                }, year, month, day);
                dpd.show();
            }
        });

        btnValidar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!cbTerminos.isChecked()) {
                    Toast.makeText(MainActivity.this, getString(R.string.msg_aceptar_terminos), Toast.LENGTH_SHORT).show();
                    return;
                }

                if (validarCampos()) {
                    Toast.makeText(MainActivity.this, getString(R.string.msg_registro_exitoso), Toast.LENGTH_SHORT).show();
                    // Aquí podría continuar la lógica posterior al registro
                }
            }
        });

        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, com.example.examenparcial.LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private boolean validarCampos() {

        String nombre = etNombre.getText().toString().trim();
        String correo = etCorreo.getText().toString().trim();
        String celular = etCelular.getText().toString().trim();
        String fechaNacimiento = etFechaNacimiento.getText().toString().trim();
        int generoId = rgGenero.getCheckedRadioButtonId();

        if (nombre.isEmpty() || correo.isEmpty() || celular.isEmpty() || fechaNacimiento.isEmpty() || generoId == -1) {
            Toast.makeText(this, getString(R.string.msg_complete_campos), Toast.LENGTH_SHORT).show();
            return false;
        }

        if (nombre.length() > 15) {
            Toast.makeText(this, getString(R.string.msg_nombre_max), Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(correo).matches()) {
            Toast.makeText(this, getString(R.string.msg_correo_invalido), Toast.LENGTH_SHORT).show();
            return false;
        }

        if (celular.length() != 9) {
            Toast.makeText(this, getString(R.string.msg_celular_invalid), Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!validarFecha(fechaNacimiento)) {
            Toast.makeText(this, getString(R.string.msg_fecha_invalida), Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private boolean validarFecha(String fecha) {

        try {
            String[] partes = fecha.split("/");
            if (partes.length != 3) return false;

            int d = Integer.parseInt(partes[0]);
            int m = Integer.parseInt(partes[1]);
            int y = Integer.parseInt(partes[2]);

            if (d < 1 || d > 31) return false;
            if (m < 1 || m > 12) return false;
            if (y < 1900) return false;

            Calendar fechaSel = Calendar.getInstance();
            fechaSel.set(y, m - 1, d);

            Calendar hoy = Calendar.getInstance();

            if (fechaSel.after(hoy)) return false;

            return true;

        } catch (Exception e) {
            return false;
        }
    }
}
