package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.enums.CourseStatus;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.repository.CourseRepository;
import com.airtribe.learntrack.util.IdGenerator;
import java.util.List;

public class CourseService {
    private final CourseRepository repository;

    public CourseService() {
        this(new CourseRepository());
    }

    public CourseService(CourseRepository repository) {
        if (repository == null) throw new IllegalArgumentException("Repository cannot be null.");
        this.repository = repository;
    }

    public Course addCourse(String name, String description, int durationInWeeks) {
        Course course = new Course(IdGenerator.getNextCourseId(), name, description, durationInWeeks);
        repository.save(course);
        return course;
    }

    public Course findCourseById(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Course with ID " + id + " was not found."));
    }

    public List<Course> listCourses() { return repository.findAll(); }

    public void setCourseStatus(int id, CourseStatus status) {
        Course course = findCourseById(id);
        course.setStatus(status);
        System.out.println("Course " + id + " status changed to " + status + ".");
    }
}
