import java.util.Scanner;

public class StudentGradeCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Initialize a Scanner object to read user input

        // Step 1: Input the number of subjects
        System.out.print("Enter the number of subjects: ");
        int numberOfSubjects = scanner.nextInt(); // Read the number of subjects from the user

        // Array to hold marks for each subject
        int[] marks = new int[numberOfSubjects]; // Create an array to store marks for each subject

        // Step 2: Input marks obtained in each subject
        for (int i = 0; i < numberOfSubjects; i++) {
            System.out.print("Enter marks obtained in subject " + (i + 1) + " (out of 100): ");
            marks[i] = scanner.nextInt(); // Read marks for each subject from the user
        }

        // Step 3: Calculate total marks
        int totalMarks = calculateTotalMarks(marks); // Call the method to calculate total marks

        // Step 4: Calculate average percentage
        double averagePercentage = calculateAveragePercentage(totalMarks, numberOfSubjects); // Call the method to calculate average percentage

        // Step 5: Determine the grade based on average percentage
        char grade = calculateGrade(averagePercentage); // Call the method to calculate the grade

        // Step 6: Display results
        displayResults(totalMarks, averagePercentage, grade); // Call the method to display the results

        scanner.close(); // Close the Scanner object to prevent resource leaks
    }

    // Method to calculate total marks
    private static int calculateTotalMarks(int[] marks) {
        int sum = 0; // Initialize sum to 0
        for (int mark : marks) { // Iterate through the marks array
            sum += mark; // Add each mark to the sum
        }
        return sum; // Return the total sum of marks
    }

    // Method to calculate average percentage
    private static double calculateAveragePercentage(int totalMarks, int numberOfSubjects) {
        return (double) totalMarks / numberOfSubjects; // Calculate and return the average percentage
    }

    // Method to determine the grade based on average percentage
    private static char calculateGrade(double averagePercentage) {
        if (averagePercentage >= 90) {
            return 'A'; // Grade A for 90% and above
        } else if (averagePercentage >= 80) {
            return 'B'; // Grade B for 80% to 89%
        } else if (averagePercentage >= 70) {
            return 'C'; // Grade C for 70% to 79%
        } else if (averagePercentage >= 60) {
            return 'D'; // Grade D for 60% to 69%
        } else {
            return 'F'; // Grade F for below 60%
        }
    }

    // Method to display results
    private static void displayResults(int totalMarks, double averagePercentage, char grade) {
        System.out.println("\n--- Student Grade Report ---");
        System.out.println("Total Marks: " + totalMarks);
        System.out.printf("Average Percentage: %.2f%%\n", averagePercentage); // Format and display average percentage
        System.out.println("Grade: " + grade);
    }
}