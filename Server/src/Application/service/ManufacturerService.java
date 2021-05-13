package Application.service;

import Application.DAO.ManufacturerDAO;
import Application.models.Manufacturer;

import java.sql.Connection;
import java.util.List;

public class ManufacturerService implements InterfaceService<Manufacturer> {
    ManufacturerDAO ob = new ManufacturerDAO();

    public void setConnection(Connection connection) {
        ob.setConnection(connection);
    }

    @Override
    public void add(Manufacturer manufacturer) {
        ob.add(manufacturer);
    }

    @Override
    public void update(Manufacturer manufacturer) {
        ob.update(manufacturer);
    }

    @Override
    public void delete(Manufacturer manufacturer) {
        ob.delete(manufacturer);
    }

    @Override
    public Manufacturer getObject(int id) {
        return ob.getObject(id);
    }

    @Override
    public List<Manufacturer> getAllObjects() {
        return ob.getAllObjects();
    }
}
