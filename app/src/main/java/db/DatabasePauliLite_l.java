package db;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.Vergehen;

public class DatabasePauliLite_l {

  // DBHelperSchuelerVergehen dbHelperSchuelerVergehen = new DBHelperSchuelerVergehen();

    String [] vergehensArray = {
            "Verspätung",
            "Mitgeholfen",
            "Diskustion mit Schueler . ",
            "Hausaufgabe gemacht",
            "Gespraech mit Shueler .. ",
    };

    public void createVergehenListe(){

        List<String> vergehenListe = new ArrayList<>(Arrays.asList(vergehensArray));

        for (String vergehensTitle: vergehenListe){
           // dbHelperSchuelerVergehen.createVergehen(new Vergehen(vergehensTitle));

        }

    }



}
