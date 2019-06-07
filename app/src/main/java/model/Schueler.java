package model;

public class Schueler {

    int id;
    String name;
    String nachname;
    String gebdatum;
    String geschlecht;
    String schulklasse;  // 5a
    String created_at;

    public Schueler() {

    }

    public Schueler(int id, String name) {
        this.id = id;
        this.name = name;
    }


    public Schueler( String name) {

        this.name = name;
    }

    public Schueler(String name, String schulklasse) {

        this.name = name;
        this.schulklasse = schulklasse;
    }



    public Schueler(String name, String nachname, String gebdatum, String geschlect) {
        this.name = name;
        this.nachname = nachname;
        this.gebdatum = gebdatum;
        this.geschlecht = geschlect;
    }

    public String getGebdatum() {
        return gebdatum;
    }

    public void setGebdatum(String gebdatum) {
        this.gebdatum = gebdatum;
    }

    public String getGeschlecht() {
        return geschlecht;
    }

    public void setGeschlecht(String geschlecht) {
        this.geschlecht = geschlecht;
    }



    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getSchulklasse() {
        return schulklasse;
    }

    public void setSchulklasse(String schulklasse) {
        this.schulklasse = schulklasse;
    }

    public void setCreatedAt(String created_at){
        this.created_at = created_at;
    }

}
