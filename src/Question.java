import java.util.List;

/**
 * Question.java
 * ---------------------------------------------------------
 * Represents a single quiz question. This is a plain "model"
 * class - it just holds data about one question and has no
 * game logic in it at all. Keeping data and logic separate is
 * a core Object-Oriented Programming (OOP) idea!
 *
 * A Question knows:
 *  - what it asks (questionText)
 *  - what choices the player can pick from (options)
 *  - what the correct answer is (correctAnswer)
 *  - which category and difficulty it belongs to
 *  - what type of question it is (MCQ / True-False / Fill-in-blank)
 *  - a short fun explanation shown after the player answers
 * ---------------------------------------------------------
 */
public class Question {

    /**
     * QuestionType is an enum - a special Java type used when a
     * variable can only hold one of a fixed set of values.
     * Here a Question can only ever be ONE of these three kinds.
     */
    public enum QuestionType {
        MULTIPLE_CHOICE,
        TRUE_FALSE,
        FILL_IN_BLANK
    }

    private final String questionText;
    private final List<String> options;     // 4 options for MCQ, 2 for True/False, empty for Fill-in-blank
    private final String correctAnswer;      // exact correct option text (or correct word for fill-in-blank)
    private final String category;
    private final String difficulty;         // "Easy", "Medium", or "Hard"
    private final QuestionType type;
    private final String funFact;            // short explanation/fun fact shown after answering

    public Question(String questionText, List<String> options, String correctAnswer,
                     String category, String difficulty, QuestionType type, String funFact) {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswer = correctAnswer;
        this.category = category;
        this.difficulty = difficulty;
        this.type = type;
        this.funFact = funFact;
    }

    // ----- Getters (no setters needed - once created, a question never changes) -----

    public String getQuestionText() {
        return questionText;
    }

    public List<String> getOptions() {
        return options;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public String getCategory() {
        return category;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public QuestionType getType() {
        return type;
    }

    public String getFunFact() {
        return funFact;
    }

    /**
     * Checks whether a player's typed answer is correct.
     * For Fill-in-the-blank questions we ignore case and extra
     * spaces so beginners typing "java virtual machine" or
     * "Java Virtual Machine" both count as correct.
     */
    public boolean isCorrect(String playerAnswer) {
        if (playerAnswer == null) {
            return false;
        }
        return playerAnswer.trim().equalsIgnoreCase(correctAnswer.trim());
    }

    /**
     * Returns how many points this question is worth based on its
     * difficulty. Harder questions give more points - this rewards
     * players for taking on a challenge!
     */
    public int getBasePoints() {
        switch (difficulty) {
            case "Easy":
                return 10;
            case "Medium":
                return 20;
            case "Hard":
                return 30;
            default:
                return 10;
        }
    }
}
