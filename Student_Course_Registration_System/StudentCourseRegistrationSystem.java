import java.util.*;

// Class to represent a Course in the registration system
class Course {
    // Private fields to store course information
    private String courseCode;       // Unique identifier for the course (e.g., "CS101")
    private String title;            // Title of the course (e.g., "Introduction to Programming")
    private String description;      // Description of what the course covers
    private int capacity;            // Maximum number of students that can enroll
    private int availableSlots;      // Current number of available slots
    private String schedule;         // When the course meets (e.g., "Mon/Wed 10:00 AM")

    // Constructor to initialize a Course object
    public Course(String courseCode, String title, String description, int capacity, String schedule) {
        this.courseCode = courseCode;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.availableSlots = capacity; // Initially all slots are available
        this.schedule = schedule;
    }

    // Getter methods to access private fields
    public String getCourseCode() {
        return courseCode;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getAvailableSlots() {
        return availableSlots;
    }

    public String getSchedule() {
        return schedule;
    }

    // Method to register a student for this course
    // Returns true if registration was successful, false if no slots available
    public boolean register() {
        if (availableSlots > 0) {
            availableSlots--;  // Decrease available slots when a student registers
            return true;
        }
        return false;
    }

    // Method to drop this course (student unregisters)
    public void drop() {
        if (availableSlots < capacity) {
            availableSlots++;  // Increase available slots when a student drops
        }
    }

    // Override toString() to provide a formatted string representation of the course
    @Override
    public String toString() {
        return String.format("%s: %s\nDescription: %s\nSchedule: %s\nAvailable Slots: %d/%d",
                courseCode, title, description, schedule, availableSlots, capacity);
    }
}

// Class to represent a Student in the registration system
class Student {
    // Private fields to store student information
    private String studentId;            // Unique identifier for the student (e.g., "S001")
    private String name;                // Student's full name
    private List<Course> registeredCourses;  // List of courses the student is registered for

    // Constructor to initialize a Student object
    public Student(String studentId, String name) {
        this.studentId = studentId;
        this.name = name;
        this.registeredCourses = new ArrayList<>();  // Initialize empty list of courses
    }

    // Getter methods to access private fields
    public String getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public List<Course> getRegisteredCourses() {
        return registeredCourses;
    }

    // Method to register this student for a course
    public void registerForCourse(Course course) {
        if (course.register()) {  // Try to register for the course
            registeredCourses.add(course);  // Add to student's course list if successful
            System.out.println("Successfully registered for " + course.getTitle());
        } else {
            System.out.println("Registration failed. No available slots for " + course.getTitle());
        }
    }

    // Method to drop a course this student is registered for
    public void dropCourse(Course course) {
        if (registeredCourses.contains(course)) {  // Check if student is registered
            course.drop();  // Free up a slot in the course
            registeredCourses.remove(course);  // Remove from student's course list
            System.out.println("Successfully dropped " + course.getTitle());
        } else {
            System.out.println("You are not registered for " + course.getTitle());
        }
    }

    // Method to display all courses this student is registered for
    public void displayRegisteredCourses() {
        if (registeredCourses.isEmpty()) {
            System.out.println("You are not registered for any courses.");
        } else {
            System.out.println("\n--- Your Registered Courses ---");
            for (Course course : registeredCourses) {
                System.out.println(course);  // Uses Course's toString() method
            }
        }
    }
}

// Main class to manage the Course Registration System
class CourseRegistrationSystem {
    // Private fields to store system data
    private List<Course> courses;    // List of all available courses
    private List<Student> students;  // List of all registered students
    private Scanner scanner;         // Scanner object for user input

