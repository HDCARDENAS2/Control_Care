package hd.control_care;

import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by hernandario on 14/11/2016.
 */
public class DatosTemporales {

    private static String fecha_ultima_regla;


    public static void setFecha(String dato){
        fecha_ultima_regla =  dato;
    }


    public static int getSemana(){


        int resultado = -1;
        String temp   = "";

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        try {

            Log.e("-> ",fecha_ultima_regla);
            Date fechaUltima = sdf.parse(fecha_ultima_regla);
            Calendar fecha = Calendar.getInstance();
            fecha.setTime(fechaUltima);
            int año = fecha.get(Calendar.YEAR);
            int mes = fecha.get(Calendar.MONTH) + 1;
            int dia = fecha.get(Calendar.DAY_OF_MONTH);
            Log.e("->> ",dia+" "+mes+" "+año);
            double diferenciaEn_ms = (new Date().getTime() - fechaUltima.getTime()) / (1000 * 60 * 60 * 24) ;
            Log.e("->>> ",(int)diferenciaEn_ms+" ");
            temp = (int) ( Math.ceil(diferenciaEn_ms / 7 ))+"";

        } catch (ParseException e) {
            Log.e("->>","ERRROR AL OTENER LA SEMANA");

        }

        Log.e("->>>>",temp);
        if(temp != "" ){
            resultado = Integer.parseInt(temp);
        }

        return resultado;
    }

    public static String getFechaHoy(){
        Date fechaActual = new Date();
        //Fecha actual desglosada:
        Calendar fecha = Calendar.getInstance();
        int año = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH) + 1;
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        return año + "-" + mes + "-" + dia;
    }



}
