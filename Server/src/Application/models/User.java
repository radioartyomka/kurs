package Application.models;


import java.io.Serializable;

public class User implements Serializable {
    private int idUser;
    private String login;
    private String password;
    private int role;

    public User() {
        this.idUser = 0;
        this.login = "";
        this.password = "";
        this.role = 0;
    }

    public User(int idUser, String login, String password, int role) {
        this.idUser = idUser;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUsers) {
        this.idUser = idUsers;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
