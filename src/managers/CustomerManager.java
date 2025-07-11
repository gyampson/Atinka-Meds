package managers;

import models.Customer;
import java.util.ArrayList;
import java.util.List;

public class CustomerManager {
    // ANSI Color Constants (can be in a separate class or your main class)
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[34m";    // For prompts
    public static final String ANSI_CYAN = "\u001B[36m";    // For section headers
    public static final String ANSI_GREEN = "\u001B[32m";   // For success messages
    public static final String ANSI_RED = "\u001B[31m";     // For error messages
    public static final String ANSI_BOLD = "\u001B[1m";
    private List<Customer> customers;

    public CustomerManager() {
        this.customers = new ArrayList<>();
    }


    // Inside your CustomerManager class
    public boolean removeCustomer(String customerID) {
        // Assuming 'customers' is a List<Customer>
        boolean removed = customers.removeIf(c -> c.getCustomerID().equalsIgnoreCase(customerID));

        if (removed) {
            System.out.println(ANSI_GREEN + ANSI_BOLD + "✅ Customer with ID '" + customerID + "' has been successfully removed." + ANSI_RESET);
            return true;
        } else {
            // This case would ideally not be reached if findByID() already checked for existence,
            // but it's good for robustness or if called directly.
            System.out.println(ANSI_YELLOW + ANSI_BOLD + "⚠️ Warning: No customer found with the ID '" + customerID + "'. No changes were made." + ANSI_RESET);
            return false;
        }
    }


    // Inside your CustomerManager class (assuming it manages a List<Customer> customers)
    public void addCustomer(Customer customer) {
        // Check for unique ID
        for (Customer c : customers) {
            if (c.getCustomerID().equalsIgnoreCase(customer.getCustomerID())) {
                // Found a duplicate ID
                System.out.println(ANSI_RED + ANSI_BOLD + "❌ Error: A customer with the ID '" + customer.getCustomerID() + "' already exists. Please use a unique ID." + ANSI_RESET);
                return;
            }
        }

        // No duplicate found, proceed with adding the customer
        customers.add(customer);
        System.out.println(ANSI_GREEN + ANSI_BOLD + "✅ Customer '" + customer.getName() + "' (ID: " + customer.getCustomerID() + ") added successfully!" + ANSI_RESET);
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
