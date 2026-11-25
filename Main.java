import java.util.Scanner;

/**
 * Entry point for the Number Guessing Game.
 * Handles the main menu, game loop, play-again logic, and high score display.
 */
public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final ScoreBoard scoreBoard = new ScoreBoard();

    public static void main(String[] args) {
        printBanner();

        // Get the player's name
        System.out.print("Enter your name: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) name = "Player";

        Player player = new Player(name);
        System.out.println("\nWelcome, " + player.getName() + "! Let's play!\n");

        boolean playAgain = true;

        while (playAgain) {
            // Reset player state for a new round
            player.resetForNewRound();

            // Choose difficulty
            DifficultyLevel difficulty = chooseDifficulty();

            // Run the game
            Game game = new Game(player, difficulty);
            boolean won = game.play(scanner);

            // Record score if the player won
            if (won) {
                if (scoreBoard.isNewHighScore(player.getScore())) {
                    System.out.println("⭐ NEW HIGH SCORE! ⭐");
                }
                scoreBoard.addScore(player.getName(), player.getScore(), difficulty);
            }

            // Show the scoreboard
            scoreBoard.displayScores();

            // Ask to play again
            System.out.print("\nPlay again? (yes/no): ");
            String response = scanner.nextLine().trim().toLowerCase();
            playAgain = response.startsWith("y");

            if (playAgain) {
                System.out.println("\n" + "═".repeat(40) + "\n");
            }
        }

        System.out.println("\nThanks for playing! Goodbye 👋");
        scanner.close();
    }

    /**
     * Prints the game banner.
     */
    private static void printBanner() {
        System.out.println("═".repeat(44));
        System.out.println("         🎯 NUMBER GUESSING GAME");
        System.out.println("═".repeat(44));
        System.out.println();
    }

    /**
     * Prompts the user to select a difficulty level.
     * Keeps asking until a valid choice is made.
     *
     * @return the chosen DifficultyLevel
     */
    private static DifficultyLevel chooseDifficulty() {
        DifficultyLevel level = null;

        while (level == null) {
            System.out.println("Select Difficulty:");
            System.out.println("  1. " + DifficultyLevel.EASY.getDisplayString());
            System.out.println("  2. " + DifficultyLevel.MEDIUM.getDisplayString());
            System.out.println("  3. " + DifficultyLevel.HARD.getDisplayString());
            System.out.print("Choice: ");

            String input = scanner.nextLine().trim();
            try {
                int choice = Integer.parseInt(input);
                level = DifficultyLevel.fromChoice(choice);
                if (level == null) {
                    System.out.println("⚠ Please choose 1, 2, or 3.\n");
                }
            } catch (NumberFormatException e) {
                System.out.println("⚠ Invalid input. Please enter 1, 2, or 3.\n");
            }
        }

        return level;
    }
}
