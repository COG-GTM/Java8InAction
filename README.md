Java8InAction
===============

This repository contains all the source code for the examples and quizzes in the book Java 8 in Action: Lambdas, Streams and functional-style programming.

You can purchase the book here: [http://manning.com/urma/](http://manning.com/urma/) or on Amazon

The source code for all examples can be found in the directory [src/main/java/lambdasinaction](https://github.com/java8/Java8InAction/tree/master/src/main/java/lambdasinaction)

* Chapter 1: Java 8: why should you care?
* Chapter 2: Passing code with behavior parameterization
* Chapter 3: Lambda expressions
* Chapter 4: Working with Streams
* Chapter 5: Processing data with streams
* Chapter 6: Collecting data with streams
* Chapter 7: Parallel data processing and performance
* Chapter 8: Refactoring, testing, debugging
* Chapter 9: Default methods
* Chapter 10: Using Optional as a better alternative to null
* Chapter 11: CompletableFuture: composable asynchronous programming
* Chapter 12: New Date and Time API
* Chapter 13: Thinking functionally
* Chapter 14: Functional programming techniques
* Chapter 15: Blending OOP and FP: comparing Java 8 and Scala
* Chapter 16: Conclusions and "where next" for Java
* Appendix A: Miscellaneous language updates
* Appendix B: Miscellaneous library updates
* Appendix C: Performing multiple operations in parallel on a Stream
* Appendix D: Lambdas and JVM bytecode
We will update the repository as we update the book. Stay tuned!

### Prerequisites

**JDK 11** or higher is required. You can use any distribution (Temurin, OpenJDK, Oracle JDK, etc.).

```
$ java -version
openjdk version "11.0.x" ...
```

Download Temurin JDK 11: https://adoptium.net/temurin/releases/?version=11

### Compile/Run the examples

Using Maven:

```
$ mvn clean compile
$ cd target/classes
$ java lambdasinaction/chap1/FilteringApples
```

Run tests:

```
$ mvn test
```

Alternatively you can import the project in your favorite IDE:
  * In IntelliJ use "File->Open" menu and navigate to the folder where the project resides
  * In Eclipse use "File->Import->Existing Maven Projects"
  * In Netbeans use "File->Open Project" menu