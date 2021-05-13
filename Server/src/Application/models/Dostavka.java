package Application.models;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;

public class Dostavka implements Serializable {
    private int idDostavka;
    private String avto;
    private String numberAvto;
    private String kol;
    private String type;
    private String price;
    private String date;

    public Dostavka() {
        this.idDostavka = 0;
        this.avto = "";
        this.numberAvto = "";
        this.kol = "";
        this.type = "";
        this.price = "";
        this.date = "";
    }

    public Dostavka(int idDostavka, String avto, String numberAvto, String kol, String type, String price, String date) {
        this.idDostavka = idDostavka;
        this.avto = avto;
        this.numberAvto = numberAvto;
        this.kol = kol;
        this.type = type;
        this.price = price;
        this.date = date;
    }

    public int getIdDostavka() {
        return idDostavka;
    }

    public void setIddostavka(int idDostavka) {
        this.idDostavka = idDostavka;
    }

    public String getAvto() {
        return avto;
    }

    public void setAvto(String avto) {
        this.avto = avto;
    }
    public String getNumberAvto() {
        return numberAvto;
    }

    public void setNumberAvto(String numberAvto) {
        this.numberAvto = numberAvto;
    }

    public String getKol() {
        return kol;
    }

    public void setKol(String  kol) {
        this.kol = kol;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


}

