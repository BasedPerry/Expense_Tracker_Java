# Expense Tracker

Expense Tracker is a console-based Java application designed to help users manage their personal expenses. This project serves as a learning tool to practice software development concepts, such as object-oriented programming, file handling, and JSON integration.

---

## Features

- **Add Expenses**: Record a new expense with details like date, description, category, and amount.
- **View Expenses**: Display all recorded expenses in a list format.
- **Total Expenses**: Calculate and display the total of all recorded expenses.
- **Filter by Category**: View expenses filtered by specific categories.
- **Monthly Summary**: Generate a breakdown of expenses by month.
- **Category Summary**: 
  - Summarize expenses grouped by category.
  - Save summaries to `category-summary.json`.
  - Load summaries from existing files.
  - Display saved or newly generated category summaries dynamically.
- **Clear All Entries**: Remove all stored expenses.
- **Save/Load Data**: Persist expense records and category summaries using JSON for seamless session management.

---

## Installation

1. **Clone the repository**:
   ```bash
   git clone https://github.com/basedperry/Expense_Tracker_Java
   ```

2. **Navigate to the project directory**:
   ```bash
   cd ExpenseTracker
   ```

3. **Open the project** in your favorite Java IDE (e.g., IntelliJ IDEA, Eclipse).

4. **Build and run** the application.

---

## Usage

1. Launch the application.
2. Follow the on-screen menu to perform various operations:
   - Add expenses
   - View or filter expenses
   - Generate monthly or category summaries
   - Save or load data
3. Save changes when exiting to ensure all expenses and summaries persist.

---

## Changelog

### **v1.1.0 - Category Summary and Enhancements**
- **Category Summary Functionality**:
  - Added the ability to dynamically generate category summaries.
  - Save summaries to `category-summary.json` for persistent storage.
  - Load summaries from existing files.
  - Display saved or newly generated summaries.
- **Refactored Code**:
  - Introduced constants for file paths (`EXPENSE_FILE_PATH`, `CATEGORY_SUMMARY_PATH`) for maintainability.
- **Error Handling Improvements**:
  - Enhanced error messages and streamlined file operations.
- **Comprehensive Documentation**:
  - Updated method-level documentation for clarity and consistency.

### **v1.0.0 - Initial Release**
- Core features:
  - Add expenses with details like date, description, category, and amount.
  - View all recorded expenses.
  - Calculate the total of all expenses.
  - Filter expenses by specific categories.
  - Generate a monthly summary of expenses.
  - Clear all recorded entries.
  - Persistent storage for expense records using JSON files.

---

## Upcoming Features

This project is under active development as a learning experience. Future plans include:

- Enhanced input validation for edge cases.
- Integration with databases like SQLite or MySQL.
- Graphical User Interface (GUI) using JavaFX or Swing.
- Web-based interface for cross-platform accessibility.
- Printable expense and summary reports.

---

## Contributions

Contributions are welcome! Feel free to fork the repository and submit pull requests.

### Steps to Contribute:

1. **Fork the project**.
2. Create a new branch:
   ```bash
   git checkout -b feature/YourFeatureName
   ```
3. Make your changes and commit:
   ```bash
   git commit -m "Add a feature"
   ```
4. Push to the branch:
   ```bash
   git push origin feature/YourFeatureName
   ```
5. Open a pull request.

---

## License

This project is licensed under the MIT License. See the `LICENSE` file for details.

---

## Acknowledgements

- **Java**: For being a versatile and powerful programming language.
- **Jackson Library**: For enabling seamless JSON serialization and deserialization.

---

## Author

**Brandon Perry**
