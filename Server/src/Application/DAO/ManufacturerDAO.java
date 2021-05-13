package Application.DAO;

import Application.models.Manufacturer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ManufacturerDAO implements InterfaceDAO<Manufacturer> {
    private Connection connection;

    public ManufacturerDAO() {
    }

    public void setConnection(Connection connection){
        this.connection = connection;
    }

    @Override
    public void add(Manufacturer manufacturer) {
    }

    @Override
    public void update(Manufacturer manufacturer) {
    }

    @Override
    public void delete(Manufacturer manufacturer){
    }

    @Override
    public Manufacturer getObject(int id) {
        return null;
    }

    @Override
    public List<Manufacturer> getAllObjects(){
        List<Manufacturer> manufacturerList = new ArrayList<>();
        Statement statement = null;
        try {
            String getStr = "SELECT * FROM mynewdb.manufacturer";

            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(getStr);
            while (resultSet.next()) {
                Manufacturer manufacturer = new Manufacturer();
                manufacturer.setManufacturer(resultSet.getString("manufacturer"));
                manufacturer.setCountry(resultSet.getString("country"));

                manufacturerList.add(manufacturer);
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
        return manufacturerList;
    }
}
