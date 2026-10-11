package com.airtribe.learntrack.entity;

public class Student extends Person {
    private String batch;
    private boolean active;

    public Student() {
        super();
        this.active = true;
    }

    // Constructor overloading: a student can be created with or without an email.
    public Student(int id, String firstName, String lastName, String batch) {
        this(id, firstName, lastName, "not-provided@example.invalid", batch);
    }

    public Student(int id, String firstName, String lastName, String email, String batch) {
        super(id, firstName, lastName, email);
        setBatch(batch);
        this.active = true;
    }

    public String getBatch() { return batch; }
    public void setBatch(String batch) { this.batch = requireText(batch, "Batch"); }
    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }

    @Override
    public String getDisplayName() {
        return "Student: " + super.getDisplayName();
    }
}
