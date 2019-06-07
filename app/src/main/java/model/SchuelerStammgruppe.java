package model;

public class SchuelerStammgruppe {

    int id;
    Schueler schueler;
    Stammgruppe stammgruppe;

    String schuelerName;

    public String getSchuelerName() {
        return schuelerName;
    }

    public void setSchuelerName(String schuelerName) {
        this.schuelerName = schuelerName;
    }

    public String getStammgruppeName() {
        return stammgruppeName;
    }

    public void setStammgruppeName(String stammgruppeName) {
        this.stammgruppeName = stammgruppeName;
    }

    String stammgruppeName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public SchuelerStammgruppe(Schueler schueler, Stammgruppe stammgruppe) {
        this.schueler = schueler;
        this.stammgruppe = stammgruppe;
    }


    public SchuelerStammgruppe(String schueler, String stammgruppe) {
        schuelerName = schueler;
        stammgruppeName = stammgruppe;
    }

    public Schueler getSchueler() {
        return schueler;
    }

    public void setSchueler(Schueler schueler) {
        this.schueler = schueler;
    }

    public Stammgruppe getStammgruppe() {
        return stammgruppe;
    }

    public void setStammgruppe(Stammgruppe stammgruppe) {
        this.stammgruppe = stammgruppe;
    }
}
