package com.airtribe.learntrack.entity;

import com.airtribe.learntrack.enums.EnrollmentStatus;
import java.time.LocalDate;

public class Enrollment {
    private int id;
    private int studentId;
    private int courseId;
    private LocalDate enrollmentDate;
    private EnrollmentStatus status;

    public Enrollment() {
        this.enrollmentDate = LocalDate.now();
        this.status = EnrollmentStatus.ACTIVE;
    }

    public Enrollment(int id, int studentId, int courseId, LocalDate enrollmentDate,
                      EnrollmentStatus status) {
        setId(id);
        setStudentId(studentId);
        setCourseId(courseId);
        setEnrollmentDate(enrollmentDate);
        setStatus(status);
    }

    public int getId() { return id; }
    public void setId(int id) {
        if (id <= 0) throw new IllegalArgumentException("Enrollment ID must be positive.");
        this.id = id;
    }
    public int getStudentId() { return studentId; }
    public void setStudentId(int studentId) {
        if (studentId <= 0) throw new IllegalArgumentException("Student ID must be positive.");
        this.studentId = studentId;
    }
    public int getCourseId() { return courseId; }
    public void setCourseId(int courseId) {
        if (courseId <= 0) throw new IllegalArgumentException("Course ID must be positive.");
        this.courseId = courseId;
    }
    public LocalDate getEnrollmentDate() { return enrollmentDate; }
    public void setEnrollmentDate(LocalDate enrollmentDate) {
        if (enrollmentDate == null) throw new IllegalArgumentException("Enrollment date cannot be null.");
        this.enrollmentDate = enrollmentDate;
    }
    public EnrollmentStatus getStatus() { return status; }
    public void setStatus(EnrollmentStatus status) {
        if (status == null) throw new IllegalArgumentException("Enrollment status cannot be null.");
        this.status = status;
    }

    @Override
    public String toString() {
        return "Enrollment{id=" + id + ", studentId=" + studentId + ", courseId=" + courseId
                + ", enrollmentDate=" + enrollmentDate + ", status=" + status + "}";
    }
}
