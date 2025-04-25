package Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepository {
    private static final String URL = "jdbc:sqlite:webbutiken.db";

    public void addCustomer(Customer customer) {
        String sql = "INSERT INTO customers (name, email, phone, address, password) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, customer.getName());
            pstmt.setString(2, customer.getEmail());
            pstmt.setString(3, customer.getPhone());
            pstmt.setString(4, customer.getAddress());
            pstmt.setString(5, customer.getPassword());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Fel vid tillägg av kund: " + e.getMessage());
        }
    }

    public void updateEmail(long id, String newEmail) {
        String sql = "UPDATE customers SET email = ? WHERE customer_id = ?";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, newEmail);
            pstmt.setLong(2, id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Fel vid uppdatering av email: " + e.getMessage());
        }
    }

    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM customers";

        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                customers.add(new Customer(
                        rs.getLong("customer_id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getString("address"),
                        rs.getString("password")
                ));
            }

        } catch (SQLException e) {
            System.out.println("Fel vid hämtning av kunder: " + e.getMessage());
        }

        return customers;
    }

    public Customer getCustomerById(long id) {
        String sql = "SELECT * FROM customers WHERE customer_id = ?";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Customer(
                        rs.getLong("customer_id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getString("address"),
                        rs.getString("password")
                );
            }

        } catch (SQLException e) {
            System.out.println("Fel vid hämtning av kund: " + e.getMessage());
        }

        return null;
    }
}
