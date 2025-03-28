import java.util.Random;
import java.util.Scanner;

public class NumberGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Create a Scanner object to read user input
        Random random = new Random(); // Create a Random object to generate random numbers
        boolean playAgain; // Variable to control whether the user wants to play again

        do {
            // Step 1: Generate a random number between 1 and 100
            int generatedNumber = random.nextInt(100) + 1; // Generate a random number between 1 and 100 (inclusive)
            int attempts = 0; // Initialize the number of attempts to 0
            int maxAttempts = 10; // Limit the number of attempts to 10
            boolean guessedCorrectly = false; // Flag to track if the user guessed correctly

            System.out.println("Welcome to the Number Guessing Game!");
            System.out.println("I have generated a random number between 1 and 100.");
            System.out.println("You have " + maxAttempts + " attempts to guess it.");

            // Step 2 & 3: Prompt the user for their guess and provide feedback
            while (attempts < maxAttempts && !guessedCorrectly) {
                System.out.print("Enter your guess: "); // Prompt the user to enter their guess
                int userGuess = scanner.nextInt(); // Read the user's guess from the input
                attempts++; // Increment the number of attempts

                if (userGuess < generatedNumber) {
                    System.out.println("Your guess is too low. Try again!"); // Provide feedback if the guess is too low
                } else if (userGuess > generatedNumber) {
                    System.out.println("Your guess is too high. Try again!"); // Provide feedback if the guess is too high
                } else {
                    guessedCorrectly = true; // Set the flag to true if the guess is correct
                    System.out.println("Congratulations! You've guessed the correct number: " + generatedNumber); // Congratulate the user
                }

                // Provide feedback on remaining attempts
                if (!guessedCorrectly) {
                    int remainingAttempts = maxAttempts - attempts; // Calculate the remaining attempts
                    if (remainingAttempts > 0) {
                        System.out.println("You have " + remainingAttempts + " attempts left."); // Display the remaining attempts
                    } else {
                        System.out.println("Sorry, you've run out of attempts! The correct number was " + generatedNumber); // Display the correct number if attempts are exhausted
                    }
                }
            }

            // Step 6: Ask the user if they want to play again
            System.out.print("Do you want to play another round? (yes/no): "); // Prompt the user to play again
            String response = scanner.next(); // Read the user's response
            playAgain = response.equalsIgnoreCase("yes"); // Check if the user wants to play again (case-insensitive)

        } while (playAgain); // Repeat the game loop if the user wants to play again

        System.out.println("Thank you for playing the Number Guessing Game! Goodbye!"); // Display a goodbye message
        scanner.close(); // Close the Scanner object to prevent resource leaks
    }
}