package Application.controllers;

import Application.Connection;
import Application.MainApp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.text.DateFormatSymbols;
import java.util.Arrays;
import java.util.Locale;

public class ProfitStatisticsController {
    @FXML
    private BarChart<String, Integer> saleBarChart;
    @FXML
    private BarChart<String, Integer> countBarChart;
    @FXML
    private CategoryAxis xAxis;
    @FXML
    private Label profitPerYearLabel;
    @FXML
    private Label soldField;
    @FXML
    private Label rassrochkaField;
    @FXML
    private Label fullPayLabel;

    private ObservableList<String> monthNames = FXCollections.observableArrayList();

    public void showProfitStatistics() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/ProfitStatistics.fxml"));
            AnchorPane page = loader.load();

            Stage stage = new Stage();
            Scene scene = new Scene(page);
            stage.setTitle("Данные о продажах");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(MainApp.primaryStage);
            stage.setScene(scene);
            ProfitStatisticsController controller = loader.getController();
            controller.setAcquisitionSaleData();
            controller.setAcquisitionCountData();
            controller.setField();
            stage.show();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {
        // Получаем массив с английскими именами месяцев.
        String[] months = DateFormatSymbols.getInstance(Locale.ENGLISH).getMonths();
        // Преобразуем его в список и добавляем в наш ObservableList месяцев.
        monthNames.addAll(Arrays.asList(months));
        // Назначаем имена месяцев категориями для горизонтальной оси.
        xAxis.setCategories(monthNames);

        profitPerYearLabel.setText("");
        soldField.setText("");
        rassrochkaField.setText("");
        fullPayLabel.setText("");
    }

    private void setAcquisitionSaleData() {
        int [] monthProfit = Connection.monthProfit();

        XYChart.Series<String, Integer> series = new XYChart.Series<>();
        for (int i = 0; i < monthProfit.length; i++) {
            series.getData().add(new XYChart.Data<>(monthNames.get(i), monthProfit[i]));
        }
        saleBarChart.getData().add(series);
    }

    private void setAcquisitionCountData() {
        int[] monthCount = Connection.monthCount();

        XYChart.Series<String, Integer> countData = new XYChart.Series<>();
        for (int i = 0; i < monthCount.length; i++) {
            countData.getData().add(new XYChart.Data<>(monthNames.get(i), monthCount[i]));
        }
        countBarChart.getData().add(countData);
    }

    private void setField() {
        profitPerYearLabel.setText(Integer.toString(Connection.totalProfit()));
        soldField.setText(Integer.toString(Connection.totalCount()));
        rassrochkaField.setText(Integer.toString(Connection.rassrochkaCount()));
        fullPayLabel.setText(Integer.toString(Connection.fullPayCount()));
    }

}
