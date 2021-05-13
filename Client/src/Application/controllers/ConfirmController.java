package Application.controllers;


import Application.MainApp;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class ConfirmController {
    private Stage stage;
    private boolean handle = false;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public boolean getHandle() {
        return handle;
    }

    public boolean showConfirmWindow() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/ConfirmWin.fxml"));
            AnchorPane page = loader.load();

            Stage confirmStage = new Stage();
            confirmStage.initModality(Modality.APPLICATION_MODAL);
            Scene scene = new Scene(page);
            confirmStage.setScene(scene);
            confirmStage.setResizable(false);

            ConfirmController controller = loader.getController();
            controller.setStage(confirmStage);

            confirmStage.showAndWait();

            return controller.getHandle();
        }
        catch(IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @FXML
    private void HandleYes() {
        handle = true;
        stage.close();
    }

    @FXML
    private void HandleNo() {
        stage.close();
    }
}
