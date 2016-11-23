package hd.control_care;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class GestorCita extends android.app.Fragment implements View.OnClickListener {

    AlertDialog.Builder builder;
    private Button mDateButton;
    private Button mTimeButton;
    public Calendar mCalendar;
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final String TIME_FORMAT = "kk:mm";
    DialogFragment dateFragment;
    DialogFragment timeFragment;

    public EditText txt_obervaciones;
    private View myview;
    private Context context;
    private ArrayList<HashMap<String, String>> obj_lista;
    private ArrayList<Cita> list_usuario;
    private ListView lista;
    private BDCita BD;

    private final String FECHA  = "FECHA";
    private final String HORA = "HORA";
    private final String OBSERVA   = "OBSERVA";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        myview = inflater.inflate(R.layout.vista_cita, container, false);
        context = myview.getContext();
        lista = (ListView) myview.findViewById(R.id.lista_cita);
        BD = new BDCita(context);

        mTimeButton = (Button) myview.findViewById(R.id.btn_hora_cita);
        mDateButton = (Button) myview.findViewById(R.id.btn_fecha_cita);
        txt_obervaciones = (EditText) myview.findViewById(R.id.input_observaciones);
        mCalendar = Calendar.getInstance();

        mDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(v);
            }
        });
        mTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog(v);
            }
        });

        Button registrar = (Button) myview.findViewById(R.id.btn_registrarCita);
        registrar.setOnClickListener(this);

        AdaptarLista();
        return myview;
    }

    public void showDatePickerDialog(View v) {
        dateFragment = new DatePickerFragment();
        dateFragment.show(getFragmentManager(), "datePicker");

    }

    public void showTimePickerDialog(View v) {
        timeFragment = new TimePickerFragment();
        timeFragment.show(getFragmentManager(), "timePicker");
    }

    public void updateDateButtonText() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        String dateForButton = dateFormat.format(mCalendar.getTime());
        mDateButton.setText(dateForButton);
    }

    private void updateTimeButtonText() {
        SimpleDateFormat timeFormat = new SimpleDateFormat(TIME_FORMAT);
        String timeForButton = timeFormat.format(mCalendar.getTime());
        mTimeButton.setText(timeForButton);
    }

    class DatePickerFragment extends DialogFragment implements
            DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            mCalendar.set(Calendar.YEAR, year);
            mCalendar.set(Calendar.MONTH, month);
            mCalendar.set(Calendar.DAY_OF_MONTH, day);
            updateDateButtonText();
        }
    }

    public class TimePickerFragment extends DialogFragment implements
            TimePickerDialog.OnTimeSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            mCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
            mCalendar.set(Calendar.MINUTE, minute);
            updateTimeButtonText();
        }
    }

    @Override
    public void onClick(View view) {
        Button boton = (Button) view;

        if(boton.getId() == R.id.btn_registrarCita){
            Toast toast = null;

            try {
                boolean registro = false;
                
                String fecha_cita    = mDateButton.getText().toString();
                String hora_cita = mTimeButton.getText().toString();
                String observaciones  = txt_obervaciones.getText().toString();

                if(fecha_cita.length() > 0 ){
                    if(hora_cita.length() > 0 ){
                        registro  = true;
                    }else{
                        toast = Toast.makeText(context, "Seleccione una hora para la cita", Toast.LENGTH_SHORT);
                    }
                }else{
                    toast = Toast.makeText(context, "Seleccione una fecha para la cita", Toast.LENGTH_SHORT);
                }

                if(registro) {
                    Cita obj_cita = new Cita( fecha_cita, hora_cita, observaciones );
                    Log.d("cita", obj_cita.toString());
                    if(BD.create(obj_cita)) {
                        toast = Toast.makeText(context, "Cita registrada con exito.", Toast.LENGTH_SHORT);
                        txt_obervaciones.setText("");
                        AdaptarLista();
                    } else {
                        toast = Toast.makeText(context, "No se pudo registrar la Cita.", Toast.LENGTH_SHORT);
                    }
                }

            } catch (Exception e) {
                toast = Toast.makeText(context, "Error" + e.getMessage(), Toast.LENGTH_SHORT);
            }

            toast.show();

        }
    }

    private void AdaptarLista(){

        obj_lista = new ArrayList<HashMap<String, String>>();
        list_usuario = BD.f_get_cita();

        for ( Cita datos : list_usuario ) {

            HashMap<String, String> dato = new HashMap<String, String>();
            dato.put(FECHA, (String) datos.getFecha_cita()+"");
            dato.put(HORA, (String) datos.getHora_cita()+"");
            dato.put(OBSERVA, (String) datos.getObservacion()+"");
            obj_lista.add(dato);
        }

        String[] from = new String[] {FECHA,HORA,OBSERVA};

        int[] to = new int[] {
                R.id.tv_ls_fecha_cita,
                R.id.tv_ls_hora_cita,
                R.id.tv_ls_observacion};

        ListAdapter adapter = new SimpleAdapter(context, obj_lista, R.layout.lista_cita, from, to);
        lista.setAdapter(adapter);

    }
}
