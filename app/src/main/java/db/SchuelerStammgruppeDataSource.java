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
import model.SchuelerStammgruppe;

public class SchuelerStammgruppeDataSource {

    private static final String TAG = SchuelerStammgruppeDataSource.class.getSimpleName();

    private SQLiteDatabase database;
    private Schueler_StammgruppenDBHelper dbHelper;

    // SchuelerStammgruppeData

    private String[] columns = {
            Schueler_StammgruppenDBHelper.COLUMN_ID,
            Schueler_StammgruppenDBHelper.COLUMN_SCHUELER_ID,
            Schueler_StammgruppenDBHelper.COLUMN_STAMMGRUPPE_ID,


    };

    public SchuelerStammgruppeDataSource(Context context){
        Log.d("Datasourse", "DataSource erzeugt jetzt den dbHelper");
        dbHelper = new Schueler_StammgruppenDBHelper(context);
    }

    public SchuelerStammgruppe createSchuelerStammgruppe(int schuelerId, int stammgruppeId){

        ContentValues values= new ContentValues();
        values.put(Schueler_StammgruppenDBHelper.COLUMN_SCHUELER_ID, schuelerId);
        values.put(Schueler_StammgruppenDBHelper.COLUMN_STAMMGRUPPE_ID, stammgruppeId);


        long insertId = database.insert(Schueler_StammgruppenDBHelper.TABLE_NAME,null,values);

        Log.i("insertid", "." +  insertId);
        Cursor cursor = database.query(Schueler_StammgruppenDBHelper.TABLE_NAME,columns,
                Schueler_StammgruppenDBHelper.COLUMN_ID + "=" + insertId,null,null,null,null);

        cursor.moveToFirst();
        SchuelerStammgruppe schuelerStammgruppe = cursorToSchuelerStammgruppe(cursor);
        cursor.close();
        return schuelerStammgruppe;
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



    public SchuelerStammgruppe updateShoppingMemo(long id, String newProduct, int newQuantity, boolean newChecked){
        int intValueChecked = newChecked? 1 : 0;

        ContentValues values = new ContentValues();
        values.put(KollegeDBHelper.KOLLEGE_COLUMN_VORNAME,newProduct);

        database.update(Schueler_StammgruppenDBHelper.TABLE_NAME,values,KollegeDBHelper.KOLLEGE_COLUMN_ID + "=" + id,
                null);

        Cursor cursor = database.query(KollegeDBHelper.TABLE_NAME,columns,KollegeDBHelper.KOLLEGE_COLUMN_ID +"="+id,
                null,null,null,null);
        cursor.moveToFirst();
        SchuelerStammgruppe memo = cursorToSchuelerStammgruppe(cursor);
        cursor.close();
        return memo;
    }

    public void deleteShoppingMemo(Kollege shoppingMemo){
        long id = shoppingMemo.getId();
        database.delete(KollegeDBHelper.TABLE_NAME,
                KollegeDBHelper.KOLLEGE_COLUMN_ID + "=" + id,null);
        Log.d(TAG, "Eintrag gelöscht! ID: " + id + " Inhalt: " + shoppingMemo.toString());
    }

    private SchuelerStammgruppe cursorToSchuelerStammgruppe(Cursor cursor) {

        int idIndex = cursor.getColumnIndex(Schueler_StammgruppenDBHelper.COLUMN_ID);
        int idSchueler = cursor.getColumnIndex(Schueler_StammgruppenDBHelper.COLUMN_SCHUELER_ID);
        int idStammgruppe = cursor.getColumnIndex(Schueler_StammgruppenDBHelper.COLUMN_STAMMGRUPPE_ID);


        int id = cursor.getInt(idIndex);
        String schueler = cursor.getString(idSchueler);
        String stammgruppe = cursor.getString(idStammgruppe);

        SchuelerStammgruppe schuelerStammgruppe = new SchuelerStammgruppe(schueler, stammgruppe);
        return schuelerStammgruppe;
    }

    public List<SchuelerStammgruppe> getAllSchuelerStammgruppen (){

        List<SchuelerStammgruppe> schuelerStammgruppen = new ArrayList<>();
        Cursor cursor = database.query(Schueler_StammgruppenDBHelper.TABLE_NAME,columns,
                null,null,null,null,null);
        cursor.moveToFirst();
        SchuelerStammgruppe schuelerStammgruppe;

        while (!cursor.isAfterLast()){
            schuelerStammgruppe = cursorToSchuelerStammgruppe(cursor);
            schuelerStammgruppen.add(schuelerStammgruppe);
            Log.d(TAG, "ID: " + schuelerStammgruppe.getSchueler());
            cursor.moveToNext();
        }
        cursor.close();
        return schuelerStammgruppen;
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
