package models;

import java.util.List;

public class Drug {
    // ANSI Color Constants
    public static final String ANSI_RESET  = "\u001B[0m";
    public static final String ANSI_BOLD   = "\u001B[1m";
    public static final String ANSI_BLUE   = "\u001B[34m";
    public static final String ANSI_CYAN   = "\u001B[36m";
    public static final String ANSI_GREEN  = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";




    private String name;
    private String code;
    private List<String> suppliers;

    private String expiryDate;
    private double price;
    private int stock;

    public Drug(String name, String code, List<String> suppliers, String expiryDate, double price, int stock) {
        this.name = name;
        this.code = code;
        this.suppliers = suppliers;
        this.expiryDate = expiryDate;
        this.price = price;
        this.stock = stock;
    }

    // Getters

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public List<String> getSuppliers() {
        return suppliers;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setSuppliers(List<String> suppliers) {
        this.suppliers = suppliers;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }


    @Override
    public String toString() {
        String suppliersStr = (suppliers != null && !suppliers.isEmpty())
                ? String.join(", ", suppliers)
                : "N/A";

        return ANSI_CYAN + ANSI_BOLD + "\n--- Drug Details ---" + ANSI_RESET + "\n" +
                ANSI_GREEN + "══════════════════════════════════════════════" + ANSI_RESET + "\n" +
                ANSI_GREEN + "  Code:          " + ANSI_BOLD + code + ANSI_RESET + "\n" +
                ANSI_GREEN + "  Name:          " + ANSI_BOLD + name + ANSI_RESET + "\n" +
                ANSI_GREEN + "  Suppliers:     " + ANSI_BOLD + suppliersStr + ANSI_RESET + "\n" +
                ANSI_GREEN + "  Expiry Date:   " + ANSI_BOLD + expiryDate + ANSI_RESET + "\n" +
                ANSI_GREEN + String.format("  Price:         " + ANSI_BOLD + "GH₵ %.2f", price) + ANSI_RESET + "\n" +
                ANSI_GREEN + "  Current Stock: " + ANSI_BOLD + stock + ANSI_RESET + "\n" +
                ANSI_GREEN + "══════════════════════════════════════════════" + ANSI_RESET;
    }


}
