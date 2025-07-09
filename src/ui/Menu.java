package ui;

import managers.DrugInventory;
import managers.SupplierManager;
import models.Drug;
import models.Supplier;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private DrugInventory drugInventory;
    private SupplierManager supplierManager;
    private Scanner scanner;

    public Menu(DrugInventory drugInventory, SupplierManager supplierManager) {
        this.drugInventory = drugInventory;
        this.supplierManager = supplierManager;
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
            System.out.println("6. Exit");
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
}
