package db;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.HashMap;

public class KollegeDBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "PauliRotLite.db";
    public static final String TABLE_NAME = "kollege";
    public static final String KOLLEGE_COLUMN_ID = "id";
    public static final String KOLLEGE_COLUMN_VORNAME = "vorname";
    public static final String KOLLEGE_COLUMN_NACHNAME  = "nachname";
    public static final String KOLLEGE_COLUMN_PASSWORT= "passwort";
    public static final String KOLLEGE_COLUMN_KUERZEL = "kuerzel";
    public static final String KOLLEGE_COLUMN_STANDORT = "standort";
    public static final String KOLLEGE_COLUMN_STATUS = "status";

    private HashMap hp;

    public KollegeDBHelper(Context context) {
        super(context, DATABASE_NAME , null, 5);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table kollege " +
                        "(id integer primary key, vorname text, nachname text, passwort text, kuerzel text, standort text, status text)"
        );
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS contacts");
        onCreate(db);
    }
}
