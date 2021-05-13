package Application.models;

import java.io.Serializable;
import java.time.LocalDate;

public class Sale implements Serializable {
    private int idSale;
    private float price;
    private String salesTerms;
    private String category;
    private LocalDate dateOfSale;
    private int idUser;
    private int idClient;
    private int idProduct;
    private int idDostavka;

    public Sale() {
        this.idSale = 0;
        this.price = 0.0f;
        this.salesTerms = "";
        this.category = "";
        this.dateOfSale = LocalDate.now();
        this.idUser = 0;
        this.idClient = 0;
        this.idProduct = 0;
        this.idDostavka = 0;
    }

    public Sale(int idSale, float price, String salesTerms, String category, LocalDate dateOfSale, int idUser, int idClient, int idProduct,int idDostavka) {
        this.idSale = idSale;
        this.price = price;
        this.salesTerms = salesTerms;
        this.category = category;
        this.dateOfSale = dateOfSale;
        this.idUser = idUser;
        this.idClient = idClient;
        this.idProduct = idProduct;
        this.idDostavka = idDostavka;
    }

    public int getIdSale() {
        return idSale;
    }

    public void setIdSale(int idSale) {
        this.idSale = idSale;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getSalesTerms() {
        return salesTerms;
    }

    public void setSalesTerms(String salesTerms) {
        this.salesTerms = salesTerms;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalDate getDateOfSale() {
        return dateOfSale;
    }

    public void setDateOfSale(LocalDate dateOfSale) {
        this.dateOfSale = dateOfSale;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }
    public int getIdDostavka() {
        return idDostavka;
    }

    public void setIdDostavka(int idDostavka) {
        this.idDostavka = idDostavka;
    }

}
