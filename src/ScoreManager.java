import java.io.*;
import java.util.*;

/**
 * ScoreManager.java
 * ---------------------------------------------------------
 * Handles saving and loading high scores to/from a simple text
 * file (highscores.txt) so scores survive even after you close
 * the program. This is a beginner-friendly way to do "persistence"
 * without needing a database.
 *
 * File format (one line per score, fields separated by "|"):
 *   playerName|score|category|difficulty
 * ---------------------------------------------------------
 */
public class ScoreManager {

    private static final String FILE_NAME = "highscores.txt";

    /** Small inner record-like class to hold one saved score entry. */
    public static class ScoreEntry {
        String name;
        int score;
        String category;
        String difficulty;

        ScoreEntry(String name, int score, String category, String difficulty) {
            this.name = name;
            this.score = score;
            this.category = category;
            this.difficulty = difficulty;
        }
    }

    /**
     * Appends a new score to highscores.txt.
     * If the file doesn't exist yet, Java creates it automatically.
     */
    public void saveScore(Player player, String category, String difficulty) {
        try (FileWriter fw = new FileWriter(FILE_NAME, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {

            String line = player.getName() + "|" + player.getScore() + "|"
                    + category + "|" + difficulty;
            out.println(line);

        } catch (IOException e) {
            System.out.println(ConsoleColors.colorize(
                    "⚠️  Couldn't save your score to file: " + e.getMessage(),
                    ConsoleColors.YELLOW));
        }
    }

    /**
     * Reads all saved scores from highscores.txt.
     * Returns an empty list if the file doesn't exist yet
     * (e.g. this is the very first time the game is played).
     */
    public List<ScoreEntry> loadAllScores() {
        List<ScoreEntry> entries = new ArrayList<>();
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            return entries; // No scores yet - that's fine!
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 4) {
                    entries.add(new ScoreEntry(parts[0],
                            Integer.parseInt(parts[1]), parts[2], parts[3]));
                }
            }
        } catch (IOException e) {
            System.out.println(ConsoleColors.colorize(
                    "⚠️  Couldn't read high scores: " + e.getMessage(),
                    ConsoleColors.YELLOW));
        }

        return entries;
    }

    /**
     * Prints the top N scores (highest first) in a nice leaderboard
     * format. Great little dopamine hit for replaying the quiz!
     */
    public void printLeaderboard(int topN) {
        List<ScoreEntry> entries = loadAllScores();

        if (entries.isEmpty()) {
            System.out.println(ConsoleColors.colorize(
                    "No high scores yet - be the first legend! 🏆",
                    ConsoleColors.BRIGHT_YELLOW));
            return;
        }

        // Sort scores from highest to lowest
        entries.sort((a, b) -> b.score - a.score);

        System.out.println(ConsoleColors.colorize(
                "\n🏆 ===== TOP " + topN + " LEADERBOARD ===== 🏆", ConsoleColors.BRIGHT_PURPLE));

        int limit = Math.min(topN, entries.size());
        for (int i = 0; i < limit; i++) {
            ScoreEntry e = entries.get(i);
            String medal = (i == 0) ? "🥇" : (i == 1) ? "🥈" : (i == 2) ? "🥉" : "  ";
            System.out.println(medal + " " + (i + 1) + ". "
                    + ConsoleColors.colorize(e.name, ConsoleColors.BRIGHT_CYAN)
                    + " - " + e.score + " pts (" + e.category + " / " + e.difficulty + ")");
        }
        System.out.println();
    }
}
