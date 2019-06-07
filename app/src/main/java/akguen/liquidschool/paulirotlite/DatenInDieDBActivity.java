package akguen.liquidschool.paulirotlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import db.DBHelperSchuelerVergehen;
import model.Schueler;
import model.Vergehen;

public class DatenInDieDBActivity extends AppCompatActivity {

    // DatabaseHelperPauli db;
    DBHelperSchuelerVergehen db;
    String name;


    String klasse5a = " 5a\n" +
            "Jasmin\n" +
            "Tolgahan\n" +
            "Gulnaz\n" +
            "Christian\n" +
            "Emirhan\n" +
            "Alisina\n" +
            "Oscar Rio Gordon Ludwig Fritz\n" +
            "Serhat\n" +
            "Medine\n" +
            "Raffaela\n" +
            "Aysel\n" +
            "Raghad\n" +
            "Marcello Sebastian\n" +
            "Lulit Mirja\n" +
            "Klejvis\n" +
            "Talal\n" +
            "Marko\n" +
            "Amir Ali\n" +
            "Fynn Lasse\n" +
            "John Markus\n" +
            "Petra\n" +
            " Xhilda";

    List<Vergehen> vergehens;
    String[] vergehensArray = {
            "Verspätung",
            "Mitgeholfen",
            "Diskustion mit Schueler . ",
            "Hausaufgabe gemacht",
            "Gespraech mit Shueler .. ",
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daten_in_die_db);

        // Databasehelper holen
        db = new DBHelperSchuelerVergehen(getApplicationContext());
        createKlasse5a(klasse5a, "5a");


        List<String> vergehenListe = new ArrayList<>(Arrays.asList(vergehensArray));

        for (String vergehensTitle : vergehenListe) {
            db.createVergehen(new Vergehen(vergehensTitle));

        }

    }


    List<Schueler> createKlasse5a(String schuelerAsString, String schulklasse) {

        List<Schueler> schueler5aListe = new ArrayList<>();

        String[] schueler = schuelerAsString.split("\n");
        Log.i("split", "--- " + schueler.length);
        schueler5aListe = new ArrayList<Schueler>();

        for (int i = 1; i < schueler.length; i++) {
            name = schueler[i];
            Schueler schuelerObj = new Schueler(name, schulklasse);
            schueler5aListe.add(schuelerObj);
            // Schueler in die DB
            db.createSchueler(schuelerObj);
        }

        return schueler5aListe;
    }




}
