package Application.controllers;

import Application.Connection;
import Application.models.Client;
import Application.models.Dostavka;
import Application.models.Sale;
import Application.util.DateUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import Application.MainApp;
import Application.models.Product;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;



public class SaleOverviewController implements Serializable {
    static ObservableList<Sale> saleList;
    static ObservableList<Product> productList;
    static ObservableList<Dostavka> dostavkaList;
    static ObservableList<Client> clientList;

    @FXML
    private TableView<Sale> saleTable;
    @FXML
    private TableColumn<Sale, String> categoryColumn;
    @FXML
    private TableColumn<Sale, Float> priceColumn;
    @FXML
    private TableColumn<Sale, String> salesTermsColumn;
    @FXML
    private TableColumn<Sale, String> dateOfSaleColumn;
    @FXML
    private TableColumn<Sale, Integer> idSaleColumn;
    @FXML
    private TableColumn<Sale, Integer> idClientColumn;
    @FXML
    private TableColumn<Sale, Integer> idProductColumn;
    @FXML
    private Label nameProductLabel;
    @FXML
    private Label sizeScreenLabel;
    @FXML
    private Label colorLabel;
    @FXML
    private Label usbPortLabel;
    @FXML
    private Label osLabel;
    @FXML
    private Label manufacturerLabel;
    @FXML
    private Label avtoLabel;
    @FXML
    private Label numberAvtoLabel;
    @FXML
    private Label kolLabel;
    @FXML
    private Label typeLabel;
    @FXML
    private Label priceLabel;
    @FXML
    private Label dateLabel;
    @FXML
    private Label firstNameLabel;
    @FXML
    private Label secondNameLabel;
    @FXML
    private Label  phoneNumberLabel;
    @FXML
    private Label emailLabel;
    @FXML
    private Label addressLabel;
    @FXML
    private Label loginUserField;

    @FXML
    private TextField filterField;

    private Sale sale;

    public SaleOverviewController(){
    }

    private void setSale(Sale sale) {
        this.sale = sale;
    }

