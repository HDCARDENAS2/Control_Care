package hd.control_care;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by hernandario on 14/11/2016.
 */
public class BDPeso extends BDConexion {


    public BDPeso(Context context) {
        super(context);
    }


    public ArrayList<Peso> f_get_pesos() {

        ArrayList<Peso> recordsList = new ArrayList<Peso>();

        String sql = " SELECT * FROM peso";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(sql, null);

        while (cursor.moveToNext()) {
            recordsList.add(
                    new Peso(
                            cursor.getInt(cursor.getColumnIndex("id")),
                            cursor.getString(cursor.getColumnIndex("peso")),
                            cursor.getInt(cursor.getColumnIndex("cod_semana")),
                            cursor.getInt(cursor.getColumnIndex("cambio")),
                            cursor.getString(cursor.getColumnIndex("fecha_creacion"))
                    )
            );
        }

        cursor.close();
        db.close();
        Log.e("conexion","consulta divisas");

        return recordsList;
    }

    public boolean create(Peso objeto) {

        ContentValues values = new ContentValues();

        values.put("peso", objeto.getPeso() );
        values.put("cod_semana", objeto.getCod_semana());
        values.put("cambio", objeto.getCambio());
        values.put("fecha_creacion", objeto.getFecha_creacion());

        SQLiteDatabase db = this.getWritableDatabase();

        boolean createSuccessful = db.insert("peso", null, values) > 0;
        db.close();
        Log.e("conexion","se creo usuario");
        return createSuccessful;

    }




}
