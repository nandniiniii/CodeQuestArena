import java.util.*;

/**
 * QuestionBank.java
 * ---------------------------------------------------------
 * Holds every question available in Code Quest Arena and knows
 * how to filter/shuffle them for a quiz round.
 *
 * This is a great place to ADD MORE QUESTIONS LATER - just copy
 * one of the buildXxx() methods below and add new Question
 * objects to the list. The rest of the program doesn't need to
 * change at all, which is the power of good OOP design!
 * ---------------------------------------------------------
 */
public class QuestionBank {

    public static final String CATEGORY_BASIC = "Basic Programming Concepts";
    public static final String CATEGORY_JAVA_SYNTAX = "Java Syntax";
    public static final String CATEGORY_VARIABLES = "Variables and Data Types";
    public static final String CATEGORY_LOOPS = "Loops and Conditions";
    public static final String CATEGORY_ARRAYS = "Arrays";
    public static final String CATEGORY_OOP = "OOP Basics";
    public static final String CATEGORY_MIXED = "Mixed Questions";

    public static final String[] ALL_CATEGORIES = {
            CATEGORY_BASIC, CATEGORY_JAVA_SYNTAX, CATEGORY_VARIABLES,
            CATEGORY_LOOPS, CATEGORY_ARRAYS, CATEGORY_OOP, CATEGORY_MIXED
    };

    public static final String[] ALL_DIFFICULTIES = {"Easy", "Medium", "Hard"};

    private final List<Question> allQuestions;

    public QuestionBank() {
        allQuestions = new ArrayList<>();
        allQuestions.addAll(buildBasicConcepts());
        allQuestions.addAll(buildJavaSyntax());
        allQuestions.addAll(buildVariables());
        allQuestions.addAll(buildLoops());
        allQuestions.addAll(buildArrays());
        allQuestions.addAll(buildOop());
        allQuestions.addAll(buildMixed());
    }

    /** Small helper so we don't repeat List.of(...) everywhere below. */
    private static List<String> opts(String... values) {
        return new ArrayList<>(Arrays.asList(values));
    }

    private static List<String> trueFalseOpts() {
        return opts("True", "False");
    }

    // ----------------------------------------------------------------
    // 1. Basic Programming Concepts
    // ----------------------------------------------------------------
    private List<Question> buildBasicConcepts() {
        List<Question> list = new ArrayList<>();
        String cat = CATEGORY_BASIC;

        list.add(new Question("What does CPU stand for?",
                opts("Central Processing Unit", "Computer Power Unit", "Central Power Unit", "Core Processing Unit"),
                "Central Processing Unit", cat, "Easy", Question.QuestionType.MULTIPLE_CHOICE,
                "The CPU is the 'brain' of the computer that executes instructions."));

        list.add(new Question("A compiler translates source code into machine code.",
                trueFalseOpts(), "True", cat, "Easy", Question.QuestionType.TRUE_FALSE,
                "Compilers like javac turn human-readable code into something the computer understands."));

        list.add(new Question("Which of these is NOT a programming paradigm?",
                opts("Object-Oriented", "Procedural", "Functional", "Alphabetical"),
                "Alphabetical", cat, "Easy", Question.QuestionType.MULTIPLE_CHOICE,
                "Alphabetical ordering is not a way of structuring programs!"));

        list.add(new Question("An algorithm is a step-by-step procedure to solve a problem.",
                trueFalseOpts(), "True", cat, "Easy", Question.QuestionType.TRUE_FALSE,
                "Even a recipe for tea is technically an algorithm!"));

        list.add(new Question("What is the short form for 'Integrated Development Environment'?",
                opts(), "IDE", cat, "Easy", Question.QuestionType.FILL_IN_BLANK,
                "VS Code, IntelliJ, and Eclipse are all popular IDEs."));

        list.add(new Question("Which of these is an example of a low-level language?",
                opts("Assembly", "Java", "Python", "C#"),
                "Assembly", cat, "Medium", Question.QuestionType.MULTIPLE_CHOICE,
                "Low-level languages are closer to machine code than human language."));

        list.add(new Question("Debugging means finding and fixing errors in code.",
                trueFalseOpts(), "True", cat, "Easy", Question.QuestionType.TRUE_FALSE,
                "The term comes from removing an actual moth from a computer in 1947!"));

        list.add(new Question("Which symbol starts a single-line comment in both C and Java?",
                opts(), "//", cat, "Easy", Question.QuestionType.FILL_IN_BLANK,
                "Anything after // on that line is ignored by the compiler."));

        return list;
    }

