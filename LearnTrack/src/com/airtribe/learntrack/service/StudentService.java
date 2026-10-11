package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.repository.StudentRepository;
import com.airtribe.learntrack.util.IdGenerator;
import java.util.List;

public class StudentService {
    private final StudentRepository repository;

    public StudentService() {
        this(new StudentRepository());
    }

    public StudentService(StudentRepository repository) {
        if (repository == null) throw new IllegalArgumentException("Repository cannot be null.");
        this.repository = repository;
    }

    public Student addStudent(String firstName, String lastName, String email, String batch) {
        Student student = new Student(IdGenerator.getNextStudentId(), firstName, lastName, email, batch);
        repository.save(student);
        return student;
    }

    // Overloaded method demonstrates a second supported creation form.
    public Student addStudent(String firstName, String lastName, String batch) {
        Student student = new Student(IdGenerator.getNextStudentId(), firstName, lastName, batch);
        repository.save(student);
        return student;
    }

    public Student findStudentById(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Student with ID " + id + " was not found."));
    }

    public List<Student> listStudents() { return repository.findAll(); }

    public void updateStudent(int id, String firstName, String lastName, String email, String batch) {
        Student student = findStudentById(id);
        if (firstName != null && !firstName.isBlank()) student.setFirstName(firstName);
        if (lastName != null && !lastName.isBlank()) student.setLastName(lastName);
        if (email != null && !email.isBlank()) student.setEmail(email);
        if (batch != null && !batch.isBlank()) student.setBatch(batch);
    }

    public void deactivateStudent(int id) {
        Student student = findStudentById(id);
        student.setActive(false);
        System.out.println("Student " + id + " deactivated.");
    }
}
