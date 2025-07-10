package managers;

import models.Drug;

import java.util.ArrayList;
import java.util.List;

public class DrugInventory {
    private List<Drug> drugs;

    public DrugInventory() {
        this.drugs = new ArrayList<>();
    }

    public void addDrug(Drug drug) {
        for (Drug d : drugs) {
            if (d.getCode().equals(drug.getCode())) {
                System.out.println("Error: Drug code must be unique.");
                return;
            }
        }
        drugs.add(drug);
        System.out.println("Drug added successfully!");
    }

    public void removeDrug(String code) {
        drugs.removeIf(d -> d.getCode().equalsIgnoreCase(code));
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
