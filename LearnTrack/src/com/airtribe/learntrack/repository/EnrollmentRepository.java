package com.airtribe.learntrack.repository;

import com.airtribe.learntrack.entity.Enrollment;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EnrollmentRepository {
    private final List<Enrollment> enrollments = new ArrayList<>();

    public void save(Enrollment enrollment) {
        if (enrollment == null) throw new IllegalArgumentException("Enrollment cannot be null.");
        enrollments.add(enrollment);
    }

    public List<Enrollment> findByStudentId(int studentId) {
        List<Enrollment> matches = new ArrayList<>();
        for (Enrollment enrollment : enrollments) {
            if (enrollment.getStudentId() == studentId) matches.add(enrollment);
        }
        return matches;
    }

    public Enrollment findById(int id) {
        for (Enrollment enrollment : enrollments) {
            if (enrollment.getId() == id) return enrollment;
        }
        return null;
    }

    public List<Enrollment> findAll() {
        return Collections.unmodifiableList(new ArrayList<>(enrollments));
    }
}
