package models;


public class Customer {
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
        return customerID + " | " + name + " | " + phone + " | " + address;
    }
}