    // Constructor to initialize the system
    public CourseRegistrationSystem() {
        this.courses = new ArrayList<>();
        this.students = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    // Method to add a new course to the system
    public void addCourse(Course course) {
        courses.add(course);
    }

    // Method to add a new student to the system
    public void addStudent(Student student) {
        students.add(student);
    }

    // Method to display all available courses and their details
    public void displayAvailableCourses() {
        System.out.println("\n--- Available Courses ---");
        for (Course course : courses) {
            System.out.println(course);  // Uses Course's toString() method
            System.out.println("-----------------------------");
        }
    }

    // Helper method to find a course by its code
    private Course findCourseByCode(String courseCode) {
        for (Course course : courses) {
            if (course.getCourseCode().equalsIgnoreCase(courseCode)) {
                return course;
            }
        }
        return null;  // Return null if course not found
    }

    // Helper method to find a student by their ID
    private Student findStudentById(String studentId) {
        for (Student student : students) {
            if (student.getStudentId().equalsIgnoreCase(studentId)) {
                return student;
            }
        }
        return null;  // Return null if student not found
    }

    // Method to handle the process of registering a student for a course
    public void registerStudentForCourse() {
        System.out.print("Enter your student ID: ");
        String studentId = scanner.nextLine();
        Student student = findStudentById(studentId);

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        displayAvailableCourses();  // Show available courses
        System.out.print("Enter the course code to register: ");
        String courseCode = scanner.nextLine();
        Course course = findCourseByCode(courseCode);

        if (course == null) {
            System.out.println("Course not found.");
        } else {
            student.registerForCourse(course);  // Attempt registration
        }
    }

    // Method to handle the process of dropping a course for a student
    public void dropCourseForStudent() {
        System.out.print("Enter your student ID: ");
        String studentId = scanner.nextLine();
        Student student = findStudentById(studentId);

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        student.displayRegisteredCourses();  // Show student's current courses
        System.out.print("Enter the course code to drop: ");
        String courseCode = scanner.nextLine();
        Course course = findCourseByCode(courseCode);

        if (course == null) {
            System.out.println("Course not found.");
        } else {
            student.dropCourse(course);  // Attempt to drop the course
        }
    }

    // Method to display the main menu options
    public void displayMenu() {
        System.out.println("\n--- Course Registration System ---");
        System.out.println("1. View Available Courses");
        System.out.println("2. Register for a Course");
        System.out.println("3. Drop a Course");
        System.out.println("4. View Registered Courses");
        System.out.println("5. Exit");
    }

    // Main method to run the registration system
    public void run() {
        boolean isRunning = true;

        while (isRunning) {
            displayMenu();  // Show menu options
            System.out.print("Choose an option (1-5): ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline left after nextInt()

            // Handle user's menu choice
            switch (choice) {
                case 1:
                    displayAvailableCourses();  // Show all available courses
                    break;
                case 2:
                    registerStudentForCourse();  // Register student for a course
                    break;
                case 3:
                    dropCourseForStudent();  // Drop a course for a student
                    break;
                case 4:
                    System.out.print("Enter your student ID: ");
                    String studentId = scanner.nextLine();
                    Student student = findStudentById(studentId);
                    if (student != null) {
                        student.displayRegisteredCourses();  // Show student's courses
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;
                case 5:
                    System.out.println("Exiting the system. Goodbye!");
                    isRunning = false;  // Exit the loop and program
                    break;
                default:
                    System.out.println("Invalid option. Please choose a valid option (1-5).");
            }
        }
        scanner.close();  // Close scanner when done
    }
}

// Main class to start the application
public class StudentCourseRegistrationSystem {
    public static void main(String[] args) {
        // Create an instance of the registration system
        CourseRegistrationSystem system = new CourseRegistrationSystem();

        // Add some sample courses to the system
        system.addCourse(new Course("CS101", "Introduction to Programming", 
            "Learn the basics of programming.", 30, "Mon/Wed 10:00 AM"));
        system.addCourse(new Course("MATH101", "Calculus I", 
            "Introduction to differential and integral calculus.", 25, "Tue/Thu 2:00 PM"));
        system.addCourse(new Course("ENG201", "Advanced Writing", 
            "Develop advanced writing skills.", 20, "Mon/Fri 1:00 PM"));

        // Add some sample students to the system
        system.addStudent(new Student("S001", "Alice Johnson"));
        system.addStudent(new Student("S002", "Bob Smith"));

        // Start the system's main loop
        system.run();
    }
}