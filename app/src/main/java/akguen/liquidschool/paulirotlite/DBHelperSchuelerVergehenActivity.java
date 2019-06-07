package akguen.liquidschool.paulirotlite;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import db.DatabaseHelperPauli;
import model.Schueler;
import model.Stammgruppe;

public class DBHelperSchuelerVergehenActivity extends AppCompatActivity {

    // Database Helper
    DatabaseHelperPauli db;
    String name;
    String schueler5aa = " 5a\n" +
            "            Jasmin\n" +
            "            Tolgahan\n" +
            "            Gulnaz\n" +
            "            Christian\n" +
            "            Emirhan\n" +
            "            Alisina\n" +
            "            Oscar Rio Gordon Ludwig Fritz\n" +
            "            Serhat\n" +
            "            Medine\n" +
            "            Raffaela\n" +
            "            Aysel\n" +
            "            Raghad\n" +
            "            Marcello Sebastian\n" +
            "            Lulit Mirja\n" +
            "            Klejvis\n" +
            "            Talal\n" +
            "            Marko\n" +
            "            Amir Ali\n" +
            "            Fynn Lasse\n" +
            "            John Markus\n" +
            "            Petra\n" +
            "            Xhilda";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_base_helper);


        db = new DatabaseHelperPauli(getApplicationContext());


        Schueler pauliSchueler1 = new Schueler("Jasmin", "5a");
        Schueler pauliSchueler2 = new Schueler("Tolgahan", "5a");
        Schueler pauliSchueler3 = new Schueler("Gulnaz", "5a");
        Schueler pauliSchueler4 = new Schueler("Christian", "5a");
        Schueler pauliSchueler5 = new Schueler("Alisina", "5a");
        Schueler pauliSchueler6 = new Schueler("Oscar Rio Gordon Ludwig Fritz", "5a");
        Schueler pauliSchueler7 = new Schueler("Serhat", "5a");
        Schueler pauliSchueler8 = new Schueler("Medine", "5a");
        Schueler pauliSchueler9 = new Schueler("Raffaela", "5a");
        Schueler pauliSchueler10 = new Schueler("Aysel", "5a");
        Schueler pauliSchueler11 = new Schueler("Raghad", "5a");
        Schueler pauliSchuele12 = new Schueler("Marcello Sebastian", "5a");
        Schueler pauliSchueler13 = new Schueler("Lulit Mirja", "5a");
        Schueler pauliSchueler14 = new Schueler("Medine", "5a");
        Schueler pauliSchueler15 = new Schueler("Talal", "5a");
        Schueler pauliSchueler16 = new Schueler("Marko", "5a");
        Schueler pauliSchueler17 = new Schueler("Amir Ali", "5a");
        Schueler pauliSchueler18 = new Schueler("Fynn Lasse", "5a");
        Schueler pauliSchueler19 = new Schueler("John Markus", "5a");
        Schueler pauliSchueler20 = new Schueler("Petra", "5a");
        Schueler pauliSchueler21 = new Schueler("Xhilda", "5a");


        //createSchuelerList(schueler5aa, "5a");


        // Inserting schueler in db

        for(Schueler schueler: createSchuelerList(schueler5aa, "5a") ){
            db.createSchueler(schueler);
        }


        db.closeDB();


        db.getAllSchueler();

    }




   List<Schueler> createSchuelerList(String schuelerAsString, String schulklasse) {
       Log.i("split", "createSchuelerList ");

        List<Schueler> schueler5aListe = new ArrayList<>();
        String[] schueler = schuelerAsString.split("\n");
        Log.i("split", "--- " + schueler.length);
        schueler5aListe = new ArrayList<Schueler>();

        for (int i = 1; i < schueler.length; i++) {
            name = schueler[i];
            Schueler schuelerObj = new Schueler(name, schulklasse);
            schueler5aListe.add(schuelerObj);
        }

        return schueler5aListe;
    }

}
