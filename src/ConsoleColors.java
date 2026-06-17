/**
 * ConsoleColors.java
 * ---------------------------------------------------------
 * A tiny helper class that stores ANSI escape codes so we can
 * print colorful text to the console. This is what gives
 * Code Quest Arena its fun, game-like feel instead of looking
 * like a boring exam screen.
 *
 * NOTE FOR BEGINNERS:
 * ANSI escape codes are special character sequences that tell
 * the terminal "hey, change the text color now!". They work on
 * Linux, Mac, IntelliJ/Eclipse consoles, and modern Windows
 * Terminal / Git Bash. Old Windows cmd.exe may not show colors
 * correctly - that's normal, the quiz will still work fine.
 * ---------------------------------------------------------
 */
public class ConsoleColors {

    // Reset code - ALWAYS print this after colored text so the
    // rest of the console output doesn't stay colored.
    public static final String RESET = "\u001B[0m";

    // Regular colors
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";

    // Bright / vivid versions - these look great for a game UI
    public static final String BRIGHT_RED = "\u001B[91m";
    public static final String BRIGHT_GREEN = "\u001B[92m";
    public static final String BRIGHT_YELLOW = "\u001B[93m";
    public static final String BRIGHT_BLUE = "\u001B[94m";
    public static final String BRIGHT_PURPLE = "\u001B[95m";
    public static final String BRIGHT_CYAN = "\u001B[96m";

    // Text styles
    public static final String BOLD = "\u001B[1m";
    public static final String UNDERLINE = "\u001B[4m";

    /**
     * Wraps a piece of text with a color and automatically resets
     * the color afterwards, so callers don't have to remember to
     * add RESET every single time.
     *
     * Example: ConsoleColors.colorize("Correct!", ConsoleColors.GREEN)
     */
    public static String colorize(String text, String color) {
        return color + text + RESET;
    }
}
