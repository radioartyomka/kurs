package Application.service;

import Application.DAO.DostavkaDAO;
import Application.models.Dostavka;


import java.sql.Connection;
import java.util.List;

public class DostavkaService implements InterfaceService<Dostavka> {
    DostavkaDAO ob = new DostavkaDAO();

    public void setConnection(Connection connection) {
        ob.setConnection(connection);
    }

    @Override
    public void add(Dostavka dostavka) {
        ob.add(dostavka);
    }

    @Override
    public void update(Dostavka dostavka) {
        ob.update(dostavka);
    }

    @Override
    public void delete(Dostavka dostavka) {
        ob.delete(dostavka);
    }

    @Override
    public Dostavka getObject(int id) {
        return ob.getObject(id);
    }

    @Override
    public List<Dostavka> getAllObjects() {
        return ob.getAllObjects();
    }
}
