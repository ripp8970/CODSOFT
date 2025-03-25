import java.util.Scanner;
import java.util.concurrent.*;

class QuizQuestion {
    private String question;
    private String[] options;
    private int correctAnswer;

    public QuizQuestion(String question, String[] options, int correctAnswer) {
        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public String[] getOptions() {
        return options;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }
}

class QuizApplication {
    private QuizQuestion[] questions;
    private int score;
    private Scanner scanner;

    public QuizApplication(QuizQuestion[] questions) {
        this.questions = questions;
        this.score = 0;
        this.scanner = new Scanner(System.in);
    }

    // Method to display a question and handle user input
    private boolean askQuestion(QuizQuestion question, int timeLimit) {
        System.out.println("\n" + question.getQuestion());
        String[] options = question.getOptions();
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ". " + options[i]);
        }

        System.out.println("You have " + timeLimit + " seconds to answer.");

        // Timer implementation
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Integer> future = executor.submit(() -> {
            System.out.print("Enter your answer (1-" + options.length + "): ");
            return scanner.nextInt();
        });

        try {
            int userAnswer = future.get(timeLimit, TimeUnit.SECONDS);
            if (userAnswer == question.getCorrectAnswer()) {
                System.out.println("Correct!");
                return true;
            } else {
                System.out.println("Incorrect! The correct answer was " + question.getCorrectAnswer() + ".");
                return false;
            }
        } catch (TimeoutException e) {
            System.out.println("\nTime's up! The correct answer was " + question.getCorrectAnswer() + ".");
            future.cancel(true); // Stop the input thread
            return false;
        } catch (Exception e) {
            System.out.println("An error occurred.");
            return false;
        } finally {
            executor.shutdown();
        }
    }

    // Method to run the quiz
    public void runQuiz() {
        System.out.println("Welcome to the Quiz Application!");
        System.out.println("You will have 10 seconds to answer each question. Good luck!\n");

        for (int i = 0; i < questions.length; i++) {
            System.out.println("Question " + (i + 1) + ":");
            if (askQuestion(questions[i], 10)) {
                score++;
            }
        }

        displayResult();
    }

    // Method to display the final result
    private void displayResult() {
        System.out.println("\n--- Quiz Results ---");
        System.out.println("Total Questions: " + questions.length);
        System.out.println("Correct Answers: " + score);
        System.out.println("Incorrect Answers: " + (questions.length - score));
        System.out.println("Final Score: " + score + "/" + questions.length);
    }
}

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