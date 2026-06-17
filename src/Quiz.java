import java.util.*;

/**
 * Quiz.java
 * ---------------------------------------------------------
 * This is the "game engine" of Code Quest Arena. It takes a
 * Player and a chosen category/difficulty, asks the questions
 * one at a time, gives instant colorful feedback, tracks score
 * and timing, and shows a fun ending message based on how well
 * the player did.
 *
 * Keeping all of this gameplay logic in ONE class (instead of
 * scattering it across Main.java) is a good OOP habit - Main
 * just has to say "new Quiz(...).play()" and doesn't need to
 * know HOW the quiz works internally.
 * ---------------------------------------------------------
 */
public class Quiz {

    private static final int QUESTIONS_PER_ROUND = 10;
    private static final int FAST_ANSWER_BONUS_SECONDS = 10;
    private static final int FAST_ANSWER_BONUS_POINTS = 5;

    private final Player player;
    private final String category;
    private final String difficulty;
    private final QuestionBank questionBank;
    private final Scanner scanner;
    private final ScoreManager scoreManager;

    public Quiz(Player player, String category, String difficulty,
                QuestionBank questionBank, Scanner scanner, ScoreManager scoreManager) {
        this.player = player;
        this.category = category;
        this.difficulty = difficulty;
        this.questionBank = questionBank;
        this.scanner = scanner;
        this.scoreManager = scoreManager;
    }

    /** Runs one full quiz round from start to finish. */
    public void play() {
        player.resetForNewRound();

        List<Question> questions = questionBank.getQuizQuestions(category, difficulty, QUESTIONS_PER_ROUND);

        if (questions.isEmpty()) {
            System.out.println(ConsoleColors.colorize(
                    "😅 No questions found for that combo yet! Try a different category or difficulty.",
                    ConsoleColors.YELLOW));
            return;
        }

        if (questions.size() < QUESTIONS_PER_ROUND) {
            System.out.println(ConsoleColors.colorize(
                    "ℹ️  Only " + questions.size() + " questions available for this combo right now - let's go anyway!",
                    ConsoleColors.CYAN));
        }

        System.out.println(ConsoleColors.colorize(
                "\n🎮 Starting " + category + " (" + difficulty + ") - " + questions.size() + " questions!",
                ConsoleColors.BRIGHT_CYAN));

        int totalQuestions = questions.size();
        for (int i = 0; i < totalQuestions; i++) {
            askQuestion(questions.get(i), i + 1, totalQuestions);
        }

        showFinalResults();
        scoreManager.saveScore(player, category, difficulty);
    }

    /** Asks a single question, reads the player's answer, and gives feedback. */
    private void askQuestion(Question q, int questionNumber, int totalQuestions) {
        System.out.println();
        System.out.println(ConsoleColors.colorize(
                "📋 Question " + questionNumber + "/" + totalQuestions
                        + "  |  " + q.getCategory() + "  |  " + q.getDifficulty(),
                ConsoleColors.PURPLE));
        System.out.println(ConsoleColors.colorize(q.getQuestionText(), ConsoleColors.BRIGHT_YELLOW));

        String playerAnswer;
        long startTime = System.currentTimeMillis();

        if (q.getType() == Question.QuestionType.FILL_IN_BLANK) {
            System.out.print(ConsoleColors.colorize("✏️  Type your answer: ", ConsoleColors.WHITE));
            playerAnswer = scanner.nextLine().trim();
        } else {
            // Multiple choice AND True/False both use lettered options (A, B, C...)
            List<String> options = q.getOptions();
            char letter = 'A';
            for (String option : options) {
                System.out.println("   " + letter + ". " + option);
                letter++;
            }
            System.out.print(ConsoleColors.colorize("👉 Your answer (letter): ", ConsoleColors.WHITE));
            String raw = scanner.nextLine().trim();
            playerAnswer = convertLetterToOptionText(raw, options);
        }

        long elapsedSeconds = (System.currentTimeMillis() - startTime) / 1000;

        boolean correct = q.isCorrect(playerAnswer);
        int pointsEarned = 0;

        if (correct) {
            pointsEarned = q.getBasePoints();
            if (elapsedSeconds <= FAST_ANSWER_BONUS_SECONDS) {
                pointsEarned += FAST_ANSWER_BONUS_POINTS;
                System.out.println(ConsoleColors.colorize(
                        "⚡ Speed bonus! +" + FAST_ANSWER_BONUS_POINTS + " pts for a quick answer ("
                                + elapsedSeconds + "s)", ConsoleColors.BRIGHT_GREEN));
            }
            System.out.println(ConsoleColors.colorize(randomCorrectMessage() + "  (+" + pointsEarned + " pts)",
                    ConsoleColors.BRIGHT_GREEN));
        } else {
            System.out.println(ConsoleColors.colorize(
                    randomWrongMessage() + " The correct answer was: " + q.getCorrectAnswer(),
                    ConsoleColors.BRIGHT_RED));
        }

        System.out.println(ConsoleColors.colorize("💡 " + q.getFunFact(), ConsoleColors.CYAN));

        player.recordAnswer(correct, pointsEarned);
    }

