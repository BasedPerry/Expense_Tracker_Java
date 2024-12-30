package org.example;

import com.fasterxml.jackson.databind.ObjectMapper; // JSON serialization/deserialization
import com.fasterxml.jackson.core.type.TypeReference; // For generic type mapping
import java.io.File; // File handling
import java.io.IOException; // Handling I/O exceptions
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Main class for the Expense Tracker application.
 * Handles user interaction and manages expense records.
 */
public class ExpenseTracker {
    private static ArrayList<Expense> expenses = new ArrayList<>(); // List to store expenses

    // Constants for file paths
    public static final String EXPENSE_FILE_PATH = "expense-tracker/src/main/resources/expenses.json";
    public static final String CATEGORY_SUMMARY_PATH = "expense-tracker/src/main/resources/category-summary.json";

    /**
     * Loads expenses from a JSON file if it exists.
     */
    private static void loadExpenses() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            expenses = mapper.readValue(new File(EXPENSE_FILE_PATH), new TypeReference<>() {});
            System.out.println("Expenses loaded successfully.");
        } catch (IOException e) {
            System.out.println("No previous expenses found or error loading file: " + e.getMessage());
        }
    }

    /**
     * Loads the category summary from a JSON file.
     *
     * @return a map containing the category totals or an empty map if the file does not exist
     */
    private static Map<String, Double> loadCategorySummary() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(new File(CATEGORY_SUMMARY_PATH), new TypeReference<>() {});
        } catch (IOException e) {
            System.out.println("No previous category summary found or error loading file: " + e.getMessage());
            return new HashMap<>(); // Return an empty map if loading fails
        }
    }

    /**
     * Saves the list of expenses to a JSON file.
     * Ensures directories exist before saving and formats the output for readability.
     */
    private static void saveExpenses() {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File(EXPENSE_FILE_PATH);

        try {
            // Create directories if they don't exist
            if (file.getParentFile() != null && !file.getParentFile().exists() && !file.getParentFile().mkdirs()) {
                System.out.println("Warning: Failed to create necessary directories for the file.");
            }

            // Save the expenses in pretty-print format
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, expenses);
            System.out.println("Expenses saved successfully to: " + file.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Error saving expenses: " + e.getMessage());
        }
    }

    /**
     * Generates a category summary and saves it to a JSON file.
     * Creates directories if they don't exist and formats the output for readability.
     */
    private static void saveCategorySummary() {
        Map<String, Double> categoryTotals = generateCategorySummary();
        ObjectMapper mapper = new ObjectMapper();
        File file = new File(CATEGORY_SUMMARY_PATH);
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, categoryTotals);
            System.out.println("Category Summary saved successfully to: " + file.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Error saving category summary: " + e.getMessage());
        }
    }

    /**
     * Displays a category summary.
     *
     * @param categorySummary the map containing category totals to display
     */
    private static void displayCategorySummary(Map<String, Double> categorySummary) {
        if (categorySummary.isEmpty()) {
            System.out.println("No category summary to display.");
            return;
        }
        System.out.println("Category Summary:");
        categorySummary.forEach((key, value) -> System.out.println(key + ": $" + value));
    }

    /**
     * Main method to start the Expense Tracker application.
     * Loads saved data, provides menu options, and manages user input.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Loading existing expenses...");
        loadExpenses();

        boolean running = true;
        while (running) {
            displayMenu();

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a numeric value.");
                continue;
            }

            switch (choice) {
                case 1 -> addExpense(scanner);
                case 2 -> viewExpenses();
                case 3 -> totalExpense();
                case 4 -> filterByCategory(scanner);
                case 5 -> monthlySummary();
                case 6 -> clearEntries();
                case 7 -> {
                    System.out.println("Saving expenses...");
                    saveExpenses();
                    System.out.println("Goodbye!");
                    running = false;
                }
                case 8 -> saveCategorySummary();
                case 9 -> {
                    Map<String, Double> categorySummary = loadCategorySummary();
                    displayCategorySummary(categorySummary);
                }
                case 10 -> {
                    Map<String, Double> categorySummary = loadCategorySummary();
                    if (categorySummary.isEmpty()) {
                        System.out.println("No saved category summary found. Generating a new one.");
                        categorySummary = generateCategorySummary();
                    }
                    displayCategorySummary(categorySummary);
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
        scanner.close();
    }

    /**
     * Displays the main menu options to the user.
     */
    private static void displayMenu() {
        System.out.println("\nExpense Tracker Menu:");
        System.out.println("1. Add Expense");
        System.out.println("2. View Expenses");
        System.out.println("3. Total Expenses");
        System.out.println("4. Filter by Category");
        System.out.println("5. Monthly Summary");
        System.out.println("6. Clear All Entries");
        System.out.println("7. Exit");
        System.out.println("8. Save Category Summary");
        System.out.println("9. Load Category Summary");
        System.out.println("10. Display Category Summary");
        System.out.print("Choose an option: ");
    }

    /**
     * Adds a new expense to the list by prompting the user for details.
     *
     * @param scanner the Scanner object for user input
     */
    private static void addExpense(Scanner scanner) {
        System.out.print("Enter date (dd/mm/yyyy): ");
        String date = scanner.nextLine();
        System.out.print("Enter description: ");
        String description = scanner.nextLine();
        System.out.print("Enter category: ");
        String category = scanner.nextLine();
        System.out.print("Enter amount: ");
        double amount;
        try {
            amount = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid numeric amount.");
            return;
        }

        expenses.add(new Expense(date, description, category, amount));
        System.out.println("Expense added successfully.");
    }

    /**
     * Displays all recorded expenses.
     */
    private static void viewExpenses() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses to show.");
            return;
        }
        System.out.println("Expenses:");
        for (Expense expense : expenses) {
            System.out.println(expense.getDate() + " - " + expense.getDescription() + " - " + expense.getCategory() + " - $" + expense.getAmount());
        }
    }

    /**
     * Calculates and displays the total amount of all recorded expenses.
     */
    private static void totalExpense() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses to calculate.");
            return;
        }
        double total = expenses.stream().mapToDouble(Expense::getAmount).sum();
        System.out.println("Total expense: $" + total);
    }

    /**
     * Filters expenses by category and displays matching entries.
     *
     * @param scanner the Scanner object for user input
     */
    private static void filterByCategory(Scanner scanner) {
        System.out.print("Enter category: ");
        String category = scanner.nextLine();
        int count = 0;

        System.out.println("Expenses in " + category + ":");
        for (Expense expense : expenses) {
            if (expense.getCategory().equalsIgnoreCase(category)) {
                System.out.println(expense.getDate() + " - " + expense.getDescription() + " - " + expense.getCategory() + " - $" + expense.getAmount());
                count++;
            }
        }
        if (count == 0) {
            System.out.println("No expenses found for the given category.");
        }
    }

    /**
     * Generates a summary of expenses grouped by category.
     *
     * @return a map where the keys are categories and the values are the total amounts spent
     */
    private static Map<String, Double> generateCategorySummary() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses available for summary.");
            return new HashMap<>(); // Return an empty map if no expenses exist
        }

        Map<String, Double> categoryTotals = new HashMap<>();
        for (Expense expense : expenses) {
            String category = expense.getCategory();
            categoryTotals.put(category, categoryTotals.getOrDefault(category, 0.0) + expense.getAmount());
        }

        return categoryTotals; // Return the map for external use
    }

    /**
     * Generates and displays a summary of expenses grouped by month.
     */
    private static void monthlySummary() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses available for summary.");
            return;
        }
        Map<String, Double> monthlyTotals = new HashMap<>();
        for (Expense expense : expenses) {
            String month = expense.getDate().substring(3, 10);
            monthlyTotals.put(month, monthlyTotals.getOrDefault(month, 0.0) + expense.getAmount());
        }
        System.out.println("Monthly Summary:");
        monthlyTotals.forEach((key, value) -> System.out.println(key + ": $" + value));
    }

    /**
     * Clears all expenses from the list.
     */
    private static void clearEntries() {
        expenses.clear();
        System.out.println("All entries have been cleared.");
    }
}
