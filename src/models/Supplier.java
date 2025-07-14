package models;

import java.util.List;

public class Supplier {

    // ANSI Color Constants
    public static final String ANSI_RESET  = "\u001B[0m";
    public static final String ANSI_BOLD   = "\u001B[1m";
    public static final String ANSI_BLUE   = "\u001B[34m";
    public static final String ANSI_CYAN   = "\u001B[36m";
    public static final String ANSI_GREEN  = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";




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
        return ANSI_CYAN + ANSI_BOLD + "\n--- Supplier Details ---" + ANSI_RESET + "\n" +
                ANSI_GREEN + "══════════════════════════════════════" + ANSI_RESET + "\n" +
                ANSI_GREEN + "  Supplier ID : " + ANSI_BOLD + supplierID + ANSI_RESET + "\n" +
                ANSI_GREEN + "  Name        : " + ANSI_BOLD + name + ANSI_RESET + "\n" +
                ANSI_GREEN + "  Location    : " + ANSI_BOLD + location + ANSI_RESET + "\n" +
                ANSI_GREEN + "  Turnaround  : " + ANSI_BOLD + deliveryTurnaroundTime + " days" + ANSI_RESET + "\n" +
                ANSI_GREEN + "══════════════════════════════════════" + ANSI_RESET;
    }

}
