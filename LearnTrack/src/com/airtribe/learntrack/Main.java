package com.airtribe.learntrack;

import com.airtribe.learntrack.constants.AppConstants;
import com.airtribe.learntrack.constants.MenuOptions;
import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.entity.Person;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.enums.CourseStatus;
import com.airtribe.learntrack.enums.EnrollmentStatus;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.service.CourseService;
import com.airtribe.learntrack.service.EnrollmentService;
import com.airtribe.learntrack.service.StudentService;
import com.airtribe.learntrack.util.InputValidator;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    private final Scanner scanner = new Scanner(System.in);
    private final StudentService studentService = new StudentService();
    private final CourseService courseService = new CourseService();
    private final EnrollmentService enrollmentService =
            new EnrollmentService(studentService, courseService);

    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        System.out.println(AppConstants.APP_NAME + " v" + AppConstants.APP_VERSION);
        boolean running = true;
        while (running) {
            printMainMenu();
            int choice = readInt("Choose an option: ");
            try {
                switch (choice) {
                    case MenuOptions.STUDENT_MANAGEMENT -> studentMenu();
                    case MenuOptions.COURSE_MANAGEMENT -> courseMenu();
                    case MenuOptions.ENROLLMENT_MANAGEMENT -> enrollmentMenu();
                    case MenuOptions.EXIT -> {
                        running = false;
                        System.out.println("Thank you for using LearnTrack. Goodbye!");
                    }
                    default -> System.out.println("Invalid menu option. Choose 1 to 4.");
                }
            } catch (EntityNotFoundException | InvalidInputException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input: " + e.getMessage());
            }
        }
        scanner.close();
    }

    private void printMainMenu() {
        System.out.println("\n========== LEARNTRACK ==========");
        System.out.println("1. Student Management");
        System.out.println("2. Course Management");
        System.out.println("3. Enrollment Management");
        System.out.println("4. Exit");
    }

    private void studentMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n------ STUDENT MANAGEMENT ------");
            System.out.println("1. Add student");
            System.out.println("2. View all students");
            System.out.println("3. Search student by ID");
            System.out.println("4. Update student");
            System.out.println("5. Deactivate student");
            System.out.println("6. Back");
            switch (readInt("Choose an option: ")) {
                case 1 -> addStudent();
                case 2 -> printStudents(studentService.listStudents());
                case 3 -> printStudent(studentService.findStudentById(readInt("Student ID: ")));
                case 4 -> updateStudent();
                case 5 -> studentService.deactivateStudent(readInt("Student ID to deactivate: "));
                case 6 -> back = true;
                default -> System.out.println("Invalid menu option.");
            }
        }
    }

    private void addStudent() {
        String first = readRequired("First name: ");
        String last = readRequired("Last name: ");
        String email = readRequired("Email: ");
        String batch = readRequired("Batch: ");
        Student student = studentService.addStudent(first, last, email, batch);
        System.out.println("Student created successfully:");
        printStudent(student);
    }

    private void updateStudent() {
        int id = readInt("Student ID to update: ");
        Student student = studentService.findStudentById(id);
        System.out.println("Press Enter to keep a field unchanged.");
        String first = readOptional("First name [" + student.getFirstName() + "]: ");
        String last = readOptional("Last name [" + student.getLastName() + "]: ");
        String email = readOptional("Email [" + student.getEmail() + "]: ");
        String batch = readOptional("Batch [" + student.getBatch() + "]: ");
        studentService.updateStudent(id, first, last, email, batch);
        System.out.println("Student updated.");
        printStudent(studentService.findStudentById(id));
    }

    private void courseMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n-------- COURSE MANAGEMENT --------");
            System.out.println("1. Add course");
            System.out.println("2. View all courses");
            System.out.println("3. Search course by ID");
            System.out.println("4. Activate course");
            System.out.println("5. Deactivate course");
            System.out.println("6. Back");
            switch (readInt("Choose an option: ")) {
                case 1 -> addCourse();
                case 2 -> printCourses(courseService.listCourses());
                case 3 -> printCourse(courseService.findCourseById(readInt("Course ID: ")));
                case 4 -> courseService.setCourseStatus(readInt("Course ID: "), CourseStatus.ACTIVE);
                case 5 -> courseService.setCourseStatus(readInt("Course ID: "), CourseStatus.INACTIVE);
                case 6 -> back = true;
                default -> System.out.println("Invalid menu option.");
            }
        }
    }

    private void addCourse() {
        String name = readRequired("Course name: ");
        String description = readRequired("Description: ");
        int weeks = readInt("Duration in weeks: ");
        Course course = courseService.addCourse(name, description, weeks);
        System.out.println("Course created successfully:");
        printCourse(course);
    }

    private void enrollmentMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n----- ENROLLMENT MANAGEMENT -----");
            System.out.println("1. Enroll student in course");
            System.out.println("2. View enrollments for student");
            System.out.println("3. Mark enrollment completed");
            System.out.println("4. Cancel enrollment");
            System.out.println("5. View all enrollments");
            System.out.println("6. Back");
            switch (readInt("Choose an option: ")) {
                case 1 -> {
                    int studentId = readInt("Student ID: ");
                    int courseId = readInt("Course ID: ");
                    Enrollment enrollment = enrollmentService.enrollStudent(studentId, courseId);
                    System.out.println("Enrollment created: " + enrollment);
                }
                case 2 -> printEnrollmentsForStudent(readInt("Student ID: "));
                case 3 -> enrollmentService.updateStatus(readInt("Enrollment ID: "), EnrollmentStatus.COMPLETED);
                case 4 -> enrollmentService.updateStatus(readInt("Enrollment ID: "), EnrollmentStatus.CANCELLED);
                case 5 -> printEnrollments(enrollmentService.listEnrollments());
                case 6 -> back = true;
                default -> System.out.println("Invalid menu option.");
            }
        }
    }

    private int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return InputValidator.parsePositiveInt(input, "Please enter a positive whole number.");
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private String readRequired(String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine().trim();
            try {
                return InputValidator.requireNonBlank(value, "This field cannot be empty.");
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private String readOptional(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private void printStudents(List<Student> students) {
        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }
        students.forEach(this::printStudent);
    }

    private void printStudent(Student student) {
        System.out.printf("ID: %d | Name: %s | Email: %s | Batch: %s | Active: %s%n",
                student.getId(), student.getDisplayName(), student.getEmail(),
                student.getBatch(), student.isActive());
    }

    private void printCourses(List<Course> courses) {
        if (courses.isEmpty()) {
            System.out.println("No courses found.");
            return;
        }
        courses.forEach(this::printCourse);
    }

    private void printCourse(Course course) {
        System.out.printf("ID: %d | Name: %s | Duration: %d weeks | Status: %s | Description: %s%n",
                course.getId(), course.getCourseName(), course.getDurationInWeeks(),
                course.getStatus(), course.getDescription());
    }

    private void printEnrollmentsForStudent(int studentId) {
        studentService.findStudentById(studentId);
        List<Enrollment> enrollments = enrollmentService.findEnrollmentsByStudentId(studentId);
        if (enrollments.isEmpty()) {
            System.out.println("No enrollments found for student " + studentId + ".");
            return;
        }
        printEnrollments(enrollments);
    }

    private void printEnrollments(List<Enrollment> enrollments) {
        if (enrollments.isEmpty()) {
            System.out.println("No enrollments found.");
            return;
        }
        for (Enrollment enrollment : enrollments) {
            System.out.printf("Enrollment ID: %d | Student ID: %d | Course ID: %d | Date: %s | Status: %s%n",
                    enrollment.getId(), enrollment.getStudentId(), enrollment.getCourseId(),
                    enrollment.getEnrollmentDate(), enrollment.getStatus());
        }
    }
}
