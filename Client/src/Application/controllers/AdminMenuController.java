package Application.controllers;

import Application.MainApp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.Serializable;

public class AdminMenuController implements Serializable {
    private Stage startAdminStage;

    public void setStartAdminStage(Stage startAdminStage) {
        this.startAdminStage = startAdminStage;
    }

    public void showAdminMenu() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/AdminMenuController.fxml"));
            AnchorPane startAdminWin = loader.load();

            Stage startAdminStage = new Stage();
            Scene scene = new Scene(startAdminWin);
            startAdminStage.setScene(scene);
            startAdminStage.setTitle("Администратор");
            startAdminStage.setResizable(false);

            AdminMenuController controller = loader.getController();
            controller.setStartAdminStage(startAdminStage);

            startAdminStage.show();

        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    private void usersHandle() {
        UsersManagementController usersManagementController = new UsersManagementController();
        usersManagementController.showUsersManagement();
        startAdminStage.hide();
    }

    @FXML
    private void productHandle() {
        SaleOverviewController saleOverviewController = new SaleOverviewController();
        saleOverviewController.showSaleOverview();
        startAdminStage.hide();
    }

    @FXML
    private void exitHandle() {
        startAdminStage.close();
        System.exit(0);
    }
}
