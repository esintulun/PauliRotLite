package db;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import model.Schueler;
import model.Vergehen;

public class DBHelperSchuelerVergehen extends SQLiteOpenHelper {


    // Logcat tag
    private static final String LOG = DBHelperSchuelerVergehen.class.getName();

    // Database Version
    private static final int DATABASE_VERSION = 2;

    // Database Name
    private static final String DATABASE_NAME = "paulilite3";

    // Table Names
    private static final String TABLE_VERGEHEN = "vergehen";  //  vergehen
    private static final String TABLE_SCHUELER = "schueler";  // schueler


    private static final String TABLE_SCHUELER_VERGEHEN = "schueler_vergehen";

    // Common column names: id s
    private static final String KEY_ID = "id";
    private static final String KEY_CREATED_AT = "created_at";

    // GRUPPEN Table - column nmaes
    private static final String KEY_VERGEHEH_NAME = "vergehenname";
    private static final String KEY_STATUS = "status";

    // SCHUELER Table - column names
    private static final String KEY_SCHUELER_NAME = "schuelername";
    private static final String KEY_SCHUELER_KLASSE = "schulklasse";


    // GRUPPEN_SCHUELER Table - column names
    private static final String KEY_VERGEHEN_ID = "vergehen_id";
    private static final String KEY_SCHUELER_ID = "schueler_id";

    // Table Create Statements
    // Gruppe table create statement
    private static final String CREATE_TABLE_VERGEHEN = "CREATE TABLE "
            + TABLE_VERGEHEN + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_VERGEHEH_NAME + " TEXT,"
            + KEY_STATUS + " INTEGER,"
            + KEY_CREATED_AT
            + " DATETIME" + ")";

    // Schueler table create statement
    private static final String CREATE_TABLE_SCHUELER = "CREATE TABLE "
            + TABLE_SCHUELER
            + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_SCHUELER_NAME + " TEXT,"
            + KEY_SCHUELER_KLASSE + " TEXT,"
            + KEY_CREATED_AT + " DATETIME" + ")";

    // gruppe_schueler table table create statement
    private static final String CREATE_TABLE_SCHUELER_VERGEHEN = "CREATE TABLE "
            + TABLE_SCHUELER_VERGEHEN + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_SCHUELER_ID + " INTEGER,"
            + KEY_VERGEHEN_ID + " INTEGER,"
            + KEY_CREATED_AT + " DATETIME" + ")";