    public void showSaleOverview(){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/SaleOverview.fxml"));
            AnchorPane OverviewWin = loader.load();

            Scene scene = new Scene(OverviewWin);
            MainApp.primaryStage.setScene(scene);
            MainApp.primaryStage.setMinHeight(700);
            MainApp.primaryStage.setMinWidth(900);
            MainApp.primaryStage.setResizable(true);

            MainApp.primaryStage.show();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {
        productList = FXCollections.observableArrayList(Connection.getProductList());
        dostavkaList = FXCollections.observableArrayList(Connection.getDostavkaList());
        clientList = FXCollections.observableArrayList(Connection.getClientList());
        saleList = FXCollections.observableArrayList(Connection.getSaleList());


        filter();


        idSaleColumn.setCellValueFactory(new PropertyValueFactory<Sale, Integer>("idSale"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<Sale, String>("category"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Sale, Float>("price"));
        salesTermsColumn.setCellValueFactory(new PropertyValueFactory<Sale, String>("salesTerms"));
        dateOfSaleColumn.setCellValueFactory(new PropertyValueFactory<Sale, String>("dateOfSale"));
        idClientColumn.setCellValueFactory(new PropertyValueFactory<Sale, Integer>("idClient"));
        idProductColumn.setCellValueFactory(new PropertyValueFactory<Sale, Integer>("idProduct"));

        loginUserField.setText(AuthorizationController.loginUser);

        showProductDetails(null, null, null , null);

        saleTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showProductDetails(newValue, productList, clientList,dostavkaList));
    }

    private void showProductDetails(Sale sale, ObservableList<Product> productList, ObservableList<Client> clientList,ObservableList<Dostavka> dostavkaList) {
        if (sale != null) {
            for (Product product : productList) {
                if (sale.getIdProduct() == product.getIdProduct()) {
                    nameProductLabel.setText(product.getProductName());
                    sizeScreenLabel.setText(Float.toString(product.getSizeScreen()));
                    colorLabel.setText(product.getColor());
                    usbPortLabel.setText(product.getUsbPort());
                    osLabel.setText(product.getOs());
                    manufacturerLabel.setText(product.getManufacturer());
                }
            }
            for (Dostavka dostavka : dostavkaList) {
                if (sale.getIdDostavka() == dostavka.getIdDostavka()) {
                    avtoLabel.setText(dostavka.getAvto());
                    numberAvtoLabel.setText(dostavka.getNumberAvto());
                    kolLabel.setText(dostavka.getKol());
                    typeLabel.setText(dostavka.getType());
                    priceLabel.setText(dostavka.getPrice());
                    dateLabel.setText(dostavka.getDate());



                }
            }
            for (Client client : clientList) {
                if (client.getIdClient() == sale.getIdClient()) {
                    firstNameLabel.setText(client.getFirstName());
                    secondNameLabel.setText(client.getSecondName());
                    phoneNumberLabel.setText(client.getPhoneNumber());
                    emailLabel.setText(client.getEmail());
                    addressLabel.setText(client.getAddress());
                }
            }
        }
        else {
            nameProductLabel.setText("");
            sizeScreenLabel.setText("");
            colorLabel.setText("");
            usbPortLabel.setText("");
            osLabel.setText("");
            manufacturerLabel.setText("");
            firstNameLabel.setText("");
            secondNameLabel.setText("");
            phoneNumberLabel.setText("");
            emailLabel.setText("");
            addressLabel.setText("");
        }
    }

    private void filter() {
        FilteredList<Sale> filteredData = new FilteredList<>(saleList, a -> true);

        filterField.textProperty().addListener((observable, oldValue, newValue) -> filteredData.setPredicate(sale -> {
            if (newValue == null || newValue.isEmpty()) {
                return true;
            }

            String lowerCaseFilter = newValue.toLowerCase();

            if (sale.getSalesTerms().toLowerCase().contains(lowerCaseFilter)) {
                return true;
            }
            else if (sale.getCategory().toLowerCase().contains(lowerCaseFilter)){
                return true;
            }
            else if (Float.toString(sale.getPrice()).toLowerCase().contains(lowerCaseFilter)) {
                return true;
            }
            else if (DateUtil.format(sale.getDateOfSale()).toLowerCase().contains(lowerCaseFilter)) {
                return true;
            }

            return false;
        }));

        SortedList<Sale> sortedData = new SortedList<>(filteredData);

        sortedData.comparatorProperty().bind(saleTable.comparatorProperty());

        saleTable.setItems(sortedData);
    }

    @FXML
    private void addObject() {
        SaleEditDialogController saleEditDialogController = new SaleEditDialogController();
        Sale itemSale = new Sale();
        Client itemClient = new Client();
        boolean onClicked = saleEditDialogController.showSaleEditDialog(itemSale, itemClient, 1);
        if (onClicked) {
            if (!Connection.addSale(itemSale) && !Connection.addClient(itemClient)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initModality(Modality.WINDOW_MODAL);
                alert.setTitle("Ошибка");
                alert.setHeaderText("При добавлении произошла ошибка!");
                alert.showAndWait();
            }
            saleList.add(itemSale);
            clientList.add(itemClient);
        }
    }

    @FXML
    private void changeObject() {
        SaleEditDialogController saleEditDialogController = new SaleEditDialogController();
        Sale selectedSale = saleTable.getSelectionModel().getSelectedItem();
        if (selectedSale != null) {
            for (Client ob : clientList) {
                if (selectedSale.getIdClient() == ob.getIdClient()) {
                    boolean onClicked = saleEditDialogController.showSaleEditDialog(selectedSale, ob, 2);
                    if (onClicked) {
                        showProductDetails(selectedSale, productList, clientList,dostavkaList);
                }
            }

            }
            saleTable.refresh();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(MainApp.primaryStage);
            alert.setTitle("Предупреждение...");
            alert.setHeaderText("Строка для изменения не выбрана!");
            alert.setContentText("Пожалуйста, выберите необходимую строку\n для изменения информации!");

            alert.showAndWait();
        }
    }

    @FXML
    private void deleteObject() {
        ConfirmController confirmController = new ConfirmController();
            Sale deletedSale = saleTable.getSelectionModel().getSelectedItem();
            if (deletedSale != null) {
                if (confirmController.showConfirmWindow()) {
                    for (int i = 0; i < saleList.size(); i++) {
                        if (deletedSale.getIdSale() == saleList.get(i).getIdSale()) {
                            if (!Connection.deleteSale(deletedSale)) {
                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.initModality(Modality.WINDOW_MODAL);
                                alert.setTitle("Ошибка");
                                alert.setHeaderText("При удалении произошла ошибка!");
                                alert.showAndWait();
                            }
                            saleList.remove(saleList.get(i));
                        }
                    }
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.initOwner(MainApp.primaryStage);
                alert.setTitle("Ошибка...");
                alert.setHeaderText("Строка для удаления не выбрана!");
                alert.setContentText("Пожалуйста, выберите необходимую строку в таблицей!");

                alert.showAndWait();
            }
    }

    @FXML
    private void productsList() {
        ProductOverviewController productOverviewController = new ProductOverviewController();
        productOverviewController.showProductOverview();
    }
    @FXML
    private void dostavkaList() {
        ProductDostavkaOverviewController  productDostavkaOverviewController = new  ProductDostavkaOverviewController();
        productDostavkaOverviewController.showProductDostavkaOverview();
    }

    @FXML
    private void CalculationOfProfit() {
        ProfitStatisticsController profitStatisticsController = new ProfitStatisticsController();
        profitStatisticsController.showProfitStatistics();
    }

    @FXML
    private void exitLogWin() {
        if (AuthorizationController.roleUser != 0) {
            MainApp.primaryStage.close();
            AdminMenuController adminMenuController = new AdminMenuController();
            adminMenuController.showAdminMenu();
        } else {
            MainApp.primaryStage.close();
            System.exit(0);
        }
    }
    @FXML
    private void exit1LogWin() {
        MainApp.primaryStage.close();
        System.exit(0);
    }
}
