package Application;

import Application.models.*;

import java.io.*;
import java.net.Socket;
import java.util.List;

public class Connection {
    static ObjectInputStream cois;
    static ObjectOutputStream coos;

    public static void connectionToServer() {
        try {
            Socket clientSocket = new Socket("127.0.0.1", 1813);
            System.out.println("Соединение установлено...");
            cois = new ObjectInputStream(clientSocket.getInputStream());
            coos = new ObjectOutputStream(clientSocket.getOutputStream());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static User authorizationUser(String login, String password) {
        try {
            coos.writeObject("authorization");
            coos.writeObject(login);
            coos.writeObject(password);
            User user = (User)cois.readObject();
            return user;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean addUser(User user) {
        try {
            coos.writeObject("addUser");
            coos.writeObject(user);
            return (boolean) cois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean addProduct(Product product) {
        try {
            coos.writeObject("addProduct");
            coos.writeObject(product);
            return (boolean)cois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
    public static boolean addDostavka(Dostavka dostavka) {
        try {
            coos.writeObject("add");
            coos.writeObject("addDostavka");
            coos.writeObject(dostavka);
            return (boolean)cois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
    public static boolean addSale(Sale sale) {
        try {
            coos.writeObject("addSale");
            coos.writeObject(sale);
            return (boolean)cois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean addClient(Client client) {
        try {
            coos.writeObject("addClient");
            coos.writeObject(client);
            return (boolean)cois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean addManufacturer(Manufacturer manufacturer) {
        try {
            coos.writeObject("addManufacturer");
            coos.writeObject(manufacturer);
            return (boolean) cois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean updateUser(User user) {
        try {
            coos.writeObject("updateUser");
            coos.writeObject(user);
            return (boolean) cois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean updateProduct(Product product) {
        try {
            coos.writeObject("updateProduct");
            coos.writeObject(product);
            return (boolean)cois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
    public static boolean updateDostavka(Dostavka dostavka) {
        try {
            coos.writeObject("updateDostavka");
            coos.writeObject(dostavka);
            return (boolean)cois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
    public static boolean updateSale(Sale sale) {
        try {
            coos.writeObject("updateSale");
            coos.writeObject(sale);
            return (boolean)cois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean updateClient(Client client) {
        try {
            coos.writeObject("updateClient");
            coos.writeObject(client);
            return (boolean)cois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean updateManufacturer(Manufacturer manufacturer) {
        try {
            coos.writeObject("updateManufacturer");
            coos.writeObject(manufacturer);
            return (boolean) cois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean deleteUser(User user) {
        try {
            coos.writeObject("deleteUser");
            coos.writeObject(user);
            return (boolean)cois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean deleteProduct(Product product) {
        try {
            coos.writeObject("deleteProduct");
            coos.writeObject(product);
            return (boolean) cois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
    public static boolean deleteDostavka(Dostavka dostavka) {
        try {
            coos.writeObject("deleteDostavka");
            coos.writeObject(dostavka);
            return (boolean) cois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
    public static boolean deleteSale(Sale sale) {
        try {
            coos.writeObject("deleteSale");
            coos.writeObject(sale);
            return (boolean) cois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean deleteClient(Client client) {
        try {
            coos.writeObject("deleteClient");
            coos.writeObject(client);
            return (boolean) cois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean deleteManufacturer(Manufacturer manufacturer) {
        try {
            coos.writeObject("deleteManufacturer");
            coos.writeObject(manufacturer);
            return (boolean) cois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static List<User> getUserList() {
        try {
            coos.writeObject("getUserList");
            return (List<User>)cois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Product> getProductList() {
        try {
            coos.writeObject("getProductList");
            return (List<Product>)cois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static List<Dostavka> getDostavkaList() {
        try {
            coos.writeObject("getDostavkaList");
            return (List<Dostavka>)cois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Client> getClientList() {
        try {
            coos.writeObject("getClientList");
            return (List<Client>)cois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Sale> getSaleList() {
        try {
            coos.writeObject("getSaleList");
            return (List<Sale>)cois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Manufacturer> getManufacturerList() {
        try {
            coos.writeObject("getManufacturerList");
            return (List<Manufacturer>) cois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int[] monthProfit() {
        try {
            coos.writeObject("monthProfit");
            return (int[]) cois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int[] monthCount() {
        try {
            coos.writeObject("monthCount");
            return (int[]) cois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int totalProfit() {
        try {
            coos.writeObject("totalProfit");
            return (int) cois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static int totalCount() {
        try {
            coos.writeObject("totalCount");
            return (int) cois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static int rassrochkaCount() {
        try {
            coos.writeObject("rassrochkaCount");
            return (int) cois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static int fullPayCount() {
        try {
            coos.writeObject("fullPayCount");
            return (int) cois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }
}