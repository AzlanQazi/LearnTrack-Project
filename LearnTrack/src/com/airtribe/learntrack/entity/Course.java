package com.airtribe.learntrack.entity;

import com.airtribe.learntrack.enums.CourseStatus;

public class Course {
    private int id;
    private String courseName;
    private String description;
    private int durationInWeeks;
    private CourseStatus status;

    public Course() {
        this.status = CourseStatus.ACTIVE;
    }

    public Course(int id, String courseName, String description, int durationInWeeks) {
        setId(id);
        setCourseName(courseName);
        setDescription(description);
        setDurationInWeeks(durationInWeeks);
        this.status = CourseStatus.ACTIVE;
    }

    public int getId() { return id; }
    public void setId(int id) {
        if (id < 0) throw new IllegalArgumentException("ID cannot be negative.");
        this.id = id;
    }
    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = requireText(courseName, "Course name"); }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = requireText(description, "Description"); }
    public int getDurationInWeeks() { return durationInWeeks; }
    public void setDurationInWeeks(int durationInWeeks) {
        if (durationInWeeks <= 0) throw new IllegalArgumentException("Duration must be greater than zero.");
        this.durationInWeeks = durationInWeeks;
    }
    public CourseStatus getStatus() { return status; }
    public void setStatus(CourseStatus status) {
        if (status == null) throw new IllegalArgumentException("Course status cannot be null.");
        this.status = status;
    }
    public boolean isActive() { return status == CourseStatus.ACTIVE; }

    private String requireText(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " cannot be empty.");
        }
        return value.trim();
    }
}
