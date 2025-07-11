package managers;

import models.Drug;

import java.util.ArrayList;
import java.util.List;

public class DrugInventory {

    // ANSI Color Constants (can be in a separate class or your main class)
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BOLD = "\u001B[1m";

    private List<Drug> drugs;

    public DrugInventory() {
        this.drugs = new ArrayList<>();
    }

    public void addDrug(Drug drug) {
        // Check for unique code first
        for (Drug d : drugs) {
            if (d.getCode().equalsIgnoreCase(drug.getCode())) {
                // Found a duplicate code, provide a specific error message
                System.out.println(ANSI_RED + ANSI_BOLD + "❌ Error: A drug with the code '" + drug.getCode() + "' already exists. Please use a unique code." + ANSI_RESET);
                return;
            }
        }

        // No duplicate found, proceed with adding the drug
        drugs.add(drug);
        System.out.println(ANSI_GREEN + ANSI_BOLD + "✅ Drug '" + drug.getName() + "' (Code: " + drug.getCode() + ") added successfully!" + ANSI_RESET);
    }
    public boolean removeDrug(String code) {
        drugs.removeIf(d -> d.getCode().equalsIgnoreCase(code));

        return false;
    }


    public void updateDrug(String code, double newPrice, int newStock) {
        for (Drug d : drugs) {
            if (d.getCode().equals(code)) {
                d.setPrice(newPrice);
                d.setStock(newStock);
                System.out.println("Drug updated!");
                return;
            }
        }
        System.out.println("Drug not found.");
    }

    public void listDrugs() {
        for (Drug d : drugs) {
            System.out.println(d);
        }
    }

    public Drug findByCode(String code) {
        for (Drug d : drugs) {
            if (d.getCode().equalsIgnoreCase(code)) {
                return d;
            }
        }
        return null;
    }

    public List<Drug> findByName(String name) {
        List<Drug> results = new ArrayList<>();
        for (Drug d : drugs) {
            if (d.getName().toLowerCase().contains(name.toLowerCase())) {
                results.add(d);
            }
        }
        return results;
    }

    public List<Drug> getAllDrugs() {
        return drugs;
    }
}