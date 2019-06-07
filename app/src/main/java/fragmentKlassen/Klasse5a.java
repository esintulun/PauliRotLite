package fragmentKlassen;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;

import akguen.liquidschool.paulirotlite.R;
import akguen.liquidschool.paulirotlite.SchuelerKlCustomAdapter;
import akguen.liquidschool.paulirotlite.VergehenActivity;
import db.DBHelperSchuelerVergehen;
import db.DatabaseHelperPauli;
import model.Schueler;
import model.SchuelerMitCBox;
import model.Vergehen;
import utils.CustomAdapterSchuelerMitCb;


public class Klasse5a extends Fragment {

    Button btnnext, btnselect, btndeselect;
    ArrayList<SchuelerMitCBox> modelArrayList;


    private CustomAdapterSchuelerMitCb customAdapter;

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



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        // Databasehelper holen
        db = new DBHelperSchuelerVergehen(getContext());
        //createKlasse5a(klasse5a, "5a");


        View rootView=inflater.inflate(R.layout.fragment_schueler,container,false);
        ListView lv= (ListView) rootView.findViewById(R.id.schuelerListView);
        SchuelerKlCustomAdapter adapter=new SchuelerKlCustomAdapter(this.getActivity(), getSchuelerName());
        lv.setAdapter(adapter);


        //btnselect = (Button) findViewById(R.id.select);
        // btndeselect = (Button) findViewById(R.id.deselect);
        btnnext = (Button) rootView.findViewById(R.id.btn_zumvergehen);
        modelArrayList = getModel_SchuelerMitCb(false);
        customAdapter = new CustomAdapterSchuelerMitCb(getContext(),  modelArrayList);


        lv.setAdapter(customAdapter);
        //String [] parts = pasteData.split("<br />");


        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final StringBuilder schuelerNamen = new StringBuilder();
                Log.i("adapter: count: ", "--"+customAdapter.getCount());

                Intent intentVergehen = new Intent(getActivity(), VergehenActivity.class);

                //TODO
                // Intent intent = new Intent(getActivity(), VorfaelleListView.class);
                //Intent intent = new Intent(getActivity(), SchuelerVorfallInfo.class);

                for(int i= 0; i < customAdapter.getCount(); i++){

                    if(((SchuelerMitCBox)(customAdapter.getItem(i))).isChecked()){

                        String schuelerName = ((SchuelerMitCBox)(customAdapter.getItem(i))).getSchuelerName();

                        // Für die 2 oder mehrere Schueler!
                        //schuelerNamen.append(" " + schuelerName);
                        // intent.putExtra("name", sb.toString());
                        intentVergehen.putExtra("name", schuelerName);
                    }
                }
                startActivity(intentVergehen);

            }
        });


        return rootView;


    }


    // Hier soll die daten von der DB kommen
    //
    private ArrayList<String> getSchuelerName() {
        ArrayList<String> schuelerName=new ArrayList<>();
        schuelerName.add("esin5a");
        schuelerName.add("siegfried5a");
        schuelerName.add("ayse5a");
        schuelerName.add("rania5a");
        schuelerName.add("farouk5a");
        schuelerName.add("kamal5a");
        schuelerName.add("petek5a");
        schuelerName.add("esin5a");
        schuelerName.add("siegfried5a");
        schuelerName.add("ayse5a");
        schuelerName.add("rania5a");
        schuelerName.add("farouk5a");
        schuelerName.add("kamal5a");
        schuelerName.add("petek5a");
        schuelerName.add("petek5a");
        schuelerName.add("siegfried");
        schuelerName.add("ayse");
        schuelerName.add("rania");
        schuelerName.add("farouk");
        schuelerName.add("kamal");
        schuelerName.add("petek");
        return schuelerName;

        //Jasmin
        //Tolgahan
        //Gulnaz
        //Christian
        //Emirhan
        //Alisina
        //Oscar Rio Gordon Ludwig Fritz
        //Serhat
        //Medine
        //Raffaela
        //Aysel
        //Raghad
        //Marcello Sebastian
        //Lulit Mirja
        //Klejvis
        //Talal
        //Marko
        //Amir Ali
        //Fynn Lasse
        //John Markus
        //Petra
        //Xhilda
    }

    @Override
    public String toString() {
        String title="Kl-5a";
        return title;
    }


    private ArrayList<SchuelerMitCBox> getModel_SchuelerMitCb(boolean isSelect){

        ArrayList<SchuelerMitCBox> list = new ArrayList<>();

        db.getAllSchueler();

        for (Schueler schueler : db.getAllSchueler()) {

            SchuelerMitCBox model = new SchuelerMitCBox();
            model.setChecked(isSelect);
            model.setSchuelerName(schueler.getName());
            list.add(model);
        }


      /*  for(int i = 0; i<10; i++){

            SchuelerMitCBox model = new SchuelerMitCBox();
            model.setChecked(isSelect);
            model.setSchuelerName(getSchuelerName().get(i));
            list.add(model);

        }
*/



        return list;
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
