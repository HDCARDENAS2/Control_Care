package hd.control_care;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class Gestorpeso extends android.app.Fragment implements View.OnClickListener{

    private View myview;
    private Context context;
    private ArrayList<HashMap<String, String>> obj_lista;
    private ListView lista;
    private BDPeso BD ;
    private EditText input_pesoActual;
    private ArrayList<Peso> list_usuario;

    private final String FECHA  = "FECHA";
    private final String SEMANA = "SEMANA";
    private final String PESO   = "PESO";
    private final String CAMBIO = "CAMBIO";

    @Nullable
    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        myview    = inflater.inflate(R.layout.vista_peso,container,false);
        context   = myview.getContext();
        lista = (ListView) myview.findViewById(R.id.ListView_peso);
        BD = new BDPeso(context);
        input_pesoActual = (EditText) myview.findViewById(R.id.input_pesoActual);
        Button registrar = (Button) myview.findViewById(R.id.btn_registrarPeso);
        registrar.setOnClickListener(this);

        AdaptarLista();
        return myview;

    }


    private void AdaptarLista(){

        obj_lista = new ArrayList<HashMap<String, String>>();
        list_usuario = BD.f_get_pesos();

        for ( Peso datos : list_usuario ) {

            HashMap<String, String> dato = new HashMap<String, String>();
            dato.put(FECHA, (String) datos.getFecha_creacion()+"");
            dato.put(SEMANA, (String) ""+datos.getCod_semana()+"S");
            dato.put(PESO, (String) datos.getPeso()+"Kg");
            if( datos.getCambio() > 0){
                dato.put(CAMBIO, (String) "+"+datos.getCambio());
            }else {
                dato.put(CAMBIO,  (String) ""+datos.getCambio());
            }
            obj_lista.add(dato);
        }

        String[] from = new String[] {FECHA,SEMANA,PESO,CAMBIO};
        int[] to = new int[] {
                R.id.tv_ls_peso_fecha,
                R.id.tv_ls_peso_semana,
                R.id.tv_ls_peso_peso,
                R.id.tv_ls_peso_cambio};

        ListAdapter adapter = new SimpleAdapter(context,obj_lista,R.layout.lista_base_peso, from, to);
        lista.setAdapter(adapter);
    }


    @Override
    public void onClick(View view) {

        Button boton = (Button) view;

        if(boton.getId() == R.id.btn_registrarPeso){

            Toast toast = null;

            try {

                boolean registro = false;
                String peso_txt = input_pesoActual.getText().toString().toString();

                if(peso_txt.length() > 0 ){
                    registro = true;
                }else{
                    toast = Toast.makeText(context, "El campo peso esta vacio.", Toast.LENGTH_SHORT);
                }

                if(registro) {

                    int peso = Integer.parseInt(peso_txt);
                    int diferencia = 0;

                    if(list_usuario.size() > 0 ){
                        Peso ultimo = list_usuario.get(list_usuario.size()-1);
                        int ultimo_peso = Integer.parseInt(ultimo.getPeso());
                        diferencia = peso-ultimo_peso;
                    }

                    Peso obj_peso =  new Peso(0,peso_txt,DatosTemporales.getSemana(),diferencia,DatosTemporales.getFechaHoy());

                    if(BD.create(obj_peso)){
                        toast = Toast.makeText(context, "Peso Registrado con exito.", Toast.LENGTH_SHORT);
                        input_pesoActual.setText("");
                        AdaptarLista();
                    }

                }

            } catch (Exception e) {
                toast = Toast.makeText(context, "Error" + e.getMessage(), Toast.LENGTH_SHORT);
            }
            toast.show();


        }

    }
}
