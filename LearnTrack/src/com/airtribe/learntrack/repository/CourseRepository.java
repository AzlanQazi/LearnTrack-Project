package com.airtribe.learntrack.repository;

import com.airtribe.learntrack.entity.Course;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class CourseRepository {
    private final List<Course> courses = new ArrayList<>();

    public void save(Course course) {
        if (course == null) throw new IllegalArgumentException("Course cannot be null.");
        courses.add(course);
    }

    public Optional<Course> findById(int id) {
        for (Course course : courses) {
            if (course.getId() == id) return Optional.of(course);
        }
        return Optional.empty();
    }

    public List<Course> findAll() {
        return Collections.unmodifiableList(new ArrayList<>(courses));
    }
}
