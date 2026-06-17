 Code Quest Arena

A colorful, beginner-friendly **Java console quiz game** that tests your knowledge of programming basics, Java syntax, and core CS concepts — built as a fun mini-game rather than a serious exam!

```
=====================================================
            CODE QUEST ARENA  
      Test your coding knowledge - one quest at a time!
=====================================================
```

 Features

 **Game-like console UI** with colors (ANSI escape codes) and emojis
 **7 categories**: Basic Programming Concepts, Java Syntax, Variables & Data Types, Loops & Conditions, Arrays, OOP Basics, and Mixed Questions
 **3 difficulty levels**: Easy, Medium, Hard (harder questions = more points!)
 **56 hand-written questions** covering Java, C, and general programming, with multiple-choice, true/false, and fill-in-the-blank types
 **Speed bonus** — answer quickly for extra points
 **Funny, randomized feedback messages** after every answer
 **Persistent high scores** saved to `highscores.txt`, with a top-5 leaderboard
 **Replay support** — play as many rounds as you like
 A short "fun fact" explanation after every question, win or lose

## Project Structure

```
CodeQuestArena/
├── src/
│   ├── Main.java           # Entry point - welcome screen, menus, game loop
│   ├── Quiz.java           # Core gameplay engine - asks questions, scoring, feedback
│   ├── Question.java       # Model class representing a single question
│   ├── Player.java         # Model class representing the player and their stats
│   ├── QuestionBank.java   # Holds all 56 questions and filters/shuffles them
│   └── ConsoleColors.java  # Small helper for colorful terminal text
└── README.md
```

### Why these classes? (OOP design)

| Class | Responsibility |
|---|---|
| `Main` | Orchestrates the program flow only — no game logic itself |
| `Quiz` | The "game engine" — runs a round, scores answers, prints feedback |
| `Question` | Pure data: text, options, correct answer, category, difficulty |
| `Player` | Pure data: name, score, accuracy — protected via encapsulation |
| `QuestionBank` | Owns the question data and knows how to filter/shuffle it |
| `ScoreManager` | Reads/writes `highscores.txt` for persistent leaderboards |
| `ConsoleColors` | Reusable ANSI color constants, kept separate so it can be swapped out (e.g. for a GUI version) later |

Each class has **one clear job** — this is the Single Responsibility Principle in action, and it makes the project easy to extend.

## ▶️ How to Run

You need a JDK installed (Java 8 or newer).

```bash
# 1. Navigate to the project folder
cd CodeQuestArena

# 2. Compile all source files into a bin/ folder
javac -d bin src/*.java

# 3. Run the game
java -cp bin Main
```

> 💡 **Tip:** If colors/emojis don't display correctly, try running from Git Bash, the VS Code integrated terminal, or Windows Terminal instead of old `cmd.exe`.

##  How to Play

1. Enter your name when prompted.
2. Pick a category (or choose **Mixed Questions** for a big variety pack pulled from every category).
3. Pick a difficulty: Easy, Medium, or Hard.
4. Answer each question by typing the letter (A/B/C/D) — or for True/False questions you can also just type `True` or `False` directly.
5. Get instant feedback, a fun fact, and watch your score grow!
6. See your final score, accuracy, and a tiered ending message.
7. Check the leaderboard and choose to play again.

## 🛠️ How to Add More Questions

Open `QuestionBank.java` and find the `buildXxx()` method for the category you want to extend (e.g. `buildArrays()`). Just copy an existing `Question(...)` block and change the text:

```java
list.add(new Question(
    "Your question text here?",
    opts("Option A", "Option B", "Option C", "Option D"),
    "Option A",          // the correct answer (must match an option exactly)
    cat,                 // category - already set at the top of the method
    "Medium",            // difficulty: Easy / Medium / Hard
    Question.QuestionType.MULTIPLE_CHOICE,
    "A short fun fact shown after answering."
));
```

No other file needs to change — that's the benefit of keeping question data separate from game logic!

##  Possible Future Enhancements

-  **Real countdown timer** per question using a background thread, auto-submitting "no answer" if time runs out (the current version tracks elapsed time for the speed bonus but doesn't forcibly cut the player off)
-  A **Swing GUI version** reusing the same `Question`, `Player`, and `QuestionBank` classes — only the "view" layer would need to change, since the game logic is already decoupled
-  Per-category/per-difficulty statistics (e.g. "You're strongest in OOP Basics!")
-  Load questions from a JSON or CSV file instead of hardcoding them in Java, so non-programmers can contribute questions
-  Multiplayer "pass the keyboard" mode with multiple `Player` objects
-  Unit tests for `Question.isCorrect()` and `QuestionBank.getQuizQuestions()`

##  License

Free to use and modify for learning, portfolios, and fun. Attribution appreciated but not required.

---

Built as a learning project — happy codingggggggggg
