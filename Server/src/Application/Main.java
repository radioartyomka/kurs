package Application;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Main {
    public static void main(String[] args) {

        try{
            ServerSocket server = new ServerSocket(1813);
            System.out.println("Сервер запущен...");

            while (true) {
                Socket socket = server.accept();
                System.out.println("Подключение: "+ socket.getInetAddress().getHostName());
                Server thread = new Server(socket);
                thread.start();
            }
        }catch (IOException e) {
            System.err.println(e.getMessage());
            System.out.println("Ошибка покдючения!");
        }
    }
}
