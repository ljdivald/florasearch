package com.example.ljudevit.florasearch;

public class HerbarObject{
    int ID_herbara;
    int ID_slike;
    String porodica;
    String Ime_svojte;
    String sabirac;
    int godina;
    int tip;
    String naziv_zbirke;
    String drzava;
    String koordinate;

    public HerbarObject() {
    };

    public int getID_herbara() {
        return ID_herbara;
    }

    public void setID_herbara(int ID_herbara) {
        this.ID_herbara = ID_herbara;
    }

    public int getID_slike() {
        return ID_slike;
    }

    public void setID_slike(int ID_slike) {
        this.ID_slike = ID_slike;
    }

    public String getPorodica() {
        return porodica;
    }

    public void setPorodica(String porodica) {
        this.porodica = porodica;
    }

    public String getIme_svojte() {
        return Ime_svojte;
    }

    public void setIme_svojte(String ime_svojte) {
        Ime_svojte = ime_svojte;
    }

    public String getSabirac() {
        return sabirac;
    }

    public void setSabirac(String sabirac) {
        this.sabirac = sabirac;
    }

    public int getGodina() {
        return godina;
    }

    public void setGodina(int godina) {
        this.godina = godina;
    }

    public int getTip() {
        return tip;
    }

    public void setTip(int tip) {
        this.tip = tip;
    }

    public String getNaziv_zbirke() {
        return naziv_zbirke;
    }

    public void setNaziv_zbirke(String naziv_zbirke) {
        this.naziv_zbirke = naziv_zbirke;
    }

    public String getDrzava() {
        return drzava;
    }

    public void setDrzava(String drzava) {
        this.drzava = drzava;
    }

    public String getKoordinate() {
        return koordinate;
    }

    public void setKoordinate(String koordinate) {
        this.koordinate = koordinate;
    }
}