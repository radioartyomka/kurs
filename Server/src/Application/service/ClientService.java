package Application.service;

import Application.DAO.ClientDAO;
import Application.models.Client;

import java.sql.Connection;
import java.util.List;

public class ClientService implements InterfaceService<Client> {
    ClientDAO ob = new ClientDAO();

    public void setConnection(Connection connection) {
        ob.setConnection(connection);
    }

    @Override
    public void add(Client client) {
        ob.add(client);
    }

    @Override
    public void update(Client client) {
        ob.update(client);
    }

    @Override
    public void delete(Client client) {
        ob.delete(client);
    }

    @Override
    public Client getObject(int id) {
        return ob.getObject(id);
    }

    @Override
    public List<Client> getAllObjects() {
        return ob.getAllObjects();
    }
}
