package Application.DAO;

import Application.models.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO implements InterfaceDAO<Product> {
    private Connection connection;

    public ProductDAO() {
    }

    public void setConnection(Connection connection){
        this.connection = connection;
    }

    @Override
    public void add(Product product) {
        Statement statement = null;
        try {
            String str = "INSERT INTO mynewdb.product (idProduct, productName, sizeScreen, color, usbPort, os, manufacturer_manufacturer)" +
                    "VALUES ('"+product.getIdProduct()+"','"
                    +product.getProductName()+"','"
                    +product.getSizeScreen()+"','"
                    +product.getColor()+"','"
                    +product.getUsbPort()+"','"
                    +product.getOs()+"','"
                    +product.getManufacturer()+"')";
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
    public void update(Product product) {
        Statement statement = null;
        try {
            String str = "UPDATE mynewdb.product SET productName = '" + product.getProductName() + "'"
                    +", sizeScreen = '"+product.getSizeScreen() +"'"+
                    ", color = '" + product.getColor()+"'"+
                    ", usbPort = '" + product.getUsbPort()+"'"+
                    ", os = '" + product.getOs()+"'"+
                    ", manufacturer_manufacturer = '" + product.getManufacturer()+"' WHERE idProduct = " + product.getIdProduct();

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
    public void delete(Product product) {
        Statement statement = null;
        try {
            String str = "DELETE FROM mynewdb.product WHERE idProduct = " + product.getIdProduct();
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
    public Product getObject(int id) {
        return null;
    }

    @Override
    public List<Product> getAllObjects() {
        List<Product> productList = new ArrayList<>();
        Statement statement = null;
        try {
            String getStr = "SELECT * FROM mynewdb.product";

            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(getStr);
            while (resultSet.next()) {
                Product product = new Product();
                product.setIdProduct(resultSet.getInt("idProduct"));
                product.setProductName(resultSet.getString("productName"));
                product.setSizeScreen(resultSet.getFloat("sizeScreen"));
                product.setColor(resultSet.getString("color"));
                product.setUsbPort(resultSet.getString("usbPort"));
                product.setOs(resultSet.getString("os"));
                product.setManufacturer(resultSet.getString("manufacturer_manufacturer"));

                productList.add(product);
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
        return productList;
    }
}
