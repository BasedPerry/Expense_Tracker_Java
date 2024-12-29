// Class representing an individual expense with date, description, category, and amount
public class Expense {
    // Fields to store expense details
    private String date;         // Date of the expense
    private String description;  // Description of the expense
    private String category;     // Category of the expense
    private double amount;       // Amount spent

    // No-argument constructor (required for JSON deserialization)
    public Expense() {}

    // Constructor to initialize the expense attributes
    public Expense(String date, String description, String category, double amount) {
        this.date = date;
        this.description = description;
        this.category = category;
        this.amount = amount;
    }

    // Getters and Setters
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
