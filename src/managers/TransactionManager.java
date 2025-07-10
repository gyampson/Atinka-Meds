package managers;

import models.Transaction;
import java.util.ArrayList;
import java.util.List;

public class TransactionManager {
    private List<Transaction> transactions;

    public TransactionManager() {
        this.transactions = new ArrayList<>();
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
        System.out.println("Transaction recorded successfully!");
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

