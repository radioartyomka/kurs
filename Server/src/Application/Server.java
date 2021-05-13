package Application;

import Application.models.*;
import Application.service.*;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;

public class Server extends Thread {
    private Socket socket;
    private InetAddress inetAddress;
    private PrintStream out;
    private BufferedReader in;

    public Server(Socket socket) throws IOException {
        this.socket = socket;
        inetAddress = socket.getInetAddress();
        out = new PrintStream(socket.getOutputStream());
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public void run() {
        String choice;
        try {
            ObjectOutputStream soos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream sois = new ObjectInputStream(socket.getInputStream());

            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/MySQL?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

            Connection connection = DriverManager.getConnection(url, "root", "root");

            while(true) {
                choice = (String) sois.readObject();
                switch (choice) {
                    case "authorization": {
                        String log = (String) sois.readObject();
                        String pass = (String) sois.readObject();
                        UserService ob = new UserService();
                        ob.setConnection(connection);
                        User userInput = ob.findUser(log, pass);
                        soos.writeObject(userInput);
                        break;
                    }
                    case "addUser": {
                        User newUser = (User)sois.readObject();
                        UserService ob = new UserService();
                        ob.setConnection(connection);
                        ob.add(newUser);
                        soos.writeObject(true);
                        break;
                    }
                    case "addProduct": {
                        Product newProduct = (Product) sois.readObject();
                        ProductService ob = new ProductService();
                        ob.setConnection(connection);
                        ob.add(newProduct);
                        soos.writeObject(true);
                        break;
                    }
                    case "addDostavka": {
                        Dostavka newDostavka = (Dostavka) sois.readObject();
                        DostavkaService ob = new DostavkaService();
                        ob.setConnection(connection);
                        ob.add(newDostavka);
                        soos.writeObject(true);
                        break;
                    }
                    case "addSale": {
                        Sale newSale = (Sale) sois.readObject();
                        SaleService ob = new SaleService();
                        ob.setConnection(connection);
                        ob.add(newSale);
                        soos.writeObject(true);
                        break;
                    }
                    case "addClient": {
                        Client newClient = (Client) sois.readObject();
                        ClientService ob = new ClientService();
                        ob.setConnection(connection);
                        ob.add(newClient);
                        soos.writeObject(true);
                        break;
                    }
                    case "updateUser": {
                        User oldUser = (User) sois.readObject();
                        UserService ob = new UserService();
                        ob.setConnection(connection);
                        ob.update(oldUser);
                        soos.writeObject(true);
                        break;
                    }
                    case "updateProduct": {
                        Product oldProduct = (Product)sois.readObject();
                        ProductService ob = new ProductService();
                        ob.setConnection(connection);
                        ob.update(oldProduct);
                        soos.writeObject(true);
                        break;
                    }
                    case "updateDostavka": {
                        Dostavka oldDostavka = (Dostavka)sois.readObject();
                        DostavkaService ob = new DostavkaService();
                        ob.setConnection(connection);
                        ob.update(oldDostavka);
                        soos.writeObject(true);
                        break;
                    }
                    case "updateSale": {
                        Sale sale = (Sale) sois.readObject();
                        SaleService ob = new SaleService();
                        ob.setConnection(connection);
                        ob.update(sale);
                        soos.writeObject(true);
                        break;
                    }
                    case "updateClient": {
                        Client client = (Client) sois.readObject();
                        ClientService ob = new ClientService();
                        ob.setConnection(connection);
                        ob.update(client);
                        soos.writeObject(true);
                        break;
                    }
                    case "deleteUser": {
                        User user = (User)sois.readObject();
                        UserService ob = new UserService();
                        ob.setConnection(connection);
                        ob.delete(user);
                        soos.writeObject(true);
                        break;
                    }
                    case "deleteProduct": {
                        Product product = (Product)sois.readObject();
                        ProductService ob = new ProductService();
                        ob.setConnection(connection);
                        ob.delete(product);
                        soos.writeObject(true);
                        break;
                    }
                    case "deleteDostavka": {
                        Dostavka dostavka = (Dostavka)sois.readObject();
                        DostavkaService ob = new DostavkaService();
                        ob.setConnection(connection);
                        ob.delete(dostavka);
                        soos.writeObject(true);
                        break;
                    }
                    case "deleteSale": {
                        Sale sale = (Sale)sois.readObject();
                        SaleService ob = new SaleService();
                        ob.setConnection(connection);
                        ob.delete(sale);
                        soos.writeObject(true);
                        break;
                    }
                    case "deleteClient": {
                        Client client = (Client) sois.readObject();
                        ClientService ob = new ClientService();
                        ob.setConnection(connection);
                        ob.delete(client);
                        soos.writeObject(true);
                        break;
                    }
                    case "getUserList": {
                        UserService ob = new UserService();
                        ob.setConnection(connection);
                        soos.writeObject(ob.getAllObjects());
                        break;
                    }
                    case "getProductList": {
                        ProductService ob = new ProductService();
                        ob.setConnection(connection);
                        soos.writeObject(ob.getAllObjects());
                        break;
                    }
                    case "getDostavkaList": {
                        DostavkaService ob = new DostavkaService();
                        ob.setConnection(connection);
                        soos.writeObject(ob.getAllObjects());
                        break;
                    }
                    case "getSaleList": {
                        SaleService ob = new SaleService();
                        ob.setConnection(connection);
                        soos.writeObject(ob.getAllObjects());
                        break;
                    }
                    case "getClientList": {
                        ClientService ob = new ClientService();
                        ob.setConnection(connection);
                        soos.writeObject(ob.getAllObjects());
                        break;
                    }
                    case "getManufacturerList": {
                        ManufacturerService ob = new ManufacturerService();
                        ob.setConnection(connection);
                        soos.writeObject(ob.getAllObjects());
                        break;
                    }
                    case "monthProfit": {
                        StatisticService statisticService = new StatisticService();
                        statisticService.setConnection(connection);
                        soos.writeObject(statisticService.monthProfit());
                        break;
                    }
                    case "monthCount": {
                        StatisticService statisticService = new StatisticService();
                        statisticService.setConnection(connection);
                        soos.writeObject(statisticService.monthCount());
                        break;
                    }
                    case "totalProfit" : {
                        StatisticService statisticService = new StatisticService();
                        statisticService.setConnection(connection);
                        soos.writeObject(statisticService.totalProfit());
                        break;
                    }
                    case "totalCount" : {
                        StatisticService statisticService = new StatisticService();
                        statisticService.setConnection(connection);
                        soos.writeObject(statisticService.totalCount());
                        break;
                    }
                    case "rassrochkaCount" : {
                        StatisticService statisticService = new StatisticService();
                        statisticService.setConnection(connection);
                        soos.writeObject(statisticService.rassrochkaCount());
                        break;
                    }
                    case "fullPayCount" : {
                        StatisticService statisticService = new StatisticService();
                        statisticService.setConnection(connection);
                        soos.writeObject(statisticService.fullPayCount());
                        break;
                    }
                }
            }

        } catch (IOException e) {
            System.err.println(e.getMessage());
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        finally {
            disconnect();
        }
    }

    public void disconnect() {
        try {
            if (out != null) {
                out.close();
            }
            if (in != null) {
                in.close();
            }

            System.out.println("Отключен от сервера: " + inetAddress.getHostName());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        finally {
            this.interrupt();
        }
    }
}