    /**
     * Converts a typed letter like "a", "B", "c" into the matching
     * option text, e.g. "A" -> options.get(0). If the player typed
     * something unexpected, we just return what they typed so it
     * simply counts as wrong instead of crashing the program.
     */
    private String convertLetterToOptionText(String rawInput, List<String> options) {
        if (rawInput.isEmpty()) {
            return "";
        }
        String trimmed = rawInput.trim();

        // First, allow players to type the full option directly,
        // e.g. typing "True" works just as well as typing "A".
        for (String option : options) {
            if (option.equalsIgnoreCase(trimmed)) {
                return option;
            }
        }

        // Otherwise, treat the input as a letter (A, B, C, D...).
        char firstChar = Character.toUpperCase(trimmed.charAt(0));
        int index = firstChar - 'A';

        if (index >= 0 && index < options.size()) {
            return options.get(index);
        }
        return rawInput; // not a valid letter or option - will simply be marked wrong
    }

    private String randomCorrectMessage() {
        String[] messages = {
                "🎉 Correct! You're coding like a pro 😎",
                "✅ Nailed it! Keep that streak going!",
                "🔥 Boom! That's the right answer!",
                "🚀 Yes! Your brain compiles faster than javac!",
                "🌟 Spot on! You really know your stuff!"
        };
        return messages[new Random().nextInt(messages.length)];
    }

    private String randomWrongMessage() {
        String[] messages = {
                "😂 Oops! Even programmers Google things sometimes.",
                "🤔 Not quite - but every bug teaches a lesson!",
                "💭 Close, but no cigar this time.",
                "🛠️ That's a bug in your logic, not your potential!",
                "😅 Nope! On to the next one, champ."
        };
        return messages[new Random().nextInt(messages.length)];
    }

    /** Prints the final score, accuracy, and a tiered fun ending message. */
    private void showFinalResults() {
        double accuracy = player.getAccuracyPercent();

        System.out.println(ConsoleColors.colorize(
                "\n================ 🏁 QUIZ COMPLETE 🏁 ================", ConsoleColors.BRIGHT_PURPLE));
        System.out.println(ConsoleColors.colorize("Player: " + player.getName(), ConsoleColors.WHITE));
        System.out.println(ConsoleColors.colorize("Correct Answers: " + player.getCorrectAnswers()
                + " / " + player.getTotalQuestionsAnswered(), ConsoleColors.WHITE));
        System.out.println(ConsoleColors.colorize(
                "Accuracy: " + String.format("%.1f", accuracy) + "%", ConsoleColors.WHITE));
        System.out.println(ConsoleColors.colorize("Final Score: " + player.getScore() + " pts",
                ConsoleColors.BRIGHT_YELLOW));

        System.out.println(ConsoleColors.colorize(getEndingMessage(accuracy), ConsoleColors.BRIGHT_GREEN));
        System.out.println(ConsoleColors.colorize("=====================================================\n",
                ConsoleColors.BRIGHT_PURPLE));
    }

    private String getEndingMessage(double accuracy) {
        if (accuracy >= 90) {
            return "🏆 LEGENDARY CODER! You're basically a human compiler!";
        } else if (accuracy >= 70) {
            return "😎 Awesome! You're coding like a true pro!";
        } else if (accuracy >= 50) {
            return "🙂 Solid effort! A bit more practice and you'll be unstoppable.";
        } else if (accuracy >= 30) {
            return "😂 Oops! Even programmers Google things - keep practicing!";
        } else {
            return "💻 Everyone starts somewhere - go review the basics and try again!";
        }
    }
}
