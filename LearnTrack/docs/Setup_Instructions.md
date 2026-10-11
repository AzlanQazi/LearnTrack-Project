# Setup Instructions

## JDK version

Recommended: **JDK 17 or newer**. The source uses switch expressions/arrow labels introduced in modern Java. Record the exact installed version when preparing your submission.

Check installation:

```bash
java -version
javac -version
```

Both commands should work. `java` runs compiled Java programs; `javac` compiles `.java` source files into `.class` bytecode files.

## Compile and run

From the project root:

**Windows PowerShell**
```powershell
javac -d out (Get-ChildItem -Recurse src -Filter *.java | ForEach-Object { $_.FullName })
java -cp out com.airtribe.learntrack.Main
```

**macOS / Linux**
```bash
mkdir -p out
javac -d out $(find src -name "*.java")
java -cp out com.airtribe.learntrack.Main
```

## Hello World verification

Create `HelloWorld.java`:

```java
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }
}
```

Compile and run:

```bash
javac HelloWorld.java
java HelloWorld
```

Expected output:

```text
Hello, World!
```

## Screenshot note

Before submitting, run the Hello World program and LearnTrack in your own environment and insert screenshots here or attach them to your submission. Screenshots are intentionally not fabricated by this project package.
