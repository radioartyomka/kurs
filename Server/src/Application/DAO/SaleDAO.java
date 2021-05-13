package Application.DAO;

import Application.models.Sale;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SaleDAO implements InterfaceDAO<Sale> {
    private Connection connection;

    public SaleDAO() {
    }

    public void setConnection(Connection connection){
        this.connection = connection;
    }

    @Override
    public void add(Sale sale) {
        Statement statement = null;
        try{
            String insertAcquisition = "INSERT INTO mynewdb.sale " +
                    "(idSale, price, salesTerms, category, dateOfSale, User_idUser, " +
                    "Client_idClient, Product_idProduct)" +
                    "VALUES ('"+ sale.getIdSale()+"','"
                    + sale.getPrice()+"','"+ sale.getSalesTerms() +"','"
                    + sale.getCategory()+"','"+ sale.getDateOfSale() +"','"
                    + sale.getIdUser()+"','"+ sale.getIdClient() +"','"
                    + sale.getIdProduct()+"','"+sale.getIdDostavka()+"')";
            statement = connection.createStatement();
            int n = statement.executeUpdate(insertAcquisition);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void update(Sale sale) {
        Statement statement = null;
        try {
            String sqlStr = "UPDATE mynewdb.sale SET price = '"+ sale.getPrice()+"'" +
                    ", salesTerms = '"+ sale.getSalesTerms()+"'"+
                    ", category = '"+ sale.getCategory()+"'"+
                    ", dateOfSale = '"+ sale.getDateOfSale()+"'"+
                    ", User_idUser = '"+ sale.getIdUser()+"'"+
                    ", Client_idClient = '"+ sale.getIdClient()+"'"+
                    ", Product_idProduct = '"+ sale.getIdProduct()+"' WHERE idSale = "
                    + sale.getIdSale();
            statement = connection.createStatement();
            int n = statement.executeUpdate(sqlStr);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void delete(Sale sale){
        Statement statement = null;
        try {
            String sqlString = "DELETE FROM mynewdb.sale WHERE idSale = " + sale.getIdSale();
            statement = connection.createStatement();
            int n = statement.executeUpdate(sqlString);
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
    public Sale getObject(int id) {
        Statement statement = null;
        try {
            String str = "SELECT * FROM mynewdb.sale " +
                    " WHERE idSale = " + Integer.toString(id);
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(str);
            Sale sale = new Sale();
            sale.setIdSale(resultSet.getInt("idSale"));
            sale.setPrice(resultSet.getFloat("price"));
            sale.setSalesTerms(resultSet.getString("salesTerms"));
            sale.setCategory(resultSet.getString("category"));
            Date date = resultSet.getDate("dateOfSale");
            sale.setDateOfSale(date.toLocalDate());
            sale.setIdUser(resultSet.getInt("User_idUser"));
            sale.setIdClient(resultSet.getInt("Client_idClient"));
            sale.setIdProduct(resultSet.getInt("Product_idProduct"));
            return sale;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    @Override
    public List<Sale> getAllObjects(){
        List<Sale> saleList = new ArrayList<>();
        Statement statement = null;
        try {
            String getStr = "SELECT * FROM mynewdb.sale";

            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(getStr);
            while (resultSet.next()) {
                Sale sale = new Sale();
                sale.setIdSale(resultSet.getInt("idSale"));
                sale.setPrice(resultSet.getFloat("price"));
                sale.setSalesTerms(resultSet.getString("salesTerms"));
                sale.setCategory(resultSet.getString("category"));
                Date date = resultSet.getDate("dateOfSale");
                sale.setDateOfSale(date.toLocalDate());
                sale.setIdUser(resultSet.getInt("User_idUser"));
                sale.setIdClient(resultSet.getInt("Client_idClient"));
                sale.setIdProduct(resultSet.getInt("Product_idProduct"));

                saleList.add(sale);
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
        return saleList;
    }
}
