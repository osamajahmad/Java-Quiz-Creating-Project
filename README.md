# Java Quiz & Programming Projects

A collection of Java projects focused on **object-oriented programming, JavaFX, data structures, file handling, multithreading, networking, and unit testing**.

The main project in this repository is an interactive quiz application, supported by additional exercises involving binary search trees, queues, inheritance, polymorphism, and object comparison.

---

## Main Project - Quiz Application

The primary project is a quiz system that includes both a **console-based version** and a **JavaFX graphical application**.

The application loads questions from a question bank, presents a randomized quiz to the user, tracks answers and scores, and includes timer and leaderboard functionality.

### Features

* Multiple-choice questions
* True/False questions
* Randomized question selection
* 10-question quiz sessions
* Username entry
* Score calculation
* Previous and Next question navigation
* Two-minute quiz timer
* Automatic submission when time expires
* File-based question loading
* TCP leaderboard communication
* Local leaderboard fallback
* JUnit testing

---

## Object-Oriented Design

The quiz application uses interfaces and separate classes to represent different types of questions.

The shared question interface allows the quiz system to handle different question formats consistently.

Current question types include:

* `MCQQuestion` - Multiple-choice questions
* `TFQQuestion` - True/False questions

This structure demonstrates concepts such as:

* Interfaces
* Abstraction
* Generics
* Encapsulation
* Polymorphism

---

## Quiz Flow

A typical quiz session works as follows:

1. The application loads questions from the question bank.
2. The questions are randomized.
3. A set of questions is selected for the quiz.
4. The user enters a username.
5. The quiz begins.
6. The user selects answers and navigates between questions.
7. A timer tracks the remaining quiz time.
8. The quiz is submitted manually or automatically when the timer expires.
9. The score is calculated.
10. The result is sent to the leaderboard or stored locally.

---

## Question Bank

The application expects a file named:

```text
questionsBase.txt
```

Questions follow a simple text-based structure.

Example multiple-choice question:

```text
Which keyword is used to inherit a class in Java?
implements,extends,imports,instanceof
extends
```

Example True/False question:

```text
Java supports object-oriented programming.
True,False
True
```

> `questionsBase.txt` is referenced by the project but is not currently included in the repository. A compatible question file must be added before running the complete quiz.

---

## JavaFX Interface

The graphical version of the quiz is built using **JavaFX**.

The interface allows the user to:

* Enter a username
* View questions
* Select answers
* Navigate between questions
* Track remaining time
* Submit the quiz
* View the final score

The application also separates event-handling logic into dedicated classes.

---

## Quiz Timer

The quiz includes a two-minute countdown timer.

The timer demonstrates Java multithreading by running separately from the main user interface.

When the timer reaches zero, the quiz is automatically submitted.

---

## Leaderboard

The JavaFX application includes support for sending quiz results to a leaderboard server over TCP.

The leaderboard information can include:

* Username
* Quiz score
* Completion time

The client expects the leaderboard server to be available at:

```text
localhost:12345
```

If the leaderboard server cannot be reached, the application can use a local leaderboard file instead.

```text
leaderboard.txt
```

> A compatible leaderboard server is not currently included in this repository.

---

## Testing

The project includes JUnit tests for core quiz functionality.

Tests cover scenarios such as:

* Correct answers increasing the score
* Incorrect answers not increasing the score
* Quiz answer handling
* Invalid or empty input
* Core question behavior

Testing helps verify that the quiz logic works independently from the graphical interface.

---

# Additional Java Projects

Alongside the quiz application, this repository contains smaller Java exercises covering important programming concepts.

---

## Binary Search Tree

The `BST1` project demonstrates binary search tree concepts and custom object comparison.

Concepts include:

* Tree nodes
* Value insertion
* Tree traversal
* In-order traversal
* Object comparison
* `Comparable`
* `Comparator`

Custom objects such as `Fruit` are used to demonstrate different approaches to sorting and comparison.

---

## Queue Data Structure

The queue project demonstrates the implementation of a queue using linked nodes.

Concepts include:

* Queue operations
* Nodes
* Linked data structures
* Encapsulation
* First-In-First-Out behavior

The implementation helps demonstrate how common data structures work internally rather than relying only on built-in Java collections.

---

## Goats OOP Project

The Goats project is an object-oriented programming exercise based around different character types and combat behavior.

It demonstrates:

* Abstract classes
* Inheritance
* Polymorphism
* Method overriding
* Enums
* Object interaction

Classes include concepts such as:

```text
Goat
Fighter
Mage
Attack
DamageType
```

Different subclasses implement their own behavior while sharing common functionality from a parent class.

---

## Technologies Used

* Java
* JavaFX
* Java Collections Framework
* Java Threads
* Java Sockets
* Java File I/O
* JUnit 4

---

## Repository Structure

```text
Java-Quiz-Creating-Project/
│
├── Question Quiz Project/
│   ├── Question.java
│   ├── MCQQuestion.java
│   ├── TFQQuestion.java
│   ├── QuizQuestions.java
│   ├── Quiz.java
│   ├── Main.java
│   ├── QuizProject.java
│   ├── QuizTimer.java
│   ├── QuizEventHandler.java
│   ├── QuizTest.java
│   └── UML.png
│
├── BST1/
│
├── DataStructures/
│   └── Queues/
│
├── Goats/
│
└── README.md
```

---

## Running the Quiz

### Prerequisites

You will need:

* Java JDK
* JavaFX SDK for the graphical version
* JUnit 4 for testing
* A compatible `questionsBase.txt` file

---

### Console Version

Compile the project and run:

```text
Question.Main
```

---

### JavaFX Version

Run:

```text
Question.QuizProject
```

JavaFX must be configured correctly in your IDE or included in the application's module/class path.

---

## Running the Tests

The quiz includes:

```text
QuizTest.java
```

Tests can be executed through an IDE configured with JUnit 4 or using an appropriate Java build configuration.

---

## Skills Demonstrated

This repository demonstrates experience with:

* Java programming
* Object-oriented programming
* Interfaces
* Abstraction
* Encapsulation
* Inheritance
* Polymorphism
* Generics
* JavaFX
* Event-driven programming
* Multithreading
* File handling
* TCP socket communication
* Java Collections
* Randomization
* Binary search trees
* Queues
* Comparable and Comparator
* Unit testing with JUnit
* Software design and UML

---

## Project Scope

These projects were developed as educational Java programming exercises.

The **Quiz Application** is the primary project in the repository, while the additional projects demonstrate individual Java concepts, data structures, and object-oriented programming techniques.

---

## Status

**Completed Educational Java Project Collection**
