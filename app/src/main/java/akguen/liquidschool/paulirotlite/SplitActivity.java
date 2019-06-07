package akguen.liquidschool.paulirotlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


import model.Schueler;
import utils.Klasse;

public class SplitActivity extends AppCompatActivity {


    String name, nachname, gebdatum, geschlecht;
    ArrayList<Schueler> alleschuelerlist;

    Klasse klasse = new Klasse();




    private String alleSchueler = "1\n" +
            "Jasmin\n" +
            "2\n" +
            "Abd Elaal\n" +
            "3\n" +
            "10.6.2008\n" +
            "4\n" +
            "w\n" +
            "1\n" +
            "Tolgahan\n" +
            "2\n" +
            "Acar\n" +
            "3\n" +
            "12.1.2008\n" +
            "4\n" +
            "m\n" +
            "1\n" +
            "Gulnaz\n" +
            "2\n" +
            "Ali Mohammad\n" +
            "3\n" +
            "7.1.2007\n" +
            "4\n" +
            "w\n" +
            "1\n" +
            "Christian\n" +
            "2\n" +
            "Andjelkovic\n" +
            "3\n" +
            "5.5.2008\n" +
            "4\n" +
            "m\n" +
            "1\n" +
            "Emirhan\n" +
            "2\n" +
            "Arik\n" +
            "3\n" +
            "23.11.2007\n" +
            "4\n" +
            "m\n" +
            "1\n" +
            "Alisina\n" +
            "2\n" +
            "Asimi\n" +
            "3\n" +
            "4.5.2007\n" +
            "4\n" +
            "m\n" +
            "1\n" +
            "Oscar Rio Gordon Ludwig Fritz\n" +
            "2\n" +
            "Bootz\n" +
            "3\n" +
            "19.9.2007\n" +
            "4\n" +
            "m\n" +
            "1\n" +
            "Serhat\n" +
            "2\n" +
            "Canpolat\n" +
            "3\n" +
            "19.8.2007\n" +
            "4\n" +
            "m\n" +
            "1\n" +
            "Medine\n" +
            "2\n" +
            "Doganay\n" +
            "3\n" +
            "07.01.2008\n" +
            "4\n" +
            "w\n" +
            "1\n" +
            "Raffaela\n" +
            "2\n" +
            "Hastedt\n" +
            "3\n" +
            "21.06.2008\n" +
            "4\n" +
            "w\n" +
            "1\n" +
            "Aysel Elcin\n" +
            "2\n" +
            "Kavzan\n" +
            "3\n" +
            "22.07.2008\n" +
            "4\n" +
            "w\n" +
            "1\n" +
            "Raghad\n" +
            "2\n" +
            "Khatib\n" +
            "3\n" +
            "03.06.2007\n" +
            "4\n" +
            "w\n" +
            "1\n" +
            "Marcello Sebastian\n" +
            "2\n" +
            "Kierpacz\n" +
            "3\n" +
            "07.08.2007\n" +
            "4\n" +
            "m\n" +
            "1\n" +
            "Lulit Mirja\n" +
            "2\n" +
            "Lemma\n" +
            "3\n" +
            "12.2.2007\n" +
            "4\n" +
            "w\n" +
            "1\n" +
            "Klejvis\n" +
            "2\n" +
            "Mekshiqi\n" +
            "3\n" +
            "24.11.2007\n" +
            "4\n" +
            "m\n" +
            "1\n" +
            "Talal\n" +
            "2\n" +
            "Mohamad\n" +
            "3\n" +
            "25.1.2006\n" +
            "4\n" +
            "m\n" +
            "1\n" +
            "Marko\n" +
            "2\n" +
            "Pasic\n" +
            "3\n" +
            "9.10.2007\n" +
            "4\n" +
            "m\n" +
            "1\n" +
            "Amir Ali\n" +
            "2\n" +
            "Rezai\n" +
            "3\n" +
            "22.2.2008\n" +
            "4\n" +
            "m\n" +
            "1\n" +
            "Fynn Lasse\n" +
            "2\n" +
            "Rindt\n" +
            "3\n" +
            "21.7.2007\n" +
            "4\n" +
            "m\n" +
            "1\n" +
            "John Markus\n" +
            "2\n" +
            "Schulz\n" +
            "3\n" +
            "13.10.2007\n" +
            "4\n" +
            "m\n" +
            "1\n" +
            "Petra\n" +
            "2\n" +
            "Smailovic\n" +
            "3\n" +
            "14.10.2007\n" +
            "4\n" +
            "w\n" +
            "1\n" +
            "Xhilda\n" +
            "2\n" +
            "Xhepa\n" +
            "3\n" +
            "7.9.2007\n" +
            "4\n" +
            "w\n";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_split);
        // readFromFile("5aSplit.txt");
        getSchuelerArray();

        //holeAlleSchueler(klasse, klasse.holeSchuelerVonString(), name, nachname, gebdatum, geschlecht);

        getAllSchueler();


    }


    void getSchuelerArray() {

        String[] schueler = alleSchueler.split("1\n");
        Log.i("split", "--- " + schueler.length);
        alleschuelerlist = new ArrayList<Schueler>();
        for (int i = 1; i < schueler.length; i++) {

            String[] splitsplit = schueler[i].split("\n");
            name = splitsplit[0];
            nachname = splitsplit[2];
            gebdatum = splitsplit[4];
            geschlecht = splitsplit[6];
            Schueler schuelerObj = new Schueler(name, nachname, gebdatum, geschlecht);
            alleschuelerlist.add(schuelerObj);
            //Log.i("--", "::::: " + "name:" + name + "nachname:" + nachname+ "gebdatum:" + gebdatum + "geschlecht:"+ geschlecht);

        }

        Klasse klasse5b = new Klasse();

        String[] schueler5b = klasse5b.alleschueler5b.split("1\n");
        Log.i("split", "--- " + schueler.length);
        for (int i = 1; i < schueler5b.length; i++) {

            String[] splitsplit = schueler5b[i].split("\n");
            name = splitsplit[0];
            nachname = splitsplit[2];
            gebdatum = splitsplit[4];
            geschlecht = splitsplit[6];
            Schueler schuelerObj = new Schueler(name, nachname, gebdatum, geschlecht);
            alleschuelerlist.add(schuelerObj);
            //Log.i("--", "::::: " + "name:" + name + "nachname:" + nachname+ "gebdatum:" + gebdatum + "geschlecht:"+ geschlecht);

        }


        String[] schueler6a = klasse5b.alleschueler6a.split("1\n");
        Log.i("split", "--- " + schueler.length);
        for (int i = 1; i < schueler6a.length; i++) {

            String[] splitsplit = schueler6a[i].split("\n");
            name = splitsplit[0];
            nachname = splitsplit[2];
            gebdatum = splitsplit[4];
            geschlecht = splitsplit[6];
            Schueler schuelerObj = new Schueler(nachname, name, gebdatum, geschlecht);
            alleschuelerlist.add(schuelerObj);
            //Log.i("--", "::::: " + "name:" + name + "nachname:" + nachname+ "gebdatum:" + gebdatum + "geschlecht:"+ geschlecht);

        }

    }


    List<Schueler> getAllSchueler() {

        for (Schueler schueler : alleschuelerlist) {

            Log.i("schueler", "name -> " + schueler.getName());
        }

        return alleschuelerlist;

    }


    private List<Schueler> holeAlleSchueler(Klasse klasse,
                                    List klassenListe,
                                    String name, String nachname, String gebdatum, String geschlecht ){



        //this.klasse = klasse;
       // this.klasse.klassenStringToList = klassenListe;

        for(int i = 0; i < klassenListe.size(); i++) {

            //String schueler = ((String) klassenListe.get(0));

            String[] schueler = ((String) klassenListe.get(0)).split("1\n");

            //Log.i("split", "--- " + schueler.length);
            for (int j = 1; j < schueler.length; j++) {

                String[] splitsplit = schueler[i].split("\n");
                name = splitsplit[0];
                nachname = splitsplit[2];
                gebdatum = splitsplit[4];
                geschlecht = splitsplit[6];
                Schueler schuelerObj = new Schueler(name, nachname, gebdatum, geschlecht);
                alleschuelerlist.add(schuelerObj);
                //Log.i("--", "::::: " + "name:" + name + "nachname:" + nachname+ "gebdatum:" + gebdatum + "geschlecht:"+ geschlecht);

            }

        }
        return alleschuelerlist;
    }
}
