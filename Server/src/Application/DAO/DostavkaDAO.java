package Application.DAO;

import Application.models.Dostavka;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DostavkaDAO implements InterfaceDAO<Dostavka> {
    private Connection connection;

    public DostavkaDAO() {
    }

    public void setConnection(Connection connection){
        this.connection = connection;
    }

    @Override
    public void add(Dostavka dostavka) {
        Statement statement = null;
        try {
            String str = "INSERT INTO mynewdb.dostavka (idDostavka, avto, numberAvto, kol, type, price, date)" +
                    "VALUES ('"+dostavka.getIdDostavka()+"','"
                    +dostavka.getAvto()+"','"
                    +dostavka.getNumberAvto()+"','"
                    +dostavka.getKol()+"','"
                    +dostavka.getType()+"','"
                    +dostavka.getPrice()+"','"
                    +dostavka.getDate()+"')";
            statement = connection.createStatement();
            int n = statement.executeUpdate(str);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {}
            }
        }
    }

    @Override
    public void update(Dostavka dostavka) {
        Statement statement = null;
        try {
            String str = "UPDATE mynewdb.dostavka SET avto = '" + dostavka.getAvto() + "'"
                    +", sizeScreen = '"+dostavka.getNumberAvto() +"'"+
                    ", color = '" + dostavka.getKol()+"'"+
                    ", usbPort = '" + dostavka.getType()+"'"+
                    ", os = '" + dostavka.getPrice()+"'"+
                    ", date = '" + dostavka.getDate()+"' WHERE idProduct = " + dostavka.getIdDostavka();

            statement = connection.createStatement();
            int n = statement.executeUpdate(str);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {}
            }
        }
    }

    @Override
    public void delete(Dostavka dostavka) {
        Statement statement = null;
        try {
            String str = "DELETE FROM mynewdb.dostavka WHERE idDostavka = " + dostavka.getIdDostavka();
            statement = connection.createStatement();
            int countOfDelete = statement.executeUpdate(str);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {}
            }
        }
    }

    @Override
    public Dostavka  getObject(int id) {
        return null;
    }

    @Override
    public List<Dostavka > getAllObjects() {
        List<Dostavka > dostavkaList = new ArrayList<>();
        Statement statement = null;
        try {
            String getStr = "SELECT * FROM mynewdb.dostavka";

            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(getStr);
            while (resultSet.next()) {
                Dostavka  dostavka  = new Dostavka ();
                dostavka.setIddostavka(resultSet.getInt("idDostavka"));
                dostavka.setAvto(resultSet.getString("avto"));
                dostavka.setNumberAvto(resultSet.getString("numberAvto"));
                dostavka.setKol(resultSet.getString("kol"));
                dostavka.setType(resultSet.getString("type"));
                dostavka.setPrice(resultSet.getString("price"));
                dostavka.setDate(resultSet.getString("date"));
                dostavkaList.add(dostavka);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                }
            }
        }
        return dostavkaList;
    }
}
