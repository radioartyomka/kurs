package Application.controllers;

import Application.Connection;
import Application.MainApp;
import Application.models.Dostavka;
import Application.models.Client;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.time.LocalDate;

public class ProductDostavkaController {
    @FXML
    private TextField idDostavkaField;
    @FXML
    private TextField avtoField;
    @FXML
    private TextField numberAvtoField;
    @FXML
    private TextField kolField;
    @FXML
    private TextField typeField;
    @FXML
    private TextField priceField;
    @FXML
    private TextField dateField;

    private boolean onClicked = false;
    private Stage page;
    private Dostavka dostavka;
    private int mode;

    public boolean showProductDostavka(Dostavka dostavka, Stage pageOwner, int mode) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/ProductDostavka.fxml"));
            AnchorPane page = loader.load();

            Stage dialogWindow = new Stage();
            dialogWindow.initModality(Modality.WINDOW_MODAL);
            dialogWindow.initOwner(pageOwner);
            Scene scene = new Scene(page);

            dialogWindow.setScene(scene);

            ProductDostavkaController controller = loader.getController();
            controller.setStage(dialogWindow);
            controller.getDostavka(dostavka, mode);

            dialogWindow.showAndWait();

            return controller.isOnClicked();
        }
        catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @FXML
    void initialize() {
    }

    public void getDostavka(Dostavka dostavka, int mode) {
        this.dostavka = dostavka;
        this.mode = mode;

        if (mode == 1) {
            idDostavkaField.setEditable(true);
        }
        if (mode == 2) {
            idDostavkaField.setEditable(false);
        }
        if (dostavka.getIdDostavka() == 0) {
            idDostavkaField.setText("");
        } else {
            idDostavkaField.setText(Integer.toString(dostavka.getIdDostavka()));
        }
        avtoField.setText(dostavka.getAvto());
        numberAvtoField.setText(dostavka.getNumberAvto());
        kolField.setText(dostavka.getKol());
        typeField.setText(dostavka.getType());
        priceField.setText(dostavka.getPrice());
        dateField.setText(dostavka.getDate());
    }

    public void setDostavka() {
        dostavka.setIdDostavka(Integer.parseInt(idDostavkaField.getText()));
        dostavka.setAvto(avtoField.getText());
        dostavka.setNumberAvto(numberAvtoField.getText());
        dostavka.setKol(kolField.getText());
        dostavka.setType(typeField.getText());
        dostavka.setPrice(priceField.getText());
        dostavka.setDate(dateField.getText());
    }

    public void setStage(Stage page) {
        this.page = page;
    }

    @FXML
    private void handleCancel() {
        page.close();
    }

    public boolean isOnClicked(){
        return onClicked;
    }

    @FXML
    private void handleOk() {
        if (fieldValid()) {
            if (mode == 1) {
                if (isIdValid()) {
                    setDostavka();
                    onClicked = true;
                    page.close();
                }
            } else if (mode == 2) {
                setDostavka();
                Connection.updateDostavka(dostavka);
                onClicked = true;
                page.close();
            }
        }
    }

    private boolean fieldValid() {
        String errorMessage = "";

        if (idDostavkaField.getText() == null || idDostavkaField.getText().length() == 0) {
            errorMessage += "Поле 'ID Dostavka' не заполнено!\n";
        }
        else {
            try {
                Integer.parseInt(idDostavkaField.getText());
            }
            catch (NumberFormatException e) {
                errorMessage += "ID доставки введён некорректно(допустимы целочисленные значения)!\n";
            }
        }

        if (avtoField.getText() == null || avtoField.getText().length() == 0) {
            errorMessage += "Поле 'Авто' не заполнено!\n";
        }

        if (numberAvtoField.getText() == null || numberAvtoField.getText().length() == 0) {
            errorMessage += "Поле 'Номер авто' не заполнено!\n";
        }

        if (kolField.getText() == null || kolField.getText().length() == 0) {
            errorMessage += "Поле 'Количество товара' не заполнено!\n";
        }

        if (typeField.getText() == null || typeField.getText().length() == 0) {
            errorMessage += "Поле 'Тип товара' не заполнено!\n";
        }

        if (priceField.getText() == null || priceField.getText().length() == 0) {
            errorMessage += "Поле 'Цена' не заполнено!\n";
        }

      //  if (dateField.getText() == null || dateField.getText().length() == 0) {
     //       errorMessage += "Поле 'Дата доставки' не заполнено!\n";
    //    }

        if(errorMessage.length() == 0) {
            return true;
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner(page);
            alert.setTitle("Ошибка!");
            alert.setHeaderText("Необходимо заполнить поля!");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }

    private boolean isIdValid() {
        for (int i = 0; i < SaleOverviewController.dostavkaList.size(); i++) {
            if (SaleOverviewController.dostavkaList.get(i).getIdDostavka() == Integer.parseInt(idDostavkaField.getText())) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initModality(Modality.APPLICATION_MODAL);
                alert.setTitle("Ошибка!");
                alert.setHeaderText("Введённый ID не действителен!");
                alert.setContentText("Доставка с таким ID уже существует!");

                alert.showAndWait();
                return false;
            }
        }
        return true;
    }
}
