package Customer;

import java.util.Scanner;

public class CustomerController {
    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Kundhantering ===");
        System.out.println("1. Lägg till kund");
        System.out.println("2. Uppdatera kundens email");
        System.out.print("Välj: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1 -> {
                System.out.print("Namn: ");
                String name = scanner.nextLine();
                System.out.print("Email: ");
                String email = scanner.nextLine();
                System.out.print("Telefon: ");
                String phone = scanner.nextLine();
                System.out.print("Adress: ");
                String address = scanner.nextLine();
                System.out.print("Lösenord: ");
                String password = scanner.nextLine();
                service.addCustomer(new Customer(name, email, phone, address, password));
            }
            case 2 -> {
                System.out.print("Kund-ID: ");
                long id = scanner.nextLong();
                scanner.nextLine();
                System.out.print("Ny email: ");
                String newEmail = scanner.nextLine();
                service.updateCustomerEmail(id, newEmail);
            }
            default -> System.out.println("Ogiltigt val");
        }
    }
}
