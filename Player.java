/**
 * Represents a player in the Number Guessing Game.
 * Tracks the player's name, current round score, and attempts used.
 */
public class Player {
    private final String name;
    private int score;
    private int attemptsUsed;

    public Player(String name) {
        this.name = name;
        this.score = 0;
        this.attemptsUsed = 0;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getAttemptsUsed() {
        return attemptsUsed;
    }

    public void incrementAttempts() {
        this.attemptsUsed++;
    }

    /**
     * Resets attempts for a new round.
     */
    public void resetForNewRound() {
        this.attemptsUsed = 0;
        this.score = 0;
    }

    /**
     * Calculates the score based on attempts used and max attempts allowed.
     * Fewer attempts = higher score. Score is out of 100.
     *
     * @param maxAttempts the maximum attempts for the difficulty level
     */
    public void calculateScore(int maxAttempts) {
        if (maxAttempts <= 0) {
            this.score = 0;
            return;
        }
        // Score formula: percentage of attempts saved, scaled to 100
        double ratio = (double) (maxAttempts - attemptsUsed + 1) / maxAttempts;
        this.score = (int) Math.round(ratio * 100);
    }

    @Override
    public String toString() {
        return name + " - " + score + " pts";
    }
}
