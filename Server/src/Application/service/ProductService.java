package Application.service;

import Application.DAO.ProductDAO;
import Application.models.Product;

import java.sql.Connection;
import java.util.List;

public class ProductService implements InterfaceService<Product> {
    ProductDAO ob = new ProductDAO();

    public void setConnection(Connection connection) {
        ob.setConnection(connection);
    }

    @Override
    public void add(Product product) {
        ob.add(product);
    }

    @Override
    public void update(Product product) {
        ob.update(product);
    }

    @Override
    public void delete(Product product) {
        ob.delete(product);
    }

    @Override
    public Product getObject(int id) {
        return ob.getObject(id);
    }

    @Override
    public List<Product> getAllObjects() {
        return ob.getAllObjects();
    }
}
