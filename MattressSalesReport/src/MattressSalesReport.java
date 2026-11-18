import java.util.Scanner;

public class MattressSalesReport {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        String[] products = {
            "Cozy Nights Monarch",
            "Restonic Barbados",
            "Sealy Phuket"
        };

        int[][] sales = new int[3][3];

        for (int i = 0; i < products.length; i++) {

            for (int j = 0; j < 3; j++) {

                System.out.print(
                    "Enter number of sales for month "
                    + (j + 1) + " for "
                    + products[i] + " mattresses: "
                );

                sales[i][j] = input.nextInt();
            }
        }

        Product product = new Product(products, sales);

        PrintProduct report = new PrintProduct();

        report.PrintReport(product);

        input.close();
    }
}
