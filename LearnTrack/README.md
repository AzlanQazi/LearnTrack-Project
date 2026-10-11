# LearnTrack — Student & Course Management System

LearnTrack is a menu-driven, console-based application written in Core Java. It manages students, courses, and enrollments in memory. It is designed to demonstrate Java syntax, OOP, collections, basic exception handling, and separation of concerns.

## Features

- Add, list, search, update, and deactivate students.
- Add, list, search, activate, and deactivate courses.
- Enroll active students in active courses.
- List all enrollments or enrollments for a particular student.
- Mark active enrollments as completed or cancelled.
- Validate positive numeric input and required fields.
- Use custom exceptions to display understandable errors.
- Keep data in `ArrayList` collections for the duration of the application.

## Requirements

- JDK 17 or newer (the code uses modern `switch` arrow labels).
- Terminal, or an IDE such as IntelliJ IDEA / Eclipse / VS Code with Java support.

## Compile and run from the project root

### Windows (PowerShell)

```powershell
javac -d out (Get-ChildItem -Recurse src -Filter *.java | ForEach-Object { $_.FullName })
java -cp out com.airtribe.learntrack.Main
```

### macOS / Linux

```bash
mkdir -p out
javac -d out $(find src -name "*.java")
java -cp out com.airtribe.learntrack.Main
```

The application starts at `com.airtribe.learntrack.Main`. All information is stored in memory and is lost when the application exits.

## Package overview

- `entity`: data objects and encapsulated fields.
- `repository`: in-memory storage and basic retrieval.
- `service`: business rules and validation.
- `exception`: custom runtime exceptions.
- `util`: ID generation and input validation.
- `constants`: application-wide constant values.
- `enums`: fixed status values.

See `docs/` for setup, JVM basics, design decisions, and a manual test checklist.

Note - This project is made using AI Assistance.
