package Customer;

import java.util.List;

public class CustomerService {
    private final CustomerRepository repository;

    public CustomerService() {
        this.repository = new CustomerRepository();
    }

    public void addCustomer(Customer customer) {
        repository.addCustomer(customer);
    }

    public void updateCustomerEmail(long id, String newEmail) {
        repository.updateEmail(id, newEmail);
    }

    public List<Customer> getAllCustomers() {
        return repository.getAllCustomers();
    }

    public Customer getCustomerById(long id) {
        return repository.getCustomerById(id);
    }
}
