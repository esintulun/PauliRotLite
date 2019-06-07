package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WochenPlan_ {

    String stunde, montag, dienstag, mittwoch, donnerstag, freitag;

    List<String> stundeListe;
    List<String> montagListe;

    Map<String, Map<String, String> > bisFreitagMap;

    Map<String, String > montagPlan;
    Map<String, String > dienstagPlan;
    Map<String, String > mittwochPlan;
    Map<String, String > donnerstagPlan;





    public void getWochenPlan(){


        montagPlan = new HashMap<>();
        montagPlan.put("8:30 - 9:15", "Mathematik");
        montagPlan.put("9:15 - 10:00", "Sport");
        montagPlan.put("10:00 - 10:25", ""); // Pause
        montagPlan.put("10:25 - 11:10", "Chemie");
        montagPlan.put("11:10 - 11:55", "Chemie");
        montagPlan.put("11:55 - 12:15", ""); // Pause
        montagPlan.put("12:15 - 13:00", "Kunst");
        montagPlan.put("13:00 - 14:15", ""); // Pause
        montagPlan.put("14:15 - 13:00", "Handwerk");
        montagPlan.put("15:00 - 15:45", "Handwerk");

    }

    public void getStunde(){

        stundeListe = new ArrayList<>();
        stundeListe.add("8:30 - 9:15");
        stundeListe.add("9:15 - 10:00");
        stundeListe.add("10:00 - 10:25"); // Pause
        stundeListe.add("10:25 - 11:10");
        stundeListe.add("11:10 - 11:55");
        stundeListe.add("11:55 - 12:15"); // Pause
        stundeListe.add("12:15 - 13:00");
        stundeListe.add("13:00 - 14:15"); // Pause
        stundeListe.add("14:15 - 13:00");
        stundeListe.add("15:00 - 15:45");

    }


}
