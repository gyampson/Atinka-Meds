
import managers.DrugInventory;
import managers.SupplierManager;
import managers.TransactionManager;
import models.Drug;
import models.Transaction;
import managers.CustomerManager;
import models.Customer;
import models.Supplier;
import storage.FileHandler;
import ui.Menu;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        // ====== Load Data from Files ======
        String drugsFile = "data/drugs.txt";
        String suppliersFile = "data/suppliers.txt";
        String customersFile = "data/customers.txt";
        String transactionsFile = "data/transactions.txt";



        String ANSI_RESET = "\u001B[0m";         // Resets all formatting
        String ANSI_GREEN = "\u001B[32m";        // Green text for success
        String ANSI_BOLD = "\u001B[1m";



        // Load lists from files
        List<Drug> drugList = FileHandler.loadDrugs(drugsFile);
        List<Supplier> supplierList = FileHandler.loadSuppliers(suppliersFile);
        List<Customer> customerList = FileHandler.loadCustomers(customersFile);
        List<Transaction> transactionList = FileHandler.loadTransactions(transactionsFile);



        // Initialize Managers with loaded data
        DrugInventory drugInventory = new DrugInventory();
        SupplierManager supplierManager = new SupplierManager();
        CustomerManager customerManager = new CustomerManager();
        TransactionManager transactionManager = new TransactionManager();




        // Add loaded data to managers
        for (Drug d : drugList) {
            drugInventory.addDrug(d);
        }

        for (Supplier s : supplierList) {
            supplierManager.addSupplier(s);
        }

        for (Customer c : customerList) {
            customerManager.addCustomer(c);
        }


        for (Transaction t : transactionList) {
            transactionManager.addTransaction(t);
        }



        // ====== Start Menu ======
        Menu menu = new Menu(drugInventory, supplierManager, customerManager, transactionManager);

        menu.show();

        // ====== Save Data to Files on Exit ======
        FileHandler.saveDrugs(drugsFile, drugInventory.getAllDrugs());
        FileHandler.saveSuppliers(suppliersFile, supplierManager.getSuppliers());
        FileHandler.saveCustomers(customersFile, customerManager.getCustomers());
        FileHandler.saveTransactions(transactionsFile, transactionManager.getTransactions());



        System.out.println("\n"); // Add some space for separation
        System.out.println(ANSI_BOLD + ANSI_GREEN + "╔═══════════════════════════════════════╗" + ANSI_RESET);
        System.out.println(ANSI_BOLD + ANSI_GREEN + "║   ✅ All changes saved. Goodbye!      ║" + ANSI_RESET);
        System.out.println(ANSI_BOLD + ANSI_GREEN + "╚═══════════════════════════════════════╝" + ANSI_RESET);
        System.out.println("\n");
    }
}
