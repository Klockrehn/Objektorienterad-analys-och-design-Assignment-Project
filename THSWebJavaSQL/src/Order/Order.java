package Order;

import Product.Product;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private long orderId;
    private long customerId;
    private LocalDate orderDate;
    private List<Product> products;
    private List<Integer> quantities;

    public Order(long orderId, long customerId, LocalDate orderDate) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.products = new ArrayList<>();
        this.quantities = new ArrayList<>();
    }

    public void addProduct(Product product, int orderQuantity) {
        this.products.add(product);
        this.quantities.add(orderQuantity);
    }

    public long getOrderId() {
        return orderId;
    }

    public long getCustomerId() {
        return customerId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public List<Product> getProducts() {
        return products;
    }

    public List<Integer> getQuantities() {
        return quantities;
    }

    @Override
    public String toString() {
        return "Order [ID: " + orderId + ", Kund-ID: " + customerId + ", Datum: " + orderDate + "]";
    }
}
