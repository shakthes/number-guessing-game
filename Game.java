import java.util.Random;
import java.util.Scanner;

/**
 * Core game logic for the Number Guessing Game.
 * Generates the secret number, processes guesses, tracks attempts,
 * and provides hints (including proximity "warm/cold" hints).
 */
public class Game {
    private final int secretNumber;
    private final DifficultyLevel difficulty;
    private final Player player;
    private boolean won;

    private static final Random RANDOM = new Random();

    public Game(Player player, DifficultyLevel difficulty) {
        this.player = player;
        this.difficulty = difficulty;
        this.secretNumber = RANDOM.nextInt(difficulty.getMaxRange() - difficulty.getMinRange() + 1) + difficulty.getMinRange();
        this.won = false;
    }

    /**
     * Runs the main game loop, returning true if the player won.
     *
     * @param scanner the shared Scanner for reading user input
     * @return true if the player guessed correctly, false if they ran out of attempts
     */
    public boolean play(Scanner scanner) {
        int maxAttempts = difficulty.getMaxAttempts();

        System.out.println();
        System.out.printf("I'm thinking of a number between %d and %d...%n",
                difficulty.getMinRange(), difficulty.getMaxRange());
        System.out.println("You have " + maxAttempts + " attempts. Good luck!");
        System.out.println();

        while (player.getAttemptsUsed() < maxAttempts) {
            int remaining = maxAttempts - player.getAttemptsUsed();
            System.out.printf("Attempt %d/%d → Enter your guess: ",
                    player.getAttemptsUsed() + 1, maxAttempts);

            int guess = readValidGuess(scanner);
            if (guess == -1) continue; // invalid input, ask again (attempt not consumed)

            player.incrementAttempts();

            if (guess == secretNumber) {
                won = true;
                player.calculateScore(maxAttempts);
                System.out.printf("🎉 Correct! You guessed it in %d attempt%s!%n",
                        player.getAttemptsUsed(),
                        player.getAttemptsUsed() == 1 ? "" : "s");
                System.out.printf("Your Score: %d/100%n", player.getScore());
                return true;
            }

            // Direction hint
            if (guess > secretNumber) {
                System.out.println("📈 Too High! Try a lower number.");
            } else {
                System.out.println("📉 Too Low! Try a higher number.");
            }

            // Proximity (warm/cold) hint
            printProximityHint(guess);

            // Remaining attempts warning
            int attemptsLeft = maxAttempts - player.getAttemptsUsed();
            if (attemptsLeft > 0) {
                System.out.printf("   (%d attempt%s remaining)%n%n",
                        attemptsLeft, attemptsLeft == 1 ? "" : "s");
            }
        }

        // Player ran out of attempts
        System.out.println();
        System.out.println("💀 Out of attempts! The number was: " + secretNumber);
        player.setScore(0);
        return false;
    }

    /**
     * Reads and validates a guess from the user.
     * Returns the valid guess, or -1 if the input was invalid.
     */
    private int readValidGuess(Scanner scanner) {
        String input = scanner.nextLine().trim();

        try {
            int guess = Integer.parseInt(input);

            if (guess < difficulty.getMinRange() || guess > difficulty.getMaxRange()) {
                System.out.printf("   ⚠ Please enter a number between %d and %d.%n",
                        difficulty.getMinRange(), difficulty.getMaxRange());
                return -1;
            }

            return guess;
        } catch (NumberFormatException e) {
            System.out.println("   ⚠ Invalid input! Please enter a valid number.");
            return -1;
        }
    }

    /**
     * Prints a warm/cold proximity hint based on how close the guess is.
     */
    private void printProximityHint(int guess) {
        int diff = Math.abs(guess - secretNumber);
        int range = difficulty.getMaxRange() - difficulty.getMinRange();

        // Thresholds are proportional to the range
        if (diff <= Math.max(2, range * 0.02)) {
            System.out.println("   🔥 Extremely Close! You're almost there!");
        } else if (diff <= Math.max(5, range * 0.05)) {
            System.out.println("   🔥 Very Close! You're burning up!");
        } else if (diff <= Math.max(10, range * 0.10)) {
            System.out.println("   🌡️  Warm! Getting closer...");
        } else if (diff <= Math.max(25, range * 0.25)) {
            System.out.println("   ❄️  Cold. Try harder.");
        } else {
            System.out.println("   🧊 Freezing! Way off.");
        }
    }

    public boolean isWon() {
        return won;
    }

    public DifficultyLevel getDifficulty() {
        return difficulty;
    }
}
