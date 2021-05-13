package Application.models;

import java.io.Serializable;

public class Manufacturer implements Serializable {
    private String manufacturer;
    private String country;

    public Manufacturer() {
        this.manufacturer = "";
        this.country = "";
    }

    public Manufacturer(String manufacturer, String country) {
        this.manufacturer = manufacturer;
        this.country = country;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
