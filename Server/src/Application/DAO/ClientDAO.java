package Application.DAO;

import Application.models.Client;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO implements InterfaceDAO<Client> {
    private Connection connection;

    public ClientDAO() {
    }

    public void setConnection(Connection connection){
        this.connection = connection;
    }

    @Override
    public void add(Client client) {
        Statement statement = null;
        try {
            String str = "INSERT INTO mynewdb.client (idClient, firstName, secondName, phoneNumber, email, address) " +
                    "VALUES ('"+client.getIdClient()+"','"+client.getFirstName()+"','"+client.getSecondName()+"','"+client.getPhoneNumber()+
                    "','"+client.getEmail()+"',"+client.getAddress()+")";
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
    public void update(Client client) {
        Statement statement = null;
        try {
            String str = "UPDATE mynewdb.client SET firstName = '" + client.getFirstName() + "'"
                    +", secondName = '"+client.getSecondName() +"'"+
                    ", phoneNumber = '" + client.getPhoneNumber()+"'"+
                    ", email = '" + client.getEmail()+"'"+
                    ", address = '" + client.getAddress()+"' WHERE idClient = " + client.getIdClient();

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
    public void delete(Client client){
        Statement statement = null;
        try {
            String str = "DELETE FROM mynewdb.client WHERE idClient = " + client.getIdClient();
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
    public Client getObject(int id) {
        return null;
    }

    @Override
    public List<Client> getAllObjects(){
        List<Client> clientList = new ArrayList<>();
        Statement statement = null;
        try {
            String getStr = "SELECT * FROM mynewdb.client";

            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(getStr);
            while (resultSet.next()) {
                Client client = new Client();
                client.setIdClient(resultSet.getInt("idClient"));
                client.setFirstName(resultSet.getString("firstName"));
                client.setSecondName(resultSet.getString("secondName"));
                client.setPhoneNumber(resultSet.getString("phoneNumber"));
                client.setEmail(resultSet.getString("email"));
                client.setAddress(resultSet.getString("address"));

                clientList.add(client);
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
        return clientList;
    }
}
