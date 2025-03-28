import java.util.*;

// Class to represent a Course
class Course {
    private String courseCode;
    private String title;
    private String description;
    private int capacity;
    private int availableSlots;
    private String schedule;

    public Course(String courseCode, String title, String description, int capacity, String schedule) {
        this.courseCode = courseCode;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.availableSlots = capacity; // Initially, all slots are available
        this.schedule = schedule;
    }

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

    // Method to register a student for the course
    public boolean register() {
        if (availableSlots > 0) {
            availableSlots--;
            return true;
        }
        return false;
    }

    // Method to drop a course (increase available slots)
    public void drop() {
        if (availableSlots < capacity) {
            availableSlots++;
        }
    }

    @Override
    public String toString() {
        return String.format("%s: %s\nDescription: %s\nSchedule: %s\nAvailable Slots: %d/%d",
                courseCode, title, description, schedule, availableSlots, capacity);
    }
}

// Class to represent a Student
class Student {
    private String studentId;
    private String name;
    private List<Course> registeredCourses;

    public Student(String studentId, String name) {
        this.studentId = studentId;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public String getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public List<Course> getRegisteredCourses() {
        return registeredCourses;
    }

    // Method to register for a course
    public void registerForCourse(Course course) {
        if (course.register()) {
            registeredCourses.add(course);
            System.out.println("Successfully registered for " + course.getTitle());
        } else {
            System.out.println("Registration failed. No available slots for " + course.getTitle());
        }
    }

    // Method to drop a course
    public void dropCourse(Course course) {
        if (registeredCourses.contains(course)) {
            course.drop();
            registeredCourses.remove(course);
            System.out.println("Successfully dropped " + course.getTitle());
        } else {
            System.out.println("You are not registered for " + course.getTitle());
        }
    }

    // Method to display registered courses
    public void displayRegisteredCourses() {
        if (registeredCourses.isEmpty()) {
            System.out.println("You are not registered for any courses.");
        } else {
            System.out.println("\n--- Your Registered Courses ---");
            for (Course course : registeredCourses) {
                System.out.println(course);
            }
        }
    }
}

// Class to manage the Course Registration System
class CourseRegistrationSystem {
    private List<Course> courses;
    private List<Student> students;
    private Scanner scanner;

    public CourseRegistrationSystem() {
        this.courses = new ArrayList<>();
        this.students = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    // Method to add courses to the database
    public void addCourse(Course course) {
        courses.add(course);
    }

    // Method to add students to the database
    public void addStudent(Student student) {
        students.add(student);
    }

    // Method to display available courses
    public void displayAvailableCourses() {
        System.out.println("\n--- Available Courses ---");
        for (Course course : courses) {
            System.out.println(course);
            System.out.println("-----------------------------");
        }
    }

    // Method to find a course by course code
    private Course findCourseByCode(String courseCode) {
        for (Course course : courses) {
            if (course.getCourseCode().equalsIgnoreCase(courseCode)) {
                return course;
            }
        }
        return null;
    }

    // Method to find a student by student ID
    private Student findStudentById(String studentId) {
        for (Student student : students) {
            if (student.getStudentId().equalsIgnoreCase(studentId)) {
                return student;
            }
        }
        return null;
    }

    // Method to handle student registration
    public void registerStudentForCourse() {
        System.out.print("Enter your student ID: ");
        String studentId = scanner.nextLine();
        Student student = findStudentById(studentId);

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        displayAvailableCourses();
        System.out.print("Enter the course code to register: ");
        String courseCode = scanner.nextLine();
        Course course = findCourseByCode(courseCode);

        if (course == null) {
            System.out.println("Course not found.");
        } else {
            student.registerForCourse(course);
        }
    }

    // Method to handle course removal
    public void dropCourseForStudent() {
        System.out.print("Enter your student ID: ");
        String studentId = scanner.nextLine();
        Student student = findStudentById(studentId);

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        student.displayRegisteredCourses();
        System.out.print("Enter the course code to drop: ");
        String courseCode = scanner.nextLine();
        Course course = findCourseByCode(courseCode);

        if (course == null) {
            System.out.println("Course not found.");
        } else {
            student.dropCourse(course);
        }
    }

    // Method to display the main menu
    public void displayMenu() {
        System.out.println("\n--- Course Registration System ---");
        System.out.println("1. View Available Courses");
        System.out.println("2. Register for a Course");
        System.out.println("3. Drop a Course");
        System.out.println("4. View Registered Courses");
        System.out.println("5. Exit");
    }

    // Method to run the system
    public void run() {
        boolean isRunning = true;

        while (isRunning) {
            displayMenu();
            System.out.print("Choose an option (1-5): ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    displayAvailableCourses();
                    break;
                case 2:
                    registerStudentForCourse();
                    break;
                case 3:
                    dropCourseForStudent();
                    break;
                case 4:
                    System.out.print("Enter your student ID: ");
                    String studentId = scanner.nextLine();
                    Student student = findStudentById(studentId);
                    if (student != null) {
                        student.displayRegisteredCourses();
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;
                case 5:
                    System.out.println("Exiting the system. Goodbye!");
                    isRunning = false;
                    break;
                default:
                    System.out.println("Invalid option. Please choose a valid option (1-5).");
            }
        }
        scanner.close();
    }
}

public class StudentCourseRegistrationSystem {
    public static void main(String[] args) {
        // Create the course registration system
        CourseRegistrationSystem system = new CourseRegistrationSystem();

        // Add some courses to the database
        system.addCourse(new Course("CS101", "Introduction to Programming", "Learn the basics of programming.", 30, "Mon/Wed 10:00 AM"));
        system.addCourse(new Course("MATH101", "Calculus I", "Introduction to differential and integral calculus.", 25, "Tue/Thu 2:00 PM"));
        system.addCourse(new Course("ENG201", "Advanced Writing", "Develop advanced writing skills.", 20, "Mon/Fri 1:00 PM"));

        // Add some students to the database
        system.addStudent(new Student("S001", "Alice Johnson"));
        system.addStudent(new Student("S002", "Bob Smith"));

        // Run the system
        system.run();
    }
}