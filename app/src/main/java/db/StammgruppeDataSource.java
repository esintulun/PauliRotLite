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
import model.Stammgruppe;

public class StammgruppeDataSource {

    private static final String TAG = StammgruppeDataSource.class.getSimpleName();

    private SQLiteDatabase database;
    private StammgruppeDBHelper dbHelper;

    // Stammgruppe

    private String[] columns = {
            StammgruppeDBHelper.COLUMN_ID,
            StammgruppeDBHelper.COLUMN_NAME,

    };

    public StammgruppeDataSource(Context context){
        Log.d("Datasourse", "DataSource erzeugt jetzt den dbHelper");
        dbHelper = new StammgruppeDBHelper(context);
    }

    public Stammgruppe createStammgruppe(String namee){

        ContentValues values= new ContentValues();
        values.put(StammgruppeDBHelper.COLUMN_NAME, namee);

        long insertId = database.insert(StammgruppeDBHelper.TABLE_NAME,null,values);

        Cursor cursor = database.query(StammgruppeDBHelper.TABLE_NAME,columns,
                StammgruppeDBHelper.COLUMN_ID + "=" + insertId,null,null,null,null);

        cursor.moveToFirst();
        Stammgruppe stammgruppe = cursorToStammgruppe(cursor);
        cursor.close();
        return stammgruppe;
    }


    public List<Kollege> getAllNotes() {

        List<Kollege> noteList = new ArrayList<Kollege>();


        String selectQuery = "SELECT * FROM " + StammgruppeDBHelper.DATABASE_NAME;
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



    public Stammgruppe updateShoppingMemo(long id, String newProduct, int newQuantity, boolean newChecked){
        int intValueChecked = newChecked? 1 : 0;

        ContentValues values = new ContentValues();
        values.put(StammgruppeDBHelper.COLUMN_NAME,newProduct);

        database.update(StammgruppeDBHelper.TABLE_NAME,values,StammgruppeDBHelper.COLUMN_ID + "=" + id,
                null);

        Cursor cursor = database.query(StammgruppeDBHelper.TABLE_NAME,columns,StammgruppeDBHelper.COLUMN_ID +"="+id,
                null,null,null,null);
        cursor.moveToFirst();
        Stammgruppe stammgruppe = cursorToStammgruppe(cursor);
        cursor.close();
        return stammgruppe;
    }

    public void deleteShoppingMemo(Stammgruppe shoppingMemo){
        long id = shoppingMemo.getId();
        database.delete(StammgruppeDBHelper.TABLE_NAME,
                StammgruppeDBHelper.COLUMN_ID + "=" + id,null);
        Log.d(TAG, "Eintrag gelöscht! ID: " + id + " Inhalt: " + shoppingMemo.toString());
    }

    private Stammgruppe cursorToStammgruppe(Cursor cursor) {

        int idIndex = cursor.getColumnIndex(StammgruppeDBHelper.COLUMN_ID);
        int idVorname = cursor.getColumnIndex(StammgruppeDBHelper.COLUMN_NAME);

        int id = cursor.getInt(idIndex);
        String vorname = cursor.getString(idVorname);

        Stammgruppe stammgruppe = new Stammgruppe(id, vorname);
        return stammgruppe;
    }

    public List<Stammgruppe> getAllStammgruppen(){

        List<Stammgruppe> schuelerList = new ArrayList<>();
        Cursor cursor = database.query(StammgruppeDBHelper.TABLE_NAME,columns,
                null,null,null,null,null);
        cursor.moveToFirst();
        Stammgruppe stammgruppe;

        while (!cursor.isAfterLast()){
            stammgruppe = cursorToStammgruppe(cursor);
            schuelerList.add(stammgruppe);
            Log.d(TAG, "ID: " + stammgruppe.getId() + ", Inhalt: " + stammgruppe.toString());
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
