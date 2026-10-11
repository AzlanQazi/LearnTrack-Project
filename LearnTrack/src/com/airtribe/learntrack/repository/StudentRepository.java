package com.airtribe.learntrack.repository;

import com.airtribe.learntrack.entity.Student;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class StudentRepository {
    private final List<Student> students = new ArrayList<>();

    public void save(Student student) {
        if (student == null) throw new IllegalArgumentException("Student cannot be null.");
        students.add(student);
    }

    public Optional<Student> findById(int id) {
        for (Student student : students) {
            if (student.getId() == id) return Optional.of(student);
        }
        return Optional.empty();
    }

    public List<Student> findAll() {
        return Collections.unmodifiableList(new ArrayList<>(students));
    }
}
