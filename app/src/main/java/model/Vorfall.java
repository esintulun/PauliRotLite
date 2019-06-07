package model;

import java.util.Date;

public class Vorfall {

    int id;
    Date datum;
    String uhrzeit;
    String info;
    Kollege kollege;
    Vergehen vergehen;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public String getUhrzeit() {
        return uhrzeit;
    }

    public void setUhrzeit(String uhrzeit) {
        this.uhrzeit = uhrzeit;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Kollege getKollege() {
        return kollege;
    }

    public void setKollege(Kollege kollege) {
        this.kollege = kollege;
    }

    public Vergehen getVergehen() {
        return vergehen;
    }

    public void setVergehen(Vergehen vergehen) {
        this.vergehen = vergehen;
    }


}
