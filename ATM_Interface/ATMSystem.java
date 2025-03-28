import java.util.Scanner;

// Class to represent the user's bank account
class BankAccount {
    private double balance; // Private variable to store the account balance

    // Constructor to initialize the BankAccount with an initial balance
    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    // Method to deposit money into the account
    public void deposit(double amount) {
        // Check if the deposit amount is valid (positive)
        if (amount > 0) {
            balance += amount; // Add the deposit amount to the current balance
            System.out.println("Deposit successful. New balance: $" + balance); // Display success message and new balance
        } else {
            System.out.println("Invalid deposit amount. Please enter a positive value."); // Display error message for invalid input
        }
    }

    // Method to withdraw money from the account
    public void withdraw(double amount) {
        // Check if the withdrawal amount is valid (positive and less than or equal to the balance)
        if (amount > 0 && amount <= balance) {
            balance -= amount; // Subtract the withdrawal amount from the current balance
            System.out.println("Withdrawal successful. New balance: $" + balance); // Display success message and new balance
        } else if (amount > balance) {
            System.out.println("Insufficient funds. Your current balance is $" + balance); // Display error message for insufficient funds
        } else {
            System.out.println("Invalid withdrawal amount. Please enter a positive value."); // Display error message for invalid input
        }
    }

    // Method to check the current balance of the account
    public void checkBalance() {
        System.out.println("Your current balance is $" + balance); // Display the current balance
    }
}

// Class to represent the ATM machine
class ATM {
    private BankAccount account; // Private variable to store the BankAccount associated with the ATM

    // Constructor to initialize the ATM with a BankAccount
    public ATM(BankAccount account) {
        this.account = account;
    }

    // Method to display the ATM menu to the user
    public void displayMenu() {
        System.out.println("\nWelcome to the ATM!");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Exit");
    }

    // Method to handle user input and perform the selected ATM actions
    public void run() {
        Scanner scanner = new Scanner(System.in); // Create a Scanner object to read user input
        boolean isRunning = true; // Variable to control the ATM loop

        // Loop to keep the ATM running until the user chooses to exit
        while (isRunning) {
            displayMenu(); // Display the ATM menu
            System.out.print("Choose an option (1-4): "); // Prompt the user to choose an option
            int choice = scanner.nextInt(); // Read the user's choice

            // Switch statement to handle the user's choice
            switch (choice) {
                case 1:
                    account.checkBalance(); // Call the checkBalance method of the BankAccount
                    break;
                case 2:
                    System.out.print("Enter deposit amount: $"); // Prompt the user to enter the deposit amount
                    double depositAmount = scanner.nextDouble(); // Read the deposit amount
                    account.deposit(depositAmount); // Call the deposit method of the BankAccount
                    break;
                case 3:
                    System.out.print("Enter withdrawal amount: $"); // Prompt the user to enter the withdrawal amount
                    double withdrawAmount = scanner.nextDouble(); // Read the withdrawal amount
                    account.withdraw(withdrawAmount); // Call the withdraw method of the BankAccount
                    break;
                case 4:
                    System.out.println("Thank you for using the ATM. Goodbye!"); // Display exit message
                    isRunning = false; // Set isRunning to false to exit the loop
                    break;
                default:
                    System.out.println("Invalid option. Please choose a valid option (1-4)."); // Display error message for invalid input
            }
        }
        scanner.close(); // Close the Scanner object to prevent resource leaks
    }
}

// Main class to run the ATM system
public class ATMSystem {
    public static void main(String[] args) {
        // Create a BankAccount object with an initial balance of $1000.0
        BankAccount userAccount = new BankAccount(1000.0);

        // Create an ATM object and associate it with the user's BankAccount
        ATM atm = new ATM(userAccount);

        // Run the ATM interface
        atm.run();
    }
}