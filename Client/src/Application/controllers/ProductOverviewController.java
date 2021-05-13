package Application.controllers;

import Application.Connection;
import Application.MainApp;
import Application.models.Product;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;


public class ProductOverviewController {
    @FXML
    private TableView<Product> productTable;
    @FXML
    private TableColumn<Product, Integer> idProductColumn;
    @FXML
    private TableColumn<Product, String> productNameColumn;
    @FXML
    private TableColumn<Product, Float> sizeScreenColumn;
    @FXML
    private TableColumn<Product, String> colorColumn;
    @FXML
    private TableColumn<Product, String> usbPortColumn;
    @FXML
    private TableColumn<Product, String> osColumn;
    @FXML
    private TableColumn<Product, String> manufacturerColumn;
    @FXML
    private TextField filterProductField;

    private Stage page;

    public ProductOverviewController() {
    }

    public void showProductOverview() {
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/ProductOverview.fxml"));
            AnchorPane productWin = loader.load();

            Stage productTableWin = new Stage();
            productTableWin.initModality(Modality.NONE);
            Scene scene = new Scene(productWin);
            productTableWin.setScene(scene);

            productTableWin.show();

            ProductOverviewController controller = loader.getController();
            controller.setStage(productTableWin);
        }
        catch(IOException e){
            System.out.println("ошибка 4 здесь");
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {
        filter();

        idProductColumn.setCellValueFactory(new PropertyValueFactory<Product, Integer>("idProduct"));
        productNameColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("productName"));
        sizeScreenColumn.setCellValueFactory(new PropertyValueFactory<Product, Float>("sizeScreen"));
        colorColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("color"));
        usbPortColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("usbPort"));
        osColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("os"));
        manufacturerColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("manufacturer"));
    }

    private void filter() {
        FilteredList<Product> filteredList = new FilteredList<>(SaleOverviewController.productList, p -> true);
        filterProductField.textProperty().addListener((observable, oldValue, newValue) -> filteredList.setPredicate(product -> {
            if (newValue == null || newValue.isEmpty()) {
                return true;
            }
            String lowerCaseFilter = newValue.toLowerCase();

            if (Integer.toString(product.getIdProduct()).toLowerCase().contains(lowerCaseFilter)) {
                return true;
            } else if (product.getProductName().toLowerCase().contains(lowerCaseFilter)) {
                return true;
            } else if (Float.toString(product.getSizeScreen()).toLowerCase().contains(lowerCaseFilter)) {
                return true;
            } else if (product.getColor().toLowerCase().contains(lowerCaseFilter)) {
                return true;
            } else if (product.getManufacturer().toLowerCase().contains(lowerCaseFilter)) {
                return true;
            }else if (product.getUsbPort().toLowerCase().contains(lowerCaseFilter)){
                return true;
            } else if (product.getOs().toLowerCase().contains(lowerCaseFilter)) {
                return true;
            }
            return false;
        }));

        SortedList<Product> sortedList = new SortedList<>(filteredList);

        sortedList.comparatorProperty().bind(productTable.comparatorProperty());

        productTable.setItems(sortedList);
    }

    public void setStage(Stage page) {
        this.page = page;
    }

    @FXML
    private void addProduct() {
        ProductEditDialogController productEditDialogController = new ProductEditDialogController();
        Product itemProduct = new Product();
        boolean onClicked = productEditDialogController.showProductEditDialog(itemProduct, page, 1);
        if (onClicked) {
            if (!Connection.addProduct(itemProduct)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initModality(Modality.WINDOW_MODAL);
                alert.setTitle("Ошибка");
                alert.setHeaderText("При добавлении произошла ошибка!");
                alert.showAndWait();
            }
            SaleOverviewController.productList.add(itemProduct);
        }
    }

    @FXML
    private void ChangeProduct() {
        ProductEditDialogController productEditDialogController = new ProductEditDialogController();
        Product selectedProduct = productTable.getSelectionModel().getSelectedItem();
        if (selectedProduct != null) {
            productEditDialogController.showProductEditDialog(selectedProduct, page, 2);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initModality(Modality.WINDOW_MODAL);
            alert.setTitle("");
            alert.setHeaderText("");
            alert.setContentText("");

            alert.showAndWait();
        }
        productTable.refresh();
    }

    @FXML
    private void DeleteProduct() {
        ConfirmController confirmController = new ConfirmController();
        Product deletedProduct = productTable.getSelectionModel().getSelectedItem();
        if (deletedProduct != null) {
            if (confirmController.showConfirmWindow()) {
                for (int i = 0; i < SaleOverviewController.productList.size(); i++) {
                    if (deletedProduct.getIdProduct() == SaleOverviewController.productList.get(i).getIdProduct()) {
                        if (!Connection.deleteProduct(deletedProduct)) {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.initModality(Modality.WINDOW_MODAL);
                            alert.setTitle("Ошибка");
                            alert.setHeaderText("При удалении произошла ошибка!");
                            alert.showAndWait();
                        }
                        SaleOverviewController.productList.remove(SaleOverviewController.productList.get(i));
                    }
                }
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(page);
            alert.setTitle("Ошибка...");
            alert.setHeaderText("Продукт не выбран!");
            alert.setContentText("Пожалуйста, выберете необходимую строку в таблицей!");

            alert.showAndWait();
        }
    }

}
