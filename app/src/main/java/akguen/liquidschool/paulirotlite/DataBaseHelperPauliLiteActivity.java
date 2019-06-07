package akguen.liquidschool.paulirotlite;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.List;

import db.DatabaseHelperPauli;
import model.Schueler;
import model.Stammgruppe;

public class DataBaseHelperPauliLiteActivity extends AppCompatActivity {

    // Database Helper
    DatabaseHelperPauli db;
    String schueler5a = "Jasmin,Tolgahan,Gulnaz,Christian,Emirhan,Alisina,Oscar Rio Gordon Ludwig Fritz" +
                        "Serhat,Medine,Raffaela,Aysel,Raghad,Marcello Sebastian, " +
            "Lulit Mirja,Klejvis,Talal,Marko, Amir Ali," +
            "Fynn Lasse, John Markus, Petra, Xhilda ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_base_helper);


        db = new DatabaseHelperPauli(getApplicationContext());

        // Creating tags: schueler
        Schueler schueler1 = new Schueler("Ali");
        Schueler schueler2 = new Schueler("Veli");
        Schueler schueler3 = new Schueler("Sila");
        Schueler schueler4 = new Schueler("Emine");



        Schueler pauliSchueler1 = new Schueler("Jasmin");
        Schueler pauliSchueler2 = new Schueler("Tolgahan");
        Schueler pauliSchueler3 = new Schueler("Gulnaz");
        Schueler pauliSchueler4 = new Schueler("Christian");
        Schueler pauliSchueler5 = new Schueler("Alisina");
        Schueler pauliSchueler6 = new Schueler("Oscar Rio Gordon Ludwig Fritz");
        Schueler pauliSchueler7 = new Schueler("Serhat");
        Schueler pauliSchueler8 = new Schueler("Medine");
        Schueler pauliSchueler9 = new Schueler("Raffaela");
        Schueler pauliSchueler10 = new Schueler("Aysel");
        Schueler pauliSchueler11 = new Schueler("Raghad");
        Schueler pauliSchueler12 = new Schueler("Marcello Sebastian");
        Schueler pauliSchueler13 = new Schueler("Lulit Mirja");
        Schueler pauliSchueler14 = new Schueler("Medine");
        Schueler pauliSchueler15 = new Schueler("Talal");
        Schueler pauliSchueler16 = new Schueler("Marko");
        Schueler pauliSchueler17 = new Schueler("Amir Ali");
        Schueler pauliSchueler18 = new Schueler("Fynn Lasse");
        Schueler pauliSchueler19 = new Schueler("John Markus");
        Schueler pauliSchueler20 = new Schueler("Petra");
        Schueler pauliSchueler21 = new Schueler("Xhilda");









        // Inserting schueler in db
        long tag1_id = db.createSchueler(schueler1);
        long tag2_id = db.createSchueler(schueler2);
        long tag3_id = db.createSchueler(schueler3);
        long tag4_id = db.createSchueler(schueler4);

        Log.d("Schueler count", ": " + db.getAllSchueler().size());

        // Creating ToDos Stammgruppe
        Stammgruppe stammgruppe1 = new Stammgruppe("GruppeA", 0);
        Stammgruppe stammgruppe2 = new Stammgruppe("GruppeB", 0);
        Stammgruppe stammgruppe3 = new Stammgruppe("GruppeC", 0);

        Stammgruppe stammgruppe4 = new Stammgruppe("GruppeD", 0);
        Stammgruppe stammgruppe5 = new Stammgruppe("GruppeE", 0);
        Stammgruppe stammgruppe6 = new Stammgruppe("GruppeF", 0);
        Stammgruppe stammgruppe7 = new Stammgruppe("GruppeG", 0);

        Stammgruppe stammgruppe8 = new Stammgruppe("GruppeH", 0);
        Stammgruppe stammgruppe9 = new Stammgruppe("GruppeI", 0);

        Stammgruppe stammgruppe10 = new Stammgruppe("GruppeJ", 0);
        Stammgruppe stammgruppe11 = new Stammgruppe("GruppeK", 0);



        // Inserting gruppe in db
        // Inserting gruppen under "Schueler1_ Ali"
        long todo1_id = db.createGruppe(stammgruppe1, new long[] { tag1_id });
        long todo2_id = db.createGruppe(stammgruppe2, new long[] { tag1_id });
        long todo3_id = db.createGruppe(stammgruppe3, new long[] { tag1_id });

        // Inserting todos under "Schueler3 Sila"
        long todo4_id = db.createGruppe(stammgruppe4, new long[] { tag3_id });
        long todo5_id = db.createGruppe(stammgruppe5, new long[] { tag3_id });
        long todo6_id = db.createGruppe(stammgruppe6, new long[] { tag3_id });
        long todo7_id = db.createGruppe(stammgruppe7, new long[] { tag3_id });

        // Inserting todos under "Important" Veli
        long todo8_id = db.createGruppe(stammgruppe8, new long[] { tag2_id });
        long todo9_id = db.createGruppe(stammgruppe9, new long[] { tag2_id });

        // Inserting todos under "Androidhive" emine
        long todo10_id = db.createGruppe(stammgruppe10, new long[] { tag4_id });
        long todo11_id = db.createGruppe(stammgruppe11, new long[] { tag4_id });




        Log.e("Gruppen Count", "+++++: " + db.getGruppenCount());

        // "Gruppe J" - assigning this under "Veli"
        // Now this will have - "Emine" and "Veli"
        db.createGruppeSchueler(todo10_id, tag2_id); // Gruppe J -> Veli + emine

        // Getting all Schueler names  : alle schueler
        Log.d("Schueler", "Getting All schueler");

        List<Schueler> allSchueler = db.getAllSchueler();
        for (Schueler schueler : allSchueler) {
            Log.d("Schueler Name", schueler.getName());
        }

        // Getting all Gruppe
        Log.d("Gruppen", "Getting All Gruppen");

        List<Stammgruppe> allToDos = db.getAllGruppen();
        for (Stammgruppe stammgruppe : allToDos) {
            Log.d("Gruppen: ", stammgruppe.getName() + stammgruppe.getId());
        }

        // Getting gruppe under "Sila" tag name
        Log.d("gruppe", "Get gruppe under single schueler ");

        List<Stammgruppe> tagsWatchList = db.getAllGruppesBySchueler(schueler3.getName());
        for (Stammgruppe stammgruppe : tagsWatchList) {
            Log.d("Grpuppen -  für Sila", stammgruppe.getName() + " id: "  + stammgruppe.getId());
        }


        // Deleting a gruppe
        Log.d("Delete Gruppe", "Deleting a Gruppe");
        Log.d("Schueler Count", "Schueler Count Before Deleting: " + db.getAllGruppen());

        db.deleteGruppen(todo8_id);  // todo8_id --> GruppeH (schueler_id:2)

        Log.d("Tag Count", "Tag Count After Deleting: " + db.getAllGruppen());

        // Deleting all Todos under "Ali"
        Log.d("Tag Count",
                "Tag Count Before Deleting 'Ali' Todos: "
                        + db.getGruppenCount());

        db.deleteSchueler(schueler1, true);

        Log.d("Tag Count",
                "Tag Count After Deleting 'Ali' Todos: "
                        + db.getGruppenCount());

        // Updating schueler name
        schueler3.setName("Movies to watch");
        db.updateSchueler(schueler3);

        // Don't forget to close database connection
        db.closeDB();

    }

}
