# CODSOFT Java Internship - 5 Tasks Completed

This repository contains the completed Java tasks for the CODSOFT Java Internship.

## Overview

This project encompasses five distinct Java applications, each designed to fulfill specific requirements. These applications demonstrate fundamental Java programming concepts, including:

* Object-oriented programming (OOP) principles
* Input/output operations using `Scanner`
* Data structures (arrays, lists)
* Control flow (loops, conditional statements, switch statements)
* Basic algorithms
* Multithreading using `ExecutorService` and `Future`
* Random number generation
* String manipulation
* Formatted output

## Tasks and Descriptions

1.  **ATM Interface:**
    * A simple ATM simulator that allows users to check their balance, deposit, and withdraw funds.
    * Demonstrates basic OOP concepts with `BankAccount` and `ATM` classes.
    * Utilizes `Scanner` for user input.
    * Handles basic transaction logic and error conditions.

2.  **Number Guessing Game:**
    * A number guessing game where the user tries to guess a randomly generated number within a limited number of attempts.
    * Employs `Random` for number generation.
    * Uses a `do-while` loop for multiple rounds.
    * Provides feedback on guesses and remaining attempts.

3.  **Quiz Application:**
    * A timed quiz application that presents multiple-choice questions to the user.
    * Implements a timer using `ExecutorService` and `Future` to limit response time.
    * Demonstrates handling of timeouts and errors.
    * Uses classes to represent questions and the quiz itself.

4.  **Student Course Registration System:**
    * A system for students to register and drop courses.
    * Uses classes `Course` and `Student` to represent entities.
    * Implements course registration and removal logic.
    * Utilizes `ArrayList` to manage course and student lists.
    * Uses a menu driven system.

5.  **Student Grade Calculator:**
    * A program to calculate a student's average percentage and grade based on marks obtained in multiple subjects.
    * Takes input for the number of subjects and marks for each subject.
    * Calculates total marks, average percentage, and assigns a grade.
    * Displays the results in a formatted manner.

## How to Run the Code

1.  **Prerequisites:**
    * Java Development Kit (JDK) installed on your system.

2.  **Compilation:**
    * Open a terminal or command prompt.
    * Navigate to the directory containing the Java source files.
    * Compile each Java file using the `javac` command:
        ```bash
        javac ATMSystem.java
        javac NumberGame.java
        javac QuizApp.java
        javac StudentCourseRegistrationSystem.java
        javac StudentGradeCalculator.java
        ```

3.  **Execution:**
    * Run each compiled Java class using the `java` command:
        ```bash
        java ATMSystem
        java NumberGame
        java QuizApp
        java StudentCourseRegistrationSystem
        java StudentGradeCalculator
        ```

## Code Structure

Each task is implemented in a separate Java file:

* `ATMSystem.java`
* `NumberGame.java`
* `QuizApp.java`
* `StudentCourseRegistrationSystem.java`
* `StudentGradeCalculator.java`

Each file contains the necessary classes and methods to execute the corresponding task.

## Author

Shivam Kumar Jaiswal

## Acknowledgments

* CODSOFT for providing the internship opportunity.
* The Java programming community for valuable resources and documentation.