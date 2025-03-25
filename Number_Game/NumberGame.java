import java.util.Random;
import java.util.Scanner;

public class NumberGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        boolean playAgain;

        do {
            // Step 1: Generate a random number between 1 and 100
            int generatedNumber = random.nextInt(100) + 1;
            int attempts = 0;
            int maxAttempts = 10; // Limit the number of attempts
            boolean guessedCorrectly = false;

            System.out.println("Welcome to the Number Guessing Game!");
            System.out.println("I have generated a random number between 1 and 100.");
            System.out.println("You have " + maxAttempts + " attempts to guess it.");

            // Step 2 & 3: Prompt the user for their guess and provide feedback
            while (attempts < maxAttempts && !guessedCorrectly) {
                System.out.print("Enter your guess: ");
                int userGuess = scanner.nextInt();
                attempts++;

                if (userGuess < generatedNumber) {
                    System.out.println("Your guess is too low. Try again!");
                } else if (userGuess > generatedNumber) {
                    System.out.println("Your guess is too high. Try again!");
                } else {
                    guessedCorrectly = true;
                    System.out.println("Congratulations! You've guessed the correct number: " + generatedNumber);
                }

                // Provide feedback on remaining attempts
                if (!guessedCorrectly) {
                    int remainingAttempts = maxAttempts - attempts;
                    if (remainingAttempts > 0) {
                        System.out.println("You have " + remainingAttempts + " attempts left.");
                    } else {
                        System.out.println("Sorry, you've run out of attempts! The correct number was " + generatedNumber);
                    }
                }
            }

            // Step 6: Ask the user if they want to play again
            System.out.print("Do you want to play another round? (yes/no): ");
            String response = scanner.next();
            playAgain = response.equalsIgnoreCase("yes");

        } while (playAgain); // Repeat for multiple rounds

        System.out.println("Thank you for playing the Number Guessing Game! Goodbye!");
        scanner.close();
    }
}
