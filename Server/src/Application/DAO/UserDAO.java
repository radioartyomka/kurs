package Application.DAO;

import Application.models.User;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements InterfaceDAO<User> {
    private Connection connection;

    public UserDAO() {}

    public void setConnection(Connection connection){
        this.connection = connection;
    }

    public User checkedUser(String login, String password) {
        Statement statement = null;
        try {
            String getStr = "SELECT * FROM mynewdb.user " + "WHERE login = '" + login + "' AND password = '" + password + "'";
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(getStr);


            while (resultSet.next()) {
                User user = new User();
                user.setIdUser(resultSet.getInt("idUser"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setRole(resultSet.getInt("role"));
                return user;
            }
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
    public void add(User user) {
        Statement statement = null;
        try {
            String insertString = "INSERT INTO mynewdb.user (idUser, login, password, role) " +
                    "VALUES ('"+user.getIdUser()+"','"
                    +user.getLogin()+"','"
                    +user.getPassword()+"',"
                    +user.getRole()+")";
            statement = connection.createStatement();
            int n = statement.executeUpdate(insertString);
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
    public void update(User user) {
        Statement statement = null;
        try {
            String updateString = "UPDATE mynewdb.user SET login = '"+user.getLogin()+"'"
                    +", password = '"+user.getPassword()+"'"+", role = '"+user.getRole()
                    +"' WHERE idUser = " +user.getIdUser();
            statement = connection.createStatement();
            int n = statement.executeUpdate(updateString);
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
    public void delete(User user){
        Statement statement = null;
        try {
            String deleteString = "DELETE FROM mynewdb.user WHERE idUser = " + user.getIdUser();
            statement = connection.createStatement();
            int n = statement.executeUpdate(deleteString);
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
    public User getObject(int id) {
        Statement statement = null;
        try {
            String getStr = "SELECT * FROM mynewdb.user" + " WHERE idUser = " +Integer.toString(id);
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(getStr);

            User user = new User();
            user.setIdUser(resultSet.getInt("idUser"));
            user.setLogin(resultSet.getString("login"));
            user.setPassword(resultSet.getString("password"));
            user.setRole(resultSet.getInt("role"));

            return user;
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
    public List<User> getAllObjects() {
        List<User> userList = new ArrayList<>();
        Statement statement = null;
        try {
            String getStr = "SELECT * FROM mynewdb.user";
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(getStr);
            while (resultSet.next()) {
                User user = new User();
                user.setIdUser(resultSet.getInt("idUser"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setRole(resultSet.getInt("role"));
                userList.add(user);
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
        return userList;
    }
}
