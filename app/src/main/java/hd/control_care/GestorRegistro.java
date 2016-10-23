package hd.control_care;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class GestorRegistro extends AppCompatActivity implements View.OnClickListener{

    AlertDialog.Builder builder;
    private Button mPickDate;
    private int mYear;
    private int mMonth;
    private int mDay;
    static final int DATE_DIALOG_ID = 0;
    private BDUsuario BD;

    private EditText txt_nombres;
    private EditText txt_apellidos;
    private EditText txt_estatura;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vista_datos);

        mPickDate = (Button) findViewById(R.id.btn_fecha_regla);
        mPickDate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(DATE_DIALOG_ID);
            }
        });

        // get the current date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        BD = new BDUsuario(this);

        txt_nombres   = (EditText) findViewById(R.id.input_nombres);
        txt_apellidos = (EditText) findViewById(R.id. input_apellidos);
        txt_estatura  = (EditText) findViewById(R.id. input_estatura);

        Button registrar = (Button) findViewById(R.id.btn_registrarUsuario);
        registrar.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Button boton = (Button) view;

        if(boton.getId() == R.id.btn_registrarUsuario){

          Toast toast = null;

          try {

             boolean registro = false;

             String nombre    = txt_nombres.getText().toString().trim();
             String apellidos = txt_apellidos.getText().toString().trim();
             String estatura  = txt_estatura.getText().toString().trim();
             String fecha     =  mPickDate.getText().toString();

             if(nombre.length() > 0 ){
                 if(apellidos.length() > 0 ){
                     if(estatura.length() > 0 ){
                         if(fecha.length() > 0 ){
                             registro  = true;
                         }else{
                             toast = Toast.makeText(this, "Seleciona una fecha..", Toast.LENGTH_SHORT);
                         }
                     }else{
                         toast = Toast.makeText(this, "El campo estatura esta vacio.", Toast.LENGTH_SHORT);
                     }
                 }else{
                     toast = Toast.makeText(this, "El campo apellidos esta vacio.", Toast.LENGTH_SHORT);
             }
             }else{
                 toast = Toast.makeText(this, "El campo nombre esta vacio.", Toast.LENGTH_SHORT);
             }

              if(registro) {
                 if(BD.create(new Usuario(nombre,apellidos,estatura,fecha))) {
                     toast = Toast.makeText(this, "Usuario Registrado con exito.", Toast.LENGTH_SHORT);
                     Intent i = new Intent(this,MainActivity.class);
                     startActivity(i);
                     finish();
                 }
              }

            } catch (Exception e) {
                toast = Toast.makeText(this, "Error" + e.getMessage(), Toast.LENGTH_SHORT);
            }

            toast.show();

        }

    }

    private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            mYear = year;
            mMonth = monthOfYear;
            mDay = dayOfMonth;
            updateDisplay();
        }
    };

    private void updateDisplay() {
        String dia;
        String mes;

        if (mDay > 9) {
            dia = mDay + "";
        } else {
            dia = "0" + mDay;
        }
        int aux = mMonth + 1;
        if (aux > 9) {
            mes = mMonth + 1 + "";
        } else {
            mes = "0" + aux;
        }
        mPickDate.setText(new StringBuilder()
                .append(dia).append("-").append(mes).append("-").append(mYear)
                .append(""));
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this, mDateSetListener, mYear, mMonth,
                        mDay);
        }
        return null;
    }







}
