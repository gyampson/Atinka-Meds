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
        System.out.print("Enter Drug Code: ");
        String code = scanner.nextLine();

        System.out.print("Enter Drug Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Supplier IDs (separate by |): ");
        String supplierIDs = scanner.nextLine();
        List<String> suppliers = List.of(supplierIDs.split("\\|"));

        System.out.print("Enter Expiry Date (YYYY-MM-DD): ");
        String expiry = scanner.nextLine();

        System.out.print("Enter Price: ");
        double price = scanner.nextDouble();

        System.out.print("Enter Stock: ");
        int stock = scanner.nextInt();
        scanner.nextLine();

        Drug drug = new Drug(name, code, suppliers, expiry, price, stock);
        drugInventory.addDrug(drug);
    }

    private void removeDrug() {
        System.out.print("Enter Drug Code to Remove: ");
        String code = scanner.nextLine();
        drugInventory.removeDrug(code);
    }

    private void addSupplier() {
        System.out.print("Enter Supplier ID: ");
        String id = scanner.nextLine();

        System.out.print("Enter Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Contact: ");
        String contact = scanner.nextLine();

        System.out.print("Enter Location: ");
        String location = scanner.nextLine();

        System.out.print("Enter Delivery Turnaround Time (days): ");
        double turnaround = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Enter Drug Codes Supplied (separate by |): ");
        String drugCodes = scanner.nextLine();
        List<String> drugsSupplied = List.of(drugCodes.split("\\|"));

        Supplier supplier = new Supplier(id, name, contact, location, turnaround, drugsSupplied);
        supplierManager.addSupplier(supplier);
    }




    private void addCustomer() {
        System.out.print("Enter Customer ID: ");
        String id = scanner.nextLine();

        System.out.print("Enter Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Phone: ");
        String phone = scanner.nextLine();

        System.out.print("Enter Address: ");
        String address = scanner.nextLine();

        customerManager.addCustomer(new Customer(id, name, phone, address));
    }
