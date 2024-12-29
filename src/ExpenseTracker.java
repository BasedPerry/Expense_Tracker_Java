// Provides a resizable array implementation, which is part of the Java Collections Framework
import java.util.ArrayList;
// Provides a data structure to store key-value pairs, part of the Java Collections Framework.
import java.util.HashMap;
// Defines the interface for maps (key-value pairs).
import java.util.Map;
// Provides methods to read input from the user or a file.
import java.util.Scanner;

// Class representing an individual expense with date, description, category, and amount
class Expense {
    String date;
    String description;
    String category;
    double amount;

    // Constructor to initialize the expense attributes
    Expense(String date, String description, String category, double amount) {
        this.date = date;
        this.description = description;
        this.category = category;
        this.amount = amount;
    }
}

// Main class for the Expense Tracker application
public class ExpenseTracker {
    private static ArrayList<Expense> expenses = new ArrayList<>(); // List to store expenses

    // Main method to drive the application
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Scanner for user input
        boolean running = true; // Flag to control the application loop

        // Main application loop
        while (running) {
            // Display menu options
            System.out.println("1. Add Expense");
            System.out.println("2. View Expenses");
            System.out.println("3. Total Expenses");
            System.out.println("4. Filter by Category");
            System.out.println("5. Monthly Summary");
            System.out.println("6. Clear All Entries");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");

            // Read user choice and handle input validation
            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a numeric value.");
                continue; // Continue instead of return to keep the loop running
            }

            // Execute actions based on user choice
            switch (choice) {
                case 1:
                    addExpense(scanner);
                    break;
                case 2:
                    viewExpenses();
                    break;
                case 3:
                    totalExpense();
                    break;
                case 4:
                    filterByCategory(scanner);
                    break;
                case 5:
                    monthlySummary();
                    break;
                case 6:
                    clearEntries();
                    break;
                case 7:
                    running = false; // Exit the application loop
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
        scanner.close(); // Close the scanner to avoid resource leaks
    }

    // Method to add a new expense
    private static void addExpense(Scanner scanner) {
        System.out.print("Enter date (dd/mm/yyyy): ");
        String date = scanner.nextLine(); // Read date input
        System.out.print("Enter description: ");
        String description = scanner.nextLine(); // Read description input
        System.out.print("Enter category: ");
        String category = scanner.nextLine(); // Read category input
        System.out.print("Enter amount: ");
        double amount;
        try {
            amount = Double.parseDouble(scanner.nextLine()); // Read and validate amount input
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid numeric amount.");
            return; // Exit the method if input is invalid
        }
        // Create and add the new expense to the list
        Expense expense = new Expense(date, description, category, amount);
        expenses.add(expense);
        System.out.println("Expense added successfully.");
    }

    // Method to view all expenses
    private static void viewExpenses() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses to show."); // Notify if there are no expenses
            return;
        }
        System.out.println("Expenses:");
        // Print each expense in the list
        for (Expense expense : expenses) {
            System.out.println(expense.date + " - " + expense.description + " - " + expense.category + " - " + expense.amount);
        }
    }

    // Method to calculate and display the total expense amount
    private static void totalExpense() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses to calculate."); // Notify if there are no expenses
            return;
        }
        double total = 0;
        // Sum up the amount of each expense
        for (Expense expense : expenses) {
            total += expense.amount;
        }
        System.out.println("Total expense: $" + total); // Display the total amount
    }

    // Method to filter and display expenses by category
    private static void filterByCategory(Scanner scanner) {
        System.out.print("Enter category: ");
        String category = scanner.nextLine(); // Read category input
        int count = 0;
        System.out.println("Expenses in " + category + ":");
        // Print expenses that match the given category
        for (Expense expense : expenses) {
            if (expense.category.equalsIgnoreCase(category)) {
                System.out.println(expense.date + " - " + expense.description + " - " + expense.category + " - " + expense.amount);
                count++;
            }
        }
        if (count == 0) {
            System.out.println("No expenses found for the given category."); // Notify if no matching expenses are found
        }
    }

    // Method to generate and display a monthly summary of expenses
    private static void monthlySummary() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses available for summary."); // Notify if there are no expenses
            return;
        }
        Map<String, Double> monthlyTotals = new HashMap<>(); // Map to store monthly totals
        // Calculate monthly totals for each expense
        for (Expense expense : expenses) {
            String month = expense.date.substring(3, 10); // Extract month and year (mm/yyyy)
            monthlyTotals.put(month, monthlyTotals.getOrDefault(month, 0.0) + expense.amount);
        }
        System.out.println("Monthly Summary:");
        // Print the monthly totals
        for (Map.Entry<String, Double> entry : monthlyTotals.entrySet()) {
            System.out.println(entry.getKey() + ": $" + entry.getValue());
        }
    }

    // Method to clear all entries from the expense list
    private static void clearEntries() {
        expenses.clear(); // Clear the expense list
        System.out.println("All entries have been cleared."); // Notify the user
    }
}

