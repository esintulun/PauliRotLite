package akguen.liquidschool.paulirotlite;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import db.DBHelperSchuelerVergehen;
import model.Schueler;
import model.Vergehen;

public class VergehenActivity extends AppCompatActivity {

    DBHelperSchuelerVergehen dbHelperSchuelerVergehen;
    String vergehenTitle = "";


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
        setContentView(R.layout.activity_vergehen);

        String schuelername = getIntent().getStringExtra("name");

        dbHelperSchuelerVergehen = new DBHelperSchuelerVergehen(getApplicationContext());


        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.hamburger);
        //Datenbanksachen db = new Datenbanksachen("a");

        List<String> vergehenListe = new ArrayList<>(Arrays.asList(vergehensArray));
        List<String> vergehenListe2 = new ArrayList<>();


        for (String vergehensTitle : vergehenListe) {
           // dbHelperSchuelerVergehen.createVergehen(new Vergehen(vergehensTitle));

        }

        for (Vergehen vergehen : dbHelperSchuelerVergehen.getAllVergehen()) {

            vergehenListe2.add(vergehen.getTitel());
            Log.i("vergehen", "-- title " + vergehen.getTitel());

        }

        //new Datenbanksachen(vergehenListe).execute("someParams");

        ArrayAdapter<String> aktienlisteAdapter =
                new ArrayAdapter<>(
                        this, // Die aktuelle Umgebung (diese Activity)
                        R.layout.list_item_vergehensliste, // ID der XML-Layout Datei
                        R.id.tv_list_item_vergehensliste, // ID des TextViews
                        vergehenListe2); // Beispieldaten in einer ArrayList

        ListView vergehenlisteListView = findViewById(R.id.listview_vergehens);
        vergehenlisteListView.setAdapter(aktienlisteAdapter);

        vergehenlisteListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                Log.i("click", "" + id);
                vergehenTitle = (String) adapterView.getItemAtPosition(position);
                Long clickId = adapterView.getSelectedItemId();

               /* if (id == 2) {
                    Log.i("click", "2 geklickt " + id);
                    // Intent erzeugen und Starten der KlasseFragment_l mit explizitem Intent
                    Intent klasseActivityMitCBox = new Intent(VergehenActivity.this, KlasseActivityMitCBox.class);
                    klasseActivityMitCBox.putExtra(Intent.EXTRA_TEXT, vergehenTitle);
                    //klasseActivityMitCBox.putExtra("position", clickId);
                    startActivity(klasseActivityMitCBox);

                } else {
                    Log.i("click", "2 nicht geklickt " + id);
                    // Intent erzeugen und Starten der KlasseFragment_l mit explizitem Intent
                    Intent klasseActivity = new Intent(VergehenActivity.this, KlasseActivity.class);
                    klasseActivity.putExtra(Intent.EXTRA_TEXT, vergehenTitle);
                    startActivity(klasseActivity);
                }*/


                    // Intent erzeugen und Starten der KlasseFragment_l mit explizitem Intent
                    Intent klasseActivity = new Intent(VergehenActivity.this, VorfaelleActivity.class);
                    klasseActivity.putExtra(Intent.EXTRA_TEXT, vergehenTitle);
                    Log.i("schuelerundvergehenti", "-----" + vergehenTitle +  " schuelername: " + schuelername);

                    int schuelerID = dbHelperSchuelerVergehen.getSchuelerIdBySchuelerName(schuelername);
                    int vergehenID = dbHelperSchuelerVergehen.getVergehenIdByTitel(vergehenTitle);
                    dbHelperSchuelerVergehen.createSchuelerVergehen(schuelerID,vergehenID);



                   // Log.i("id" + "schueler:" , "" +  dbHelperSchuelerVergehen.getSchuelerIdBySchuelerName(schuelername));
                    Log.i("id" + "vergehen:" , "" +  dbHelperSchuelerVergehen.getVergehenIdByTitel(vergehenTitle));


                startActivity(klasseActivity);

            }
        });


       for(Schueler schueler: dbHelperSchuelerVergehen.getAllSchueler()){

           Log.i("schuelername:" + "-" , schueler.getName()+ " id: " +schueler.getId());


       }


    }


    public void createVergehenListe() {

        List<String> vergehenListe = new ArrayList<>(Arrays.asList(vergehensArray));

        for (String vergehensTitle : vergehenListe) {
            dbHelperSchuelerVergehen.createVergehen(new Vergehen(vergehensTitle));

        }

    }


    public List<Vergehen> getVergehenVonDB() {

        createVergehenListe();

        return dbHelperSchuelerVergehen.getAllVergehen();


    }


}
