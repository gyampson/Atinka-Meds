package managers;

import models.Supplier;
import java.util.ArrayList;
import java.util.List;

public class SupplierManager {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BOLD = "\u001B[1m";
    private List<Supplier> suppliers;

    public SupplierManager() {
        this.suppliers = new ArrayList<>();
    }

    public void addSupplier(Supplier supplier) {
        // Check for unique ID first
        for (Supplier s : suppliers) {
            if (s.getSupplierID().equalsIgnoreCase(supplier.getSupplierID())) { // Using equalsIgnoreCase for case-insensitivity
                // Found a duplicate ID, provide a specific error message
                System.out.println(ANSI_RED + ANSI_BOLD + "❌ Error: A supplier with the ID '" + supplier.getSupplierID() + "' already exists. Please use a unique ID." + ANSI_RESET);
                return;
            }
        }

        // No duplicate found, proceed with adding the supplier
        suppliers.add(supplier);
        System.out.println(ANSI_GREEN + ANSI_BOLD + "✅ Supplier '" + supplier.getName() + "' (ID: " + supplier.getSupplierID() + ") added successfully!" + ANSI_RESET);
    }

    public void removeSupplier(String supplierID) {
        suppliers.removeIf(s -> s.getSupplierID().equals(supplierID));
        System.out.println("Supplier removed (if it existed).");
    }

    public void listSuppliers() {
        for (Supplier s : suppliers) {
            System.out.println(s);
        }
    }

    public void filterByLocation(String location) {
        for (Supplier s : suppliers) {
            if (s.getLocation().equalsIgnoreCase(location)) {
                System.out.println(s);
            }
        }
    }

    public void filterByTurnaround(double maxDays) {
        for (Supplier s : suppliers) {
            if (s.getDeliveryTurnaroundTime() <= maxDays) {
                System.out.println(s);
            }
        }
    }

    // OPTIONAL: Add updateSupplier() if needed
    public void updateSupplier(Supplier updatedSupplier) {
        for (int i = 0; i < suppliers.size(); i++) {
            if (suppliers.get(i).getSupplierID().equals(updatedSupplier.getSupplierID())) {
                suppliers.set(i, updatedSupplier);
                System.out.println("Supplier updated!");
                return;
            }
        }
        System.out.println("Supplier not found.");
    }

    public List<Supplier> getSuppliers() {
        return suppliers;
    }
}
