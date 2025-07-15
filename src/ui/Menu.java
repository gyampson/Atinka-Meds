package ui;

import managers.DrugInventory;
import managers.SupplierManager;
import managers.TransactionManager;
import models.Customer;
import models.Drug;
import models.Supplier;
import managers.CustomerManager;
import models.Transaction;



import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;
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
            System.out.println("00. Save & Exit");
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

                case 00:
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
        System.out.println(ANSI_CYAN + ANSI_BOLD + "\n--- ➕ Add New Drug ---" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "(Tip: You can type ':cancel' at any time to return to the main menu)" + ANSI_RESET);

        try {
            String code = promptMatchingPatternOrCancel("🧾 Enter Drug Code (e.g., DR001): ", "^DR\\d+$", "DR001");
            String name = promptLettersOnlyOrCancel("📛 Enter Drug Name: ");
            String supplierIDs = promptMatchingPatternOrCancel("🏷️ Enter Supplier ID (e.g., SUP001): ", "^SUP\\d+$", "SUP001");
            List<String> suppliers = List.of(supplierIDs.split("\\|"));
            String expiry = promptDateOrCancel("⏳ Enter Expiry Date (YYYY-MM-DD): ");
            double price = promptPositiveDoubleOrCancel("💰 Enter Price: ");
            int stock = promptPositiveIntOrCancel("📦 Enter Stock Quantity: ");

            Drug drug = new Drug(name, code, suppliers, expiry, price, stock);
            drugInventory.addDrug(drug);



        } catch (CancelInputException e) {
            System.out.println(ANSI_YELLOW + "↩️ Input cancelled. Returning to main menu..." + ANSI_RESET);
        }
    }



    private void removeDrug() {
        System.out.print(ANSI_BLUE + "Enter Drug Code to Remove " + ANSI_RESET);
        String code = scanner.nextLine();
        drugInventory.removeDrug(code);
        waitForEnter();

    }

    private void addSupplier() {
        System.out.println(ANSI_CYAN + ANSI_BOLD + "\n--- ➕ Add New Supplier ---" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "(Tip: You can type ':cancel' at any time to return to the main menu)" + ANSI_RESET);

        try {
            String id = promptMatchingPatternOrCancel("🏷️ Enter Supplier ID (e.g., SUP001): ", "^SUP\\d+$", "SUP001");


            String name = promptLettersOnlyOrCancel("🏛️️ Enter Supplier Name: ");
            String contact = promptNumbersOnlyOrCancel("📞 Enter Contact Number: ");


            String location = promptNonEmptyStringOrCancel("📍 Enter Location: ");
            double turnaround = promptPositiveDoubleOrCancel("⏱️ Enter Delivery Turnaround Time (in days): ");
            String drugCodes = promptMatchingPatternOrCancel("🧾 Enter Drug Code (e.g., DR001): ", "^DR\\d+$", "DR001");
            List<String> drugsSupplied = List.of(drugCodes.split("\\|"));

            for (String code : drugsSupplied) {
                if (!code.trim().matches("^DR\\d+$")) {
                    System.out.println(ANSI_RED + "❌ Invalid Drug Code format: " + code + ". Expected format: DR001" + ANSI_RESET);
                    return;
                }
            }

            Supplier supplier = new Supplier(id, name, contact, location, turnaround, drugsSupplied);
            supplierManager.addSupplier(supplier);


        } catch (CancelInputException e) {
            System.out.println(ANSI_YELLOW + "↩️ Input cancelled. Returning to main menu..." + ANSI_RESET);
        }
    }



    private void addCustomer() {
        System.out.println(ANSI_CYAN + ANSI_BOLD + "\n--- ➕ Add New Customer ---" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "(Tip: You can type ':cancel' at any time to return to the main menu)" + ANSI_RESET);

        try {
            // Enforce Customer ID format: CUS + numbers
            String id = promptMatchingPatternOrCancel("🪪 Enter Customer ID (e.g., CUS001): ", "^CUS\\d+$", "CUS001");

            String name = promptLettersOnlyOrCancel("📛 Enter Customer Name: ");
            String phone = promptNumbersOnlyOrCancel("📞 Enter Contact Number: ");

            String address = promptNonEmptyStringOrCancel("📍 Enter Address: ");

            customerManager.addCustomer(new Customer(id, name, phone, address));



        } catch (CancelInputException e) {
            System.out.println(ANSI_YELLOW + "↩️ Input cancelled. Returning to main menu..." + ANSI_RESET);
        }
    }






    private void recordTransaction() {
        System.out.println(ANSI_CYAN + ANSI_BOLD + "\n--- 💰 Record New Transaction ---" + ANSI_RESET);

        try {
            String txnID = promptMatchingPatternOrCancel("🧾 Enter Transaction ID (e.g., TRX001):", "^TRX\\d+$", "TRX001");

            // Customer ID & validation
            String customerID;
            Customer customer;
            while (true) {
                customerID = promptMatchingPatternOrCancel("🪪 Enter Customer ID (e.g., CUS001):", "^CUS\\d+$", "CUS001");
                customer = customerManager.findByID(customerID);
                if (customer != null) break;
                System.out.println(ANSI_RED + "❌ Not found. Add the customer or enter another ID." + ANSI_RESET);
            }

            // Drug Code & validation
            String drugCode;
            Drug drug;
            while (true) {
                drugCode = promptMatchingPatternOrCancel("💊 Enter Drug Code (e.g., DR001):", "^DR\\d+$", "DR001");
                drug = drugInventory.findByCode(drugCode);
                if (drug != null) break;
                System.out.println(ANSI_RED + "❌ Not found. Type ':cancel' to go add the drug." + ANSI_RESET);
            }

            int quantity = promptPositiveInt("🔢 Enter Quantity: ");
            double totalPrice = quantity * drug.getPrice();
            String date = promptDate("📅 Enter Date (YYYY-MM-DD): ");

            // Reduce stock
            drug.setStock(drug.getStock() - quantity);
            Transaction transaction = new Transaction(txnID, customerID, drugCode, quantity, totalPrice, date);
            transactionManager.addTransaction(transaction);


        } catch (CancelInputException e) {
            System.out.println(ANSI_YELLOW + "↩️ Input cancelled. Returning to main menu..." + ANSI_RESET);
        }
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
        System.out.printf(ANSI_GREEN + "Total Revenue Generated:    GH₵ " + ANSI_BOLD + "%.2f\n" + ANSI_RESET, totalSales);

        System.out.println(ANSI_BLUE + ANSI_BOLD + "\n--- Individual Transactions ---" + ANSI_RESET);

        System.out.println(String.format(ANSI_BOLD + "%-12s %-15s %-15s %-10s %-10s" + ANSI_RESET,
                "TXN ID", "CUSTOMER ID", "DRUG CODE", "QTY", "PRICE"));
        System.out.println(ANSI_CYAN + "------------------------------------------------------------" + ANSI_RESET);

        for (Transaction t : transactions) {

            System.out.println(String.format("%-12s %-15s %-15s %-10d GH₵ %-6.2f",
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
        waitForEnter();

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
            System.out.println(ANSI_GREEN + String.format("  Price:        GH₵ %.2f", drug.getPrice()) + ANSI_RESET);
            System.out.println(ANSI_GREEN + "  Stock:        " + ANSI_BOLD + drug.getStock() + ANSI_RESET);
            System.out.println(ANSI_GREEN + "═════════════════════════════════════" + ANSI_RESET); // Bottom Separator
        } else {
            System.out.println(ANSI_RED + ANSI_BOLD + "❌ Drug with code '" + code + "' not found in inventory." + ANSI_RESET);
            waitForEnter();

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
        waitForEnter();

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
        waitForEnter();

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
        waitForEnter();


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
        waitForEnter();

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


        waitForEnter();

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
        waitForEnter();

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
        waitForEnter();

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
        waitForEnter();

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
                    "TXN ID", "DRUG CODE", "QTY", "TOTAL (GH₵)", "DATE"));
            System.out.println(ANSI_CYAN + "------------------------------------------------------------------------" + ANSI_RESET);

            // Print each transaction in a formatted row
            for (Transaction t : customerTransactions) {
                // Assuming Transaction has getTxnID(), getDrugCode(), getQuantity(), getTotalPrice(), getDate()
                System.out.println(String.format("%-12s | %-15s | %-10d | GH₵ %-8.2f | %s",
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
        waitForEnter();

    }





    private String promptNonEmptyString(String message) {
        String input;
        do {
            System.out.print(ANSI_BLUE + message + ANSI_RESET);
            input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println(ANSI_RED + "❌ Input cannot be empty. Please try again." + ANSI_RESET);
            }
        } while (input.isEmpty());
        return input;
    }

    private double promptPositiveDouble(String message) {
        double value = -1;
        while (value <= 0) {
            System.out.print(ANSI_BLUE + message + ANSI_RESET);
            if (scanner.hasNextDouble()) {
                value = scanner.nextDouble();
                if (value <= 0) {
                    System.out.println(ANSI_RED + "❌ Must be greater than 0." + ANSI_RESET);
                }
            } else {
                System.out.println(ANSI_RED + "❌ Invalid number. Please try again." + ANSI_RESET);
                scanner.next(); // discard invalid input
            }
        }
        scanner.nextLine(); // clear newline
        return value;
    }

    private int promptPositiveInt(String message) {
        int value = -1;
        while (value <= 0) {
            System.out.print(ANSI_BLUE + message + ANSI_RESET);
            if (scanner.hasNextInt()) {
                value = scanner.nextInt();
                if (value <= 0) {
                    System.out.println(ANSI_RED + "❌ Must be a positive number." + ANSI_RESET);
                }
            } else {
                System.out.println(ANSI_RED + "❌ Invalid input. Enter a number." + ANSI_RESET);
                scanner.next();
            }
        }
        scanner.nextLine(); // clear newline
        return value;
    }

    private String promptDate(String message) {
        String input;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        while (true) {
            System.out.print(ANSI_BLUE + message + ANSI_RESET);
            input = scanner.nextLine().trim();
            try {
                LocalDate.parse(input, formatter);
                break;
            } catch (DateTimeParseException e) {
                System.out.println(ANSI_RED + "❌ Invalid format. Use YYYY-MM-DD." + ANSI_RESET);
            }
        }
        return input;
    }
    private String promptMatchingPattern(String message, String pattern, String example) {
        Pattern compiledPattern = Pattern.compile(pattern);
        String input;
        while (true) {
            System.out.print(ANSI_BLUE + message + ANSI_RESET);
            input = scanner.nextLine().trim();
            if (compiledPattern.matcher(input).matches()) {
                break;
            } else {
                System.out.println(ANSI_RED + "❌ Invalid format. Example: " + example + ANSI_RESET);
            }
        }
        return input;
    }
    private String promptWithCancel(String message) {
        System.out.print(ANSI_BLUE + message + ANSI_RESET);
        String input = scanner.nextLine().trim();
        if (input.equalsIgnoreCase(":cancel") || input.equalsIgnoreCase(":exit")) {
            throw new CancelInputException();
        }
        return input;
    }

    private String promptMatchingPatternOrCancel(String message, String pattern, String example) {
        Pattern compiledPattern = Pattern.compile(pattern);
        while (true) {
            String input = promptWithCancel(message);
            if (compiledPattern.matcher(input).matches()) {
                return input;
            } else {
                System.out.println(ANSI_RED + "❌ Invalid format. Example: " + example + ANSI_RESET);
            }
        }
    }

    private String promptNonEmptyStringOrCancel(String message) {
        String input;
        while (true) {
            input = promptWithCancel(message);
            if (input.isBlank()) {
                System.out.println(ANSI_RED + "❌ Input cannot be empty. Please try again." + ANSI_RESET);
            } else {
                return input;
            }
        }
    }

    private int promptPositiveIntOrCancel(String message) {
        while (true) {
            String input = promptWithCancel(message);
            try {
                int value = Integer.parseInt(input);
                if (value > 0) {
                    return value;
                } else {
                    System.out.println(ANSI_RED + "❌ Must be a positive number." + ANSI_RESET);
                }
            } catch (NumberFormatException e) {
                System.out.println(ANSI_RED + "❌ Invalid number. Please enter a whole number." + ANSI_RESET);
            }
        }
    }

    private double promptPositiveDoubleOrCancel(String message) {
        while (true) {
            String input = promptWithCancel(message);
            try {
                double value = Double.parseDouble(input);
                if (value > 0) {
                    return value;
                } else {
                    System.out.println(ANSI_RED + "❌ Must be greater than 0." + ANSI_RESET);
                }
            } catch (NumberFormatException e) {
                System.out.println(ANSI_RED + "❌ Invalid number. Please enter a valid price." + ANSI_RESET);
            }
        }
    }
    private String promptDateOrCancel(String message) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        while (true) {
            String input = promptWithCancel(message);
            try {
                LocalDate.parse(input, formatter);
                return input;
            } catch (DateTimeParseException e) {
                System.out.println(ANSI_RED + "❌ Invalid date format. Please use YYYY-MM-DD." + ANSI_RESET);
            }
        }
    }

    private String promptLettersOnlyOrCancel(String message) {
        while (true) {
            String input = promptWithCancel(message);
            if (input.matches("^[A-Za-z ]+$")) {
                return input;
            } else {
                System.out.println(ANSI_RED + "❌ Invalid input. Only letters and spaces are allowed." + ANSI_RESET);
            }
        }
    }

    private String promptNumbersOnlyOrCancel(String message) {
        while (true) {
            String input = promptWithCancel(message);
            if (input.matches("^\\d+$")) {
                return input;
            } else {
                System.out.println(ANSI_RED + "❌ Invalid input. Only numbers are allowed." + ANSI_RESET);
            }
        }
    }


    private void waitForEnter() {
        System.out.println();
        System.out.println(ANSI_BLUE + "(Press Enter to continue...)" + ANSI_RESET);
        scanner.nextLine();
    }





}