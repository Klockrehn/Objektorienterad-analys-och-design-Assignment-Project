package Product;

import java.util.Scanner;

public class ProductController {
    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Produktmeny ===");
        System.out.println("1. Lista alla produkter");
        System.out.println("2. Sök produkt på namn");
        System.out.println("3. Sök produkt på kategori");
        System.out.println("4. Lägg till produkt");
        System.out.println("5. Uppdatera pris");
        System.out.println("6. Uppdatera lagersaldo");
        System.out.print("Välj: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1 -> service.listAllProducts();
            case 2 -> {
                System.out.print("Sökterm: ");
                String name = scanner.nextLine();
                service.searchProductsByName(name);
            }
            case 3 -> {
                System.out.print("Kategori: ");
                String cat = scanner.nextLine();
                service.searchProductsByCategory(cat);
            }
            case 4 -> {
                System.out.print("Namn: ");
                String name = scanner.nextLine();
                System.out.print("Beskrivning: ");
                String desc = scanner.nextLine();
                System.out.print("Pris: ");
                double price = scanner.nextDouble();
                System.out.print("Lagersaldo: ");
                int stock = scanner.nextInt();
                System.out.print("Tillverkare-ID: ");
                int manufacturerId = scanner.nextInt();
                Product p = new Product(name, desc, price, stock, manufacturerId);
                service.addProduct(p);
            }
            case 5 -> {
                System.out.print("Produkt-ID: ");
                int id = scanner.nextInt();
                System.out.print("Nytt pris: ");
                double price = scanner.nextDouble();
                service.updatePrice(id, price);
            }
            case 6 -> {
                System.out.print("Produkt-ID: ");
                int id = scanner.nextInt();
                System.out.print("Nytt lagersaldo: ");
                int stock = scanner.nextInt();
                service.updateStock(id, stock);
            }
            default -> System.out.println("Ogiltigt val.");
        }
    }
}
