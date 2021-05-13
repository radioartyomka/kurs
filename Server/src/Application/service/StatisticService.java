package Application.service;

import java.sql.Connection;

public class StatisticService {
    private Connection connection;

    public void setConnection(Connection connection){
        this.connection = connection;
    }

    public int[] monthProfit() {
        SaleService saleService = new SaleService();
        saleService.setConnection(connection);

        int[] monthProfit = new int[12];
        for (int i = 0; i < saleService.getAllObjects().size(); i++) {
            int month = saleService.getAllObjects().get(i).getDateOfSale().getMonthValue() - 1;
            monthProfit[month] += (int) saleService.getAllObjects().get(i).getPrice();
        }
        return monthProfit;
    }

    public int[] monthCount() {
        SaleService saleService = new SaleService();
        saleService.setConnection(connection);

        int[] monthCount = new int[12];
        int month;
        for (int i = 0; i < saleService.getAllObjects().size(); i++) {
            month = saleService.getAllObjects().get(i).getDateOfSale().getMonthValue() - 1;
            monthCount[month]++;
        }
        return monthCount;
    }

    public int totalProfit() {
        SaleService saleService = new SaleService();
        saleService.setConnection(connection);
        int profit = 0;
        for (int i = 0; i < saleService.getAllObjects().size(); i++) {
            profit += (int) saleService.getAllObjects().get(i).getPrice();
        }
        return profit;
    }

    public int totalCount() {
        SaleService saleService = new SaleService();
        saleService.setConnection(connection);
        return saleService.getAllObjects().size();
    }

    public int rassrochkaCount() {
        SaleService saleService = new SaleService();
        saleService.setConnection(connection);

        int count = 0;
        for (int i = 0; i < saleService.getAllObjects().size(); i++) {
            if (saleService.getAllObjects().get(i).getSalesTerms().equals("Рассрочка")) {
                count++;
            }
        }
        return count;
    }

    public int fullPayCount() {
        SaleService saleService = new SaleService();
        saleService.setConnection(connection);

        int count = 0;
        for (int i = 0; i < saleService.getAllObjects().size(); i++) {
            if (saleService.getAllObjects().get(i).getSalesTerms().equalsIgnoreCase("Полная оплата")) {
                count++;
            }
        }
        return count;
    }
}
