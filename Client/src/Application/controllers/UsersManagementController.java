package Application.controllers;

import Application.Connection;
import Application.MainApp;
import Application.models.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;


public class UsersManagementController {
    static ObservableList<User> userList;
    @FXML
    private TableView<User> usersTable;
    @FXML
    private TableColumn<User, String> loginColumn;
    @FXML
    private TableColumn<User, String> passwordColumn;
    @FXML
    private TableColumn<User, Integer> roleColumn;
    @FXML
    private TextField filterField;

    private Stage page;

    public UsersManagementController(){
    }

    public void showUsersManagement() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/UsersManagement.fxml"));
            AnchorPane win = loader.load();

            Scene scene = new Scene(win);
            MainApp.primaryStage.setResizable(false);
            MainApp.primaryStage.setScene(scene);
            MainApp.primaryStage.setMaxWidth(600);
            MainApp.primaryStage.setMaxHeight(400);
            MainApp.primaryStage.show();

            UsersManagementController controller = loader.getController();
            controller.setStage(MainApp.primaryStage);
        }
        catch(IOException e){
            System.out.println("ошибка 4 здесь");
            e.printStackTrace();
        }
    }

    @FXML
    void initialize(){
        userList = FXCollections.observableArrayList(Connection.getUserList());

        filter();
        loginColumn.setCellValueFactory(new PropertyValueFactory<User, String>("login"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<User, String>("password"));
        roleColumn.setCellValueFactory(new PropertyValueFactory<User, Integer>("role"));
    }

    private void filter() {
        FilteredList<User> filteredList = new FilteredList<>(userList, p -> true);
        filterField.textProperty().addListener((observable, oldValue, newValue) -> filteredList.setPredicate(user -> {
            if (newValue == null || newValue.isEmpty()) {
                return true;
            }
            String lowerCaseFilter = newValue.toLowerCase();
            if (user.getLogin().toLowerCase().contains(lowerCaseFilter)) {
                return true;
            }

            return false;
        }));

        SortedList<User> sortedList = new SortedList<>(filteredList);

        sortedList.comparatorProperty().bind(usersTable.comparatorProperty());

        usersTable.setItems(sortedList);
    }

    public void setStage(Stage page) {
        this.page = page;
    }

    @FXML
    private void addUser() {
        User itemUser = new User();
        UsersManagementEditDialogController usersManagementEditDialogController = new UsersManagementEditDialogController();
        boolean onClicked = usersManagementEditDialogController.showUsersManagementEditDialog(itemUser, 1);
        if (onClicked) {
            if (!Connection.addUser(itemUser)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initModality(Modality.WINDOW_MODAL);
                alert.setTitle("Ошибка");
                alert.setHeaderText("При добавлении произошла ошибка!");
                alert.showAndWait();
            }
            userList.add(itemUser);
        }
    }

    @FXML
    private void changeUser() {
        User selectedUser = usersTable.getSelectionModel().getSelectedItem();
        UsersManagementEditDialogController usersManagementEditDialogController = new UsersManagementEditDialogController();
        if (selectedUser != null) {
            usersManagementEditDialogController.showUsersManagementEditDialog(selectedUser, 2);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(page);
            alert.setTitle("Ошибка...");
            alert.setHeaderText("Пользователь для изменения не выбран!");
            alert.setContentText("Выбирите пользователя и повторите попытку.");
            alert.showAndWait();
        }
        usersTable.refresh();
    }

    @FXML
    private void deleteUser() {
        User deletedUser = usersTable.getSelectionModel().getSelectedItem();
        ConfirmController confirmController = new ConfirmController();
        if (deletedUser != null) {
            if (confirmController.showConfirmWindow()) {
                for (int i = 0; i < userList.size(); i++) {
                    if (deletedUser.getLogin().equals(userList.get(i).getLogin())) {
                        if (!Connection.deleteUser(deletedUser)) {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.initModality(Modality.WINDOW_MODAL);
                            alert.setTitle("Ошибка");
                            alert.setHeaderText("При удалении произошла ошибка!");
                            alert.showAndWait();
                        }
                        userList.remove(userList.get(i));
                    }
                }
            }
            System.out.println("Количесвто:" + userList.size());
        }
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(page);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Удаляемый пользователь не выбран!");
            alert.setContentText("Выбирите пользователя и повторите попытку.");

            alert.showAndWait();
        }
    }

    @FXML
    private void exit() {
        page.close();
        AdminMenuController adminMenuController = new AdminMenuController();
        adminMenuController.showAdminMenu();
    }
    @FXML
    private void exit1(){
        page.close();
    }
}