    // ----------------------------------------------------------------
    // 2. Java Syntax
    // ----------------------------------------------------------------
    private List<Question> buildJavaSyntax() {
        List<Question> list = new ArrayList<>();
        String cat = CATEGORY_JAVA_SYNTAX;

        list.add(new Question("Which keyword is used to define a class in Java?",
                opts("class", "struct", "define", "object"),
                "class", cat, "Easy", Question.QuestionType.MULTIPLE_CHOICE,
                "Every Java program is built from one or more classes."));

        list.add(new Question("Every Java application must have a method named ___ as its entry point.",
                opts(), "main", cat, "Easy", Question.QuestionType.FILL_IN_BLANK,
                "public static void main(String[] args) is where execution begins."));

        list.add(new Question("Java statements end with a semicolon (;).",
                trueFalseOpts(), "True", cat, "Easy", Question.QuestionType.TRUE_FALSE,
                "Forgetting a semicolon is one of the most common beginner mistakes!"));

        list.add(new Question("Which is the correct way to print 'Hello' in Java?",
                opts("System.out.println(\"Hello\");", "print(\"Hello\");", "echo \"Hello\";", "cout << \"Hello\";"),
                "System.out.println(\"Hello\");", cat, "Easy", Question.QuestionType.MULTIPLE_CHOICE,
                "System.out is Java's standard output stream."));

        list.add(new Question("What is the file extension for Java source files?",
                opts(), ".java", cat, "Easy", Question.QuestionType.FILL_IN_BLANK,
                "After compiling, you get a .class file containing bytecode."));

        list.add(new Question("Which of these is NOT a valid Java access modifier?",
                opts("public", "private", "protected", "internal"),
                "internal", cat, "Medium", Question.QuestionType.MULTIPLE_CHOICE,
                "'internal' is a C# keyword, not a Java one!"));

        list.add(new Question("Java is a case-sensitive language.",
                trueFalseOpts(), "True", cat, "Easy", Question.QuestionType.TRUE_FALSE,
                "'myVar' and 'myvar' would be treated as two different variables."));

        list.add(new Question("Which keyword is used to import a package in Java?",
                opts("import", "include", "using", "require"),
                "import", cat, "Easy", Question.QuestionType.MULTIPLE_CHOICE,
                "Example: import java.util.ArrayList;"));

        return list;
    }

    // ----------------------------------------------------------------
    // 3. Variables and Data Types
    // ----------------------------------------------------------------
    private List<Question> buildVariables() {
        List<Question> list = new ArrayList<>();
        String cat = CATEGORY_VARIABLES;

        list.add(new Question("Which keyword declares a constant in Java?",
                opts("final", "const", "static", "constant"),
                "final", cat, "Medium", Question.QuestionType.MULTIPLE_CHOICE,
                "Once a 'final' variable is assigned, it can never change."));

        list.add(new Question("What is the default value of a boolean variable in Java?",
                opts("true", "false", "0", "null"),
                "false", cat, "Medium", Question.QuestionType.MULTIPLE_CHOICE,
                "Uninitialized instance boolean fields default to false."));

        list.add(new Question("'int' is a primitive data type in Java.",
                trueFalseOpts(), "True", cat, "Easy", Question.QuestionType.TRUE_FALSE,
                "Java's 8 primitive types are byte, short, int, long, float, double, char, boolean."));

        list.add(new Question("Which data type would you use to store a single character?",
                opts("char", "String", "byte", "int"),
                "char", cat, "Easy", Question.QuestionType.MULTIPLE_CHOICE,
                "char uses single quotes like 'A', while String uses double quotes."));

        list.add(new Question("What is the size of an int in Java?",
                opts("16 bits", "32 bits", "64 bits", "8 bits"),
                "32 bits", cat, "Medium", Question.QuestionType.MULTIPLE_CHOICE,
                "An int can store values from about -2.1 billion to +2.1 billion."));

        list.add(new Question("String is a primitive data type in Java.",
                trueFalseOpts(), "False", cat, "Medium", Question.QuestionType.TRUE_FALSE,
                "String is actually a class - it's an object, not a primitive!"));

        list.add(new Question("Which keyword is used to declare a variable that cannot be reassigned?",
                opts(), "final", cat, "Medium", Question.QuestionType.FILL_IN_BLANK,
                "final is also used for constants and to prevent method overriding."));

        list.add(new Question("Which is the correct way to declare a double variable named pi?",
                opts("double pi;", "int pi;", "pi double;", "var pi double;"),
                "double pi;", cat, "Easy", Question.QuestionType.MULTIPLE_CHOICE,
                "In Java, the type always comes before the variable name."));

        return list;
    }

