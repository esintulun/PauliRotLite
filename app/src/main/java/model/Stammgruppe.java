package model;

public class Stammgruppe {

    // ManyToOne Beziehung; Sila in JahrgruppeAkg
    //                      Ayse in JahrgruppeAkg
    //                      Veli in JahrgruppeAkg


    private  int id;
    String name;   // JahrgruppeAkg
    private Schueler schueler; // Sila!
    int status;
    String created_at;


    public Stammgruppe(String name, int status) {
        this.name = name;
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreatedAt(String created_at) {
        this.created_at = created_at;
    }



    public Stammgruppe(int id, String name) {
        this.id = id;
        this.name = name;
    }


    public Stammgruppe( String name) {
        this.name = name;
    }
    public Stammgruppe() {

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

    public Schueler getSchueler() {
        return schueler;
    }

    public void setSchueler(Schueler schueler) {
        this.schueler = schueler;
    }




}
