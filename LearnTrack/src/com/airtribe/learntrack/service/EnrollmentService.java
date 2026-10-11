package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.enums.EnrollmentStatus;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.repository.EnrollmentRepository;
import com.airtribe.learntrack.util.IdGenerator;
import java.time.LocalDate;
import java.util.List;

public class EnrollmentService {
    private final EnrollmentRepository repository;
    private final StudentService studentService;
    private final CourseService courseService;

    public EnrollmentService(StudentService studentService, CourseService courseService) {
        this(new EnrollmentRepository(), studentService, courseService);
    }

    public EnrollmentService(EnrollmentRepository repository, StudentService studentService,
                             CourseService courseService) {
        if (repository == null || studentService == null || courseService == null) {
            throw new IllegalArgumentException("Repository and services cannot be null.");
        }
        this.repository = repository;
        this.studentService = studentService;
        this.courseService = courseService;
    }

    public Enrollment enrollStudent(int studentId, int courseId) {
        Student student = studentService.findStudentById(studentId);
        Course course = courseService.findCourseById(courseId);
        if (!student.isActive()) throw new InvalidInputException("Cannot enroll an inactive student.");
        if (!course.isActive()) throw new InvalidInputException("Cannot enroll in an inactive course.");

        List<Enrollment> studentEnrollments = repository.findByStudentId(studentId);
        for (Enrollment existing : studentEnrollments) {
            if (existing.getCourseId() == courseId && existing.getStatus() == EnrollmentStatus.ACTIVE) {
                throw new InvalidInputException("Student already has an active enrollment in this course.");
            }
        }

        Enrollment enrollment = new Enrollment(IdGenerator.getNextEnrollmentId(), studentId,
                courseId, LocalDate.now(), EnrollmentStatus.ACTIVE);
        repository.save(enrollment);
        return enrollment;
    }

    public List<Enrollment> findEnrollmentsByStudentId(int studentId) {
        return repository.findByStudentId(studentId);
    }

    public List<Enrollment> listEnrollments() { return repository.findAll(); }

    public void updateStatus(int enrollmentId, EnrollmentStatus status) {
        Enrollment enrollment = repository.findById(enrollmentId);
        if (enrollment == null) {
            throw new EntityNotFoundException("Enrollment with ID " + enrollmentId + " was not found.");
        }
        if (enrollment.getStatus() != EnrollmentStatus.ACTIVE) {
            throw new InvalidInputException("Only ACTIVE enrollments can be completed or cancelled.");
        }
        if (status != EnrollmentStatus.COMPLETED && status != EnrollmentStatus.CANCELLED) {
            throw new InvalidInputException("Allowed target statuses are COMPLETED and CANCELLED.");
        }
        enrollment.setStatus(status);
        System.out.println("Enrollment " + enrollmentId + " status changed to " + status + ".");
    }
}