    // ----------------------------------------------------------------
    // 4. Loops and Conditions
    // ----------------------------------------------------------------
    private List<Question> buildLoops() {
        List<Question> list = new ArrayList<>();
        String cat = CATEGORY_LOOPS;

        list.add(new Question("Which loop is guaranteed to execute its body at least once?",
                opts("for", "while", "do-while", "foreach"),
                "do-while", cat, "Medium", Question.QuestionType.MULTIPLE_CHOICE,
                "do-while checks its condition AFTER running the loop body once."));

        list.add(new Question("Which keyword immediately exits a loop in Java?",
                opts("break", "continue", "return", "exit"),
                "break", cat, "Easy", Question.QuestionType.MULTIPLE_CHOICE,
                "break jumps straight out of the nearest loop or switch."));

        list.add(new Question("The 'continue' statement skips the current iteration and moves to the next.",
                trueFalseOpts(), "True", cat, "Easy", Question.QuestionType.TRUE_FALSE,
                "continue is handy for skipping specific cases without exiting the whole loop."));

        list.add(new Question("Which loop is best when you know exactly how many times to iterate?",
                opts("for", "while", "do-while", "none of these"),
                "for", cat, "Easy", Question.QuestionType.MULTIPLE_CHOICE,
                "for(int i = 0; i < n; i++) is the classic counted loop."));

        list.add(new Question("In Java, '==' is used for assignment.",
                trueFalseOpts(), "False", cat, "Easy", Question.QuestionType.TRUE_FALSE,
                "Single '=' assigns a value, double '==' compares two values."));

        list.add(new Question("What does a switch statement use to decide which block runs?",
                opts("case", "if", "loop", "condition"),
                "case", cat, "Easy", Question.QuestionType.MULTIPLE_CHOICE,
                "Each 'case' label matches one possible value of the switch expression."));

        list.add(new Question("An infinite loop runs forever unless explicitly stopped.",
                trueFalseOpts(), "True", cat, "Easy", Question.QuestionType.TRUE_FALSE,
                "while(true) { } is a classic (accidental!) infinite loop example."));

        list.add(new Question("Which keyword tests multiple possible values of a single variable?",
                opts(), "switch", cat, "Medium", Question.QuestionType.FILL_IN_BLANK,
                "switch is often cleaner than a long chain of if-else-if statements."));

        return list;
    }

