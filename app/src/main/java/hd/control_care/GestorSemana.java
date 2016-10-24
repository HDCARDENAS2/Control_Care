package hd.control_care;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

public class GestorSemana extends android.app.Fragment implements View.OnClickListener{

    private View myview;
    private Context context;
    private ImageView myImage;

    private static final Integer[] IMAGENES = {
            R.drawable.img_semana1,
            R.drawable.img_semana2,
            R.drawable.img_semana3,
            R.drawable.img_semana4,
            R.drawable.img_semana5,
            R.drawable.img_semana6,
            R.drawable.img_semana7,
            R.drawable.img_semana8,
            R.drawable.img_semana9,
            R.drawable.img_semana10,
            R.drawable.img_semana11,
            R.drawable.img_semana12,
            R.drawable.img_semana13,
            R.drawable.img_semana14,
            R.drawable.img_semana15,
            R.drawable.img_semana16,
            R.drawable.img_semana17,
            R.drawable.img_semana18,
            R.drawable.img_semana19,
            R.drawable.img_semana20,
            R.drawable.img_semana21,
            R.drawable.img_semana22,
            R.drawable.img_semana23,
            R.drawable.img_semana24,
            R.drawable.img_semana25,
            R.drawable.img_semana26,
            R.drawable.img_semana27,
            R.drawable.img_semana28,
            R.drawable.img_semana29,
            R.drawable.img_semana30,
            R.drawable.img_semana31,
            R.drawable.img_semana32,
            R.drawable.img_semana33,
            R.drawable.img_semana34,
            R.drawable.img_semana35,
            R.drawable.img_semana36,
            R.drawable.img_semana37,
            R.drawable.img_semana38,
            R.drawable.img_semana39,
            R.drawable.img_semana40
    };

    private int posicion;
    private int maximo;
    private Button an_sm;
    private Button sg_sm;
    private TextView des_semana;

    @Nullable
    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        myview= inflater.inflate(R.layout.vista_gestor_semana,container,false);
        context = myview.getContext();

        maximo = IMAGENES.length;
        posicion = 0;

        des_semana = (TextView) myview.findViewById(R.id.tv_semana);

        myImage = (ImageView) myview.findViewById(R.id.imageView);
        myImage.setImageResource(IMAGENES[posicion]);
        myImage.getLayoutParams().height = (int) getResources().getDimension(R.dimen.imageview_height);

        setSemana(posicion);

        an_sm = (Button) myview.findViewById(R.id.btn_an_semana);
        an_sm.setOnClickListener(this);
        sg_sm = (Button) myview.findViewById(R.id.btn_sg_semana);
        sg_sm.setOnClickListener(this);

        if(posicion == 0){
            an_sm.setEnabled(false);
        } else if(posicion == maximo-1){
            sg_sm.setEnabled(false);
        }

        return myview;

    }

    @Override
    public void onClick(View view) {

        int posicion_final=-1;
        int posicion_temp = posicion;

        if(view.getId() == R.id.btn_an_semana){
            posicion_temp--;
            if(posicion_temp != -1){
                sg_sm.setEnabled(true);
                posicion_final = posicion_temp;
                setSemana(posicion_final);
                if( posicion_temp == 0){
                    an_sm.setEnabled(false);
                }
            }

        }else if(view.getId() == R.id.btn_sg_semana){
            posicion_temp++;
            if(posicion_temp < maximo){
                an_sm.setEnabled(true);
                posicion_final = posicion_temp;
                setSemana(posicion_final);
                if( posicion_temp == (maximo-1) ){
                    sg_sm.setEnabled(false);
                }
            }
        }

        if( posicion_final != -1){
            posicion = posicion_final;
            myImage.setImageResource(IMAGENES[posicion]);
        }

    }

    private void setSemana(int dato){
        dato=dato+1;
        des_semana.setText("Semana "+dato);
    }





}
