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
import java.util.List;
import java.util.Locale;

import model.Schueler;
import model.Stammgruppe;

public class DatabaseHelperPauli extends SQLiteOpenHelper {


    // Logcat tag
    private static final String LOG = DatabaseHelperPauli.class.getName();

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "paulilitetest";

    // Table Names
    private static final String TABLE_GRUPPE = "gruppen";  //  gruppe


    private static final String TABLE_SCHUELER = "schueler";  // schueler


    private static final String TABLE_GRUPPE_SCHUELER = "gruppen_schueler";

    // Common column names: id s
    private static final String KEY_ID = "id";
    private static final String KEY_CREATED_AT = "created_at";

    // GRUPPEN Table - column nmaes
    private static final String KEY_GRUPPE_NAME = "gruppenname";
    private static final String KEY_STATUS = "status";

    // SCHUELER Table - column names
    private static final String KEY_SCHUELER_NAME = "schuelername";
    private static final String KEY_SCHUELER_KLASSE = "schulklasse";


    // GRUPPEN_SCHUELER Table - column names
    private static final String KEY_GRUPPE_ID = "gruppe_id";
    private static final String KEY_SCHUELER_ID = "schueler_id";

    // Table Create Statements
    // Gruppe table create statement
    private static final String CREATE_TABLE_GRUPPE = "CREATE TABLE "
            + TABLE_GRUPPE + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_GRUPPE_NAME + " TEXT,"
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
    private static final String CREATE_TABLE_GRUPPE_SCHUELER = "CREATE TABLE "
            + TABLE_GRUPPE_SCHUELER + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_GRUPPE_ID + " INTEGER,"
            + KEY_SCHUELER_ID + " INTEGER,"
            + KEY_CREATED_AT + " DATETIME" + ")";

    public DatabaseHelperPauli(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // creating required tables
        db.execSQL(CREATE_TABLE_GRUPPE);
        db.execSQL(CREATE_TABLE_SCHUELER);
        db.execSQL(CREATE_TABLE_GRUPPE_SCHUELER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GRUPPE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SCHUELER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GRUPPE_SCHUELER);

        // create new tables
        onCreate(db);
    }

    // ------------------------ "Gruppen" table methods ----------------//

