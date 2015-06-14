package g1.aplicaciones.com.nutribio;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


/**
 * Created by PINEDA on 23/05/2015.
 */
public class DataBaseManager {

    public static final String TABLE_CN="consejos";
    public static final String CN_ID="_id";
    public static final String CN_NAME="titulo";
    public static final String CN_CATEGORY="categoria";
    public static final String CN_DESCRIPTION="descripcion";

    public static final String TABLE_PR="productos";
    public static final String PR_ID="_id";
    public static final String PR_CODE="codigo";
    public static final String PR_NAME="nombre";
    public static final String PR_CALORIES="calorias";

    public static final String TABLE_QR="recetas";
    public static final String QR_ID="_id";
    public static final String QR_NAME="nombre";
    public static final String QR_IMG = "foto";

    public static final String CREATE_TABLE_PR="create table " + TABLE_PR + " ("
            + PR_ID + " integer primary key autoincrement,"
            + PR_CODE + " text not null,"
            + PR_NAME + " text not null,"
            + PR_CALORIES + " text not null);";

    public static final String CREATE_TABLE_CN="create table " + TABLE_CN + " ("
            + CN_ID + " integer primary key autoincrement,"
            + CN_NAME + " text not null,"
            + CN_CATEGORY + " text not null,"
            + CN_DESCRIPTION + " text not null);";

    public static final String CREATE_TABLE_QR="create table " + TABLE_QR + " ("
            + QR_ID + " integer primary key autoincrement,"
            + QR_NAME + " text not null,"
            + QR_IMG + " blob not null);";

    private DBHelper helper;
    private SQLiteDatabase db;

    public DataBaseManager(Context context) {
    helper=new DBHelper(context);
    db=helper.getWritableDatabase();
    }

    public ContentValues generarContentValuesReceta(String nombre, byte[] foto){
        ContentValues valores=new ContentValues();
        valores.put(QR_NAME,nombre);
        valores.put(QR_IMG,foto);
        return valores;
    }

    public void insertarReceta(String nombre, byte[] foto){
    db.insert(TABLE_QR,null,generarContentValuesReceta(nombre, foto));
    }

    public ContentValues generarContentValuesConsejo(String titulo,String categoria, String descripcion){
    ContentValues valores=new ContentValues();
    valores.put(CN_NAME,titulo);
    valores.put(CN_CATEGORY,categoria);
    valores.put(CN_DESCRIPTION,descripcion);
    return valores;
    }

    public void insertarConsejo(String titulo,String categoria, String descripcion){
    db.insert(TABLE_CN,null,generarContentValuesConsejo(titulo,categoria,descripcion));
    }

    public Cursor cargarCursorConsejos(){
    String[] columnas=new String[]{CN_ID,CN_NAME,CN_CATEGORY,CN_DESCRIPTION};
    return db.query(TABLE_CN,columnas,null,null,null,null,null);
    }

    public Cursor buscarConsejo(String busqueda){
    String[] columnas=new String[]{CN_ID,CN_NAME,CN_CATEGORY,CN_DESCRIPTION};
    Cursor cursor=db.query(TABLE_CN,columnas,CN_NAME + "=?",new String[]{busqueda},null,null,null);
    if(cursor!=null && cursor.getCount()>0){
    return cursor;
    }
    else{
    return db.query(TABLE_CN,columnas,CN_CATEGORY + "=?",new String[]{busqueda},null,null,null);
    }
    }


    public ContentValues generarContentValuesProducto(String codigo,String nombre, String calorias){
    ContentValues valores=new ContentValues();
    valores.put(PR_CODE,codigo);
    valores.put(PR_NAME,nombre);
    valores.put(PR_CALORIES,calorias);
    return valores;
    }

    public void insertarProducto(String codigo,String nombre, String calorias){
    db.insert(TABLE_PR,null,generarContentValuesProducto(codigo,nombre,calorias));
    }

    public Cursor cargarCursorProductos(){
    String[] columnas=new String[]{PR_ID,PR_CODE,PR_NAME,PR_CALORIES};
    return db.query(TABLE_PR,columnas,null,null,null,null,null);
    }

    public Cursor buscarProducto(String busqueda){
    String[] columnas=new String[]{PR_ID,PR_CODE,PR_NAME,PR_CALORIES};
    Cursor cursor= db.query(TABLE_PR,columnas,PR_NAME + "=?",new String[]{busqueda},null,null,null);
    if(cursor!=null && cursor.getCount()>0){
    return cursor;
    }
    else{
    return db.query(TABLE_PR,columnas,PR_CODE + "=?",new String[]{busqueda},null,null,null);
    }
    }

}



