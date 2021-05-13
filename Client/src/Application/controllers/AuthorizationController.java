package Application.controllers;

import Application.Connection;
import Application.MainApp;
import Application.models.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.TextField;

import java.io.IOException;

public class AuthorizationController {
    @FXML
    private TextField logField;
    @FXML
    private PasswordField passField;
    @FXML
    private Label errorInput;

    private Stage authorizationStage;
    private MainApp mainApp;
    static public int roleUser;
    static public int idUser;
    static public String loginUser;


    public AuthorizationController() {
    }

    public void setAuthorizationStage(Stage authorizationStage) {
        this.authorizationStage = authorizationStage;
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

    }

    public void show(Stage inputStage, MainApp mainApp) {
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(AuthorizationController.class.getResource("../view/Authorization.fxml"));
            AnchorPane inputWin = loader.load();

            Scene scene = new Scene(inputWin);
            inputStage.setScene(scene);
            inputStage.setResizable(false);

            AuthorizationController controller = loader.getController();
            controller.setAuthorizationStage(inputStage);
            controller.setMainApp(mainApp);

            inputStage.show();

        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    public User authorization() {
        String login = logField.getText();
        String password = passField.getText();
        User checkedUser = Connection.authorizationUser(login, password);
        return checkedUser;
    }


    @FXML
    private void handleInput() {
        User user = authorization();
        if (user != null) {
            idUser = user.getIdUser();
            loginUser = user.getLogin();
            roleUser = user.getRole();
            if (user.getRole() == 0) {
                authorizationStage.close();
                SaleOverviewController saleOverviewController = new SaleOverviewController();
                saleOverviewController.showSaleOverview();
            }
            if (user.getRole() == 1) {
                authorizationStage.close();
                AdminMenuController adminMenuController = new AdminMenuController();
                adminMenuController.showAdminMenu();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initModality(Modality.WINDOW_MODAL);
            alert.initOwner(MainApp.primaryStage);
            alert.setTitle("Ошибка авторизации!");
            alert.setHeaderText("Неверный логин или пароль!");
            alert.setContentText("Повторите ввод!");
            alert.showAndWait();
        }
    }
}