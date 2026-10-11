# JVM Basics

## JDK, JRE, and JVM

The **JVM (Java Virtual Machine)** runs Java bytecode. It provides the runtime environment that lets compiled Java programs execute on different operating systems.

The **JRE (Java Runtime Environment)** traditionally refers to the JVM plus the libraries and supporting files needed to run Java applications. The **JDK (Java Development Kit)** includes tools used to develop Java programs, especially the compiler (`javac`), as well as runtime components. Modern JDK distributions do not always package a separate JRE installer, but the conceptual distinction is still useful.

## What is bytecode?

Java source code is written in `.java` files. The Java compiler (`javac`) converts source code into `.class` files containing bytecode. The JVM for a platform interprets or compiles that bytecode for execution.

## “Write once, run anywhere”

Java source can usually be compiled once into platform-independent bytecode and then run on any supported platform that has a compatible JVM. This is the idea behind “write once, run anywhere.”

It does not mean every program is automatically portable: programs can still depend on platform-specific files, native libraries, or operating-system behavior. For a simple console application such as LearnTrack, standard Java APIs help make portability practical.
