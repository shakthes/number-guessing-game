import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Tracks and displays high scores across multiple rounds.
 * Scores are persisted to a file so they survive between sessions.
 */
public class ScoreBoard {
    private static final String HIGH_SCORES_FILE = "highscores.txt";
    private static final int MAX_ENTRIES = 10;

    private final List<ScoreEntry> scores;

    public ScoreBoard() {
        this.scores = new ArrayList<>();
        loadScores();
    }

    /**
     * Adds a new score entry and re-sorts the board.
     *
     * @param playerName the player's name
     * @param score      the score achieved
     * @param difficulty the difficulty level played
     */
    public void addScore(String playerName, int score, DifficultyLevel difficulty) {
        scores.add(new ScoreEntry(playerName, score, difficulty));
        scores.sort(Comparator.comparingInt(ScoreEntry::score).reversed());

        // Keep only the top entries
        while (scores.size() > MAX_ENTRIES) {
            scores.remove(scores.size() - 1);
        }

        saveScores();
    }

    /**
     * Displays the high score table.
     */
    public void displayScores() {
        System.out.println();
        System.out.println("🏆 High Scores:");
        System.out.println("─".repeat(40));

        if (scores.isEmpty()) {
            System.out.println("   No scores yet. Be the first!");
        } else {
            for (int i = 0; i < scores.size(); i++) {
                ScoreEntry entry = scores.get(i);
                System.out.printf("   %d. %-15s %3d pts  [%s]%n",
                        i + 1,
                        entry.playerName(),
                        entry.score(),
                        entry.difficulty().name());
            }
        }

        System.out.println("─".repeat(40));
    }

    /**
     * Returns the current highest score, or 0 if no scores exist.
     */
    public int getHighScore() {
        if (scores.isEmpty()) return 0;
        return scores.get(0).score();
    }

    /**
     * Checks if the given score is a new high score.
     */
    public boolean isNewHighScore(int score) {
        return score > getHighScore();
    }

    // ── Persistence ──────────────────────────────────────────────

    private void saveScores() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(HIGH_SCORES_FILE))) {
            for (ScoreEntry entry : scores) {
                writer.write(entry.playerName() + "|" + entry.score() + "|" + entry.difficulty().name());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("⚠ Could not save high scores: " + e.getMessage());
        }
    }

    private void loadScores() {
        File file = new File(HIGH_SCORES_FILE);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 3) {
                    String name = parts[0];
                    int score = Integer.parseInt(parts[1]);
                    DifficultyLevel difficulty = DifficultyLevel.valueOf(parts[2]);
                    scores.add(new ScoreEntry(name, score, difficulty));
                }
            }
            scores.sort(Comparator.comparingInt(ScoreEntry::score).reversed());
        } catch (IOException | IllegalArgumentException e) {
            System.out.println("⚠ Could not load high scores: " + e.getMessage());
        }
    }

    // ── Inner record for a score entry ───────────────────────────

    private record ScoreEntry(String playerName, int score, DifficultyLevel difficulty) {
    }
}
