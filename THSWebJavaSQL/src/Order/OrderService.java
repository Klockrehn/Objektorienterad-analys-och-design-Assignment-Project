package Order;

import Product.Product;
import Product.ProductRepository;
import java.util.List;

public class OrderService {
    private OrderRepository orderRepository;
    private ProductRepository productRepository;

    public OrderService() {
        this.orderRepository = new OrderRepository();
        this.productRepository = new ProductRepository();
    }

    public Product getProductById(int productId) {
        return productRepository.getProductById(productId);
    }

    public int createOrder(int customerId) {
        return orderRepository.createOrder(customerId);
    }

    public void addProductToOrder(int orderId, int productId, int quantity, double price) {
        orderRepository.addProductToOrder(orderId, productId, quantity, price);
    }

    public List<Order> getOrdersForCustomer(int customerId) {
        return orderRepository.getOrdersForCustomer(customerId);
    }
}

