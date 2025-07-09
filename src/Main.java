import managers.DrugInventory;
import managers.SupplierManager;
import models.Drug;
import models.Supplier;
import storage.FileHandler;
import ui.Menu;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // ====== Load Data from Files ======
        String drugsFile = "data/drugs.txt";
        String suppliersFile = "data/suppliers.txt";

        // Load lists from files
        List<Drug> drugList = FileHandler.loadDrugs(drugsFile);
        List<Supplier> supplierList = FileHandler.loadSuppliers(suppliersFile);

        // Initialize Managers with loaded data
        DrugInventory drugInventory = new DrugInventory();
        SupplierManager supplierManager = new SupplierManager();

        // Add loaded data to managers
        for (Drug d : drugList) {
            drugInventory.addDrug(d);
        }

        for (Supplier s : supplierList) {
            supplierManager.addSupplier(s);
        }

        // ====== Start Menu ======
        Menu menu = new Menu(drugInventory, supplierManager);
        menu.show();

        // ====== Save Data to Files on Exit ======
        FileHandler.saveDrugs(drugsFile, drugInventory.getAllDrugs());
        FileHandler.saveSuppliers(suppliersFile, supplierManager.getSuppliers());

        System.out.println("✅ All changes saved. Goodbye!");
    }
}
