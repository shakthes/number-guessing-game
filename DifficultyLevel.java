/**
 * Enum representing the difficulty levels of the Number Guessing Game.
 * Each level defines the range of numbers and the maximum attempts allowed.
 */
public enum DifficultyLevel {
    EASY(1, 50, 15),
    MEDIUM(1, 100, 10),
    HARD(1, 500, 7);

    private final int minRange;
    private final int maxRange;
    private final int maxAttempts;

    DifficultyLevel(int minRange, int maxRange, int maxAttempts) {
        this.minRange = minRange;
        this.maxRange = maxRange;
        this.maxAttempts = maxAttempts;
    }

    public int getMinRange() {
        return minRange;
    }

    public int getMaxRange() {
        return maxRange;
    }

    public int getMaxAttempts() {
        return maxAttempts;
    }

    /**
     * Returns a formatted display string for the menu.
     */
    public String getDisplayString() {
        return String.format("%-6s (1-%d, %2d attempts)", name().charAt(0) + name().substring(1).toLowerCase(),
                maxRange, maxAttempts);
    }

    /**
     * Retrieves a DifficultyLevel from a user's menu choice (1, 2, or 3).
     *
     * @param choice the menu number
     * @return the corresponding DifficultyLevel, or null if invalid
     */
    public static DifficultyLevel fromChoice(int choice) {
        return switch (choice) {
            case 1 -> EASY;
            case 2 -> MEDIUM;
            case 3 -> HARD;
            default -> null;
        };
    }
}
