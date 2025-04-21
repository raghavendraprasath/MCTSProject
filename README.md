# MCTSProject – Tic Tac Toe with Monte Carlo Tree Search  
This repository contains our final team project for **INFO 6205: Program Structures and Algorithms (Spring 2025)** at Northeastern University. We implemented the **Monte Carlo Tree Search (MCTS)** algorithm to intelligently play the game of **Tic Tac Toe**, complete with a full Java GUI and a clean, extendable architecture.  

## 👨‍💻 Contributors  
- **Raghavendra Prasath Sridhar - (002312779) ** – AI logic, GUI design, move validation, MCTS integration  
- **Nikhil Pandey - (002775062) ** – Game engine logic, difficulty scaling, UI polish  
- **Ayush Patil - (002325566) ** – Testing, debugging, performance tuning, documentation  

## 🎯 Project Objective  
To implement the Monte Carlo Tree Search algorithm from scratch using the provided Java skeleton code and apply it to a two-player game. Our goal was to design a smart AI opponent for Tic Tac Toe, enhance the gameplay experience with a GUI, and structure the code to easily support additional games in future iterations.  

## 🧠 Core Features  
- 🔁 Monte Carlo Tree Search (MCTS) algorithm implemented from scratch  
- 🧍 Single-player (vs AI) and 👥 Two-player (multiplayer) gameplay  
- 🎮 Interactive Java Swing-based GUI with:  
  - Player name input screen  
  - Difficulty selection (Easy, Medium, Hard)  
  - Move validation and game state management  
  - Dark mode toggle for user comfort  
  - Responsive full-screen layout with adaptive styling  
- 🧪 Unit tests for both Tic Tac Toe and MCTS core logic  
- ⏱ AI move simulation timing and evaluation tracking  

## 📁 Tic Tac Toe Project Structure

```
MCTSProject/
│
├── src/
│   └── main/java/com/phasmidsoftware/dsaipg/projects/mcts/
│       ├── core/          # MCTS core framework (Game, Move, Node, State, etc.)
│       └── tictactoe/     # Tic Tac Toe game logic & GUI
│           ├── WelcomeScreen.java        # Entry point for the game
│           ├── TicTacToeGUI.java         # Game GUI
│           ├── TicTacToe.java            # Game logic implementation
│           ├── Position.java             # Board state representation
│           └── TicTacToeNode.java        # Node implementation for MCTS
│
├── test/
│   └── java/com/phasmidsoftware/dsaipg/projects/mcts/
│       ├── core/          # Unit tests for core framework
│       └── tictactoe/     # Unit tests for Tic Tac Toe
│           ├── PositionTest.java
│           ├── TicTacToeNodeTest.java
│           └── TicTacToeTest.java
│
├── resources/             # Icons and GUI assets (optional)
└── README.md              # Documentation and project guide
```  

## ⚙️ How to Run the Project  

Video has been included in repo for demo of game

### 📽️ Demo Video

