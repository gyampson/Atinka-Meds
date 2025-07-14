package ui;

import managers.DrugInventory;
import managers.SupplierManager;
import managers.TransactionManager;
import models.Customer;
import models.Drug;
import models.Supplier;
import managers.CustomerManager;
import models.Transaction;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private DrugInventory drugInventory;
    private SupplierManager supplierManager;
    private CustomerManager customerManager;
    private TransactionManager transactionManager;
    private Scanner scanner;


    // Define these globally or as constants in a utility class
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLUE = "\u001B[34m";    // For prompts
    public static final String ANSI_CYAN = "\u001B[36m";    // For headers/titles within a section
    public static final String ANSI_GREEN = "\u001B[32m";   // For success messages
    public static final String ANSI_RED = "\u001B[31m";     // For error messages
    public static final String ANSI_YELLOW = "\u001B[33m";  // For warnings/special notes
    public static final String ANSI_BOLD = "\u001B[1m";
    public static final String ANSI_ITALIC = "\u001B[3m";



    public Menu(DrugInventory drugInventory, SupplierManager supplierManager, CustomerManager customerManager, TransactionManager transactionManager) {
        this.drugInventory = drugInventory;
        this.supplierManager = supplierManager;
        this.customerManager = customerManager;
        this.transactionManager = transactionManager;
        this.scanner = new Scanner(System.in);
    }


    public void show() {
        boolean exit = false;
        while (!exit) {
            System.out.println("╔══════════════════════════════════════════════╗");
            System.out.println("║   💊 Atinka Meds Pharmacy Inventory System   💊  ║");
            System.out.println("╚══════════════════════════════════════════════╝");

            System.out.println("1.  Add Drug");
            System.out.println("2.  Remove Drug");
            System.out.println("3.  List Drugs");
            System.out.println("4.  Add Supplier");
            System.out.println("5.  List Suppliers");
            System.out.println("6.  Add Customer");
            System.out.println("7.  List Customers");
            System.out.println("8.  Record Transactions");
            System.out.println("9.  List Transactions");
            System.out.println("10. View Low Stock Report");
            System.out.println("11. View Sales Report");
            System.out.println("12. Search Drug by Code");
            System.out.println("13. Search Customer by ID");
            System.out.println("14. Delete Drug by Code");
            System.out.println("15. Delete Customer by ID");
            System.out.println("16. View Supplier Drug Report");
            System.out.println("17. Export Transactions Report");
            System.out.println("18. Export Low Stock Report");
            System.out.println("19. View Top-Selling Drugs");
            System.out.println("20. View Customer Purchase History");
            System.out.println("21. Save & Exit");
            System.out.print("Choose option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            System.out.println("╔══════════════════════════════════════════════╗");
            System.out.println("║   📦 Atinka Meds Pharmacy Inventory System   ║");
            System.out.println("╚══════════════════════════════════════════════╝");
            String ANSI_RESET = "\u001B[0m";         // Resets all formattin
            String ANSI_YELLOW = "\u001B[33m";       // Yellow text for reminder
            String ANSI_BOLD = "\u001B[1m";          // Bold text

            // Reminder line
            String reminderLine = ANSI_BOLD + ANSI_YELLOW +
                    " ⓘ Please remember to save and exit by choosing option 21." + ANSI_RESET;


            System.out.println("\n" + reminderLine + "\n");

            switch (choice) {
                case 1:
                    addDrug();
                    break;
                case 2:
                    removeDrug();
                    break;
                case 3:
                    drugInventory.listDrugs();
                    break;
                case 4:
                    addSupplier();
                    break;
                case 5:
                    supplierManager.listSuppliers();
                    break;
                case 6:
                    addCustomer();
                    break;
                case 7:
                    customerManager.listCustomers();
                    break;
                case 8:
                    recordTransaction();
                    break;
                case 9:
                    transactionManager.listTransactions();
                    break;
                case 10:
                    showLowStockReport();
                    break;
                case 11:
                    showSalesReport();
                    break;
                case 12:
                    searchDrugByCode();
                    break;
                case 13:
                    searchCustomerByID();
                    break;
                case 14:
                    deleteDrugByCode();
                    break;
                case 15:
                    deleteCustomerByID();
                    break;
                case 16:
                    showSupplierDrugReport();
                    break;
                case 17:
                    exportTransactionsReport();
                    break;
                case 18:
                    exportLowStockReport();
                    break;
                case 19:
                    viewTopSellingDrugs();
                    break;
                case 20:
                    viewCustomerPurchaseHistory();
                    break;

                case 21:
                    exit = true;
                    System.out.println("Saving and exiting ...  Done!"); // Indicate action
                    // Optional: Add a small delay for user experience (requires try-catch for InterruptedException)
                    try {
                        Thread.sleep(500); // Wait for 0.5 seconds
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt(); // Restore the interrupted status
                    }
                    System.out.println("Thank you for using Atinka Meds. Goodbye!"); // Final confirmation
                    break;


                default:
                    String ANSI_RED = "\u001B[31m";          // Red text for errors

                    System.out.println(ANSI_RED + "❌ Invalid choice. Please try again." + ANSI_RESET);
                    break;
            }
        }
    }

    private void addDrug() {
        System.out.println(ANSI_CYAN + ANSI_BOLD + "\n--- Add New Drug ---" + ANSI_RESET); // Section Header

        System.out.print(ANSI_BLUE + "Enter Drug Code: " + ANSI_RESET);
        String code = scanner.nextLine();

        System.out.print(ANSI_BLUE + "Enter Drug Name: " + ANSI_RESET);
        String name = scanner.nextLine();

        System.out.print(ANSI_BLUE + "Enter Supplier IDs (e.g., SUP001|SUP002): " + ANSI_RESET); // Example for clarity
        String supplierIDs = scanner.nextLine();
        List<String> suppliers = List.of(supplierIDs.split("\\|"));

        System.out.print(ANSI_BLUE + "Enter Expiry Date (YYYY-MM-DD): " + ANSI_RESET);
        String expiry = scanner.nextLine();

        System.out.print(ANSI_BLUE + "Enter Price: " + ANSI_RESET);
        double price = scanner.nextDouble();

        System.out.print(ANSI_BLUE + "Enter Stock Quantity: " + ANSI_RESET); // More descriptive
        int stock = scanner.nextInt();
        scanner.nextLine();


        Drug drug = new Drug(name, code, suppliers, expiry, price, stock);
        drugInventory.addDrug(drug);
    }

    private void removeDrug() {
        System.out.print(ANSI_BLUE + "Enter Drug Code to Remove " + ANSI_RESET);
        String code = scanner.nextLine();
        drugInventory.removeDrug(code);
    }

    private void addSupplier() {
        System.out.println(ANSI_CYAN + ANSI_BOLD + "\n--- Add New Supplier ---" + ANSI_RESET); // Section Header

        System.out.print(ANSI_BLUE + "Enter Supplier ID (e.g., SUP001): " + ANSI_RESET); // Example
        String id = scanner.nextLine();

        System.out.print(ANSI_BLUE + "Enter Supplier Name: " + ANSI_RESET);
        String name = scanner.nextLine();

        System.out.print(ANSI_BLUE + "Enter Contact Person/Number: " + ANSI_RESET); // More descriptive
        String contact = scanner.nextLine();

        System.out.print(ANSI_BLUE + "Enter Location: " + ANSI_RESET);
        String location = scanner.nextLine();

        System.out.print(ANSI_BLUE + "Enter Estimated Delivery Turnaround Time (in days): " + ANSI_RESET); // More descriptive
        double turnaround = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        System.out.print(ANSI_BLUE + "Enter Drug Codes Supplied (separate by | e.g., DRG001|DRG005): " + ANSI_RESET); // Example
        String drugCodes = scanner.nextLine();
        List<String> drugsSupplied = List.of(drugCodes.split("\\|"));

        Supplier supplier = new Supplier(id, name, contact, location, turnaround, drugsSupplied);
        supplierManager.addSupplier(supplier);
    }




    private void addCustomer() {
        System.out.println(ANSI_CYAN + ANSI_BOLD + "\n--- Add New Customer ---" + ANSI_RESET); // Section Header

        System.out.print(ANSI_BLUE + "Enter Customer ID (e.g., CUST001): " + ANSI_RESET); // Example for clarity
        String id = scanner.nextLine();

        System.out.print(ANSI_BLUE + "Enter Customer Name: " + ANSI_RESET);
        String name = scanner.nextLine();

        System.out.print(ANSI_BLUE + "Enter Contact Number: " + ANSI_RESET);
        String phone = scanner.nextLine();

        System.out.print(ANSI_BLUE + "Enter Address: " + ANSI_RESET);
        String address = scanner.nextLine();

        customerManager.addCustomer(new Customer(id, name, phone, address));
    }
//    private void listCustomers(){}


    private void recordTransaction() {
        System.out.println(ANSI_CYAN + ANSI_BOLD + "\n--- Record New Transaction ---" + ANSI_RESET); // Section Header

        System.out.print(ANSI_BLUE + "Enter Transaction ID: " + ANSI_RESET);
        String txnID = scanner.nextLine();

        System.out.print(ANSI_BLUE + "Enter Customer ID: " + ANSI_RESET);
        String customerID = scanner.nextLine();

        // Validate customer exists
        Customer customer = customerManager.findByID(customerID);
        if (customer == null) {
            System.out.println(ANSI_RED + ANSI_BOLD + "❌ Error: Customer ID '" + customerID + "' not found. Please add the customer first." + ANSI_RESET);
            return;
        }

        System.out.print(ANSI_BLUE + "Enter Drug Code: " + ANSI_RESET);
        String drugCode = scanner.nextLine();

        // Validate drug exists
        Drug drug = drugInventory.findByCode(drugCode);
        if (drug == null) {
            System.out.println(ANSI_RED + ANSI_BOLD + "❌ Error: Drug code '" + drugCode + "' not found. Please add the drug first." + ANSI_RESET);
            return;
        }

        System.out.print(ANSI_BLUE + "Enter Quantity: " + ANSI_RESET);
        int quantity;
        try {
            quantity = Integer.parseInt(scanner.nextLine());
            if (quantity <= 0) { // Added validation for positive quantity
                System.out.println(ANSI_RED + ANSI_BOLD + "❌ Error: Quantity must be a positive number. Transaction cancelled." + ANSI_RESET);
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println(ANSI_RED + ANSI_BOLD + "❌ Invalid quantity format. Please enter a whole number. Transaction cancelled." + ANSI_RESET);
            return;
        }

        if (quantity > drug.getStock()) {
            System.out.println(ANSI_RED + ANSI_BOLD + "❌ Not enough stock for '" + drug.getName() + "'. Available: " + drug.getStock() + ", Requested: " + quantity + ". Transaction cancelled." + ANSI_RESET);
            return;
        }

        double totalPrice = quantity * drug.getPrice();

        System.out.print(ANSI_BLUE + "Enter Date (YYYY-MM-DD): " + ANSI_RESET);
        String date = scanner.nextLine();

        // Optional: Validate Date Format (Good Practice)
        try {
            java.time.LocalDate.parse(date); // Just parse to check validity, no need to store LocalDate here if String is preferred
        } catch (java.time.format.DateTimeParseException e) {
            System.out.println(ANSI_YELLOW + ANSI_BOLD + "⚠️ Warning: Invalid date format. Please use YYYY-MM-DD. Continuing with provided date." + ANSI_RESET);
            // You might choose to prompt again or use current date here if invalid input is critical.
        }


        // Reduce stock
        drug.setStock(drug.getStock() - quantity);
        System.out.println(ANSI_GREEN + "✅ Stock updated for '" + drug.getName() + "'. New stock: " + drug.getStock() + ANSI_RESET);

        // Record transaction
        // Assuming transactionManager.addTransaction handles unique ID check and prints its own success/error
        // If not, you'd add a try-catch for addTransaction here similar to previous examples.
        Transaction transaction = new Transaction(txnID, customerID, drugCode, quantity, totalPrice, date);
        transactionManager.addTransaction(transaction); // This method should handle its own success/duplicate ID message

        // This final message is good if addTransaction doesn't print its own success
        // If addTransaction prints success, you might remove this or make it more general.
        // Assuming transactionManager.addTransaction does not print success, we keep this.
        System.out.println(ANSI_GREEN + ANSI_BOLD + "✅ Transaction '" + txnID + "' recorded successfully!" + ANSI_RESET);
    }
    private void showSalesReport() {
        // Assuming transactionManager.getTransactions() returns a List<Transaction>
        List<Transaction> transactions = transactionManager.getTransactions();

        System.out.println(ANSI_CYAN + ANSI_BOLD + "\n📊 ATINKA MEDS SALES REPORT 📊" + ANSI_RESET); // Main Header
        System.out.println(ANSI_CYAN + "═══════════════════════════════════════" + ANSI_RESET); // Separator

        if (transactions.isEmpty()) {
            System.out.println(ANSI_YELLOW + "⚠️ No transactions recorded yet. The sales report is currently empty." + ANSI_RESET);
            System.out.println(ANSI_CYAN + "═══════════════════════════════════════" + ANSI_RESET); // Bottom Separator
            return;
        }

        // Calculate totals
        int totalTransactions = transactions.size();
        double totalSales = 0.0;



        System.out.println(ANSI_BLUE + ANSI_BOLD + "--- Summary ---" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "Total Transactions Recorded: " + ANSI_BOLD + totalTransactions + ANSI_RESET);
        // Ensure getTotalPrice() returns the correct total for the transaction
        for (Transaction t : transactions) {
            totalSales += t.getTotalPrice();
        }
        System.out.printf(ANSI_GREEN + "Total Revenue Generated:    GHS " + ANSI_BOLD + "%.2f\n" + ANSI_RESET, totalSales);

        System.out.println(ANSI_BLUE + ANSI_BOLD + "\n--- Individual Transactions ---" + ANSI_RESET);

        System.out.println(String.format(ANSI_BOLD + "%-12s %-15s %-15s %-10s %-10s" + ANSI_RESET,
                "TXN ID", "CUSTOMER ID", "DRUG CODE", "QTY", "PRICE"));
        System.out.println(ANSI_CYAN + "------------------------------------------------------------" + ANSI_RESET);

        for (Transaction t : transactions) {

            System.out.println(String.format("%-12s %-15s %-15s %-10d GHS %-6.2f",
                    t.getTxnID(),
                    t.getCustomerID(),
                    t.getDrugCode(),
                    t.getQuantity(),
                    t.getTotalPrice()));
        }
        System.out.println(ANSI_CYAN + "------------------------------------------------------------" + ANSI_RESET);



        System.out.println(ANSI_CYAN + "═══════════════════════════════════════" + ANSI_RESET); // Bottom Separator
        System.out.println(ANSI_YELLOW + "Report generated at " + java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("MMM dd, yyyy HH:mm")) + ANSI_RESET);
        System.out.println(ANSI_CYAN + "═══════════════════════════════════════" + ANSI_RESET); // Final Separator
    }
    private void searchDrugByCode() {
        System.out.println(ANSI_CYAN + ANSI_BOLD + "\n--- Search Drug by Code ---" + ANSI_RESET); // Section Header

        System.out.print(ANSI_BLUE + "Enter Drug Code to search: " + ANSI_RESET);
        String code = scanner.nextLine();


        Drug drug = drugInventory.findByCode(code);

        if (drug != null) {
            System.out.println(ANSI_GREEN + ANSI_BOLD + "✅ Drug found!" + ANSI_RESET);
            System.out.println(ANSI_GREEN + "═════════════════════════════════════" + ANSI_RESET); // Separator
            System.out.println(ANSI_GREEN + "  Code:         " + ANSI_BOLD + drug.getCode() + ANSI_RESET);
            System.out.println(ANSI_GREEN + "  Name:         " + ANSI_BOLD + drug.getName() + ANSI_RESET);
            // Assuming drug.getSuppliers() returns a List<String> or similar
            System.out.println(ANSI_GREEN + "  Suppliers:    " + ANSI_BOLD + String.join(", ", drug.getSuppliers()) + ANSI_RESET);
            System.out.println(ANSI_GREEN + "  Expiry Date:  " + ANSI_BOLD + drug.getExpiryDate() + ANSI_RESET);
            System.out.println(ANSI_GREEN + String.format("  Price:        GHS %.2f", drug.getPrice()) + ANSI_RESET);
            System.out.println(ANSI_GREEN + "  Stock:        " + ANSI_BOLD + drug.getStock() + ANSI_RESET);
            System.out.println(ANSI_GREEN + "═════════════════════════════════════" + ANSI_RESET); // Bottom Separator
        } else {
            System.out.println(ANSI_RED + ANSI_BOLD + "❌ Drug with code '" + code + "' not found in inventory." + ANSI_RESET);
        }
    }
    private void searchCustomerByID() {
        System.out.println(ANSI_CYAN + ANSI_BOLD + "\n--- Search Customer by ID ---" + ANSI_RESET); // Section Header

        System.out.print(ANSI_BLUE + "Enter Customer ID to search: " + ANSI_RESET);
        String id = scanner.nextLine();

        // Assuming customerManager.findByID(id) returns a Customer object or null
        Customer customer = customerManager.findByID(id);

        if (customer != null) {
            System.out.println(ANSI_GREEN + ANSI_BOLD + "✅ Customer found!" + ANSI_RESET);
            System.out.println(ANSI_GREEN + "═════════════════════════════════════" + ANSI_RESET); // Separator
            System.out.println(ANSI_GREEN + "  ID:      " + ANSI_BOLD + customer.getCustomerID() + ANSI_RESET);
            System.out.println(ANSI_GREEN + "  Name:    " + ANSI_BOLD + customer.getName() + ANSI_RESET);
            System.out.println(ANSI_GREEN + "  Contact: " + ANSI_BOLD + customer.getPhone() + ANSI_RESET);
            System.out.println(ANSI_GREEN + "  Address: " + ANSI_BOLD + customer.getAddress() + ANSI_RESET);
            System.out.println(ANSI_GREEN + "═════════════════════════════════════" + ANSI_RESET); // Bottom Separator
        } else {
            System.out.println(ANSI_RED + ANSI_BOLD + "❌ Customer with ID '" + id + "' not found." + ANSI_RESET);
        }
    }
    private void deleteDrugByCode() {
        System.out.println(ANSI_CYAN + ANSI_BOLD + "\n--- Delete Drug by Code ---" + ANSI_RESET); // Section Header

        System.out.print(ANSI_BLUE + "Enter Drug Code to delete: " + ANSI_RESET);
        String code = scanner.nextLine();

        // Assuming drugInventory.findByCode(code) returns a Drug object or null
        Drug drug = drugInventory.findByCode(code);
        if (drug == null) {
            System.out.println(ANSI_RED + ANSI_BOLD + "❌ Error: Drug with code '" + code + "' not found. Nothing deleted." + ANSI_RESET);
            return;
        }

        // Confirmation step (optional but highly recommended for deletions)
        System.out.print(ANSI_YELLOW + "⚠️ Are you sure you want to delete '" + drug.getName() + "' (Code: " + drug.getCode() + ")? (yes/no): " + ANSI_RESET);
        String confirmation = scanner.nextLine().toLowerCase();

        if (!confirmation.equals("yes")) {
            System.out.println(ANSI_YELLOW + "Action cancelled. Drug not deleted." + ANSI_RESET);
            return;
        }


        boolean wasDeleted = drugInventory.removeDrug(code);


        if (wasDeleted) {

        }
    }
    private void deleteCustomerByID() {
        System.out.println(ANSI_CYAN + ANSI_BOLD + "\n--- Delete Customer by ID ---" + ANSI_RESET); // Section Header

        System.out.print(ANSI_BLUE + "Enter Customer ID to delete: " + ANSI_RESET);
        String id = scanner.nextLine();

        // Assuming customerManager.findByID(id) returns a Customer object or null
        Customer customer = customerManager.findByID(id);
        if (customer == null) {
            System.out.println(ANSI_RED + ANSI_BOLD + "❌ Error: Customer with ID '" + id + "' not found. Nothing deleted." + ANSI_RESET);
            return;
        }

        // Confirmation step (highly recommended for deletion)
        System.out.print(ANSI_YELLOW + "⚠️ Are you sure you want to delete customer '" + customer.getName() + "' (ID: " + customer.getCustomerID() + ")? (yes/no): " + ANSI_RESET);
        String confirmation = scanner.nextLine().toLowerCase();

        if (!confirmation.equals("yes")) {
            System.out.println(ANSI_YELLOW + "Action cancelled. Customer not deleted." + ANSI_RESET);
            return;
        }


        boolean wasDeleted = customerManager.removeCustomer(id);


    }
    private void showLowStockReport() {
        System.out.println(ANSI_CYAN + ANSI_BOLD + "\n📉 LOW STOCK REPORT 📉" + ANSI_RESET);
        System.out.println(ANSI_CYAN + "════════════════════════════════════════════════════════════════════════" + ANSI_RESET);
        System.out.println(ANSI_CYAN + ANSI_BOLD + " (Items with less than 5 units)" + ANSI_RESET);
        System.out.println(ANSI_CYAN + "════════════════════════════════════════════════════════════════════════" + ANSI_RESET);

        // Assuming drugInventory.getAllDrugs() returns a List<Drug>
        List<Drug> allDrugs = drugInventory.getAllDrugs();
        List<Drug> lowStockDrugs = new ArrayList<>();

        for (Drug d : allDrugs) {
            if (d.getStock() < 5) {
                lowStockDrugs.add(d);
            }
        }

        if (lowStockDrugs.isEmpty()) {
            System.out.println(ANSI_GREEN + ANSI_BOLD + "✅ All drugs are sufficiently stocked. No low-stock items found." + ANSI_RESET);
        } else {
            // Print a formatted table header
            System.out.println(String.format(ANSI_BOLD + "%-12s | %-25s | %-12s | %s" + ANSI_RESET,
                    "DRUG CODE", "DRUG NAME", "STOCK", "SUPPLIERS"));
            System.out.println(ANSI_CYAN + "------------------------------------------------------------------------" + ANSI_RESET);

            // Print each low-stock drug in a formatted row
            for (Drug d : lowStockDrugs) {
                String suppliers = String.join(", ", d.getSuppliers());
                String stockColor = d.getStock() < 2 ? ANSI_RED : ANSI_YELLOW; // Highlight very low stock
                System.out.println(String.format("%-12s | %-25s | " + stockColor + "%-12d" + ANSI_RESET + " | %s",
                        d.getCode(), d.getName(), d.getStock(), suppliers));
            }
            System.out.println(ANSI_CYAN + "------------------------------------------------------------------------" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "Total low-stock items: " + ANSI_BOLD + lowStockDrugs.size() + ANSI_RESET);
        }

        System.out.println(ANSI_CYAN + "════════════════════════════════════════════════════════════════════════" + ANSI_RESET);
    }

    private void showSupplierDrugReport() {
        System.out.println(ANSI_CYAN + ANSI_BOLD + "\n📦 SUPPLIER DRUG REPORT 📦" + ANSI_RESET);
        System.out.println(ANSI_CYAN + "═════════════════════════════════════════════════════" + ANSI_RESET);

        // Assuming supplierManager.getSuppliers() returns a List<Supplier>
        List<Supplier> suppliers = supplierManager.getSuppliers();
        if (suppliers.isEmpty()) {
            System.out.println(ANSI_YELLOW + "⚠️ No suppliers found to generate a report." + ANSI_RESET);
            System.out.println(ANSI_CYAN + "═════════════════════════════════════════════════════" + ANSI_RESET);
            return;
        }

        int supplierCount = 0;
        for (Supplier s : suppliers) {
            supplierCount++;
            System.out.println(ANSI_BLUE + ANSI_BOLD + "\n--- Supplier " + supplierCount + " ---" + ANSI_RESET);
            System.out.println(ANSI_BLUE + "  ID:      " + ANSI_BOLD + s.getSupplierID() + ANSI_RESET);
            System.out.println(ANSI_BLUE + "  Name:    " + ANSI_BOLD + s.getName() + ANSI_RESET);
            System.out.println(ANSI_BLUE + "  Contact: " + ANSI_BOLD + s.getContact() + ANSI_RESET);
            System.out.println(ANSI_BLUE + "  Location: " + ANSI_BOLD + s.getLocation() + ANSI_RESET);
            System.out.println(ANSI_BLUE + "  Delivery Turnaround: " + ANSI_BOLD + String.format("%.0f days", s.getDeliveryTurnaroundTime()) + ANSI_RESET); // Assuming it returns days

            List<String> drugsSupplied = s.getDrugsSupplied();
            if (drugsSupplied.isEmpty()) {
                System.out.println(ANSI_YELLOW + "  Drugs Supplied: None" + ANSI_RESET);
            } else {
                // Optional: Fetch drug names for better display, if you have a way to do that
                // For now, sticking to drug codes as per your data structure.
                System.out.println(ANSI_BLUE + "  Drugs Supplied: " + ANSI_BOLD + String.join(", ", drugsSupplied) + ANSI_RESET);
            }
            System.out.println(ANSI_CYAN + "-----------------------------------------------------" + ANSI_RESET); // Separator for each supplier
        }

        System.out.println(ANSI_GREEN + ANSI_BOLD + "\n✅ Report Complete: " + suppliers.size() + " suppliers listed." + ANSI_RESET);
        System.out.println(ANSI_CYAN + "═════════════════════════════════════════════════════" + ANSI_RESET);
    }

    private void exportTransactionsReport() {
        // CLI Header
        System.out.println(ANSI_CYAN + ANSI_BOLD + "\n--- Export Transactions Report ---" + ANSI_RESET);

        String reportFile = "reports/transactions_report.txt";
        List<Transaction> transactions = transactionManager.getTransactions();

        if (transactions.isEmpty()) {
            System.out.println(ANSI_YELLOW + "⚠️ No transactions recorded yet to export." + ANSI_RESET);
            return;
        }

        // Processing message
        System.out.println(ANSI_BLUE + "⚙️ Preparing and writing transactions to file..." + ANSI_RESET);

        try {
            File file = new File(reportFile);

            // Ensure the directory exists
            file.getParentFile().mkdirs();

            BufferedWriter writer = new BufferedWriter(new FileWriter(file));

            // Write CSV header
            writer.write("TransactionID,CustomerID,DrugCode,Quantity,TotalPrice,Date\n");

            // Iterate and write transaction data
            for (Transaction t : transactions) {
                // Assuming t.getTxnID(), t.getCustomerID(), t.getDrugCode(), t.getQuantity(), t.getTotalPrice(), t.getDate() exist
                writer.write(
                        t.getTxnID() + "," +
                                t.getCustomerID() + "," +
                                t.getDrugCode() + "," +
                                t.getQuantity() + "," +
                                t.getTotalPrice() + "," +
                                t.getDate() + "\n"
                );
            }

            writer.close();

            // Success message
            System.out.println(ANSI_GREEN + ANSI_BOLD + "✅ Transactions report successfully exported to " + reportFile + ANSI_RESET);

        } catch (IOException e) {
            // Error message
            System.out.println(ANSI_RED + ANSI_BOLD + "❌ Error writing transactions report to file: " + e.getMessage() + ANSI_RESET);
        }
    }
    private void exportLowStockReport() {
        // CLI Header
        System.out.println(ANSI_CYAN + ANSI_BOLD + "\n--- Export Low Stock Report ---" + ANSI_RESET);

        String reportFile = "reports/low_stock_report.txt";
        List<Drug> drugs = drugInventory.getAllDrugs();
        boolean hasLowStock = false;

        // Processing message
        System.out.println(ANSI_BLUE + "⚙️ Scanning inventory and generating report..." + ANSI_RESET);

        try {
            File file = new File(reportFile);

            // Ensure the directory exists
            file.getParentFile().mkdirs();

            BufferedWriter writer = new BufferedWriter(new FileWriter(file));

            // Write CSV header
            writer.write("Code,Name,Suppliers,ExpiryDate,Price,Stock\n");

            // Iterate and write low-stock drugs
            for (Drug d : drugs) {
                if (d.getStock() < 5) {
                    hasLowStock = true;
                    // Assuming d.getExpiryDate() and other getters exist
                    writer.write(
                            d.getCode() + "," +
                                    d.getName() + "," +
                                    String.join("|", d.getSuppliers()) + "," +
                                    d.getExpiryDate() + "," +
                                    d.getPrice() + "," +
                                    d.getStock() + "\n"
                    );
                }
            }

            writer.close();

            // --- CLI Output after processing ---
            if (hasLowStock) {
                // Success: Report generated
                System.out.println(ANSI_GREEN + ANSI_BOLD + "✅ Low stock report successfully exported to " + reportFile + ANSI_RESET);
            } else {
                // Success: All stock sufficient, delete empty file
                System.out.println(ANSI_GREEN + ANSI_BOLD + "✅ All drugs are sufficiently stocked. No low stock report was generated." + ANSI_RESET);
                file.delete(); // Remove the empty report file
            }

        } catch (IOException e) {
            // Error: Export failed
            System.out.println(ANSI_RED + ANSI_BOLD + "❌ Error writing low stock report to file: " + e.getMessage() + ANSI_RESET);
        }
    }
    private void viewTopSellingDrugs() {
        System.out.println(ANSI_CYAN + ANSI_BOLD + "\n🏆 TOP-SELLING DRUGS REPORT 🏆" + ANSI_RESET);
        System.out.println(ANSI_CYAN + "══════════════════════════════════════════════════════════" + ANSI_RESET);

        List<Transaction> transactions = transactionManager.getTransactions();

        if (transactions.isEmpty()) {
            System.out.println(ANSI_YELLOW + "⚠️ No transactions recorded yet. Cannot generate top-selling report." + ANSI_RESET);
            System.out.println(ANSI_CYAN + "══════════════════════════════════════════════════════════" + ANSI_RESET);
            return;
        }

        Map<String, Integer> salesCount = new HashMap<>();

        // Count total quantity sold for each drug code
        for (Transaction t : transactions) {
            salesCount.put(
                    t.getDrugCode(), // Assuming getDrugCode() returns the drug code
                    salesCount.getOrDefault(t.getDrugCode(), 0) + t.getQuantity() // Assuming getQuantity() returns the quantity
            );
        }

        if (salesCount.isEmpty()) { // This might be redundant if transactions.isEmpty() already caught it, but good for safety
            System.out.println(ANSI_YELLOW + "⚠️ No sales data available from recorded transactions." + ANSI_RESET);
            System.out.println(ANSI_CYAN + "══════════════════════════════════════════════════════════" + ANSI_RESET);
            return;
        }

        // Sort by highest quantity sold
        List<Map.Entry<String, Integer>> sortedSales = new ArrayList<>(salesCount.entrySet());
        sortedSales.sort((a, b) -> b.getValue().compareTo(a.getValue())); // Use compareTo for Integer for robustness

        System.out.println(ANSI_BLUE + ANSI_BOLD + String.format("%-12s %-30s %-15s", "CODE", "DRUG NAME", "QUANTITY SOLD") + ANSI_RESET);
        System.out.println(ANSI_CYAN + "------------------------------------------------------------" + ANSI_RESET);

        // Print the report
        int rank = 0;
        for (Map.Entry<String, Integer> entry : sortedSales) {
            rank++;
            String code = entry.getKey();
            int sold = entry.getValue();

            Drug drug = drugInventory.findByCode(code); // Assuming this method exists
            String name = (drug != null) ? drug.getName() : "Unknown Drug"; // Assuming getName() exists

            // Highlight top 3 or just use consistent formatting
            String rowColor = ANSI_RESET;
            if (rank == 1) rowColor = ANSI_GREEN + ANSI_BOLD; // Top seller
            else if (rank <= 3) rowColor = ANSI_YELLOW + ANSI_BOLD; // Top 3

            System.out.println(rowColor + String.format("%-12s %-30s %-15d", code, name, sold) + ANSI_RESET);
        }

        System.out.println(ANSI_CYAN + "------------------------------------------------------------" + ANSI_RESET);
        System.out.println(ANSI_GREEN + ANSI_BOLD + "✅ Report complete. Displaying " + sortedSales.size() + " unique top-selling drugs." + ANSI_RESET);
        System.out.println(ANSI_CYAN + "══════════════════════════════════════════════════════════" + ANSI_RESET);
    }
    private void viewCustomerPurchaseHistory() {
        System.out.println(ANSI_CYAN + ANSI_BOLD + "\n--- View Customer Purchase History ---" + ANSI_RESET); // Section Header

        System.out.print(ANSI_BLUE + "Enter Customer ID to view history: " + ANSI_RESET);
        String customerID = scanner.nextLine();

        // Validate customer exists and retrieve their name for the report title
        Customer customer = customerManager.findByID(customerID);
        if (customer == null) {
            System.out.println(ANSI_RED + ANSI_BOLD + "❌ Error: Customer with ID '" + customerID + "' not found." + ANSI_RESET);
            return;
        }

        // Assuming transactionManager.getTransactions() returns a List<Transaction>
        List<Transaction> allTransactions = transactionManager.getTransactions();
        List<Transaction> customerTransactions = new ArrayList<>();

        // Filter transactions for the specific customer
        for (Transaction t : allTransactions) {
            if (t.getCustomerID().equalsIgnoreCase(customerID)) {
                customerTransactions.add(t);
            }
        }

        System.out.println(ANSI_CYAN + ANSI_BOLD + "\n🧾 PURCHASE HISTORY for " + customer.getName().toUpperCase() + " (ID: " + customer.getCustomerID() + ") 🧾" + ANSI_RESET);
        System.out.println(ANSI_CYAN + "════════════════════════════════════════════════════════════════════════" + ANSI_RESET);

        if (customerTransactions.isEmpty()) {
            System.out.println(ANSI_YELLOW + "⚠️ No transactions found for this customer." + ANSI_RESET);
        } else {
            // Print a formatted table header
            System.out.println(String.format(ANSI_BLUE + ANSI_BOLD + "%-12s | %-15s | %-10s | %-12s | %s" + ANSI_RESET,
                    "TXN ID", "DRUG CODE", "QTY", "TOTAL (GHS)", "DATE"));
            System.out.println(ANSI_CYAN + "------------------------------------------------------------------------" + ANSI_RESET);

            // Print each transaction in a formatted row
            for (Transaction t : customerTransactions) {
                // Assuming Transaction has getTxnID(), getDrugCode(), getQuantity(), getTotalPrice(), getDate()
                System.out.println(String.format("%-12s | %-15s | %-10d | GHS %-8.2f | %s",
                        t.getTxnID(),
                        t.getDrugCode(),
                        t.getQuantity(),
                        t.getTotalPrice(),
                        t.getDate()));
            }
        }

        System.out.println(ANSI_CYAN + "════════════════════════════════════════════════════════════════════════" + ANSI_RESET);
        System.out.println(ANSI_GREEN + ANSI_BOLD + "✅ Report complete. Displaying " + customerTransactions.size() + " transactions." + ANSI_RESET);
        System.out.println(ANSI_CYAN + "════════════════════════════════════════════════════════════════════════" + ANSI_RESET);
    }


}