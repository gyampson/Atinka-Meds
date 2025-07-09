package managers;

import models.Supplier;
import java.util.ArrayList;
import java.util.List;

public class SupplierManager {
    private List<Supplier> suppliers;

    public SupplierManager() {
        this.suppliers = new ArrayList<>();
    }

    public void addSupplier(Supplier supplier) {
        // Check for unique ID
        for (Supplier s : suppliers) {
            if (s.getSupplierID().equals(supplier.getSupplierID())) {
                System.out.println("Error: Supplier ID must be unique.");
                return;
            }
        }
        suppliers.add(supplier);
        System.out.println("Supplier added successfully!");
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