👉 [Watch Tic Tac Toe with MCTS – Java Project Walkthrough](https://github.com/raghavendraprasath/MCTSProject/blob/main/Tic%20Tac%20Toe%20with%20MCTS%20%E2%80%93%20Java%20Project%20Walkthrough.mp4)


### Prerequisites  
- Java 17+

### Steps  

1. **Clone the repository**  
```bash  
git clone https://github.com/raghavendraprasath/MCTSProject.git  
cd MCTSProject  
```  
2. After that, you can open the project folder in IntelliJ, Eclipse, or VS Code with Maven support and run the `WelcomeScreen.java` file directly to run the game.  

## 🧪 Testing and Validation  
We thoroughly tested the implementation using:  
- ✅ **All unit tests** provided in the skeleton (`runGame`, etc.)  
- ✅ **Custom unit tests** to validate edge cases and simulate fixed seed games for predictable outcomes  
- ✅ **Tested all core classes** in the `core` and `tictactoe` packages with appropriate assertions and code coverage  
- ✅ **Manual testing** via the GUI for all gameplay modes and difficulty levels  
> Example test class: `TicTacToeGameTest.java`  
We also implemented detailed logging and observation for MCTS decision-making (visit count, win rate, random playout behavior) and validated AI strength improvements across difficulty levels.  

## 💡 MCTS Design Highlights  
- MCTS logic implemented in `Node.java`, `Game.java`, and `State.java`  
- Used **UCT (Upper Confidence Bound)** formula to balance exploration and exploitation  
- Difficulty levels are controlled by the number of playouts and simulation logic  
  - Easy: minimal rollouts  
  - Medium: moderate rollouts with basic pruning  
  - Hard: increased rollouts + simple heuristics for board evaluation  
- AI simulations are seeded in unit tests for consistency  

## ✨ GUI Features Recap  
- Full-screen **GUI with move feedback and styling**  
- Toggle between **dark mode and light mode**  
- Styled welcome screen with:  
  - Player 1 and Player 2 name input  
  - Difficulty selector (if playing vs AI)  
  - Clear navigation between screens  
- Option to play against another person or AI  
- Game restarts cleanly after win/loss/draw  
- Cleaned-up fonts and layout for aesthetic polish  

## 📈 Performance Insights  
- Tracked time per MCTS simulation using `System.nanoTime()` in AI mode  
- Logged number of rollouts per AI move  
- Benchmarked win consistency across difficulty levels in test cases  
- The game performs efficiently due to the low complexity of Tic Tac Toe, but design is extendable for larger games  

## 📚 References  
- [Monte Carlo Tree Search – Wikipedia](https://en.wikipedia.org/wiki/Monte_Carlo_tree_search)  
- [Monte Carlo Tree Search: A Review of Recent Modifications and Applications – arXiv:2103.04931](https://arxiv.org/abs/2103.04931)  
- [Monte Carlo Tree Search: A New Framework for Game AI – Cameron Browne et al., IEEE 2012](https://ieeexplore.ieee.org/document/6145622)  
- [A Survey of Monte Carlo Tree Search Methods – Robert Coulom](http://mcts.ai/pubs/PUC_Relation_MCTS.pdf)  
- [Monte Carlo Tree Search for the Game of Hex – Tristan Cazenave](https://www.lamsade.dauphine.fr/~cazenave/papers/HexMCTS.pdf)  
- [Monte Carlo Tree Search: A Review of the Field – ICGA Journal, 2012](https://core.ac.uk/download/pdf/82352484.pdf)  
- [int8.io Beginner’s Guide to MCTS](https://int8.io/monte-carlo-tree-search-beginners-guide/)  
- [UCT Algorithm Explained (Upper Confidence Bounds Applied to Trees)](https://lilianweng.github.io/posts/2021-06-23-mcts/#uct-upper-confidence-bound-for-trees)  
- [MCTS Tutorial: Monte Carlo Tree Search for AI Games – Towards Data Science](https://towardsdatascience.com/monte-carlo-tree-search-an-introduction-503d8c04e168)  


## 🧩 Future Work  
- Add a second game (to be chosen) under a new sub-package  
- Implement GUI and MCTS logic for the second game  
- Explore multi-threaded playouts and performance optimizations  
- Package application as a `.jar` or installable desktop launcher  
- Add data persistence (e.g., leaderboard, saved state)  

## 🗂️ Academic Requirements Checklist  
- ✅ Used professor’s skeleton in `com.phasmidsoftware.dsaipg.projects.mcts`  
- ✅ Added implementation in `core` and `tictactoe` packages  
- ✅ Created GUI from scratch with user input features  
- ✅ Implemented and passed all required test cases  
- ✅ Added custom test cases for validation  
- ✅ Measured performance and documented AI behavior  
- ✅ README and report include setup and explanation  
- 📽️ Demo video included


------------------------------------------------------------------------------------------------------------------------------------------------------------------

## 🎮 MCTSProject – 2048 with Monte Carlo Tree Search

This section documents the second game implemented as part of our INFO 6205 final project: the single-player puzzle game **2048**, powered by the **Monte Carlo Tree Search (MCTS)** algorithm.

We extended the MCTS framework from our Tic Tac Toe implementation to handle the stochastic nature of 2048, showcasing the versatility of MCTS beyond two-player games.

---

### 🎯 Project Objective

To apply Monte Carlo Tree Search to a single-player environment where moves lead to randomized tile additions and exponential board state complexity. Our AI simulates possible move outcomes and selects the most promising action using `log2(max tile)` as the evaluation function.

---

### 🧠 Core Features

- 🔁 MCTS-based AI that simulates and scores rollouts in real time  
- 🎮 Interactive 2048 game built with Java Swing  
- ⌨️ Supports keyboard input (WASD / Arrow keys) for manual play  
- 🧠 AI autoplay enabled by pressing any key  
- 📊 Console logs include:
  - Best move direction  
  - Max tile reached  
  - Evaluation score (log2-based)  
  - Number of rollouts per decision  
  - Time taken per move  
- 🧪 Full unit test suite for:
  - Grid updates and merges  
  - Tile spawning  
  - Move validation  
  - MCTS rollout logic  

---

### 🎨 GUI Highlights

- Responsive 4x4 tile grid  
- Color-coded tiles based on value  
- Player name entry at start  
- Instant visual updates on every move  
- AI control using the keyboard  
- Real-time benchmarking logs printed in terminal

---

### 📽️ Demo Video

👉 [Watch 2048 with MCTS – Java Project Walkthrough]()

---

### 🧪 Testing & Validation

- ✅ Validated all `game2048` components with unit tests  
- ✅ Verified edge case tile merges and move logic  
- ✅ Tested rollouts under different grid conditions  
- ✅ Observed and logged performance at each move decision  

---

### 📈 Benchmarking Logs

Example output after every AI move:

```
Best Move: LEFT  
Max Tile: 512  
Evaluation Score: 9.0  
Simulations: 100  
Time Taken: 47 ms  
```

---

### 📁 2048 Project Structure

```
src/
└── mcts/
    └── game2048/
        ├── Game2048.java       # Game interface implementation
        ├── State2048.java      # Grid logic, evaluation, spawning, merging
        ├── Move2048.java       # Enum for directions (UP, DOWN, LEFT, RIGHT)
        ├── MCTS2048.java       # AI controller for best move prediction
        └── Game2048GUI.java    # Java Swing GUI for the game
test/
└── game2048/
    ├── State2048Test.java
    ├── Move2048Test.java
    ├── Game2048Test.java
    └── MCTS2048Test.java
```

---

### 🔮 Future Improvements

- Add animation to tile transitions  
- Smarter heuristics (e.g., open cell count, merge potential)  
- Leaderboard to track best scores  
- Auto-play toggle within GUI  
- Save and resume game functionality

NOTE: 
macOS IMKClient Messages:
When running the 2048 Java GUI application on macOS, you might see terminal messages like:

+[IMKClient subclass]: chose IMKClient_Modern  

+[IMKInputSession subclass]: chose IMKInputSession_Modern

These are informational logs from macOS related to its input system (used for international keyboard support) and do not affect functionality or performance. They can be safely ignored.

---

### 📜 Summary

This 2048 implementation demonstrates that MCTS can effectively power strategic decision-making in single-player puzzle games involving randomness and exponential state spaces. With minimal parameter tuning, the AI can consistently reach tiles as high as 1024+ while providing transparent reasoning via logs.


## 📜 License  
This repository is created for academic coursework and is not licensed for commercial use. Contact the authors for permission to reuse or contribute.
