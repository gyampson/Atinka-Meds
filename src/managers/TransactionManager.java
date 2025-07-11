package managers;

import models.Transaction;
import java.util.ArrayList;
import java.util.List;

public class TransactionManager {
    // ANSI Color Constants (can be in a separate class or your main class)
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLUE = "\u001B[34m";    // For prompts
    public static final String ANSI_CYAN = "\u001B[36m";    // For section headers
    public static final String ANSI_GREEN = "\u001B[32m";   // For success messages
    public static final String ANSI_RED = "\u001B[31m";     // For error messages
    public static final String ANSI_BOLD = "\u001B[1m";
    private List<Transaction> transactions;

    public TransactionManager() {
        this.transactions = new ArrayList<>();
    }

    public void addTransaction(Transaction transaction) {
        // Check for unique Transaction ID
        // Assuming 'transactions' is a List<Transaction> and Transaction has a getTxnID() method
        for (Transaction t : transactions) {
            if (t.getTxnID().equalsIgnoreCase(transaction.getTxnID())) {
                // Found a duplicate ID, print a specific error message
                System.out.println(ANSI_RED + ANSI_BOLD + "❌ Error: A transaction with the ID '" + transaction.getTxnID() + "' already exists. Transaction not recorded." + ANSI_RESET);
                return; // Exit the method as the transaction cannot be added
            }
        }

        // If no duplicate ID is found, proceed with adding the transaction
        transactions.add(transaction);

        // Provide a clear success message
        System.out.println(ANSI_GREEN + ANSI_BOLD + "✅ Transaction '" + transaction.getTxnID() + "' recorded successfully!" + ANSI_RESET);
        // You can add more details here for a richer confirmation, e.g.:
        // System.out.println(ANSI_GREEN + "    Customer: " + transaction.getCustomerID() + ANSI_RESET);
        // System.out.println(ANSI_GREEN + "    Drug: " + transaction.getDrugCode() + " (Qty: " + transaction.getQuantity() + ")" + ANSI_RESET);
        // System.out.println(ANSI_GREEN + String.format("    Total Amount: GHS %.2f", transaction.getTotalPrice()) + ANSI_RESET);
        // System.out.println(ANSI_GREEN + "    Date: " + transaction.getDate() + ANSI_RESET);
    }

    public void listTransactions() {
        for (Transaction t : transactions) {
            System.out.println(t);
        }
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }
}

