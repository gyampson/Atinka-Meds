package storage;

import models.Drug;
import models.Supplier;

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
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filepath))) {
            writer.write("code,name,suppliers,expiry_date,price,stock\n");
            for (Drug d : drugs) {
                String supplierStr = String.join("|", d.getSuppliers());
                writer.write(d.getCode() + "," + d.getName() + "," + supplierStr + "," +
                        d.getExpiryDate() + "," + d.getPrice() + "," + d.getStock() + "\n");
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
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filepath))) {
            writer.write("supplierID,name,contact,location,turnaround,drugs\n");
            for (Supplier s : suppliers) {
                String drugStr = String.join("|", s.getDrugsSupplied());
                writer.write(s.getSupplierID() + "," + s.getName() + "," + s.getContact() + "," +
                        s.getLocation() + "," + s.getDeliveryTurnaroundTime() + "," + drugStr + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error writing suppliers file: " + e.getMessage());
        }
    }
}
