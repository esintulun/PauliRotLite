package db;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.HashMap;

public class Schueler_StammgruppenDBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "PauliRotLite.db";


    //CREATE TABLE kollege_schueler_stammgruppe (
    // id INTEGER PRIMARY KEY,
    // kollege_id INTEGER,
    // schueler_id INTEGER,
    // stammgruppe INTEGER);
    public static final String TABLE_NAME = "schueler_stammgruppe";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_SCHUELER_ID = "schuelerId";
    public static final String COLUMN_STAMMGRUPPE_ID  = "stammgruppeId";


    public Schueler_StammgruppenDBHelper(Context context) {

        super(context, DATABASE_NAME , null, 5);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table schueler_stammgruppe " +
                        "(id integer primary key, schuelerId integer, stammgruppeId integer)"
        );
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS schueler_stammgruppe");
        onCreate(db);
    }
}