    // ----------------------------------------------------------------
    // 5. Arrays
    // ----------------------------------------------------------------
    private List<Question> buildArrays() {
        List<Question> list = new ArrayList<>();
        String cat = CATEGORY_ARRAYS;

        list.add(new Question("Array indices in Java start from ___.",
                opts(), "0", cat, "Easy", Question.QuestionType.FILL_IN_BLANK,
                "The first element of any array is at index 0, not 1."));

        list.add(new Question("Arrays in Java have a fixed size once created.",
                trueFalseOpts(), "True", cat, "Medium", Question.QuestionType.TRUE_FALSE,
                "If you need a resizable list, use ArrayList instead."));

        list.add(new Question("How do you get the number of elements in a Java array named arr?",
                opts("arr.length", "arr.length()", "arr.size()", "length(arr)"),
                "arr.length", cat, "Medium", Question.QuestionType.MULTIPLE_CHOICE,
                "length is a property (no parentheses), unlike String's length() method!"));

        list.add(new Question("What does arr.length return for int[] arr = {1,2,3,4,5};?",
                opts("4", "5", "6", "Error"),
                "5", cat, "Easy", Question.QuestionType.MULTIPLE_CHOICE,
                "There are exactly 5 elements in that array."));

        list.add(new Question("A 2D array in Java can be thought of as an array of arrays.",
                trueFalseOpts(), "True", cat, "Medium", Question.QuestionType.TRUE_FALSE,
                "int[][] grid is really an array where each element is itself an int[]."));

        list.add(new Question("Which exception is thrown when you access an invalid array index?",
                opts("ArrayIndexOutOfBoundsException", "NullPointerException", "ArithmeticException", "ClassCastException"),
                "ArrayIndexOutOfBoundsException", cat, "Hard", Question.QuestionType.MULTIPLE_CHOICE,
                "Always make sure your index is between 0 and arr.length - 1."));

        list.add(new Question("Which keyword is used to create a new array in Java?",
                opts("new", "create", "array", "make"),
                "new", cat, "Easy", Question.QuestionType.MULTIPLE_CHOICE,
                "Example: int[] nums = new int[5];"));

        list.add(new Question("ArrayList has a fixed size like a normal array.",
                trueFalseOpts(), "False", cat, "Medium", Question.QuestionType.TRUE_FALSE,
                "ArrayList automatically grows and shrinks as you add/remove elements."));

        return list;
    }

    // ----------------------------------------------------------------
    // 6. OOP Basics
    // ----------------------------------------------------------------
    private List<Question> buildOop() {
        List<Question> list = new ArrayList<>();
        String cat = CATEGORY_OOP;

        list.add(new Question("Which OOP concept lets a class inherit properties from another class?",
                opts("Inheritance", "Encapsulation", "Polymorphism", "Abstraction"),
                "Inheritance", cat, "Easy", Question.QuestionType.MULTIPLE_CHOICE,
                "Think of it like a child inheriting traits from a parent class."));

        list.add(new Question("Which keyword is used for inheritance in Java?",
                opts("extends", "implements", "inherits", "super"),
                "extends", cat, "Easy", Question.QuestionType.MULTIPLE_CHOICE,
                "class Dog extends Animal lets Dog reuse Animal's code."));

        list.add(new Question("Wrapping data and methods together into a single unit is called ___.",
                opts(), "encapsulation", cat, "Medium", Question.QuestionType.FILL_IN_BLANK,
                "Private fields with public getters/setters is classic encapsulation."));

        list.add(new Question("A class can implement multiple interfaces in Java.",
                trueFalseOpts(), "True", cat, "Medium", Question.QuestionType.TRUE_FALSE,
                "Unlike classes, Java allows 'multiple inheritance' through interfaces."));

        list.add(new Question("Which OOP principle allows the same method name to behave differently?",
                opts("Polymorphism", "Inheritance", "Abstraction", "Encapsulation"),
                "Polymorphism", cat, "Medium", Question.QuestionType.MULTIPLE_CHOICE,
                "Method overriding and overloading are both forms of polymorphism."));

        list.add(new Question("A constructor has the same name as its class.",
                trueFalseOpts(), "True", cat, "Easy", Question.QuestionType.TRUE_FALSE,
                "Constructors also have no return type, not even void."));

        list.add(new Question("Which keyword is used to create an object in Java?",
                opts("new", "create", "object", "make"),
                "new", cat, "Easy", Question.QuestionType.MULTIPLE_CHOICE,
                "Example: Player p = new Player(\"Nandni\");"));

        list.add(new Question("An abstract class can have both abstract and concrete methods.",
                trueFalseOpts(), "True", cat, "Hard", Question.QuestionType.TRUE_FALSE,
                "This makes abstract classes flexible 'partial blueprints' for subclasses."));

        return list;
    }

