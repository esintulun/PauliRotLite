package model;

public class Kollege {

    private int id;
    private String vorname;
    private String nachname;   // Akgün
    private String passwort;
    private String kuerzel;    //  Akg
    private String status;     //  Lehrer, Schulleiter, Trainer ;



    public Kollege() {

    }

    public Kollege(int id, String vorname) {
        this.id = id;
        this.vorname = vorname;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {

        return nachname;
    }

    public void setNachname(String nachname) {

        this.nachname = nachname;
    }

    public String getPasswort() {
        return passwort;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }

    public String getKuerzel() {
        return kuerzel;
    }

    public void setKuerzel(String kuerzel) {
        this.kuerzel = kuerzel;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
