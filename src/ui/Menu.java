package ui;

import managers.DrugInventory;
import managers.SupplierManager;
import managers.TransactionManager;
import models.Customer;
import models.Drug;
import models.Supplier;
import managers.CustomerManager;
import models.Transaction;


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
            System.out.println("\n=== Atinka Meds Pharmacy Inventory System ===");
            System.out.println("1. Add Drug");
            System.out.println("2. Remove Drug");
            System.out.println("3. List Drugs");
            System.out.println("4. Add Supplier");
            System.out.println("5. List Suppliers");
            System.out.println("6. Add Customer");
            System.out.println("7. List Customers");
            System.out.println("8. Record Transactions");
            System.out.println("9. List Transactions");
            System.out.println("10. Save & Exit");
            System.out.print("Choose option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // clear newline

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
                    exit = true;
                    System.out.println("Exiting. Goodbye!");
                    break;


                default:
                    System.out.println("Invalid choice. Try again.");
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

        System.out.print("Enter Drug Code: ");
        String drugCode = scanner.nextLine();

        System.out.print("Enter Quantity: ");
        int quantity = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter Total Price: ");
        double totalPrice = Double.parseDouble(scanner.nextLine());

        System.out.print("Enter Date (YYYY-MM-DD): ");
        String date = scanner.nextLine();

        Transaction transaction = new Transaction(txnID, customerID, drugCode, quantity, totalPrice, date);
        transactionManager.addTransaction(transaction);
    }

}
