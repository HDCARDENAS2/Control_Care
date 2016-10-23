package hd.control_care;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class GestorContracciones extends android.app.Fragment implements View.OnClickListener{

    private View myview;
    private Context context;


    @Nullable
    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        myview= inflater.inflate(R.layout.vista_contracciones,container,false);
        context = myview.getContext();


        return myview;

    }



    @Override
    public void onClick(View view) {

        Button boton = (Button) view;

    }

}
