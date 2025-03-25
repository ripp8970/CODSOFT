import java.util.Scanner;

public class StudentGradeCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Step 1: Input the number of subjects
        System.out.print("Enter the number of subjects: ");
        int numberOfSubjects = scanner.nextInt();
        
        // Array to hold marks for each subject
        int[] marks = new int[numberOfSubjects];
        
        // Step 2: Input marks obtained in each subject
        for (int i = 0; i < numberOfSubjects; i++) {
            System.out.print("Enter marks obtained in subject " + (i + 1) + " (out of 100): ");
            marks[i] = scanner.nextInt();
        }
        
        // Step 3: Calculate total marks
        int totalMarks = calculateTotalMarks(marks);
        
        // Step 4: Calculate average percentage
        double averagePercentage = calculateAveragePercentage(totalMarks, numberOfSubjects);
        
        // Step 5: Determine the grade based on average percentage
        char grade = calculateGrade(averagePercentage);
        
        // Step 6: Display results
        displayResults(totalMarks, averagePercentage, grade);
        
        scanner.close();
    }
    
    // Method to calculate total marks
    private static int calculateTotalMarks(int[] marks) {
        int sum = 0;
        for (int mark : marks) {
            sum += mark;
        }
        return sum;
    }
    
    // Method to calculate average percentage
    private static double calculateAveragePercentage(int totalMarks, int numberOfSubjects) {
        return (double) totalMarks / numberOfSubjects;
    }
    
    // Method to determine the grade based on average percentage
    private static char calculateGrade(double averagePercentage) {
        if (averagePercentage >= 90) {
            return 'A';
        } else if (averagePercentage >= 80) {
            return 'B';
        } else if (averagePercentage >= 70) {
            return 'C';
        } else if (averagePercentage >= 60) {
            return 'D';
        } else {
            return 'F'; // Fail
        }
    }
    
    // Method to display results
    private static void displayResults(int totalMarks, double averagePercentage, char grade) {
        System.out.println("\n--- Student Grade Report ---");
        System.out.println("Total Marks: " + totalMarks);
        System.out.printf("Average Percentage: %.2f%%\n", averagePercentage);
        System.out.println("Grade: " + grade);
    }
}
