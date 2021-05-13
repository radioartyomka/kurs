package Application.controllers;


import Application.Connection;
import Application.MainApp;
import Application.models.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class UsersManagementEditDialogController {
    @FXML
    private TextField idField;
    @FXML
    private TextField loginField;
    @FXML
    private TextField passwordField;
    @FXML
    private TextField roleField;

    private Stage page;
    private User user;
    private boolean onClicked = false;
    private int mode;

    public UsersManagementEditDialogController() {
    }

    public boolean showUsersManagementEditDialog(User user, int mode) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/UsersManagementEditDialog.fxml"));
            AnchorPane editDialog = loader.load();

            Stage dialogWindow = new Stage();
            dialogWindow.initModality(Modality.WINDOW_MODAL);
            dialogWindow.initOwner(MainApp.primaryStage);
            Scene scene = new Scene(editDialog);

            dialogWindow.setScene(scene);

            UsersManagementEditDialogController controller = loader.getController();
            controller.setStage(dialogWindow);
            controller.getUsers(user, mode);

            dialogWindow.showAndWait();

            return controller.isOnClicked();
        }
        catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @FXML
    private void initialize(){
    }

    public void setStage(Stage page) {
        this.page = page;
    }

    public boolean isOnClicked() {
        return onClicked;
    }

    public void getUsers(User user, int mode) {
        this.user = user;
        this.mode= mode;
        if (mode == 1) {
            idField.setEditable(true);
        }
        if (mode == 2) {
            idField.setEditable(false);
        }
        idField.setText(Integer.toString(user.getIdUser()));
        loginField.setText(user.getLogin());
        passwordField.setText(user.getPassword());
        roleField.setText(Integer.toString(user.getRole()));
    }

    public void setUsers() {
        user.setIdUser(Integer.parseInt(idField.getText()));
        user.setLogin(loginField.getText());
        user.setPassword(passwordField.getText());
        user.setRole(Integer.parseInt(roleField.getText()));
    }

    @FXML
    private void handleCancel() {
        page.close();
    }

    @FXML
    private void handleOk() {
        if (isValidValue()) {
            if (mode == 1) {
                if (isValidId()) {
                    setUsers();
                    onClicked = true;
                    page.close();
                }
            } else if (mode == 2) {
                setUsers();
                Connection.updateUser(user);
                onClicked = true;
                page.close();
            }
        }

    }

    private boolean isValidValue() {
        String errorMessage = "";

        if (idField.getText() == null || idField.getText().length() == 0) {
            errorMessage += "Id пользователя введён некорректно!\n";
        }
        else {
            try {
                Integer.parseInt(idField.getText());
            } catch(NumberFormatException e) {
                errorMessage += "Поле id допускает ввод только целочисленного значения!\n";
            }
        }

        if (loginField.getText() == null || loginField.getText().length() == 0) {
            errorMessage += "Некорректное имя пользователя!\n";
        }

        if (passwordField.getText() == null || passwordField.getText().length() == 0) {
            errorMessage += "Некорректный пароль!\n";
        }

        if (roleField.getText() == null || roleField.getText().length() == 0) {
            errorMessage += "Некорректный индекс прав пользователя!\n";
        }
        else {
            try{
                Integer.parseInt(roleField.getText());
            }
            catch (NumberFormatException e) {
                errorMessage += "Некорректные значения индекс прав(Допускаются 0 и 1).\n";
            }
        }

        if (Integer.parseInt(roleField.getText()) < 0 || Integer.parseInt(roleField.getText()) > 1) {
            errorMessage += "Некорректные значения индекс прав(Допускаются 0 и 1).\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(page);
            alert.setTitle("Ошибка...");
            alert.setHeaderText("Введите корректные значения.");
            alert.setContentText(errorMessage);

            alert.showAndWait();
            return false;
        }
    }

    private boolean isValidId() {
        for (int i = 0; i < UsersManagementController.userList.size(); i++) {
            if (UsersManagementController.userList.get(i).getIdUser() == Integer.parseInt(idField.getText())) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initModality(Modality.APPLICATION_MODAL);
                alert.setTitle("Ошибка");
                alert.setHeaderText("Ошибка ввода id");
                alert.setContentText("Пользователь с таким id уже существует!");

                alert.showAndWait();
                return false;
            }
        }
        return true;
    }
}

