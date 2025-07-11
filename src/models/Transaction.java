package models;

public class Transaction {
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
        return transactionID + " | " + customerID + " | " + drugCode + " | Qty: " + quantity
                + " | Total: " + totalPrice + " | Date: " + date;
    }


}

