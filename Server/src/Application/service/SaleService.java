package Application.service;

import Application.DAO.SaleDAO;
import Application.models.Sale;

import java.sql.Connection;
import java.util.List;

public class SaleService implements InterfaceService<Sale>{
    SaleDAO ob = new SaleDAO();

    public void setConnection(Connection connection) {
        ob.setConnection(connection);
    }

    @Override
    public void add(Sale sale) {
        ob.add(sale);
    }

    @Override
    public void update(Sale sale) {
        ob.update(sale);
    }

    @Override
    public void delete(Sale sale) {
        ob.delete(sale);
    }

    @Override
    public Sale getObject(int id) {
        return ob.getObject(id);
    }

    @Override
    public List<Sale> getAllObjects() {
        return ob.getAllObjects();
    }

}
