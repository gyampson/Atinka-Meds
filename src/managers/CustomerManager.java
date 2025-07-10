package managers;

import models.Customer;
import java.util.ArrayList;
import java.util.List;

public class CustomerManager {
    private List<Customer> customers;

    public CustomerManager() {
        this.customers = new ArrayList<>();
    }

    public void removeCustomer(String id) {
        customers.removeIf(c -> c.getCustomerID().equalsIgnoreCase(id));
    }


    public void addCustomer(Customer customer) {
        customers.add(customer);
        System.out.println(" Customer added successfully!.");
    }

    public void listCustomers() {
        for (Customer c : customers) {
            System.out.println(c);
        }
    }

    public Customer findByID(String id) {
        for (Customer c : customers) {
            if (c.getCustomerID().equalsIgnoreCase(id)) {
                return c;
            }
        }
        return null;
    }

    public List<Customer> getCustomers() {
        return customers;
    }
}
