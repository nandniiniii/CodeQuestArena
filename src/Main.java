import java.util.Scanner;

/**
 * Main.java
 * ---------------------------------------------------------
 * The entry point of Code Quest Arena 🚀
 *
 * This class is intentionally "thin" - its only job is to:
 *   1. Show a welcome screen and ask the player's name
 *   2. Show menus for picking a category and difficulty
 *   3. Hand control over to a Quiz object to actually run the game
 *   4. Ask if the player wants to play again
 *
 * All the real game logic lives in Quiz.java, Question.java,
 * Player.java, QuestionBank.java, and ScoreManager.java. This
 * separation of concerns is a key OOP / software design idea.
 * ---------------------------------------------------------
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        QuestionBank questionBank = new QuestionBank();
        ScoreManager scoreManager = new ScoreManager();

        printWelcomeBanner();

        System.out.print(ConsoleColors.colorize("👤 Enter your name, coder: ", ConsoleColors.BRIGHT_CYAN));
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) {
            name = "Mystery Coder";
        }
        Player player = new Player(name);

        System.out.println(ConsoleColors.colorize(
                "\nWelcome aboard, " + player.getName() + "! Let's see what you've got. 💪\n",
                ConsoleColors.BRIGHT_GREEN));

        boolean playAgain = true;
        while (playAgain) {
            String category = chooseCategory(scanner);
            String difficulty = chooseDifficulty(scanner);

            Quiz quiz = new Quiz(player, category, difficulty, questionBank, scanner, scoreManager);
            quiz.play();

            scoreManager.printLeaderboard(5);

            playAgain = askYesNo(scanner, "🔁 Want to play another round? (Y/N): ");
        }

        System.out.println(ConsoleColors.colorize(
                "\n👋 Thanks for playing, " + player.getName() + "! Keep leveling up your coding skills. See you next time! 🚀\n",
                ConsoleColors.BRIGHT_CYAN));

        scanner.close();
    }

    private static void printWelcomeBanner() {
        System.out.println(ConsoleColors.colorize(
                "=====================================================", ConsoleColors.BRIGHT_PURPLE));
        System.out.println(ConsoleColors.colorize(
                "          🚀  CODE QUEST ARENA  🚀", ConsoleColors.BRIGHT_YELLOW));
        System.out.println(ConsoleColors.colorize(
                "      Test your coding knowledge - one quest at a time!", ConsoleColors.BRIGHT_CYAN));
        System.out.println(ConsoleColors.colorize(
                "=====================================================\n", ConsoleColors.BRIGHT_PURPLE));
    }

    /** Shows the category menu and returns the player's chosen category. */
    private static String chooseCategory(Scanner scanner) {
        String[] categories = QuestionBank.ALL_CATEGORIES;

        System.out.println(ConsoleColors.colorize("\n📚 Choose a category:", ConsoleColors.BRIGHT_CYAN));
        for (int i = 0; i < categories.length; i++) {
            System.out.println("   " + (i + 1) + ". " + categories[i]);
        }

        int choice = readIntInRange(scanner, "👉 Enter a number: ", 1, categories.length);
        return categories[choice - 1];
    }

    /** Shows the difficulty menu and returns the player's chosen difficulty. */
    private static String chooseDifficulty(Scanner scanner) {
        String[] difficulties = QuestionBank.ALL_DIFFICULTIES;

        System.out.println(ConsoleColors.colorize("\n🎯 Choose a difficulty:", ConsoleColors.BRIGHT_CYAN));
        for (int i = 0; i < difficulties.length; i++) {
            System.out.println("   " + (i + 1) + ". " + difficulties[i]);
        }

        int choice = readIntInRange(scanner, "👉 Enter a number: ", 1, difficulties.length);
        return difficulties[choice - 1];
    }

    /**
     * Keeps asking until the player types a valid number in range.
     * This protects the program from crashing on bad input - a nice
     * beginner-friendly habit to build early!
     */
    private static int readIntInRange(Scanner scanner, String prompt, int min, int max) {
        while (true) {
            System.out.print(ConsoleColors.colorize(prompt, ConsoleColors.WHITE));
            String input = scanner.nextLine().trim();
            try {
                int value = Integer.parseInt(input);
                if (value >= min && value <= max) {
                    return value;
                }
                System.out.println(ConsoleColors.colorize(
                        "⚠️  Please enter a number between " + min + " and " + max + ".", ConsoleColors.YELLOW));
            } catch (NumberFormatException e) {
                System.out.println(ConsoleColors.colorize(
                        "⚠️  That's not a valid number, try again.", ConsoleColors.YELLOW));
            }
        }
    }

    /** Simple Y/N prompt that keeps asking until it gets a clear answer. */
    private static boolean askYesNo(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(ConsoleColors.colorize(prompt, ConsoleColors.BRIGHT_CYAN));
            String input = scanner.nextLine().trim().toUpperCase();
            if (input.startsWith("Y")) {
                return true;
            } else if (input.startsWith("N")) {
                return false;
            }
            System.out.println(ConsoleColors.colorize("⚠️  Please type Y or N.", ConsoleColors.YELLOW));
        }
    }
}
