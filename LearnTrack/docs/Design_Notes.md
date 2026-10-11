# Design Notes

## Why `ArrayList` instead of arrays?

An array has a fixed length once created. An `ArrayList` can grow as students, courses, and enrollments are added, which is convenient when the number of records is not known in advance. This project uses `ArrayList` internally in each repository. The application is intentionally in-memory only, so data disappears when the program exits.

## Where are static members used, and why?

`IdGenerator` uses static counters and static methods because ID allocation is shared across objects and does not require an `IdGenerator` instance. `AppConstants` and `MenuOptions` use `static final` values for constants shared throughout the application.

## Where is inheritance used?

`Student` extends `Person`, reusing the shared identity fields (`id`, `firstName`, `lastName`, and `email`). The `Student` constructor calls `super(...)` to initialize those fields. `Student.getDisplayName()` overrides the base implementation, demonstrating runtime polymorphism.

## Separation of concerns

- **Entity:** stores state and protects fields with private access plus public methods.
- **Repository:** owns in-memory collections and performs basic storage/lookups.
- **Service:** applies business rules, coordinates repositories, and throws meaningful exceptions.
- **Main/UI:** displays menus, reads console input, and calls service methods.
- **Utility/constants/enums:** centralize reusable helpers, fixed values, and status options.

## Exception handling

`InputValidator` converts user text into positive integers and raises `InvalidInputException` for invalid input. `EntityNotFoundException` represents missing student/course/enrollment records. The console UI catches expected runtime exceptions and prints a message instead of exposing a stack trace.

## Assumptions and limitations

- No database or file persistence is used.
- IDs are unique within a single run and restart from 1 when the process restarts.
- Students and courses are deactivated rather than deleted.
- A student may not have more than one **ACTIVE** enrollment in the same course at a time.
- An enrollment can only move from ACTIVE to COMPLETED or CANCELLED; terminal statuses cannot be changed again.
- Email is required but this beginner project does not perform strict email-format validation.
- `CourseStatus` uses ACTIVE/INACTIVE instead of a boolean to make status values explicit.

## Manual test checklist

1. Start the app and choose option `1` to enter Student Management.
2. Add a student with all fields filled in; verify an ID is displayed.
3. View all students and search for the new ID.
4. Update the student's batch; verify the updated value.
5. Deactivate the student; verify `Active: false`.
6. Add a course with a positive duration; verify its ID and ACTIVE status.
7. Enroll an active student in an active course.
8. Attempt to enroll the same student in the same course again while the first enrollment is ACTIVE; expect a friendly validation message.
9. Mark the enrollment completed; attempt to change it again; expect a friendly validation message.
10. Enter text instead of a numeric menu choice; verify the program asks again rather than crashing.
11. Search for a nonexistent ID; verify a clear not-found message.
12. Exit; restart and confirm the in-memory data has been reset.
