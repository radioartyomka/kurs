package Application.controllers;

import Application.Connection;
import Application.MainApp;
import Application.models.Product;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class ProductEditDialogController {
    @FXML
    private TextField idProductField;
    @FXML
    private TextField productNameField;
    @FXML
    private TextField sizeScreenField;
    @FXML
    private TextField colorField;
    @FXML
    private TextField usbPortField;
    @FXML
    private TextField osField;
    @FXML
    private TextField manufacturerField;

    private boolean onClicked = false;
    private Stage page;
    private Product product;
    private int mode;

    public boolean showProductEditDialog(Product product, Stage pageOwner, int mode) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/ProductEditDialog.fxml"));
            AnchorPane page = loader.load();

            Stage dialogWindow = new Stage();
            dialogWindow.initModality(Modality.WINDOW_MODAL);
            dialogWindow.initOwner(pageOwner);
            Scene scene = new Scene(page);

            dialogWindow.setScene(scene);

            ProductEditDialogController controller = loader.getController();
            controller.setStage(dialogWindow);
            controller.getProduct(product, mode);

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

    public void getProduct(Product product, int mode) {
        this.product = product;
        this.mode = mode;

        if (mode == 1) {
            idProductField.setEditable(true);
        }
        if (mode == 2) {
            idProductField.setEditable(false);
        }
        if (product.getIdProduct() == 0) {
            idProductField.setText("");
        } else {
            idProductField.setText(Integer.toString(product.getIdProduct()));
        }
        productNameField.setText(product.getProductName());
        sizeScreenField.setText(Float.toString(product.getSizeScreen()));
        colorField.setText(product.getColor());
        usbPortField.setText(product.getUsbPort());
        osField.setText(product.getOs());
        manufacturerField.setText(product.getManufacturer());
    }

    public void setProduct() {
        product.setIdProduct(Integer.parseInt(idProductField.getText()));
        product.setProductName(productNameField.getText());
        product.setSizeScreen(Float.parseFloat(sizeScreenField.getText()));
        product.setColor(colorField.getText());
        product.setUsbPort(usbPortField.getText());
        product.setOs(osField.getText());
        product.setManufacturer(manufacturerField.getText());
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
                    setProduct();
                    onClicked = true;
                    page.close();
                }
            } else if (mode == 2) {
                setProduct();
                Connection.updateProduct(product);
                onClicked = true;
                page.close();
            }
        }
    }

    private boolean fieldValid() {
        String errorMessage = "";

        if (idProductField.getText() == null || idProductField.getText().length() == 0) {
            errorMessage += "Поле 'ID товара' не заполнено!\n";
        }
        else {
            try {
                Integer.parseInt(idProductField.getText());
            }
            catch (NumberFormatException e) {
                errorMessage += "ID товара введён некорректно(допустимы целочисленные значения)!\n";
            }
        }

        if (productNameField.getText() == null || productNameField.getText().length() == 0) {
            errorMessage += "Поле 'Наименование' не заполнено!\n";
        }

        if (sizeScreenField.getText() == null || sizeScreenField.getText().length() == 0) {
            errorMessage += "Поле 'Диагональ экрана' не заполнено!\n";
        }
        else {
            try {
                Float.parseFloat(idProductField.getText());
            }
            catch (NumberFormatException e) {
                errorMessage += "Для 'Диагональ экрана' " +
                        "допустимы  только численные символы!\n";
            }
        }

        if (colorField.getText() == null || colorField.getText().length() == 0) {
            errorMessage += "Поле 'Цвет' не заполнено!\n";
        }

        if (usbPortField.getText() == null || usbPortField.getText().length() == 0) {
            errorMessage += "Поле 'USB-порт' не заполнено!\n";
        }

        if (osField.getText() == null || usbPortField.getText().length() == 0) {
            errorMessage += "Поле 'ОС' не заполнено!\n";
        }

        if (manufacturerField.getText() == null || manufacturerField.getText().length() == 0) {
            errorMessage += "Поле 'Производитель' не заполнено!\n";
        }

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
        for (int i = 0; i < SaleOverviewController.productList.size(); i++) {
            if (SaleOverviewController.productList.get(i).getIdProduct() == Integer.parseInt(idProductField.getText())) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initModality(Modality.APPLICATION_MODAL);
                alert.setTitle("Ошибка!");
                alert.setHeaderText("Введённый ID не действителен!");
                alert.setContentText("Продукт с таким ID уже существует!");

                alert.showAndWait();
                return false;
            }
        }
        return true;
    }
}
