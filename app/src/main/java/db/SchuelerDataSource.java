package db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import model.Kollege;
import model.Schueler;

public class SchuelerDataSource {

    private static final String TAG = SchuelerDataSource.class.getSimpleName();

    private SQLiteDatabase database;
    private SchuelerDBHelper dbHelper;

    // Schueler

    private String[] columns = {
            SchuelerDBHelper.COLUMN_ID,
            SchuelerDBHelper.COLUMN_NAME,

    };

    public SchuelerDataSource(Context context){
        Log.d("Datasourse", "DataSource erzeugt jetzt den dbHelper");
        dbHelper = new SchuelerDBHelper(context);
    }

    public Schueler createSchueler(String name){

        ContentValues values= new ContentValues();
        values.put(SchuelerDBHelper.COLUMN_NAME, name);

        long insertId = database.insert(SchuelerDBHelper.TABLE_NAME,null,values);

        Log.i("insertid", "." +  insertId);
        Cursor cursor = database.query(SchuelerDBHelper.TABLE_NAME,columns,
                SchuelerDBHelper.COLUMN_ID + "=" + insertId,null,null,null,null);

        cursor.moveToFirst();
        Schueler schueler = cursorToSchueler(cursor);
        cursor.close();
        return schueler;
    }


    public List<Kollege> getAllNotes() {

        List<Kollege> noteList = new ArrayList<Kollege>();


        String selectQuery = "SELECT * FROM " + KollegeDBHelper.DATABASE_NAME;
        Cursor cursor = database.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Kollege note = new Kollege();
                note.setId(Integer.parseInt(cursor.getString(0)));
                note.setVorname(cursor.getString(1));
                // Adding note to list
                noteList.add(note);
            } while (cursor.moveToNext());
        }
        // return note list
        return noteList;
    }



    public Schueler updateShoppingMemo(long id, String newProduct, int newQuantity, boolean newChecked){
        int intValueChecked = newChecked? 1 : 0;

        ContentValues values = new ContentValues();
        values.put(KollegeDBHelper.KOLLEGE_COLUMN_VORNAME,newProduct);

        database.update(KollegeDBHelper.TABLE_NAME,values,KollegeDBHelper.KOLLEGE_COLUMN_ID + "=" + id,
                null);

        Cursor cursor = database.query(KollegeDBHelper.TABLE_NAME,columns,KollegeDBHelper.KOLLEGE_COLUMN_ID +"="+id,
                null,null,null,null);
        cursor.moveToFirst();
        Schueler memo = cursorToSchueler(cursor);
        cursor.close();
        return memo;
    }

    public void deleteShoppingMemo(Kollege shoppingMemo){
        long id = shoppingMemo.getId();
        database.delete(KollegeDBHelper.TABLE_NAME,
                KollegeDBHelper.KOLLEGE_COLUMN_ID + "=" + id,null);
        Log.d(TAG, "Eintrag gelöscht! ID: " + id + " Inhalt: " + shoppingMemo.toString());
    }

    private Schueler cursorToSchueler(Cursor cursor) {

        int idIndex = cursor.getColumnIndex(SchuelerDBHelper.COLUMN_ID);
        int idVorname = cursor.getColumnIndex(SchuelerDBHelper.COLUMN_NAME);

        int id = cursor.getInt(idIndex);
        String vorname = cursor.getString(idVorname);

        Schueler schueler = new Schueler(id, vorname);
        return schueler;
    }

    public List<Schueler> getAllSchueler(){

        List<Schueler> schuelerList = new ArrayList<>();
        Cursor cursor = database.query(SchuelerDBHelper.TABLE_NAME,columns,
                null,null,null,null,null);
        cursor.moveToFirst();
        Schueler schueler;

        while (!cursor.isAfterLast()){
            schueler = cursorToSchueler(cursor);
            schuelerList.add(schueler);
            Log.d(TAG, "ID: " + schueler.getId() + ", Inhalt: " + schueler.toString());
            cursor.moveToNext();
        }
        cursor.close();
        return schuelerList;
    }


    public void open(){
        Log.d(TAG, "Eine Referenz auf die Datenbank wird angefragt.");
        database = dbHelper.getWritableDatabase();
        Log.d(TAG, "open: Referenz erhalten. Pfad zur DB: " + database.getPath());
    }

    public void close(){
        dbHelper.close();
        Log.d(TAG, "Datenbank mit DbHelper geschlossen");
    }
}
