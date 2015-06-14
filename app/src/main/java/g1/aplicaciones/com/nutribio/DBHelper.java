package g1.aplicaciones.com.nutribio;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by PINEDA on 23/05/2015.
 */
public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME="Nutri&Bio.sqlite";
    private static final int DB_SCHEME_VERSION=1;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_SCHEME_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
    sqLiteDatabase.execSQL(DataBaseManager.CREATE_TABLE_CN);
    sqLiteDatabase.execSQL(DataBaseManager.CREATE_TABLE_PR);
    sqLiteDatabase.execSQL(DataBaseManager.CREATE_TABLE_QR);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {

    }
}
