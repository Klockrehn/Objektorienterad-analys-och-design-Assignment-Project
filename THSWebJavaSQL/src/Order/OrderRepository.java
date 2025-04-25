package Order;

import Product.Product;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderRepository {
    public static final String URL = "jdbc:sqlite:webbutiken.db";

    public List<Order> getOrdersForCustomer(int customerId) {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT o.order_id, o.customer_id, o.order_date, op.product_id, p.name AS product_name, op.unit_price, op.quantity " +
                "FROM orders o " +
                "LEFT JOIN orders_products op ON o.order_id = op.order_id " +
                "LEFT JOIN products p ON op.product_id = p.product_id " +
                "WHERE o.customer_id = ?";
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, customerId);
            ResultSet rs = pstmt.executeQuery();

            int lastOrderId = -1;
            Order order = null;
            while (rs.next()) {
                int orderId = rs.getInt("order_id");
                if (orderId != lastOrderId) {
                    order = new Order(orderId, rs.getInt("customer_id"), rs.getObject("order_date", LocalDate.class));
                    orders.add(order);
                    lastOrderId = orderId;
                }
                int productId = rs.getInt("product_id");
                if (!rs.wasNull()) {
                    int quantity = rs.getInt("quantity");
                    if (quantity < 0) throw new SQLException("Negativt antal för produkt-ID " + productId);
                    Product product = new Product(
                            productId,
                            rs.getString("product_name"),
                            "",
                            rs.getDouble("unit_price"),
                            0,
                            0
                    );
                    order.addProduct(product, quantity);
                }
            }
        } catch (SQLException e) {
            System.out.println("Fel vid hämtning av ordrar: " + e.getMessage());
        }
        return orders;
    }

    public int createOrder(int customerId) {
        String sql = "INSERT INTO orders (customer_id, order_date) VALUES (?, ?)";
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, customerId);
            pstmt.setObject(2, LocalDate.now());
            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("Fel vid skapande av order: " + e.getMessage());
        }
        return -1;
    }

    public void addProductToOrder(int orderId, int productId, int quantity, double price) {
        String sql = "INSERT INTO orders_products (order_id, product_id, quantity, unit_price) VALUES (?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, orderId);
            pstmt.setInt(2, productId);
            pstmt.setInt(3, quantity);
            pstmt.setDouble(4, price);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Fel vid tillägg av produkt till order: " + e.getMessage());
        }
    }
}
