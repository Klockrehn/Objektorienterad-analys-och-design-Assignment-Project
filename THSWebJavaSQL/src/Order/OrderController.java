package Order;

import Product.Product;
import java.util.List;
import java.util.Scanner;

public class OrderController {
    private OrderService orderService;

    public OrderController() {
        this.orderService = new OrderService();
    }

    public void createNewOrder() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ange kund-ID: ");
        int customerId = scanner.nextInt();

        System.out.print("Hur många produkter vill du beställa? ");
        int numProducts = scanner.nextInt();

        int orderId = orderService.createOrder(customerId);

        for (int i = 0; i < numProducts; i++) {
            System.out.print("Ange produkt-ID: ");
            int productId = scanner.nextInt();
            System.out.print("Ange antal: ");
            int quantity = scanner.nextInt();

            Product product = orderService.getProductById(productId);
            if (product != null) {
                double price = product.getPrice();
                orderService.addProductToOrder(orderId, productId, quantity, price);
            }
        }

        System.out.println("Order skapad med ID: " + orderId);
    }

    public void showOrderHistoryForCustomer(int customerId) {
        List<Order> orders = orderService.getOrdersForCustomer(customerId);

        if (orders.isEmpty()) {
            System.out.println("Inga ordrar hittades för kund med ID: " + customerId);
        } else {
            for (Order o : orders) {
                System.out.println(o);
            }
        }
    }
}
