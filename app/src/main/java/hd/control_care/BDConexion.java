package hd.control_care;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by MSI on 22/10/2016.
 */
public class BDConexion extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "control_care";

    public BDConexion(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE citas (" +
                "  id integer primary key autoincrement," +
                "  fecha_cita TEXT NOT NULL," +
                "  fecha_creacion TEXT NOT NULL," +
                "  hora_cita TEXT NOT NULL, "+
                "  observacion TEXT NOT NULL" +
                "); ";
        db.execSQL(sql);
        Log.e("conexion ", " se creo tabla citas");

         sql = "CREATE TABLE consejos_semana (" +
                "  id integer NOT NULL," +
                "  cod_semana integer NOT NULL," +
                "  descripcion TEXT NOT NULL" +
                "); ";
         db.execSQL(sql);
         Log.e("conexion ", " se creo tabla consejos_semana");

         sql = "CREATE TABLE consejos_varios (" +
                "  id integer NOT NULL," +
                "  descripcion TEXT NOT NULL," +
                "  estado integer NOT NULL" +
                "); ";
        db.execSQL(sql);
        Log.e("conexion ", " se creo tabla consejos_varios");

        sql = "CREATE TABLE contracciones (" +
                "  id integer primary key autoincrement," +
                "  hora_incio TEXT NOT NULL," +
                "  hora_fin TEXT NOT NULL," +
                "  duracion NUMERIC," +
                "  frecuencia TEXT" +
                "); ";
        db.execSQL(sql);
        Log.e("conexion ", " se creo tabla contracciones");

        sql = "CREATE TABLE datos_usuario (" +
                "  nombres TEXT NOT NULL," +
                "  apellidos TEXT NOT NULL," +
                "  estatura TEXT NOT NULL," +
                "  fecha_ultima_regla TEXT NOT NULL"+
                "); ";
        db.execSQL(sql);
        Log.e("conexion ", " se creo tabla datos_usuario");

        sql = "CREATE TABLE imagenes_app (" +
                "  cod_semana integer NOT NULL," +
                "  nombre_imagen TEXT NOT NULL" +
                ");";
        db.execSQL(sql);
        Log.e("conexion ", " se creo tabla imagenes_app");

        sql = "CREATE TABLE imagenes_usuario (" +
                "  id integer primary key autoincrement," +
                "  cod_semana integer NOT NULL," +
                "  nombre_imagen TEXT NOT NULL," +
                "  fecha_creacion TEXT NOT NULL" +
                "); ";
        db.execSQL(sql);
        Log.e("conexion ", " se creo tabla imagenes_usuario");

        sql =   "CREATE TABLE peso (" +
                "  id integer primary key autoincrement," +
                "  peso TEXT NOT NULL," +
                "  cod_semana integer NOT NULL," +
                "  cambio NUMERIC NOT NULL," +
                "  fecha_creacion TEXT NOT NULL" +
                "); ";
        db.execSQL(sql);

        Log.e("conexion ", "se creo tabla peso");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String sql = "DROP TABLE citas";
        db.execSQL(sql);
        Log.e("conexion ", " se borro la tabla citas");

        sql = "DROP TABLE consejos_semana";
        db.execSQL(sql);
        Log.e("conexion ", " se borro la tabla consejos_semana");

        sql = "DROP TABLE consejos_varios";
        db.execSQL(sql);
        Log.e("conexion ", " se borro la tabla consejos_varios");

        sql = "DROP TABLE contracciones";
        db.execSQL(sql);
        Log.e("conexion ", " se borro la tabla contracciones");

        sql =  "DROP TABLE datos_usuario";
        db.execSQL(sql);
        Log.e("conexion ", " se borro la tabla datos_usuario");

        sql =  "DROP TABLE imagenes_app";
        db.execSQL(sql);
        Log.e("conexion ", " se borro la tabla imagenes_app");

        sql = "DROP TABLE imagenes_usuario";
        db.execSQL(sql);
        Log.e("conexion ", " se borro la tabla imagenes_usuario");

        sql =  "DROP TABLE peso";
        db.execSQL(sql);
        Log.e("conexion ", " se borro la tabla peso");

        onCreate(db);

    }



}
