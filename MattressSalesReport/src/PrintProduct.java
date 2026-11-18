public class PrintProduct {

    public void PrintTotalSales(int total) {

        System.out.println("TOTAL PRODUCT SALES: " + total);
    }

    public void PrintAverageSales(double average) {

        System.out.println("AVERAGE PRODUCT SALES: "
                + average);
    }

    public void PrintPopularProduct(String popular) {

        System.out.println("MOST POPULAR PRODUCT: "
                + popular);
    }

    public void PrintReport(Product product) {

        System.out.println("\nPRODUCT SALES REPORT - 2026");
        System.out.println("==============================");

        PrintTotalSales(product.GetTotalProductsSold());

        PrintAverageSales(product.GetAverageProductSold());

        PrintPopularProduct(product.GetMostPopularProduct());
    }
}