package Application.controllers;

import Application.Connection;
import Application.MainApp;
import Application.models.Sale;
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

import java.io.IOException;
import java.time.LocalDate;


public class SaleEditDialogController {
    @FXML
    private TextField idSaleField;
    @FXML
    private TextField categoryField;
    @FXML
    private TextField priceField;
    @FXML
    private TextField salesTermsField;
    @FXML
    private DatePicker dateOfSaleField;
    @FXML
    private TextField idProductField;
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField secondNameField;
    @FXML
    private TextField phoneNumberField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField addressField;
    @FXML
    private TextField idClientField;

    private Stage dialogStage;
    private Sale sale;
    private Client client;
    private boolean onClicked = false;
    private int mode;

    public boolean showSaleEditDialog(Sale sale, Client client, int mode) {
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/SaleEditDialog.fxml"));
            AnchorPane page = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.NONE);
            dialogStage.initOwner(MainApp.primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            dialogStage.setResizable(false);

            SaleEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.getSale(sale, client, mode);
            dialogStage.showAndWait();

            return controller.isOnClicked();

        } catch(IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @FXML
    private void initialize(){
    }

    public void setDialogStage(Stage dialogStage){
        this.dialogStage = dialogStage;
    }



    public void getSale(Sale sale, Client client, int mode){
        this.sale = sale;
        this.client = client;
        this.mode = mode;

        if (mode == 1) {
            idSaleField.setEditable(true);
            idProductField.setEditable(true);
            idClientField.setEditable(true);
        }
        if (mode == 2) {
            idSaleField.setEditable(false);
            idProductField.setEditable(false);
            idClientField.setEditable(false);
        }

        idSaleField.setText(Integer.toString(sale.getIdSale()));
        categoryField.setText(sale.getCategory());

        if (sale.getPrice() == 0.0f) {
            priceField.setText("");
        } else {
            priceField.setText(Float.toString(sale.getPrice()));
        }

        salesTermsField.setText(sale.getSalesTerms());
        if (sale.getDateOfSale().equals(LocalDate.now())) {
            dateOfSaleField.setPromptText("");
        } else {
            dateOfSaleField.setValue(sale.getDateOfSale());
        }

        idProductField.setText(Integer.toString(sale.getIdProduct()));
        idClientField.setText(Integer.toString(sale.getIdClient()));

        firstNameField.setText(client.getFirstName());
        secondNameField.setText(client.getSecondName());
        phoneNumberField.setText(client.getPhoneNumber());
        emailField.setText(client.getEmail());
        addressField.setText(client.getAddress());
    }

    private void setSale() {
        sale.setIdSale(Integer.parseInt(idSaleField.getText()));
        sale.setCategory(categoryField.getText());
        sale.setPrice(Float.parseFloat(priceField.getText()));
        sale.setSalesTerms(salesTermsField.getText());
        sale.setDateOfSale(dateOfSaleField.getValue());
        sale.setIdProduct(Integer.parseInt(idProductField.getText()));
        sale.setIdClient(Integer.parseInt(idClientField.getText()));
        sale.setIdUser(AuthorizationController.idUser);
        client.setIdClient(Integer.parseInt(idClientField.getText()));
        client.setFirstName(firstNameField.getText());
        client.setSecondName(secondNameField.getText());
        client.setPhoneNumber(phoneNumberField.getText());
        client.setEmail(emailField.getText());
        client.setAddress(addressField.getText());
    }

    public  boolean isOnClicked() {
        return onClicked;
    }

    @FXML
    private void productList() {
        ProductOverviewController productOverviewController = new ProductOverviewController();
        productOverviewController.showProductOverview();
    }

    @FXML
    private void handleOk(){
        if(isInputValid()){
            if (mode == 1) {
                if (isIdValid()) {
                    setSale();
                    onClicked = true;
                    dialogStage.close();
                }
            } else if (mode == 2) {
                setSale();
                Connection.updateSale(sale);
                Connection.updateClient(client);
                onClicked = true;
                dialogStage.close();
            }
        }
    }

    @FXML
    private void handleCancel(){
        dialogStage.close();
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (categoryField.getText() == null || categoryField.getText().length() == 0) {
            errorMessage += "Поле Категория обязательно для заполения!\n";
        }

        if (priceField.getText() == null || priceField.getText().length() == 0) {
            errorMessage += "Поле Стоимость обязательно для заполения!\n";
        } else {
            try{
                Float.parseFloat(priceField.getText());
            }
            catch(NumberFormatException e) {
                errorMessage += "В поле Стоимоть допустим только ввод численных символов!\n";
            }
        }

        if (salesTermsField.getText() == null || salesTermsField.getText().length() == 0) {
            errorMessage += "Условие продажи обязательно для заполения!\n";
        }


        if(!(salesTermsField.getText().equalsIgnoreCase("Полная оплата")
                || salesTermsField.getText().equalsIgnoreCase("Рассрочка"))) {
            errorMessage += "В 'Услоивие продажи' допустим ввод(Полная оплата, Рассрочка)\n";
        }



        if (idSaleField.getText() == null || idSaleField.getText().length() == 0) {
            errorMessage += "Некорректный ID Продажи!\n";
        }
        else {
            try {
                Integer.parseInt(idSaleField.getText());
            }
            catch(NumberFormatException e) {
                errorMessage += "Некорректный ID Продажи(допустимы только численные символы)!\n";
            }
        }

        if (idProductField.getText() == null || idProductField.getText().length() == 0) {
            errorMessage += "Некорректный ID Продукта!\n";
        }
        else {
            try {
                Integer.parseInt(idSaleField.getText());
            }
            catch(NumberFormatException e) {
                errorMessage += "Некорректный ID Продукта(допустимы только численные символы)!\n";
            }
        }

        if (idClientField.getText() == null || idClientField.getText().length() == 0) {
            errorMessage += "Некорректный ID Клиента!\n";
        }
        else {
            try {
                Integer.parseInt(idSaleField.getText());
            }
            catch(NumberFormatException e) {
                errorMessage += "Некорректный ID Клиента(допустимы только численные символы)!\n";
            }
        }

        if (firstNameField.getText() == null || firstNameField.getText().length() == 0) {
            errorMessage += "Поле Имя обязательно для заполения!\n";
        }

        if (secondNameField.getText() == null || secondNameField.getText().length() == 0) {
            errorMessage += "Поле Фамилия обязательно для заполения!\n";
        }

        if (phoneNumberField.getText() == null || phoneNumberField.getText().length() == 0) {
            errorMessage += "Поле Номер телефона обязательно для заполения!\n";
        }

        if (emailField.getText() == null || emailField.getText().length() == 0) {
            errorMessage += "Поле Email обязательно для заполения(в случае отсутствия данных, поставить -)\n";
        }

        if (addressField.getText() == null || addressField.getText().length() == 0) {
            errorMessage += "Поле Адрес обязательно для заполения!\n";
        }

        if(errorMessage.length() == 0) {
            return true;
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Ошибка...");
            alert.setHeaderText("Некорректные значения!");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }

    private boolean isIdValid() {
        String errorMessage = "";
        for (int i = 0; i < SaleOverviewController.saleList.size(); i++) {
            if (Integer.parseInt(idSaleField.getText()) == SaleOverviewController.saleList.get(i).getIdSale()) {
                errorMessage += "Введённый ID Продажи уже существует!\n";
            }
            if (Integer.parseInt(idProductField.getText()) == SaleOverviewController.saleList.get(i).getIdProduct()) {
                errorMessage += "Введённый ID Продукта уже существует!\n";
            }
            if (Integer.parseInt(idClientField.getText()) == SaleOverviewController.saleList.get(i).getIdClient()) {
                errorMessage += "Введённый ID Клиента уже существует!\n";
            }
        }
        if (errorMessage.length() == 0){
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Ошибка ввода ID");
            alert.setContentText(errorMessage);

            alert.showAndWait();
            return false;
        }
    }
}
