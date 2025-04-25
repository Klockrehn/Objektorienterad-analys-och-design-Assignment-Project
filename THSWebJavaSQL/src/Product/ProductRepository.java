package Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository {
    public static final String URL = "jdbc:sqlite:webbutiken.db";

    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products";

        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                products.add(new Product(
                        rs.getInt("product_id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getDouble("price"),
                        rs.getInt("stock_quantity"),
                        rs.getInt("manufacturer_id")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Fel vid hämtning av produkter: " + e.getMessage());
        }

        return products;
    }

    public Product getProductById(int productId) {
        Product product = null;
        String sql = "SELECT * FROM products WHERE product_id = ?";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, productId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                product = new Product(
                        rs.getInt("product_id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getDouble("price"),
                        rs.getInt("stock_quantity"),
                        rs.getInt("manufacturer_id")
                );
            }
        } catch (SQLException e) {
            System.out.println("Fel vid hämtning av produkt: " + e.getMessage());
        }

        return product;
    }

    public List<Product> searchByName(String name) {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products WHERE name LIKE ?";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, "%" + name + "%");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                products.add(new Product(
                        rs.getInt("product_id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getDouble("price"),
                        rs.getInt("stock_quantity"),
                        rs.getInt("manufacturer_id")
                ));
            }

        } catch (SQLException e) {
            System.out.println("Fel vid sökning efter namn: " + e.getMessage());
        }

        return products;
    }

    public List<Product> searchByCategory(String category) {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products WHERE manufacturer_id = ?";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, category);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                products.add(new Product(
                        rs.getInt("product_id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getDouble("price"),
                        rs.getInt("stock_quantity"),
                        rs.getInt("manufacturer_id")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Fel vid sökning efter kategori: " + e.getMessage());
        }

        return products;
    }

    public void updatePrice(int productId, double newPrice) {
        String sql = "UPDATE products SET price = ? WHERE product_id = ?";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setDouble(1, newPrice);
            pstmt.setInt(2, productId);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Fel vid uppdatering av pris: " + e.getMessage());
        }
    }

    public void updateStockQuantity(int productId, int newQuantity) {
        String sql = "UPDATE products SET stock_quantity = ? WHERE product_id = ?";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, newQuantity);
            pstmt.setInt(2, productId);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Fel vid uppdatering av lagersaldo: " + e.getMessage());
        }
    }

    public void addProduct(Product product) {
        String sql = "INSERT INTO products (name, description, price, stock_quantity, manufacturer_id) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, product.getName());
            pstmt.setString(2, product.getDescription());
            pstmt.setDouble(3, product.getPrice());
            pstmt.setInt(4, product.getStockQuantity());
            pstmt.setInt(5, product.getManufacturerId());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Fel vid insättning av produkt: " + e.getMessage());
        }
    }
}
