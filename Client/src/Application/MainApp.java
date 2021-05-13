package Application;

import Application.controllers.*;
import javafx.application.Application;
import javafx.stage.Stage;


public class MainApp extends Application {
    static public Stage primaryStage;

    public MainApp() {
    }

    public static void main(String[] args) {
        Connection.connectionToServer();
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;

        AuthorizationController controller = new AuthorizationController();
        controller.show(primaryStage, this);
    }
}