    public DBHelperSchuelerVergehen(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // creating required tables
        db.execSQL(CREATE_TABLE_VERGEHEN);
        db.execSQL(CREATE_TABLE_SCHUELER);
        db.execSQL(CREATE_TABLE_SCHUELER_VERGEHEN);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_VERGEHEN);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SCHUELER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SCHUELER_VERGEHEN);

        // create new tables
        onCreate(db);
    }

    /**
     * Creating tag:SCHUELER
     */
    public long createSchueler(Schueler schueler) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_SCHUELER_NAME, schueler.getName());
        values.put(KEY_SCHUELER_KLASSE, schueler.getSchulklasse());
        values.put(KEY_CREATED_AT, getDateTime());

        // insert row
        long tag_id = db.insert(TABLE_SCHUELER, null, values);

        return tag_id;
    }

    /**
     * getting all tags
     * */
    public List<Schueler> getAllSchueler() {

        List<Schueler> tags = new ArrayList<Schueler>();
        String selectQuery = "SELECT  * FROM " + TABLE_SCHUELER;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Schueler schueler = new Schueler();
                schueler.setId(c.getInt((c.getColumnIndex(KEY_ID))));
                schueler.setName(c.getString(c.getColumnIndex(KEY_SCHUELER_NAME)));
                schueler.setSchulklasse(c.getString(c.getColumnIndex(KEY_SCHUELER_KLASSE)));
                Log.i("schueler", "-" + c.getString(c.getColumnIndex(KEY_SCHUELER_NAME))+
                        " Schulklasse: " + c.getString(c.getColumnIndex(KEY_SCHUELER_KLASSE)));

                // adding to tags list
                tags.add(schueler);
            } while (c.moveToNext());
        }
        return tags;
    }


    /**
     * getting a schueler is
     * */
    public int getSchuelerIdBySchuelerName(String schuelerName) {

        String selectQuery = "SELECT *  FROM " + TABLE_SCHUELER + " tg " +
                " WHERE tg." + KEY_SCHUELER_NAME + " = '" + schuelerName + "'";

        Log.e(LOG, selectQuery);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        int id= 0;
        if (c.moveToFirst()){
            do{
                //if you not need the loop you can remove that
               id = c.getInt(c.getColumnIndex(KEY_ID));
            }
            while(c.moveToNext());
        }c.close();
        Log.i("huh", "schuelername" + schuelerName +  " id: " + id);


        return id;
    }

    /**
     * get single schueler
     */
    public Schueler getSchuelerBySchuelerKlasse(String schuelerklasse) {

        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_SCHUELER + " WHERE "
                + KEY_SCHUELER_KLASSE + " = " + schuelerklasse;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        Schueler schueler = new Schueler();
        schueler.setId(c.getInt(c.getColumnIndex(KEY_ID)));
        schueler.setName((c.getString(c.getColumnIndex(KEY_SCHUELER_NAME))));
        schueler.setCreatedAt(c.getString(c.getColumnIndex(KEY_CREATED_AT)));
        schueler.setSchulklasse(c.getString(c.getColumnIndex(KEY_SCHUELER_KLASSE)));

        return schueler;
    }

    /**
     * Updating a schueler
     */
    public int updateSchueler(Schueler schueler) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_SCHUELER_NAME, schueler.getName());

        // updating row
        return db.update(TABLE_SCHUELER, values, KEY_ID + " = ?",
                new String[] { String.valueOf(schueler.getId()) });
    }

    /**
     * Deleting a schueler
     */
    public void deleteSchueler(Schueler tag, boolean should_delete_all_tag_todos) {
        SQLiteDatabase db = this.getWritableDatabase();

        // before deleting tag
        // check if todos under this tag should also be deleted
        if (should_delete_all_tag_todos) {
            // get all gruppe under this schueler
            List<Vergehen> allTagToDos = getAllVergehensBySchueler(tag.getName());

            // delete all gruppen
            for (Vergehen todo : allTagToDos) {
                // delete gruppe
                deleteVergehen(todo.getId());
            }
        }

        // now delete the schueler
        db.delete(TABLE_SCHUELER, KEY_ID + " = ?",
                new String[] { String.valueOf(tag.getId()) });
    }

    // ------------------------ "schueler_vergehen" table methods ----------------//

    /**
     * Creating schueler_vergehen
     */
    public long createSchuelerVergehen(long todo_id, long tag_id) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_SCHUELER_ID, todo_id);
        values.put(KEY_VERGEHEN_ID, tag_id);
        values.put(KEY_CREATED_AT, getDateTime());


        long id = db.insert(TABLE_SCHUELER_VERGEHEN, null, values);

        Log.i("huh", "schueler bei create" + todo_id +  " id: " + tag_id);


        return id;
    }


    /**
     * getting all tags
     * */
    public Map<Integer, Integer> getAllSchuelerVergehen() {

        List<Schueler> tags = new ArrayList<Schueler>();

        String selectQuery = "SELECT  * FROM " + TABLE_SCHUELER_VERGEHEN;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        Map<Integer, Integer> map = new HashMap<>();

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {

               int id =  (c.getInt((c.getColumnIndex(KEY_ID))));
               int schueler_id =  (c.getInt(c.getColumnIndex(KEY_SCHUELER_ID)));
               int vergehen_id = (c.getInt(c.getColumnIndex(KEY_VERGEHEN_ID)));
               map.put(schueler_id, vergehen_id);


            } while (c.moveToNext());
        }
        return map;
    }



    /**
     * Updating a todo tag
     */
    public int updateNoteTag(long id, long schueler_id) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_SCHUELER_ID, schueler_id);

        // updating row
        return db.update(TABLE_VERGEHEN, values, KEY_ID + " = ?",
                new String[] { String.valueOf(id) });
    }

    /**
     * Deleting a todo tag
     */
    public void deleteVergehenTag(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_VERGEHEN, KEY_ID + " = ?",
                new String[] { String.valueOf(id) });
    }




    // ------------------------ "VERGEHEN" table methods ----------------//

    /**
     * Creating a gruppe
     */
    public long createVergehen(Vergehen todo, long[] schueler_ids) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_VERGEHEH_NAME, todo.getTitel());
        values.put(KEY_STATUS, todo.getStatus());
        values.put(KEY_CREATED_AT, getDateTime());

        // insert row
        long gruppe_id = db.insert(TABLE_VERGEHEN, null, values);

        // insert tag_ids
        for (long tag_id : schueler_ids) {
            createSchuelerVergehen(gruppe_id, tag_id);
        }

        Log.i("gruppen: ", "name " + todo.getTitel());

        return gruppe_id;
    }


    public long createVergehen(Vergehen vergehen) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_VERGEHEH_NAME, vergehen.getTitel());
        values.put(KEY_STATUS, vergehen.getStatus());
        values.put(KEY_CREATED_AT, getDateTime());

        // insert row
        long gruppe_id = db.insert(TABLE_VERGEHEN, null, values);
        return gruppe_id;
    }

    /**
     * getting a schueler is
     * */
    public int getVergehenIdByTitel(String title) {

        String selectQuery = "SELECT *  FROM " + TABLE_VERGEHEN + " tg " +
                " WHERE tg." + KEY_VERGEHEH_NAME + " = '" + title + "'";

        Log.e(LOG, selectQuery);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        int id= 0;
        if (c.moveToFirst()){
            do{
                //if you not need the loop you can remove that
                id = c.getInt(c.getColumnIndex(KEY_ID));
            }
            while(c.moveToNext());
        }c.close();

        return id;
    }




    /**
     * get single gruppe
     */
    public Vergehen getSchuelerBySchuelerKlasse(long id) {

        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_VERGEHEN + " WHERE "
                + KEY_ID + " = " + id;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        Vergehen td = new Vergehen();
        td.setId(c.getInt(c.getColumnIndex(KEY_ID)));
        td.setTitel((c.getString(c.getColumnIndex(KEY_VERGEHEH_NAME))));
        td.setCreatedAt(c.getString(c.getColumnIndex(KEY_CREATED_AT)));

        return td;
    }

    /**
     * getting all todos
     * */
    public List<Vergehen> getAllVergehen() {
        List<Vergehen> vergehens = new ArrayList<Vergehen>();
        String selectQuery = "SELECT  * FROM " + TABLE_VERGEHEN;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Vergehen td = new Vergehen();
                td.setId(c.getInt((c.getColumnIndex(KEY_ID))));
                td.setTitel((c.getString(c.getColumnIndex(KEY_VERGEHEH_NAME))));
                td.setCreatedAt(c.getString(c.getColumnIndex(KEY_CREATED_AT)));

                // adding to gruppen list
                vergehens.add(td);

            } while (c.moveToNext());
        }

        return vergehens;
    }


    /**
     * getting all todos under single tag
     * */
    public List<Vergehen> getAllVergehensBySchueler(String schuelerName) {
        List<Vergehen> todos = new ArrayList<Vergehen>();
        String selectQuery = "SELECT  * FROM "
                + TABLE_VERGEHEN + " td, "
                + TABLE_SCHUELER + " tg, "
                + TABLE_SCHUELER_VERGEHEN + " tt" +
                " WHERE tg." + KEY_SCHUELER_NAME + " = '" + schuelerName + "'"
                + " AND tg." + KEY_ID
                + " = " + "tt." + KEY_SCHUELER_ID
                + " AND td." + KEY_ID
                + " = " + "tt." + KEY_VERGEHEN_ID;
        Log.e(LOG, selectQuery);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Vergehen td = new Vergehen();
                td.setId(c.getInt((c.getColumnIndex(KEY_ID))));
                td.setTitel((c.getString(c.getColumnIndex(KEY_VERGEHEH_NAME))));
                td.setCreatedAt(c.getString(c.getColumnIndex(KEY_CREATED_AT)));
                todos.add(td);
            } while (c.moveToNext());
        }

        return todos;
    }




    /**
     * getting todo count
     */
    public int getVergehensCount() {
        String countQuery = "SELECT  * FROM " + TABLE_VERGEHEN;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();

        // return count
        return count;
    }

    /**
     * Updating a todo
     */
    public int updateVergehens(Vergehen todo) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_VERGEHEH_NAME, todo.getTitel());
        values.put(KEY_STATUS, todo.getStatus());

        // updating row
        return db.update(TABLE_VERGEHEN, values, KEY_ID + " = ?",
                new String[] { String.valueOf(todo.getId()) });
    }

    /**
     * Deleting a todo
     */
    public void deleteVergehen(long gruppe_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_VERGEHEN, KEY_ID + " = ?",
                new String[] { String.valueOf(gruppe_id) });
    }

    // ------------------------ "SCHUELER" table methods ----------------//


    // closing database
    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }

    /**
     * get datetime
     * */
    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }



}