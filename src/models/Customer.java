package models;


public class Customer {

    // ANSI Color Constants
    public static final String ANSI_RESET  = "\u001B[0m";
    public static final String ANSI_BOLD   = "\u001B[1m";
    public static final String ANSI_BLUE   = "\u001B[34m";
    public static final String ANSI_CYAN   = "\u001B[36m";
    public static final String ANSI_GREEN  = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";



    private String customerID;
    private String name;
    private String phone;
    private String address;

    public Customer(String customerID, String name, String phone, String address) {
        this.customerID = customerID;
        this.name = name;
        this.phone = phone;
        this.address = address;
    }

    public String getCustomerID() { return customerID; }
    public String getName() { return name; }
    public String getPhone() { return phone; }
    public String getAddress() { return address; }

    public void setName(String name) { this.name = name; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setAddress(String address) { this.address = address; }

    @Override
    public String toString() {
        return ANSI_CYAN + ANSI_BOLD + "\n--- Customer Details ---" + ANSI_RESET + "\n" +
                ANSI_GREEN + "══════════════════════════════════════════════" + ANSI_RESET + "\n" +
                ANSI_GREEN + "  Customer ID : " + ANSI_BOLD + customerID + ANSI_RESET + "\n" +
                ANSI_GREEN + "  Name        : " + ANSI_BOLD + name + ANSI_RESET + "\n" +
                ANSI_GREEN + "  Phone       : " + ANSI_BOLD + phone + ANSI_RESET + "\n" +
                ANSI_GREEN + "  Address     : " + ANSI_BOLD + address + ANSI_RESET + "\n" +
                ANSI_GREEN + "══════════════════════════════════════════════" + ANSI_RESET;
    }

}
