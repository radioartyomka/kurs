package Application.models;

import java.io.Serializable;

public class Product implements Serializable {
    private int idProduct;
    private String productName;
    private float sizeScreen;
    private String color;
    private String usbPort;
    private String os;
    private String manufacturer;

    public Product() {
        this.idProduct = 0;
        this.productName = "";
        this.sizeScreen = 0.0f;
        this.color = "";
        this.usbPort = "";
        this.os = "";
        this.manufacturer = "";
    }

    public Product(int idProduct, String productName, float sizeScreen, String color, String usbPort, String os, String manufacturer) {
        this.idProduct = idProduct;
        this.productName = productName;
        this.sizeScreen = sizeScreen;
        this.color = color;
        this.usbPort = usbPort;
        this.os = os;
        this.manufacturer = manufacturer;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getSizeScreen() {
        return sizeScreen;
    }

    public void setSizeScreen(float sizeScreen) {
        this.sizeScreen = sizeScreen;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getUsbPort() {
        return usbPort;
    }

    public void setUsbPort(String usbPort) {
        this.usbPort = usbPort;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }
}

