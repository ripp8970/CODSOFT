import java.util.Scanner;
import java.util.concurrent.*;

// Class to represent a quiz question
class QuizQuestion {
    private String question; // The question text
    private String[] options; // The multiple-choice options
    private int correctAnswer; // The index of the correct answer (1-based)

    // Constructor to initialize a QuizQuestion object
    public QuizQuestion(String question, String[] options, int correctAnswer) {
        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    // Getter method to retrieve the question text
    public String getQuestion() {
        return question;
    }

    // Getter method to retrieve the options array
    public String[] getOptions() {
        return options;
    }

    // Getter method to retrieve the correct answer index
    public int getCorrectAnswer() {
        return correctAnswer;
    }
}

// Class to manage the quiz application logic
class QuizApplication {
    private QuizQuestion[] questions; // Array to store the quiz questions
    private int score; // The user's score
    private Scanner scanner; // Scanner object to read user input

    // Constructor to initialize the QuizApplication object
    public QuizApplication(QuizQuestion[] questions) {
        this.questions = questions;
        this.score = 0;
        this.scanner = new Scanner(System.in);
    }

    // Method to display a question and handle user input with a time limit
    private boolean askQuestion(QuizQuestion question, int timeLimit) {
        System.out.println("\n" + question.getQuestion()); // Display the question
        String[] options = question.getOptions(); // Get the options for the question
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ". " + options[i]); // Display the options with numbers
        }

        System.out.println("You have " + timeLimit + " seconds to answer."); // Inform user of the time limit

        // Timer implementation using ExecutorService and Future
        ExecutorService executor = Executors.newSingleThreadExecutor(); // Create a single-threaded executor
        Future<Integer> future = executor.submit(() -> { // Submit a task to read user input
            System.out.print("Enter your answer (1-" + options.length + "): "); // Prompt user for input
            return scanner.nextInt(); // Read and return the user's answer
        });

        try {
            int userAnswer = future.get(timeLimit, TimeUnit.SECONDS); // Get the user's answer with a timeout
            if (userAnswer == question.getCorrectAnswer()) { // Check if the answer is correct
                System.out.println("Correct!"); // Display correct message
                return true; // Return true for correct answer
            } else {
                System.out.println("Incorrect! The correct answer was " + question.getCorrectAnswer() + "."); // Display incorrect message
                return false; // Return false for incorrect answer
            }
        } catch (TimeoutException e) { // Handle timeout if user doesn't answer in time
            System.out.println("\nTime's up! The correct answer was " + question.getCorrectAnswer() + "."); // Display timeout message
            future.cancel(true); // Cancel the input thread
            return false; // Return false for timeout
        } catch (Exception e) { // Handle other exceptions
            System.out.println("An error occurred."); // Display error message
            return false; // Return false for error
        } finally {
            executor.shutdown(); // Shutdown the executor
        }
    }

    // Method to run the quiz
    public void runQuiz() {
        System.out.println("Welcome to the Quiz Application!");
        System.out.println("You will have 10 seconds to answer each question. Good luck!\n");

        for (int i = 0; i < questions.length; i++) { // Loop through the questions
            System.out.println("Question " + (i + 1) + ":"); // Display question number
            if (askQuestion(questions[i], 10)) { // Ask the question and check if the answer is correct
                score++; // Increment score if correct
            }
        }

        displayResult(); // Display the final results
    }

    // Method to display the final quiz results
    private void displayResult() {
        System.out.println("\n--- Quiz Results ---");
        System.out.println("Total Questions: " + questions.length);
        System.out.println("Correct Answers: " + score);
        System.out.println("Incorrect Answers: " + (questions.length - score));
        System.out.println("Final Score: " + score + "/" + questions.length);
    }
}

// Main class to run the quiz application
public class QuizApp {
    public static void main(String[] args) {
        // Define quiz questions
        QuizQuestion[] questions = {
                new QuizQuestion("What is the capital of France?",
                        new String[]{"Paris", "London", "Berlin", "Madrid"}, 1),
                new QuizQuestion("Which planet is known as the Red Planet?",
                        new String[]{"Earth", "Mars", "Jupiter", "Saturn"}, 2),
                new QuizQuestion("What is 2 + 2?",
                        new String[]{"3", "4", "5", "6"}, 2),
                new QuizQuestion("Who wrote 'Romeo and Juliet'?",
                        new String[]{"Charles Dickens", "William Shakespeare", "Mark Twain", "Jane Austen"}, 2),
                new QuizQuestion("What is the largest mammal?",
                        new String[]{"Elephant", "Blue Whale", "Giraffe", "Shark"}, 2)
        };

        // Create and run the quiz application
        QuizApplication quizApp = new QuizApplication(questions);
        quizApp.runQuiz();
    }
}