package models;

import java.util.List;

public class Supplier {
    private String supplierID;
    private String name;
    private String contact;
    private String location;
    private double deliveryTurnaroundTime;
    private List<String> drugsSupplied;

    public Supplier(String supplierID, String name, String contact, String location,
                    double deliveryTurnaroundTime, List<String> drugsSupplied) {
        this.supplierID = supplierID;
        this.name = name;
        this.contact = contact;
        this.location = location;
        this.deliveryTurnaroundTime = deliveryTurnaroundTime;
        this.drugsSupplied = drugsSupplied;
    }

    // Getters
    public String getSupplierID() { return supplierID; }
    public String getName() { return name; }
    public String getContact() { return contact; }
    public String getLocation() { return location; }
    public double getDeliveryTurnaroundTime() { return deliveryTurnaroundTime; }
    public List<String> getDrugsSupplied() { return drugsSupplied; }

    // Setters
    public void setName(String name) { this.name = name; }
    public void setContact(String contact) { this.contact = contact; }
    public void setLocation(String location) { this.location = location; }
    public void setDeliveryTurnaroundTime(double deliveryTurnaroundTime) {
        this.deliveryTurnaroundTime = deliveryTurnaroundTime;
    }
    public void setDrugsSupplied(List<String> drugsSupplied) { this.drugsSupplied = drugsSupplied; }

    @Override
    public String toString() {
        return supplierID + " | " + name + " | " + location + " | Turnaround: " + deliveryTurnaroundTime;
    }
}
