package Application.models;

import java.io.Serializable;

public class Client implements Serializable {
    private int idClient;
    private String firstName;
    private String secondName;
    private String phoneNumber;
    private String email;
    private String address;

    public Client() {
        this.idClient = 0;
        this.firstName = "";
        this.secondName = "";
        this.phoneNumber = "";
        this.email = "";
        this.address = "";
    }

    public Client(int idClient, String firstName, String secondName, String phoneNumber, String email, String address) {
        this.idClient = idClient;
        this.firstName = firstName;
        this.secondName = secondName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
