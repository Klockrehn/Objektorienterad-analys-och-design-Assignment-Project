package Product;

import java.util.List;

public class ProductService {
    private final ProductRepository repository = new ProductRepository();

    public void listAllProducts() {
        List<Product> products = repository.getAllProducts();
        for (Product p : products) {
            System.out.println(p.getName() + " - " + p.getPrice() + " kr - Lager: " + p.getStockQuantity());
        }
    }

    public void searchProductsByName(String name) {
        List<Product> products = repository.searchByName(name);
        for (Product p : products) {
            System.out.println(p.getName() + " - " + p.getPrice() + " kr");
        }
    }

    public void searchProductsByCategory(String category) {
        List<Product> products = repository.searchByCategory(category);
        for (Product p : products) {
            System.out.println(p.getName() + " - " + p.getPrice() + " kr");
        }
    }

    public void updatePrice(int id, double newPrice) {
        repository.updatePrice(id, newPrice);
    }

    public void updateStock(int id, int newStock) {
        repository.updateStockQuantity(id, newStock);
    }

    public void addProduct(Product product) {
        repository.addProduct(product);
    }
}
