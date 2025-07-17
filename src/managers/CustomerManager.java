package managers;

import models.Customer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CustomerManager {

    // ANSI Color Constants
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_BOLD = "\u001B[1m";

    private List<Customer> customers;
    private Scanner scanner = new Scanner(System.in);

    public CustomerManager() {
        this.customers = new ArrayList<>();
    }

    // ✅ Silent load (no checks, no messages)
    public void loadCustomer(Customer customer) {
        customers.add(customer);
    }

    // ✅ User-facing add with duplicate check + feedback
    public void addCustomer(Customer customer) {
        for (Customer c : customers) {
            if (c.getCustomerID().equalsIgnoreCase(customer.getCustomerID())) {
                printError("A customer with the ID '" + customer.getCustomerID() + "' already exists. Please use a unique ID.");
                return;
            }
        }
        customers.add(customer);
        printSuccess("Customer '" + customer.getName() + "' (CUS ID: " + customer.getCustomerID() + ") added.");
    }

    // ✅ Remove Customer with feedback
    public boolean removeCustomer(String customerID) {
        Customer toRemove = null;
        for (Customer c : customers) {
            if (c.getCustomerID().equalsIgnoreCase(customerID)) {
                toRemove = c;
                break;
            }
        }

        if (toRemove != null) {
            customers.remove(toRemove);
            printSuccess("Customer '" + toRemove.getName() + "' (ID: " + toRemove.getCustomerID() + ") removed.");
            return true;
        } else {
            printError("No customer found with the ID '" + customerID + "'.");
            return false;
        }
    }

    // ✅ List Customers with header
    public void listCustomers() {
        if (customers.isEmpty()) {
            System.out.println(ANSI_YELLOW + ANSI_BOLD + "⚠️ No customers in the system." + ANSI_RESET);
            return;
        }

        System.out.println(ANSI_BLUE + ANSI_BOLD + "\n=== 🧾 Customer List ===" + ANSI_RESET);
        for (Customer c : customers) {
            System.out.println(c);
        }
        waitForEnter();
    }

    // ✅ Find Customer by ID
    public Customer findByID(String id) {
        for (Customer c : customers) {
            if (c.getCustomerID().equalsIgnoreCase(id)) {
                return c;
            }
        }
        return null;
    }

    // ✅ Return all Customers
    public List<Customer> getCustomers() {
        return customers;
    }

    // ✅ Success message with pause
    private void printSuccess(String message) {
        System.out.println();
        System.out.println(ANSI_GREEN + ANSI_BOLD + "[✔️ SUCCESS] " + message + ANSI_RESET);
        System.out.println(ANSI_BLUE + "(Press Enter to continue...)" + ANSI_RESET);
        scanner.nextLine();
    }

    // ✅ Error message
    private void printError(String message) {
        System.out.println();
        System.out.println(ANSI_RED + ANSI_BOLD + "[❌ ERROR] " + message + ANSI_RESET);
    }

    private void waitForEnter() {
        System.out.println();
        System.out.println(ANSI_BLUE + "(Press Enter to continue...)" + ANSI_RESET);
        scanner.nextLine();
    }

}
