package com.airtribe.learntrack.entity;

public class Person {
    private int id;
    private String firstName;
    private String lastName;
    private String email;

    public Person() {
    }

    public Person(int id, String firstName, String lastName, String email) {
        setId(id);
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
    }

    public int getId() { return id; }
    public void setId(int id) {
        if (id < 0) throw new IllegalArgumentException("ID cannot be negative.");
        this.id = id;
    }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) {
        this.firstName = requireText(firstName, "First name");
    }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) {
        this.lastName = requireText(lastName, "Last name");
    }
    public String getEmail() { return email; }
    public void setEmail(String email) {
        this.email = requireText(email, "Email");
    }

    public String getDisplayName() {
        return firstName + " " + lastName;
    }

    protected String requireText(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " cannot be empty.");
        }
        return value.trim();
    }
}
