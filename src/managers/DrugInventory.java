package managers;

import models.Drug;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DrugInventory {

    // ANSI Color Constants
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_BOLD = "\u001B[1m";

    private List<Drug> drugs;
    private Scanner scanner = new Scanner(System.in);

    public DrugInventory() {
        this.drugs = new ArrayList<>();
    }

    // ✅ Silent load for startup
    public void loadDrug(Drug drug) {
        drugs.add(drug);
    }

    // ✅ User-driven add with duplicate check + message
    public void addDrug(Drug drug) {
        for (Drug d : drugs) {
            if (d.getCode().equalsIgnoreCase(drug.getCode())) {
                printError("A drug with the code '" + drug.getCode() + "' already exists.");
                return;
            }
        }
        drugs.add(drug);
        printSuccess("Drug '" + drug.getName() + "' (Drug Code: " + drug.getCode() + ") added.");
    }

    // ✅ Remove Drug with feedback
    public boolean removeDrug(String code) {
        Drug toRemove = null;
        for (Drug d : drugs) {
            if (d.getCode().equalsIgnoreCase(code)) {
                toRemove = d;
                break;
            }
        }

        if (toRemove != null) {
            drugs.remove(toRemove);
            printSuccess("Drug '" + toRemove.getName() + "' (Code: " + toRemove.getCode() + ") removed.");
            return true;
        } else {
            printError("No drug found with code '" + code + "'.");
            return false;
        }
    }

    // ✅ List Drugs with header
    public void listDrugs() {
        if (drugs.isEmpty()) {
            System.out.println(ANSI_YELLOW + ANSI_BOLD + "⚠️ No drugs in inventory." + ANSI_RESET);
            return;
        }

        System.out.println(ANSI_BLUE + ANSI_BOLD + "\n=== 📦 Drug Inventory ===" + ANSI_RESET);
        for (Drug d : drugs) {
            System.out.println(d);
        }
        waitForEnter();
    }

    public Drug findByCode(String code) {
        for (Drug d : drugs) {
            if (d.getCode().equalsIgnoreCase(code)) {
                return d;
            }
        }
        return null;
    }

    public List<Drug> getAllDrugs() {
        return drugs;


    }

    // ✅ Success & Error message helpers
    private void printSuccess(String message) {
        System.out.println();
        System.out.println(ANSI_GREEN + ANSI_BOLD + "[✔️ SUCCESS] " + message + ANSI_RESET);
        System.out.println(ANSI_BLUE + "(Press Enter to continue...)" + ANSI_RESET);
        scanner.nextLine();
    }

    private void printError(String message) {
        System.out.println();
        System.out.println(ANSI_RED + ANSI_BOLD + "[❌ ERROR] " + message + ANSI_RESET);
    }


    private void waitForEnter() {
        System.out.println();
        System.out.println(ANSI_BLUE + "(Press Enter to continue...)" + ANSI_RESET);
        scanner.nextLine();
    }

}
