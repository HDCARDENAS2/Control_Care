package hd.control_care;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by hernandario on 22/10/2016.
 */
public class BDUsuario extends BDConexion {


    public BDUsuario(Context context) {
        super(context);
    }

    public ArrayList<Usuario> f_get_usuario() {

        ArrayList<Usuario> recordsList = new ArrayList<Usuario>();

        String sql = " SELECT * FROM datos_usuario";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(sql, null);

        while (cursor.moveToNext()) {
            recordsList.add(
                    new Usuario(
                             cursor.getString(cursor.getColumnIndex("nombres")),
                             cursor.getString(cursor.getColumnIndex("apellidos")),
                             cursor.getString(cursor.getColumnIndex("estatura")),
                             cursor.getString(cursor.getColumnIndex("fecha_ultima_regla"))
                            )
            );
        }

        cursor.close();
        db.close();
        Log.e("conexion","consulta divisas");

        return recordsList;
    }

    public boolean create(Usuario objeto) {

        ContentValues values = new ContentValues();

        values.put("nombres", objeto.getNombres() );
        values.put("apellidos", objeto.getApellidos());
        values.put("estatura", objeto.getEstatura());
        values.put("fecha_ultima_regla", objeto.getFecha_ultima_regla());

        SQLiteDatabase db = this.getWritableDatabase();

        boolean createSuccessful = db.insert("datos_usuario", null, values) > 0;
        db.close();
        Log.e("conexion","se creo usuario");
        return createSuccessful;

    }






}