//    private void listCustomers(){}


    private void recordTransaction() {
        System.out.print("Enter Transaction ID: ");
        String txnID = scanner.nextLine();

        System.out.print("Enter Customer ID: ");
        String customerID = scanner.nextLine();

        // Validate customer exists
        Customer customer = customerManager.findByID(customerID);
        if (customer == null) {
            System.out.println("❌ Customer ID not found. Please add the customer first.");
            return;
        }

        System.out.print("Enter Drug Code: ");
        String drugCode = scanner.nextLine();

        // Validate drug exists
        Drug drug = drugInventory.findByCode(drugCode);
        if (drug == null) {
            System.out.println("❌ Drug code not found. Please add the drug first.");
            return;
        }

        System.out.print("Enter Quantity: ");
        int quantity;
        try {
            quantity = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("❌ Invalid quantity. Transaction cancelled.");
            return;
        }

        if (quantity > drug.getStock()) {
            System.out.println("❌ Not enough stock. Available: " + drug.getStock());
            return;
        }

        double totalPrice = quantity * drug.getPrice();

        System.out.print("Enter Date (YYYY-MM-DD): ");
        String date = scanner.nextLine();

        // Reduce stock
        drug.setStock(drug.getStock() - quantity);
        System.out.println("✅ Stock updated. New stock: " + drug.getStock());

        // Record transaction
        Transaction transaction = new Transaction(txnID, customerID, drugCode, quantity, totalPrice, date);
        transactionManager.addTransaction(transaction);

        System.out.println("✅ Transaction recorded successfully!");
    }
    private void showSalesReport() {
        List<Transaction> transactions = transactionManager.getTransactions();

        if (transactions.isEmpty()) {
            System.out.println("📈 Sales Report");
            System.out.println("No transactions recorded yet.");
            return;
        }

        int totalTransactions = transactions.size();
        double totalSales = 0.0;

        for (Transaction t : transactions) {
            totalSales += t.getTotalPrice();
        }

        System.out.println("\n📈 Sales Report");
        System.out.println("----------------------------");
        System.out.println("Total Transactions: " + totalTransactions);
        System.out.printf("Total Sales Amount: $%.2f\n", totalSales);
        System.out.println("----------------------------");
    }
    private void searchDrugByCode() {
        System.out.print("Enter Drug Code to search: ");
        String code = scanner.nextLine();

        Drug drug = drugInventory.findByCode(code);
        if (drug != null) {
            System.out.println("✅ Drug found:");
            System.out.println(drug);
        } else {
            System.out.println("❌ Drug not found.");
        }
    }
    private void searchCustomerByID() {
        System.out.print("Enter Customer ID to search: ");
        String id = scanner.nextLine();

        Customer customer = customerManager.findByID(id);
        if (customer != null) {
            System.out.println("✅ Customer found:");
            System.out.println(customer);
        } else {
            System.out.println("❌ Customer not found.");
        }
    }
    private void deleteDrugByCode() {
        System.out.print("Enter Drug Code to delete: ");
        String code = scanner.nextLine();

        Drug drug = drugInventory.findByCode(code);
        if (drug == null) {
            System.out.println("❌ Drug not found. Nothing deleted.");
            return;
        }

        drugInventory.removeDrug(code);
        System.out.println("✅ Drug deleted successfully!");
    }
    private void deleteCustomerByID() {
        System.out.print("Enter Customer ID to delete: ");
        String id = scanner.nextLine();

        Customer customer = customerManager.findByID(id);
        if (customer == null) {
            System.out.println("❌ Customer not found. Nothing deleted.");
            return;
        }

        customerManager.removeCustomer(id);
        System.out.println("✅ Customer deleted successfully!");
    }
    private void showLowStockReport() {
        System.out.println("\n📦 Low Stock Report (Below 5 Units)");
        System.out.println("------------------------------------");

        boolean found = false;
        for (Drug d : drugInventory.getAllDrugs()) {
            if (d.getStock() < 5) {
                System.out.println(d);
                found = true;
            }
        }

        if (!found) {
            System.out.println("✅ All drugs are sufficiently stocked.");
        }

        System.out.println("------------------------------------");
    }

    private void showSupplierDrugReport() {
        System.out.println("\n📦 Supplier Drug Report");
        System.out.println("------------------------------------");

        List<Supplier> suppliers = supplierManager.getSuppliers();
        if (suppliers.isEmpty()) {
            System.out.println("❌ No suppliers found.");
            return;
        }

        for (Supplier s : suppliers) {
            System.out.println("Supplier: " + s.getSupplierID() + " | " + s.getName());
            List<String> drugsSupplied = s.getDrugsSupplied();
            if (drugsSupplied.isEmpty()) {
                System.out.println("Drugs Supplied: None");
            } else {
                System.out.println("Drugs Supplied: " + String.join(" | ", drugsSupplied));
            }
            System.out.println("------------------------------------");
        }
    }

    private void exportTransactionsReport() {
        String reportFile = "reports/transactions_report.txt";
        List<Transaction> transactions = transactionManager.getTransactions();

        if (transactions.isEmpty()) {
            System.out.println("❌ No transactions to export.");
            return;
        }

        try {
            File file = new File(reportFile);
            file.getParentFile().mkdirs();  // Ensure /reports folder exists
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));

            writer.write("TransactionID,CustomerID,DrugCode,Quantity,TotalPrice,Date\n");
            for (Transaction t : transactions) {
                writer.write(
                        t.getTransactionID() + "," +
                                t.getCustomerID() + "," +
                                t.getDrugCode() + "," +
                                t.getQuantity() + "," +
                                t.getTotalPrice() + "," +
                                t.getDate() + "\n"
                );
            }

            writer.close();
            System.out.println("✅ Transactions exported successfully to " + reportFile);
        } catch (IOException e) {
            System.out.println("❌ Error writing report: " + e.getMessage());
        }
    }
    private void exportLowStockReport() {
        String reportFile = "reports/low_stock_report.txt";
        List<Drug> drugs = drugInventory.getAllDrugs();

        boolean hasLowStock = false;

        try {
            File file = new File(reportFile);
            file.getParentFile().mkdirs();
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));

            writer.write("Code,Name,Suppliers,ExpiryDate,Price,Stock\n");
            for (Drug d : drugs) {
                if (d.getStock() < 5) {
                    hasLowStock = true;
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

            if (hasLowStock) {
                System.out.println("✅ Low stock report exported to " + reportFile);
            } else {
                System.out.println("✅ All drugs sufficiently stocked. No report generated.");
                file.delete(); // Remove empty report
            }

        } catch (IOException e) {
            System.out.println("❌ Error writing report: " + e.getMessage());
        }
    }
    private void viewTopSellingDrugs() {
        System.out.println("\n📈 Top-Selling Drugs Report");
        System.out.println("------------------------------------");

        List<Transaction> transactions = transactionManager.getTransactions();

        if (transactions.isEmpty()) {
            System.out.println("❌ No transactions recorded yet.");
            return;
        }

        Map<String, Integer> salesCount = new HashMap<>();

        // Count total quantity sold for each drug code
        for (Transaction t : transactions) {
            salesCount.put(
                    t.getDrugCode(),
                    salesCount.getOrDefault(t.getDrugCode(), 0) + t.getQuantity()
            );
        }

        if (salesCount.isEmpty()) {
            System.out.println("❌ No sales data available.");
            return;
        }

        // Sort by highest quantity sold
        List<Map.Entry<String, Integer>> sortedSales = new ArrayList<>(salesCount.entrySet());
        sortedSales.sort((a, b) -> b.getValue() - a.getValue());

        // Print the report
        for (Map.Entry<String, Integer> entry : sortedSales) {
            String code = entry.getKey();
            int sold = entry.getValue();

            Drug drug = drugInventory.findByCode(code);
            String name = (drug != null) ? drug.getName() : "Unknown Drug";

            System.out.println(code + " | " + name + " | Quantity Sold: " + sold);
        }

        System.out.println("------------------------------------");
    }
    private void viewCustomerPurchaseHistory() {
        System.out.print("Enter Customer ID to view history: ");
        String customerID = scanner.nextLine();

        Customer customer = customerManager.findByID(customerID);
        if (customer == null) {
            System.out.println("❌ Customer not found.");
            return;
        }

        List<Transaction> transactions = transactionManager.getTransactions();

        boolean found = false;
        System.out.println("\n🧾 Purchase History for Customer: " + customerID);
        System.out.println("------------------------------------");

        for (Transaction t : transactions) {
            if (t.getCustomerID().equalsIgnoreCase(customerID)) {
                found = true;
                System.out.println(
                        t.getTransactionID() + " | " +
                                t.getDrugCode() + " | Quantity: " +
                                t.getQuantity() + " | Total: $" +
                                t.getTotalPrice() + " | Date: " + t.getDate()
                );
            }
        }

        if (!found) {
            System.out.println("❌ No transactions found for this customer.");
        }

        System.out.println("------------------------------------");
    }



}
