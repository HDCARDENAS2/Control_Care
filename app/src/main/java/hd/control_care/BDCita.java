package hd.control_care;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Hernan on 14/11/2016.
 */
public class BDCita extends BDConexion {

    public BDCita(Context context) {
        super(context);
    }


    public ArrayList<Cita> f_get_cita() {

        ArrayList<Cita> recordsList = new ArrayList<Cita>();

        String sql = " SELECT * FROM citas";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(sql, null);

        while (cursor.moveToNext()) {
            recordsList.add(
                    new Cita(
                            cursor.getString(cursor.getColumnIndex("fecha_cita")),
                            cursor.getString(cursor.getColumnIndex("hora_cita")),
                            cursor.getString(cursor.getColumnIndex("observacion"))
                    )
            );
        }

        cursor.close();
        db.close();
        Log.e("conexion","consulta citas");

        return recordsList;
    }

    public boolean create(Cita objeto) {

        ContentValues values = new ContentValues();

        values.put("fecha_cita", objeto.getFecha_cita() );
        values.put("fecha_creacion", DatosTemporales.getFechaHoy());
        values.put("hora_cita", objeto.getHora_cita());
        values.put("observacion", objeto.getObservacion());

        SQLiteDatabase db = this.getWritableDatabase();

        boolean createSuccessful = db.insert("citas", null, values) > 0;
        db.close();
        return createSuccessful;
    }
}
