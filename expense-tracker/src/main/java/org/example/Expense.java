package org.example;

/**
 * Represents an individual expense with details such as date, description, category, and amount.
 * This class supports JSON serialization/deserialization and provides methods for accessing
 * and modifying expense details.
 */
public class Expense {
    // Fields to store expense details
    private String date;         // The date of the expense in dd/mm/yyyy format
    private String description;  // A brief description of the expense
    private String category;     // The category to which the expense belongs (e.g., Food, Utilities)
    private double amount;       // The monetary amount of the expense

    /**
     * Default no-argument constructor.
     * Required for JSON deserialization when creating an instance of the Expense class from a JSON object.
     */
    public Expense() {}

    /**
     * Parameterized constructor to initialize all fields of the Expense class.
     *
     * @param date        the date of the expense (in dd/mm/yyyy format)
     * @param description a brief description of the expense
     * @param category    the category to which the expense belongs (e.g., Food, Utilities)
     * @param amount      the monetary amount of the expense
     */
    public Expense(String date, String description, String category, double amount) {
        this.date = date;
        this.description = description;
        this.category = category;
        this.amount = amount;
    }

    /**
     * Retrieves the date of the expense.
     *
     * @return the date of the expense in dd/mm/yyyy format
     */
    public String getDate() {
        return date;
    }

    /**
     * Updates the date of the expense.
     *
     * @param date the new date of the expense (in dd/mm/yyyy format)
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Retrieves the description of the expense.
     *
     * @return a brief description of the expense
     */
    public String getDescription() {
        return description;
    }

    /**
     * Updates the description of the expense.
     *
     * @param description the new description of the expense
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Retrieves the category of the expense.
     *
     * @return the category to which the expense belongs (e.g., Food, Utilities)
     */
    public String getCategory() {
        return category;
    }

    /**
     * Updates the category of the expense.
     *
     * @param category the new category of the expense (e.g., Food, Utilities)
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Retrieves the monetary amount of the expense.
     *
     * @return the monetary amount of the expense
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Updates the monetary amount of the expense.
     *
     * @param amount the new monetary amount of the expense
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * Generates a formatted string representation of the expense.
     * Useful for displaying expense details in a readable format.
     *
     * @return a string containing the expense's date, description, category, and amount
     */
    @Override
    public String toString() {
        return String.format("%s - %s - %s - $%.2f", date, description, category, amount);
    }
}

