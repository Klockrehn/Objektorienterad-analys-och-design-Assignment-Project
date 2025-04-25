import Customer.CustomerController;
import Product.ProductController;
import Order.OrderController;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        CustomerController customerController = new CustomerController(new Customer.CustomerService());
        ProductController productController = new ProductController(new Product.ProductService());
        OrderController orderController = new OrderController();

        boolean running = true;

        while (running) {
            System.out.println("\n=== Huvudmeny ===");
            System.out.println("1. Kundhantering");
            System.out.println("2. Produkthantering");
            System.out.println("3. Skapa ny order");
            System.out.println("4. Visa orderhistorik");
            System.out.println("0. Avsluta");
            System.out.print("Välj: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> customerController.start();
                case 2 -> productController.start();
                case 3 -> orderController.createNewOrder();
                case 4 -> {
                    System.out.print("Ange kund-ID: ");
                    int customerId = scanner.nextInt();
                    orderController.showOrderHistoryForCustomer(customerId);
                }
                case 0 -> {
                    running = false;
                    System.out.println("Avslutar...");
                }
                default -> System.out.println("Ogiltigt val, försök igen.");
            }
        }
    }
}
