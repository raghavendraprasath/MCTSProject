# MCTSProject – Tic Tac Toe with Monte Carlo Tree Search  
This repository contains our final team project for **INFO 6205: Program Structures and Algorithms (Spring 2025)** at Northeastern University. We implemented the **Monte Carlo Tree Search (MCTS)** algorithm to intelligently play the game of **Tic Tac Toe**, complete with a full Java GUI and a clean, extendable architecture.  
> ✅ This version focuses entirely on Tic Tac Toe. A second game will be added in the near future.  

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

## 📁 Project Structure  
```
MCTSProject/  
│  
├── src/  
│   └── main/java/com/phasmidsoftware/dsaipg/projects/mcts/  
│       ├── core/        # MCTS core framework (Game, Move, Node, State, etc.)  
│       └── tictactoe/   # Tic Tac Toe-specific implementation  
│  
├── test/  
│   └── java/com/phasmidsoftware/dsaipg/projects/mcts/  
│       ├── core/        # Unit tests for core logic  
│       └── tictactoe/   # Unit tests for Tic Tac Toe  
│  
├── resources/           # Icons and assets used by the GUI  
└── README.md
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
git clone https://github.com/your-username/MCTSProject.git  
cd MCTSProject  
```  
> Alternatively, you can open the project in IntelliJ, Eclipse, or VS Code with Maven support and run the `WelcomeScreen.java` file directly.  

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

## 📜 License  
This repository is created for academic coursework and is not licensed for commercial use. Contact the authors for permission to reuse or contribute.
