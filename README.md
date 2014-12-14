# Matcher
#### A string matcher for special bracket in Code Puzzle.

===

## Match Algorithm

At frist, I use Regular Expression and if else to make this application, but it likes not very beatuiful. And I think Regular Expression is a kind of **Finite-state machine(FSA)**, but FSA can't meet puzzle requirement.Later I think to write a customized **PDA** by myself. Algorithm has 1 automaton with 4 status and 1 stack.

```
enum Status {
    DEFAULT, PARENTHESES, BRACKETS, BRACES
}
```

```
Stack<Status> stack = new Stack<>();
```

**Matchter is a pushdown automaton (PDA)** to match bracket.

**PDA** is type of **automaton** that employs a **stack**.

Pushdown automata are used in theories about what can be computed by machines. They are more capable than finite-state machines but less capable than Turing machines. Deterministic pushdown automata can recognize all deterministic context-free languages while nondeterministic ones can recognize all context-free languages. Mainly the former are used in parser design.

Reference by [wiki for PDA](http://en.wikipedia.org/wiki/Pushdown_automaton)

![Model](http://upload.wikimedia.org/wikipedia/commons/thumb/7/71/Pushdown-overview.svg/500px-Pushdown-overview.svg.png)



## Thread Support
Matcher uses Fork/Join mode [fork/Join model wiki](http://en.wikipedia.org/wiki/Fork%E2%80%93join_model)  
 
![Model](http://upload.wikimedia.org/wikipedia/commons/thumb/f/f1/Fork_join.svg/500px-Fork_join.svg.png)

This is a feature in Java 7 instead of traditional thread in Java5 or java.util.concurrency callable in Java 6. This model is good at parallel compute and multi-core usage.How to use it? [Tutorial forkjoin ](https://docs.oracle.com/javase/tutorial/essential/concurrency/forkjoin.html). Why use it? 

Firstly, it can easy to implement parallel compute on one machine's all CPU core. Secondly, each task can be separated by line and don't share information in same time.Thirdly, without the order function, we can use fork function only and don't need to wait the return (join can be ignored).


## Document 

Using Maven to generate it, doc in target/site/apidocs/index.html 
```
mvn javadoc:javadoc
```
## How to Run it standalone jar?

```java -jar simple-puzzle-1.0.jar ```


Stdin console msg as follows,

Bracket validation Application

1. Enter 'q' or Ctrl+C to quit application
2. Press Enter/Return Key twice to validate.
3. Empty String is invalidated, so application ignores it!

Please enter your string to validate NOW!

## How to Run source code?
Just Use Java Run it, simple!

#####PART I is 
com.aol.adtech.puzzle.runner.SingleAppRunner
#####PART II is
com.aol.adtech.puzzle.runner.MultiAppRunner

## How to test ?
```mvn test```

there are 3 unit test in test package, include function test and multi thread feature function test.

## Make a standalone execute jar
```mvn package```

config main class of pom.xml SingleAppRunner/MultiAppRunner in line
```<mainClass>com.aol.adtech.puzzle.runner.SingleAppRunner</mainClass>```





