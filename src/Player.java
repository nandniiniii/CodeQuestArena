/**
 * Player.java
 * ---------------------------------------------------------
 * Represents the person playing Code Quest Arena. Keeps track
 * of their name, score, and simple stats during a quiz round.
 *
 * This class demonstrates ENCAPSULATION - all the fields are
 * private, and other classes can only read or change them
 * through public methods (getters/setters). This protects the
 * player's data from being changed in unexpected ways.
 * ---------------------------------------------------------
 */
public class Player {

    private String name;
    private int score;
    private int correctAnswers;
    private int totalQuestionsAnswered;

    public Player(String name) {
        this.name = name;
        this.score = 0;
        this.correctAnswers = 0;
        this.totalQuestionsAnswered = 0;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public int getCorrectAnswers() {
        return correctAnswers;
    }

    public int getTotalQuestionsAnswered() {
        return totalQuestionsAnswered;
    }

    /**
     * Call this whenever the player answers a question, correct
     * or not. Keeping this in one method means the counting logic
     * lives in exactly one place.
     */
    public void recordAnswer(boolean wasCorrect, int pointsEarned) {
        totalQuestionsAnswered++;
        if (wasCorrect) {
            correctAnswers++;
            score += pointsEarned;
        }
    }

    /**
     * Returns the player's accuracy as a percentage (0-100).
     * Useful for picking which "ending message" to show.
     */
    public double getAccuracyPercent() {
        if (totalQuestionsAnswered == 0) {
            return 0;
        }
        return (correctAnswers * 100.0) / totalQuestionsAnswered;
    }

    /** Resets stats so the same Player object can play again. */
    public void resetForNewRound() {
        score = 0;
        correctAnswers = 0;
        totalQuestionsAnswered = 0;
    }
}
