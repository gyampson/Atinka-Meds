package storage;

import models.Drug;
import models.Supplier;
import managers.CustomerManager;
import models.Customer;
import models.Transaction;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileHandler {

    // Load Drugs from File
    public static List<Drug> loadDrugs(String filepath) {
        List<Drug> drugs = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            String line = reader.readLine(); // Skip header
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String code = parts[0];
                String name = parts[1];
                List<String> suppliers = Arrays.asList(parts[2].split("\\|"));
                String expiry = parts[3];
                double price = Double.parseDouble(parts[4]);
                int stock = Integer.parseInt(parts[5]);

                Drug drug = new Drug(name, code, suppliers, expiry, price, stock);
                drugs.add(drug);
            }
        } catch (IOException e) {
            System.out.println("Error reading drugs file: " + e.getMessage());
        }
        return drugs;
    }

    // Save Drugs to File
    public static void saveDrugs(String filepath, List<Drug> drugs) {
        try {
            File file = new File(filepath);
            file.getParentFile().mkdirs(); // ensure parent folders exist

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write("code,name,suppliers,expiry_date,price,stock\n");
                for (Drug d : drugs) {
                    String supplierStr = String.join("|", d.getSuppliers());
                    writer.write(d.getCode() + "," + d.getName() + "," + supplierStr + "," +
                            d.getExpiryDate() + "," + d.getPrice() + "," + d.getStock() + "\n");
                }
            }
        } catch (IOException e) {
            System.out.println("Error writing drugs file: " + e.getMessage());
        }
    }


    // Load Suppliers from File
    public static List<Supplier> loadSuppliers(String filepath) {
        List<Supplier> suppliers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            String line = reader.readLine(); // Skip header
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String id = parts[0];
                String name = parts[1];
                String contact = parts[2];
                String location = parts[3];
                double turnaround = Double.parseDouble(parts[4]);
                List<String> drugsSupplied = Arrays.asList(parts[5].split("\\|"));

                Supplier supplier = new Supplier(id, name, contact, location, turnaround, drugsSupplied);
                suppliers.add(supplier);
            }
        } catch (IOException e) {
            System.out.println("Error reading suppliers file: " + e.getMessage());
        }
        return suppliers;
    }

    // Save Suppliers to File
    public static void saveSuppliers(String filepath, List<Supplier> suppliers) {
        try {
            File file = new File(filepath);
            file.getParentFile().mkdirs(); // ensure parent folders exist

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write("supplierID,name,contact,location,turnaround,drugs\n");
                for (Supplier s : suppliers) {
                    String drugStr = String.join("|", s.getDrugsSupplied());
                    writer.write(s.getSupplierID() + "," + s.getName() + "," + s.getContact() + "," +
                            s.getLocation() + "," + s.getDeliveryTurnaroundTime() + "," + drugStr + "\n");
                }
            }
        } catch (IOException e) {
            System.out.println("Error writing suppliers file: " + e.getMessage());
        }
    }

    public static List<Customer> loadCustomers(String filepath) {
        List<Customer> customers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            String line = reader.readLine(); // Skip header
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String id = parts[0];
                String name = parts[1];
                String phone = parts[2];
                String address = parts[3];

                customers.add(new Customer(id, name, phone, address));
            }
        } catch (IOException e) {
            System.out.println("Error reading customers file: " + e.getMessage());
        }
        return customers;
    }

    public static void saveCustomers(String filepath, List<Customer> customers) {
        try {
            File file = new File(filepath);
            file.getParentFile().mkdirs();

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write("customerID,name,phone,address\n");
                for (Customer c : customers) {
                    writer.write(c.getCustomerID() + "," + c.getName() + "," + c.getPhone() + "," + c.getAddress() + "\n");
                }
            }
        } catch (IOException e) {
            System.out.println("Error writing customers file: " + e.getMessage());
        }
    }

    public static List<Transaction> loadTransactions(String filepath) {
        List<Transaction> transactions = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            String line = reader.readLine(); // Skip header
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String txnID = parts[0];
                String customerID = parts[1];
                String drugCode = parts[2];
                int quantity = Integer.parseInt(parts[3]);
                double totalPrice = Double.parseDouble(parts[4]);
                String date = parts[5];

                transactions.add(new Transaction(txnID, customerID, drugCode, quantity, totalPrice, date));
            }
        } catch (IOException e) {
            System.out.println("Error reading transactions file: " + e.getMessage());
        }
        return transactions;
    }
    public static void saveTransactions(String filepath, List<Transaction> transactions) {
        try {
            File file = new File(filepath);
            file.getParentFile().mkdirs();

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write("transactionID,customerID,drugCode,quantity,totalPrice,date\n");
                for (Transaction t : transactions) {
                    writer.write(t.getTransactionID() + "," + t.getCustomerID() + "," +
                            t.getDrugCode() + "," + t.getQuantity() + "," +
                            t.getTotalPrice() + "," + t.getDate() + "\n");
                }
            }
        } catch (IOException e) {
            System.out.println("Error writing transactions file: " + e.getMessage());
        }
    }


}
