# 🎯 Number Guessing Game

A console-based Java game where the computer generates a secret number and the player tries to guess it within a limited number of attempts, receiving intelligent hints after each guess.

![Java](https://img.shields.io/badge/Java-21-orange?logo=openjdk)
![License](https://img.shields.io/badge/License-MIT-green)
![Platform](https://img.shields.io/badge/Platform-Console-blue)

---

## 📸 Sample Gameplay

```
════════════════════════════════════════════
         🎯 NUMBER GUESSING GAME
════════════════════════════════════════════

Enter your name: Rahul

Welcome, Rahul! Let's play!

Select Difficulty:
  1. Easy   (1-50,  15 attempts)
  2. Medium (1-100, 10 attempts)
  3. Hard   (1-500,  7 attempts)
Choice: 2

I'm thinking of a number between 1 and 100...
You have 10 attempts. Good luck!

Attempt 1/10 → Enter your guess: 50
📈 Too High! Try a lower number.
   ❄️  Cold. Try harder.
   (9 attempts remaining)

Attempt 2/10 → Enter your guess: 25
📉 Too Low! Try a higher number.
   🌡️  Warm! Getting closer...
   (8 attempts remaining)

Attempt 3/10 → Enter your guess: 35
📉 Too Low! Try a higher number.
   🔥 Very Close! You're burning up!
   (7 attempts remaining)

Attempt 4/10 → Enter your guess: 38
🎉 Correct! You guessed it in 4 attempts!
Your Score: 70/100
⭐ NEW HIGH SCORE! ⭐

🏆 High Scores:
────────────────────────────────────────
   1. Rahul            70 pts  [MEDIUM]
────────────────────────────────────────

Play again? (yes/no): no

Thanks for playing! Goodbye 👋
```

---

## ✨ Features

### Core
- 🎲 **Random Number Generation** — Secret number generated using `java.util.Random`
- ⌨️ **User Input** — Accepts guesses via `Scanner`
- 💡 **Hint System** — "Too High!" / "Too Low!" / "🎉 Correct!" after each guess
- 🔢 **Attempt Limit** — Limited attempts based on difficulty
- 📊 **Attempt Counter** — Shows remaining attempts after each guess

### Additional
- 🎚️ **Difficulty Levels** — Easy (1–50, 15 tries) · Medium (1–100, 10 tries) · Hard (1–500, 7 tries)
- 🏅 **Score System** — Fewer attempts = higher score (out of 100)
- 🔄 **Play Again** — Replay without restarting the program
- 🛡️ **Input Validation** — Handles letters, out-of-range numbers, and negatives gracefully
- 🏆 **High Score Tracker** — Persisted to file across sessions

### Bonus
- 🔥 **Warm/Cold Proximity Hints** — Extremely Close, Very Close, Warm, Cold, Freezing (thresholds scale to range)
- 💾 **File Persistence** — High scores saved to `highscores.txt` using `BufferedWriter` / `BufferedReader`

---

## 🧩 Project Structure

```
NumberGuessingGame/
│
├── Main.java              → Entry point, game loop, menus
├── Game.java              → Core game logic (generate number, check guess, hints)
├── Player.java            → Player name, score, attempt tracking
├── DifficultyLevel.java   → Enum for Easy / Medium / Hard
├── ScoreBoard.java        → High score tracking with file persistence
└── README.md              → You are here!
```

---

## 🚀 Getting Started

### Prerequisites

- **Java JDK 17+** (built with JDK 21)
- Download from [Oracle](https://www.oracle.com/java/technologies/downloads/) or [Adoptium](https://adoptium.net/)

### Compile & Run

```bash
# Clone the repository
git clone https://github.com/YOUR_USERNAME/NumberGuessingGame.git
cd NumberGuessingGame

# Compile all Java files
javac *.java

# Run the game
java Main
```

> **Windows Note:** If `javac` is not on your PATH, use the full path:
> ```powershell
> & "C:\Program Files\Java\jdk-21.0.11\bin\javac.exe" *.java
> & "C:\Program Files\Java\jdk-21.0.11\bin\java.exe" Main
> ```

---

## 🧠 Java Concepts Covered

| Concept | Usage |
|---|---|
| **OOP (Classes & Objects)** | `Game`, `Player`, `ScoreBoard` classes |
| **Enums** | `DifficultyLevel` (EASY, MEDIUM, HARD) |
| **Records** | `ScoreEntry` record inside ScoreBoard |
| **Loops** | `while` loop for guess attempts & game rounds |
| **Conditionals** | `if-else` for hint logic, `switch` for menu |
| **Exception Handling** | `try-catch` for invalid input & file I/O |
| **Random** | `java.util.Random` for secret number |
| **Scanner** | `java.util.Scanner` for user input |
| **Collections** | `ArrayList` for storing high scores |
| **File I/O** | `BufferedWriter` / `BufferedReader` for persistence |
| **Methods** | Modular code for each feature |

---

## 📐 Score Calculation

The score rewards players who guess in fewer attempts:

$$\text{Score} = \left\lfloor \frac{\text{maxAttempts} - \text{attemptsUsed} + 1}{\text{maxAttempts}} \times 100 \right\rceil$$

| Difficulty | Guess in 1 attempt | Guess in max attempts |
|---|---|---|
| Easy (15) | 100 pts | 7 pts |
| Medium (10) | 100 pts | 10 pts |
| Hard (7) | 100 pts | 14 pts |

---

## 🔮 Future Enhancements

- [ ] ⏱️ Timer mode — solve within 60 seconds for bonus points
- [ ] 🖥️ GUI version using Java Swing
- [ ] 🌐 Multiplayer — take turns and compare scores
- [ ] 📈 Statistics — win rate, average attempts, streaks

---

## 📄 License

This project is open source and available under the [MIT License](LICENSE).

---

## 🤝 Contributing

Contributions are welcome! Feel free to:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

---

<p align="center">
  Made with ❤️ in Java &nbsp;|&nbsp; ⭐ Star this repo if you found it useful!
</p>