    /**
     * Creating a gruppe
     */
    public long createGruppe(Stammgruppe todo, long[] schueler_ids) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_GRUPPE_NAME, todo.getName());
        values.put(KEY_STATUS, todo.getStatus());
        values.put(KEY_CREATED_AT, getDateTime());

        // insert row
        long gruppe_id = db.insert(TABLE_GRUPPE, null, values);

        // insert tag_ids
        for (long tag_id : schueler_ids) {
            createGruppeSchueler(gruppe_id, tag_id);
        }

        Log.i("gruppen: ", "name " + todo.getName());

        return gruppe_id;
    }

    /**
     * get single gruppe
     */
    public Stammgruppe getSchuelerBySchuelerklasse(long gruppe_id) {

        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_GRUPPE + " WHERE "
                + KEY_ID + " = " + gruppe_id;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        Stammgruppe td = new Stammgruppe();
        td.setId(c.getInt(c.getColumnIndex(KEY_ID)));
        td.setName((c.getString(c.getColumnIndex(KEY_GRUPPE_NAME))));
        td.setCreatedAt(c.getString(c.getColumnIndex(KEY_CREATED_AT)));

        return td;
    }

    /**
     * getting all todos
     * */
    public List<Stammgruppe> getAllGruppen() {
        List<Stammgruppe> gruppen = new ArrayList<Stammgruppe>();
        String selectQuery = "SELECT  * FROM " + TABLE_GRUPPE;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Stammgruppe td = new Stammgruppe();
                td.setId(c.getInt((c.getColumnIndex(KEY_ID))));
                td.setName((c.getString(c.getColumnIndex(KEY_GRUPPE_NAME))));
                td.setCreatedAt(c.getString(c.getColumnIndex(KEY_CREATED_AT)));

                // adding to gruppen list
                gruppen.add(td);

            } while (c.moveToNext());
        }

        return gruppen;
    }


    /**
     * getting all todos under single tag
     * */
    public List<Stammgruppe> getAllGruppesBySchueler(String schuelerName) {
        List<Stammgruppe> todos = new ArrayList<Stammgruppe>();

        String selectQuery = "SELECT  * FROM "
                + TABLE_GRUPPE + " td, "
                + TABLE_SCHUELER + " tg, "
                + TABLE_GRUPPE_SCHUELER + " tt" +
                " WHERE tg." + KEY_SCHUELER_NAME + " = '" + schuelerName + "'"
                + " AND tg." + KEY_ID
                + " = " + "tt." + KEY_SCHUELER_ID
                + " AND td." + KEY_ID
                + " = " + "tt." + KEY_GRUPPE_ID;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Stammgruppe td = new Stammgruppe();
                td.setId(c.getInt((c.getColumnIndex(KEY_ID))));
                td.setName((c.getString(c.getColumnIndex(KEY_GRUPPE_NAME))));
                td.setCreatedAt(c.getString(c.getColumnIndex(KEY_CREATED_AT)));

                // adding to gruppen list
                /*Log.e("gruppe ", "für g " + schuelerName + "---" + (c.getString(c.getColumnIndex(KEY_GRUPPE_NAME))));
                Log.e("gruppe ", "+++ " +"--" + (c.getString(c.getColumnIndex(KEY_GRUPPE_ID))));

                Log.e("gruppe ", "sid " +"--" + (c.getString(c.getColumnIndex(KEY_SCHUELER_ID))));
                Log.e("gruppe ", "sname " +"--" + (c.getString(c.getColumnIndex(KEY_SCHUELER_NAME))));*/



                todos.add(td);
            } while (c.moveToNext());
        }


        return todos;
    }

    /**
     * getting todo count
     */
    public int getGruppenCount() {
        String countQuery = "SELECT  * FROM " + TABLE_GRUPPE;
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
    public int updateGruppen(Stammgruppe todo) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_GRUPPE_NAME, todo.getName());
        values.put(KEY_STATUS, todo.getStatus());

        // updating row
        return db.update(TABLE_GRUPPE, values, KEY_ID + " = ?",
                new String[] { String.valueOf(todo.getId()) });
    }

    /**
     * Deleting a todo
     */
    public void deleteGruppen(long gruppe_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_GRUPPE, KEY_ID + " = ?",
                new String[] { String.valueOf(gruppe_id) });
    }

    // ------------------------ "SCHUELER" table methods ----------------//

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
     * get single gruppe
     */
    public Schueler getSchuelerBySchuelerklasse(String schuelerklasse) {

        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_SCHUELER + " WHERE "
                + KEY_SCHUELER_KLASSE + " = " + schuelerklasse;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        Schueler schueler = new Schueler();
        schueler.setId(c.getInt(c.getColumnIndex(KEY_ID)));
        schueler.setName((c.getString(c.getColumnIndex(KEY_GRUPPE_NAME))));
        schueler.setCreatedAt(c.getString(c.getColumnIndex(KEY_CREATED_AT)));
        schueler.setSchulklasse(c.getString(c.getColumnIndex(KEY_SCHUELER_KLASSE)));

        return schueler;
    }

    /**
     * Updating a tag
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
     * Deleting a tag
     */
    public void deleteSchueler(Schueler tag, boolean should_delete_all_tag_todos) {
        SQLiteDatabase db = this.getWritableDatabase();

        // before deleting tag
        // check if todos under this tag should also be deleted
        if (should_delete_all_tag_todos) {
            // get all gruppe under this schueler
            List<Stammgruppe> allTagToDos = getAllGruppesBySchueler(tag.getName());

            // delete all gruppen
            for (Stammgruppe todo : allTagToDos) {
                // delete gruppe
                deleteGruppen(todo.getId());
            }
        }

        // now delete the schueler
        db.delete(TABLE_SCHUELER, KEY_ID + " = ?",
                new String[] { String.valueOf(tag.getId()) });
    }

    // ------------------------ "todo_tags" table methods ----------------//

    /**
     * Creating gruppen_schueler
     */
    public long createGruppeSchueler(long todo_id, long tag_id) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_GRUPPE_ID, todo_id);
        values.put(KEY_SCHUELER_ID, tag_id);
        values.put(KEY_CREATED_AT, getDateTime());


        long id = db.insert(TABLE_GRUPPE_SCHUELER, null, values);

        return id;
    }

    /**
     * Updating a todo tag
     */
    public int updateNoteTag(long id, long schueler_id) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_SCHUELER_ID, schueler_id);

        // updating row
        return db.update(TABLE_GRUPPE, values, KEY_ID + " = ?",
                new String[] { String.valueOf(id) });
    }

    /**
     * Deleting a todo tag
     */
    public void deleteStammgruppeTag(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_GRUPPE, KEY_ID + " = ?",
                new String[] { String.valueOf(id) });
    }

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