    // ----------------------------------------------------------------
    // 7. Mixed Questions (general programming + C + Java)
    // ----------------------------------------------------------------
    private List<Question> buildMixed() {
        List<Question> list = new ArrayList<>();
        String cat = CATEGORY_MIXED;

        list.add(new Question("Which language is 'platform-independent' thanks to its bytecode?",
                opts("Java", "C", "C++", "Assembly"),
                "Java", cat, "Easy", Question.QuestionType.MULTIPLE_CHOICE,
                "That's the famous 'write once, run anywhere' idea, powered by the JVM."));

        list.add(new Question("In C, which function is used to print output to the console?",
                opts("printf()", "print()", "echo()", "System.out.println()"),
                "printf()", cat, "Easy", Question.QuestionType.MULTIPLE_CHOICE,
                "printf comes from the <stdio.h> header in C."));

        list.add(new Question("JVM stands for ___.",
                opts(), "Java Virtual Machine", cat, "Easy", Question.QuestionType.FILL_IN_BLANK,
                "The JVM is what actually runs your compiled .class bytecode."));

        list.add(new Question("Recursion is when a function calls itself.",
                trueFalseOpts(), "True", cat, "Easy", Question.QuestionType.TRUE_FALSE,
                "Every recursive function needs a 'base case' or it loops forever!"));

        list.add(new Question("Which of these is a popular version control system?",
                opts("Git", "GIMP", "GCC", "GUI"),
                "Git", cat, "Easy", Question.QuestionType.MULTIPLE_CHOICE,
                "Git is exactly what you'll use to push this project to GitHub!"));

        list.add(new Question("Which data structure follows LIFO (Last In, First Out)?",
                opts("Stack", "Queue", "Array", "LinkedList"),
                "Stack", cat, "Medium", Question.QuestionType.MULTIPLE_CHOICE,
                "Think of a stack of plates - you take from the top, last one placed!"));

        list.add(new Question("Big-O notation is used to describe the time complexity of an algorithm.",
                trueFalseOpts(), "True", cat, "Medium", Question.QuestionType.TRUE_FALSE,
                "You'll see this constantly once you start DSA - O(n), O(log n), O(n^2)..."));

        list.add(new Question("Which of these is NOT a sorting algorithm?",
                opts("Bubble Sort", "Quick Sort", "Binary Search", "Merge Sort"),
                "Binary Search", cat, "Hard", Question.QuestionType.MULTIPLE_CHOICE,
                "Binary Search finds an element - it doesn't sort a list!"));

        return list;
    }

    // ----------------------------------------------------------------
    // Public methods used by Quiz.java
    // ----------------------------------------------------------------

    /** Total number of questions currently in the bank. */
    public int totalQuestionCount() {
        return allQuestions.size();
    }

    /**
     * Returns a shuffled list of questions for a quiz round.
     *
     * Special case: if the player picked "Mixed Questions" as the
     * category, we pull from ALL categories (filtered only by
     * difficulty) so there are always plenty of questions to play
     * with - this is what makes "Mixed Questions" feel like the
     * big variety pack!
     */
    public List<Question> getQuizQuestions(String category, String difficulty, int howMany) {
        List<Question> matches = new ArrayList<>();

        for (Question q : allQuestions) {
            boolean difficultyMatches = q.getDifficulty().equalsIgnoreCase(difficulty);
            boolean categoryMatches = category.equals(CATEGORY_MIXED) || q.getCategory().equals(category);

            if (difficultyMatches && categoryMatches) {
                matches.add(q);
            }
        }

        Collections.shuffle(matches);

        if (matches.size() > howMany) {
            return new ArrayList<>(matches.subList(0, howMany));
        }
        return matches; // could be fewer than requested - Quiz.java handles that gracefully
    }
}
