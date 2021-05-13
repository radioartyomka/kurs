package Application.controllers;

import Application.Connection;
import Application.MainApp;
import Application.models.Dostavka;
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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class ProductDostavkaOverviewController {
    @FXML
    private TableView<Dostavka> dostavkaTable;
    @FXML
    private TableColumn<Dostavka, Integer> idDostavkaColumn;
    @FXML
    private TableColumn<Dostavka, String> avtoColumn;
    @FXML
    private TableColumn<Dostavka, String> numberAvtoColumn;
    @FXML
    private TableColumn<Dostavka, String> kolColumn;
    @FXML
    private TableColumn<Dostavka, String> typeColumn;
    @FXML
    private TableColumn<Dostavka, String> priceColumn;
    @FXML
    private TableColumn<Dostavka, LocalDate> dateColumn;
    @FXML
    private TextField filterDostavkaField;

    private Stage page;

    public ProductDostavkaOverviewController() {
    }

    public void showProductDostavkaOverview() {
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/ProductDostavkaOverview.fxml"));
            AnchorPane tryWindow = loader.load();

            Stage dostavkaTableWin = new Stage();
            dostavkaTableWin.initModality(Modality.NONE);
            Scene scene = new Scene(tryWindow);
            dostavkaTableWin.setScene(scene);

            dostavkaTableWin.show();

            ProductDostavkaOverviewController controller = loader.getController();
            controller.setStage(dostavkaTableWin);
        }
        catch(IOException e){
            System.out.println("ошибка 4 здесь");
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {
        //filter();

        idDostavkaColumn.setCellValueFactory(new PropertyValueFactory<Dostavka, Integer>("idDostavka"));
        avtoColumn.setCellValueFactory(new PropertyValueFactory<Dostavka, String>("avto"));
        numberAvtoColumn.setCellValueFactory(new PropertyValueFactory<Dostavka, String>("numberAvto"));
        kolColumn.setCellValueFactory(new PropertyValueFactory<Dostavka, String>("kol"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<Dostavka, String>("type"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Dostavka, String>("price"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<Dostavka, LocalDate>("date"));

    }

    private void filter() {
        FilteredList<Dostavka> filteredList = new FilteredList<>(SaleOverviewController.dostavkaList, p -> true);
        filterDostavkaField.textProperty().addListener((observable, oldValue, newValue) -> filteredList.setPredicate(dostavka -> {
            if (newValue == null || newValue.isEmpty()) {
                return true;
            }
            String lowerCaseFilter = newValue.toLowerCase();

            if (Integer.toString(dostavka.getIdDostavka()).toLowerCase().contains(lowerCaseFilter)) {
                return true;
            } else if (dostavka.getAvto().toLowerCase().contains(lowerCaseFilter)) {
                return true;
            } else if (dostavka.getNumberAvto().toLowerCase().contains(lowerCaseFilter)) {
                return true;
            } else if (dostavka.getKol().toLowerCase().contains(lowerCaseFilter)) {
                return true;
            } else if (dostavka.getType().toLowerCase().contains(lowerCaseFilter)) {
                return true;
            }else if (dostavka.getPrice().toLowerCase().contains(lowerCaseFilter)){
                return true;
            } else if (dostavka.getDate().equals(LocalDate.now())) {
                return true;
            }
            return false;
        }));

        SortedList<Dostavka> sortedList = new SortedList<>(filteredList);

        sortedList.comparatorProperty().bind(dostavkaTable.comparatorProperty());

        dostavkaTable.setItems(sortedList);
    }

    public void setStage(Stage page) {
        this.page = page;
    }

    @FXML
    private void addDostavka() {
        ProductDostavkaController productDostavkaController = new ProductDostavkaController();
        Dostavka itemDostavka = new Dostavka();
        boolean onClicked = productDostavkaController.showProductDostavka(itemDostavka, page, 1);
        if (onClicked) {
            if (!Connection.addDostavka(itemDostavka)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initModality(Modality.WINDOW_MODAL);
                alert.setTitle("Ошибка");
                alert.setHeaderText("При добавлении произошла ошибка!");
                alert.showAndWait();
            }
            SaleOverviewController.dostavkaList.add(itemDostavka);
        }
    }

    @FXML
    private void ChangeDostavka() {
        ProductDostavkaController productDostavkaController = new ProductDostavkaController();
        Dostavka selectedDostavka = dostavkaTable.getSelectionModel().getSelectedItem();
        if (selectedDostavka != null) {
            productDostavkaController.showProductDostavka(selectedDostavka, page, 2);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initModality(Modality.WINDOW_MODAL);
            alert.setTitle("");
            alert.setHeaderText("");
            alert.setContentText("");

            alert.showAndWait();
        }
        dostavkaTable.refresh();
    }

    @FXML
    private void DeleteDostavka() {
        ConfirmController confirmController = new ConfirmController();
        Dostavka deletedDostavka = dostavkaTable.getSelectionModel().getSelectedItem();
        if (deletedDostavka != null) {
            if (confirmController.showConfirmWindow()) {
                for (int i = 0; i < SaleOverviewController.dostavkaList.size(); i++) {
                    if (deletedDostavka.getIdDostavka() == SaleOverviewController.dostavkaList.get(i).getIdDostavka()) {
                        if (!Connection.deleteDostavka(deletedDostavka)) {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.initModality(Modality.WINDOW_MODAL);
                            alert.setTitle("Ошибка");
                            alert.setHeaderText("При удалении произошла ошибка!");
                            alert.showAndWait();
                        }
                        SaleOverviewController.dostavkaList.remove(SaleOverviewController.dostavkaList.get(i));
                    }
                }
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(page);
            alert.setTitle("Ошибка...");
            alert.setHeaderText("Доставка не выбрана!");
            alert.setContentText("Пожалуйста, выберете необходимую строку в таблицей!");

            alert.showAndWait();
        }
    }
}
