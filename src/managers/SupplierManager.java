package managers;

import models.Supplier;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SupplierManager {

    // ANSI Color Constants
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_BOLD = "\u001B[1m";

    private List<Supplier> suppliers;
    private Scanner scanner = new Scanner(System.in);

    public SupplierManager() {
        this.suppliers = new ArrayList<>();
    }

    // ✅ Silent load at startup
    public void loadSupplier(Supplier supplier) {
        suppliers.add(supplier);
    }

    // ✅ User-facing add with duplicate check
    public void addSupplier(Supplier supplier) {
        for (Supplier s : suppliers) {
            if (s.getSupplierID().equalsIgnoreCase(supplier.getSupplierID())) {
                printError("A supplier with the ID '" + supplier.getSupplierID() + "' already exists. Please use a unique ID.");
                return;
            }
        }

        suppliers.add(supplier);
        printSuccess("Supplier '" + supplier.getName() + "' (ID: " + supplier.getSupplierID() + ") added.");
    }

    // ✅ Remove Supplier with feedback
    public boolean removeSupplier(String supplierID) {
        Supplier toRemove = null;
        for (Supplier s : suppliers) {
            if (s.getSupplierID().equalsIgnoreCase(supplierID)) {
                toRemove = s;
                break;
            }
        }

        if (toRemove != null) {
            suppliers.remove(toRemove);
            printSuccess("Supplier '" + toRemove.getName() + "' (ID: " + toRemove.getSupplierID() + ") removed.");
            return true;
        } else {
            printError("No supplier found with the ID '" + supplierID + "'.");
            return false;
        }
    }

    // ✅ List Suppliers with header
    public void listSuppliers() {
        if (suppliers.isEmpty()) {
            System.out.println(ANSI_YELLOW + ANSI_BOLD + "⚠️ No suppliers in the system." + ANSI_RESET);
            return;
        }

        System.out.println(ANSI_BLUE + ANSI_BOLD + "\n=== 📦 Supplier List ===" + ANSI_RESET);
        for (Supplier s : suppliers) {
            System.out.println(s);
        }
        waitForEnter();
    }

    // ✅ Filter by Location
    public void filterByLocation(String location) {
        boolean found = false;
        for (Supplier s : suppliers) {
            if (s.getLocation().equalsIgnoreCase(location)) {
                System.out.println(s);
                found = true;
            }
        }
        if (!found) {
            System.out.println(ANSI_YELLOW + ANSI_BOLD + "⚠️ No suppliers found in that location." + ANSI_RESET);
        }
    }

    public List<Supplier> getSuppliersByTurnaround(double maxDays) {
        List<Supplier> results = new ArrayList<>();
        for (Supplier s : suppliers) {
            if (s.getDeliveryTurnaroundTime() <= maxDays) {
                results.add(s);
            }
        }
        return results;
    }


    public Supplier findByID(String id) {
        for (Supplier s : suppliers) {
            if (s.getSupplierID().equalsIgnoreCase(id)) {
                return s;
            }
        }
        return null;
    }

    // ✅ Optional Update
    public void updateSupplier(Supplier updatedSupplier) {
        for (int i = 0; i < suppliers.size(); i++) {
            if (suppliers.get(i).getSupplierID().equalsIgnoreCase(updatedSupplier.getSupplierID())) {
                suppliers.set(i, updatedSupplier);
                printSuccess("Supplier '" + updatedSupplier.getName() + "' (SUP ID: " + updatedSupplier.getSupplierID() + ") updated.");
                return;
            }
        }
        printError("Supplier with ID '" + updatedSupplier.getSupplierID() + "' not found.");
    }

    // ✅ Get all Suppliers
    public List<Supplier> getSuppliers() {
        return suppliers;
    }

    // ✅ Helpers for consistent messaging
    private void printSuccess(String message) {
        System.out.println();
        System.out.println(ANSI_GREEN + ANSI_BOLD + "[✔️ SUCCESS] " + message + ANSI_RESET);
        System.out.println(ANSI_BLUE + "(Press Enter to continue...)" + ANSI_RESET);
        scanner.nextLine();
    }

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


