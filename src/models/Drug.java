package models;

import java.util.List;

public class Drug {
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
        return code + " | " + name + " | " + price + " | Stock: " + stock + " | Expiry: " + expiryDate;
    }
}
