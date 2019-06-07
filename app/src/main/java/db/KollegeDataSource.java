package db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import model.Kollege;

public class KollegeDataSource {

    private static final String TAG = KollegeDataSource.class.getSimpleName();

    private SQLiteDatabase database;
    private KollegeDBHelper dbHelper;

    private String[] columns = {
            KollegeDBHelper.KOLLEGE_COLUMN_ID,
            KollegeDBHelper.KOLLEGE_COLUMN_VORNAME,

    };

    public KollegeDataSource(Context context){
        Log.d("Datasourse", "DataSource erzeugt jetzt den dbHelper");
        dbHelper = new KollegeDBHelper(context);
    }

    public Kollege createShoppingMemo(String name){
        ContentValues values= new ContentValues();
        values.put(KollegeDBHelper.KOLLEGE_COLUMN_VORNAME, name);

        long insertId = database.insert(KollegeDBHelper.TABLE_NAME,null,values);

        Cursor cursor = database.query(KollegeDBHelper.TABLE_NAME,columns,
                KollegeDBHelper.KOLLEGE_COLUMN_ID + "=" + insertId,null,null,null,null);

        cursor.moveToFirst();
        Kollege shoppingMemo = cursorToKollege(cursor);
        cursor.close();
        return shoppingMemo;
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



    public Kollege updateShoppingMemo(long id, String newProduct, int newQuantity, boolean newChecked){
        int intValueChecked = newChecked? 1 : 0;

        ContentValues values = new ContentValues();
        values.put(KollegeDBHelper.KOLLEGE_COLUMN_VORNAME,newProduct);

        database.update(KollegeDBHelper.TABLE_NAME,values,KollegeDBHelper.KOLLEGE_COLUMN_ID + "=" + id,
                null);

        Cursor cursor = database.query(KollegeDBHelper.TABLE_NAME,columns,KollegeDBHelper.KOLLEGE_COLUMN_ID +"="+id,
                null,null,null,null);
        cursor.moveToFirst();
        Kollege memo = cursorToKollege(cursor);
        cursor.close();
        return memo;
    }

    public void deleteShoppingMemo(Kollege shoppingMemo){
        long id = shoppingMemo.getId();
        database.delete(KollegeDBHelper.TABLE_NAME,
                KollegeDBHelper.KOLLEGE_COLUMN_ID + "=" + id,null);
        Log.d(TAG, "Eintrag gelöscht! ID: " + id + " Inhalt: " + shoppingMemo.toString());
    }

    private Kollege cursorToKollege(Cursor cursor) {
        int idIndex = cursor.getColumnIndex(KollegeDBHelper.KOLLEGE_COLUMN_ID);
        int idVorname = cursor.getColumnIndex(KollegeDBHelper.KOLLEGE_COLUMN_VORNAME);

        int id = cursor.getInt(idIndex);
        String vorname = cursor.getString(idVorname);



        Kollege shoppingMemo = new Kollege(id, vorname);
        return shoppingMemo;
    }

    public List<Kollege> getAllShoppingMemos(){
        List<Kollege> shoppingMemoList = new ArrayList<>();
        Cursor cursor = database.query(KollegeDBHelper.TABLE_NAME,columns,
                null,null,null,null,null);
        cursor.moveToFirst();
        Kollege shoppingMemo;

        while (!cursor.isAfterLast()){
            shoppingMemo = cursorToKollege(cursor);
            shoppingMemoList.add(shoppingMemo);
            Log.d(TAG, "ID: " + shoppingMemo.getId() + ", Inhalt: " + shoppingMemo.toString());
            cursor.moveToNext();
        }
        cursor.close();
        return shoppingMemoList;
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
