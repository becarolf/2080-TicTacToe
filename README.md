# TicTacToe Game
2080 - Data Structures & Algorithms - Group Project: 

A command-line Tic-Tac-Toe game made in Java, built using only basic arrays, as required by the assignment.

## How to run

Run `Main.java` and choose your game mode:
- 2 Players
- 1 Player vs AI 

## How to play (2 player mode)

- Both players enter their names
- Player 1 picks their symbol (X or O), Player 2 gets the other one
- X always goes first
- Take turns picking a row and column (0 to 2) to place your symbol
- First to get 3 in a row wins — horizontally, vertically, or diagonally
- If the board fills up with no winner, it's a draw

### The board looks like this:
```
-------------
|   |   |   |   row 0
-------------
|   |   |   |   row 1
-------------
|   |   |   |   row 2
-------------
 col 0  1  2
```

## Project structure

```
src/main/java/org/example/
├── Main.java        # game flow + mode selection
├── Board.java       # the char[][] board, display, and cell logic
├── GameLogic.java   # checks for winner (row, col, diagonal) and draw
└── Player.java      # stores player name and symbol
```

## Notes

- Board is a `char[][]` as required by the assignment
- No higher-level data structures were used
- Human vs AI game created using minimax as required



