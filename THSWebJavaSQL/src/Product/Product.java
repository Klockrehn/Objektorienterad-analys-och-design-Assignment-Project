package Product;

public class Product {
    private int productId;
    private String name;
    private String description;
    private double price;
    private int stockQuantity;
    private int manufacturerId;

    public Product(int productId, String name, String description, double price, int stockQuantity, int manufacturerId) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.manufacturerId = manufacturerId;
    }

    public Product(String name, String description, double price, int stockQuantity, int manufacturerId) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.manufacturerId = manufacturerId;
    }

    public int getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public int getManufacturerId() {
        return manufacturerId;
    }
}
