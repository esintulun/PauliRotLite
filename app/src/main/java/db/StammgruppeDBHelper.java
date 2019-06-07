package db;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.HashMap;

public class StammgruppeDBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "PauliRotLite.db";

    public static final String TABLE_NAME = "stammgruppen";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";


    private HashMap hp;

    public StammgruppeDBHelper(Context context) {

        super(context, DATABASE_NAME , null, 5);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table stammgruppen " +
                        "(id integer primary key, name text)"
        );
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS stammgruppen");
        onCreate(db);
    }
}
