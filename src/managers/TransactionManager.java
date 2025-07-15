package managers;

import models.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TransactionManager {

    // ANSI Color Constants
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_BOLD = "\u001B[1m";

    private List<Transaction> transactions;
    private Scanner scanner = new Scanner(System.in);

    public TransactionManager() {
        this.transactions = new ArrayList<>();
    }

    // ✅ Silent load at startup
    public void loadTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    // ✅ User-facing add with duplicate check + feedback
    public void addTransaction(Transaction transaction) {
        for (Transaction t : transactions) {
            if (t.getTxnID().equalsIgnoreCase(transaction.getTxnID())) {
                printError("A transaction with the ID '" + transaction.getTxnID() + "' already exists. Transaction not recorded.");
                return;
            }
        }

        transactions.add(transaction);
        printSuccess("Transaction '" + transaction.getTxnID() + "' recorded.");
    }

    // ✅ List Transactions with header
    public void listTransactions() {
        if (transactions.isEmpty()) {
            System.out.println(ANSI_YELLOW + ANSI_BOLD + "⚠️ No transactions recorded yet." + ANSI_RESET);
            return;
        }

        System.out.println(ANSI_BLUE + ANSI_BOLD + "\n=== 💳 Transaction Records ===" + ANSI_RESET);
        for (Transaction t : transactions) {
            System.out.println(t);
        }
        waitForEnter();
    }

    // ✅ Get all Transactions
    public List<Transaction> getTransactions() {
        return transactions;
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
