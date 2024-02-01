# Software Construction Problem Set Solution

This repository contains the code, materials, and solution for Problem Set of Software Construction Course. https://openlearninglibrary.mit.edu/courses/course-v1:MITx+6.005.1x+3T2016/about

The three big goals of this course, because this course isn’t really a Java programming course. We want to learn how to write good code in any language, and the three big ideas are about what we mean by good code. It should be:

- safe from bugs, or in other words correct;
- easy to understand for other programmers; and
- ready to be changed in the future.

Topics

- Static typing is a particular kind of static checking, which means checking for bugs at compile time. Bugs are the bane of programming. Many of the ideas in this course are aimed at eliminating bugs from your code, and static checking is the first idea that we've seen for this. Static typing prevents a large class of bugs from infecting your program: to be precise, bugs caused by applying an operation to the wrong types of arguments.

- Documenting Assumptions
  Why do we need to write down our assumptions? Because programming is full of assumptions. If we don't write them down, we won't remember them, and other people who need to read or change our programs later won't know them. They'll have to guess. Programs have to be written with two goals in mind:

  - communicating with the computer. First persuading the compiler that your program is sensible — syntactically correct and type-correct — and then getting the logic right so that it gives the right results at runtime.
  - communicating with other people. Making the program easy to understand, so that when somebody has to fix it, improve it, or adapt it in the future, they can do so.

- These are some things you should start to look when you're looking at your own code for improvement. Don't consider it an exhaustive list of code style guideline:

  - Don't Repeat Yourself (DRY)
  - Comments where needed
  - Fail fast
  - Avoid magic numbers
  - One purpose for each variable
  - Use good names
  - No global variables
  - Return results, don't print them
  - Use whitespace for readability

- Specifications are the linchpin of teamwork. It's impossible to delegate responsibility for implementing a method without a specification. The specification acts as a contract: the implementer is responsible for meeting the contract, and a client that uses the method can rely on the contract. In fact, we'll see that like real legal contracts, specifications place demands on both parties: when the specification has a precondition, the client has responsibilities too.

- Choosing Test Cases by Partitioning,
  the idea behind subdomains is to partition the input space into sets of similar inputs on which the program has similar behavior. Then we use one representative of each set. This approach makes the best use of limited testing resources by choosing dissimilar test cases, and forcing the testing to explore parts of the input space that random testing might not reach.

- Immutable types are safer from bugs, easier to understand, and more ready for change. Mutability makes it harder to understand what your program is doing and much harder to enforce contracts. Getting good performance is one reason why we use mutable objects. Another is convenient sharing: two parts of your program can communicate more conveniently by sharing a common mutable data structure.

- Designing an Abstract Type,
  It's better to have a few, simple operations that can be combined in powerful ways, rather than lots of complex operations. a good abstract data type (ADT) should be representation independent. This means that the use of an abstract type is independent of its representation (the actual data structure or data fields used to implement it)

- Composition over inheritance,
  Composition over inheritance advocates favoring the use of object composition to build flexible and maintainable software systems, emphasizing collaboration and modular design over rigid class hierarchies.
