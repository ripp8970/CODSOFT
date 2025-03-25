import java.util.Scanner;

// Class to represent the user's bank account
class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    // Method to deposit money
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposit successful. New balance: $" + balance);
        } else {
            System.out.println("Invalid deposit amount. Please enter a positive value.");
        }
    }

    // Method to withdraw money
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawal successful. New balance: $" + balance);
        } else if (amount > balance) {
            System.out.println("Insufficient funds. Your current balance is $" + balance);
        } else {
            System.out.println("Invalid withdrawal amount. Please enter a positive value.");
        }
    }

    // Method to check balance
    public void checkBalance() {
        System.out.println("Your current balance is $" + balance);
    }
}

// Class to represent the ATM machine
class ATM {
    private BankAccount account;

    public ATM(BankAccount account) {
        this.account = account;
    }

    // Method to display the ATM menu
    public void displayMenu() {
        System.out.println("\nWelcome to the ATM!");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Exit");
    }

    // Method to handle user input and perform actions
    public void run() {
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
            displayMenu();
            System.out.print("Choose an option (1-4): ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    account.checkBalance();
                    break;
                case 2:
                    System.out.print("Enter deposit amount: $");
                    double depositAmount = scanner.nextDouble();
                    account.deposit(depositAmount);
                    break;
                case 3:
                    System.out.print("Enter withdrawal amount: $");
                    double withdrawAmount = scanner.nextDouble();
                    account.withdraw(withdrawAmount);
                    break;
                case 4:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    isRunning = false;
                    break;
                default:
                    System.out.println("Invalid option. Please choose a valid option (1-4).");
            }
        }
        scanner.close();
    }
}

// Main class to run the program
public class ATMSystem {
    public static void main(String[] args) {
        // Create a bank account with an initial balance
        BankAccount userAccount = new BankAccount(1000.0);

        // Create an ATM and connect it to the user's bank account
        ATM atm = new ATM(userAccount);

        // Run the ATM interface
        atm.run();
    }
}