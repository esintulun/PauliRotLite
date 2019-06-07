package db;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.HashMap;

public class SchuelerDBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "PauliRotLite.db";

    public static final String TABLE_NAME = "schueler";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_NACHNAME  = "nachname";

    private HashMap hp;

    public SchuelerDBHelper(Context context) {

        super(context, DATABASE_NAME , null, 5);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table schueler " +
                        "(id integer primary key, name text, nachname text)"
        );
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS schueler");
        onCreate(db);
    }
}
