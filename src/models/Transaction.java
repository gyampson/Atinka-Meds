package models;

public class Transaction {
    // ANSI Color Constants
    public static final String ANSI_RESET  = "\u001B[0m";
    public static final String ANSI_BOLD   = "\u001B[1m";
    public static final String ANSI_BLUE   = "\u001B[34m";
    public static final String ANSI_CYAN   = "\u001B[36m";
    public static final String ANSI_GREEN  = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";



    private String transactionID;
    private String customerID;
    private String drugCode;
    private int quantity;
    private double totalPrice;
    private String date;

    public Transaction(String transactionID, String customerID, String drugCode,
                       int quantity, double totalPrice, String date) {
        this.transactionID = transactionID;
        this.customerID = customerID;
        this.drugCode = drugCode;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.date = date;
    }

    public String getTxnID() { return transactionID; }
    public String getCustomerID() { return customerID; }
    public String getDrugCode() { return drugCode; }
    public int getQuantity() { return quantity; }
    public double getTotalPrice() { return totalPrice; }
    public String getDate() { return date; }

    @Override
    public String toString() {
        return ANSI_CYAN + ANSI_BOLD + "\n--- Transaction Details ---" + ANSI_RESET + "\n" +
                ANSI_GREEN + "══════════════════════════════════════════════════════" + ANSI_RESET + "\n" +
                ANSI_GREEN + "  Transaction ID : " + ANSI_BOLD + transactionID + ANSI_RESET + "\n" +
                ANSI_GREEN + "  Customer ID    : " + ANSI_BOLD + customerID + ANSI_RESET + "\n" +
                ANSI_GREEN + "  Drug Code      : " + ANSI_BOLD + drugCode + ANSI_RESET + "\n" +
                ANSI_GREEN + "  Quantity       : " + ANSI_BOLD + quantity + ANSI_RESET + "\n" +
                ANSI_GREEN + String.format("  Total Price    : " + ANSI_BOLD + "GH₵ %.2f", totalPrice) + ANSI_RESET + "\n" +
                ANSI_GREEN + "  Date           : " + ANSI_BOLD + date + ANSI_RESET + "\n" +
                ANSI_GREEN + "══════════════════════════════════════════════════════" + ANSI_RESET;
    }



}

