package Application.service;

import Application.DAO.UserDAO;
import Application.models.User;

import java.sql.Connection;
import java.util.List;

public class UserService implements InterfaceService<User>{
    private UserDAO ob = new UserDAO();

    public void setConnection(Connection connection) {
        ob.setConnection(connection);
    }

    @Override
    public void add(User user) {
        ob.add(user);
    }

    @Override
    public void update(User user) {
        ob.update(user);
    }

    @Override
    public void delete(User user) {
        ob.delete(user);
    }

    @Override
    public User getObject(int id) {
        return ob.getObject(id);
    }

    @Override
    public List<User> getAllObjects() {
        return ob.getAllObjects();
    }

    public User findUser(String login, String password) {
        return ob.checkedUser(login, password);
    }
}
