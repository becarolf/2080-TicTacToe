package org.example;

import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        // 1) The user should be asked if it is a 2 player OR 1 player game (versus Intelligent AI)
        System.out.println("Select game mode:");
        System.out.println("1 - 2 Players");
        System.out.println("2 - 1 Player (vs AI)");
        int mode = getInput("Enter mode: ", 1, 2);

        Board board = new Board();
        GameLogic logic = new GameLogic(board);

        if (mode == 1) {
            twoPlayerGame(board, logic);
        } else {
            onePlayerGame(board, logic);
        }

        scanner.close();
    }

    // 3) If the user selects 2 player (2 Human players)
    static void twoPlayerGame(Board board, GameLogic logic) {

        // a. Each human player must be prompted to choose their name and symbol ('X' or 'O')
        //    - input their name (store in a variable)
        //    - input: choose symbol X or O (store in a variable)
        System.out.print("Player 1 - Enter your name: ");
        String name1 = scanner.nextLine();
        char symbol1 = getSymbol(name1);

        System.out.print("Player 2 - Enter your name: ");
        String name2 = scanner.nextLine();
        char symbol2 = (symbol1 == 'X') ? 'O' : 'X';
        System.out.println(name2 + ", your symbol is: " + symbol2);

        Player player1 = new Player(name1, symbol1);
        Player player2 = new Player(name2, symbol2);

        // X always goes first
        Player[] players = (symbol1 == 'X')
                ? new Player[]{player1, player2}
                : new Player[]{player2, player1};

        // b. An empty board will then be displayed. displayBoard()
        System.out.println("\nStarting game! Here is the empty board:");
        board.displayBoard();

        int currentIndex = 0;

        while (true) {
            Player current = players[currentIndex];

            // c. The next player to move will be prompted for a row and column location to play.
            //    - prompt the user to choose the row (from 0 to 2 - x) and column (from 0 to 2 - y)
            System.out.println("\n" + current.getName() + "'s turn (" + current.getSymbol() + ")");
            int row = getInput("Enter row (0-2): ", 0, 2);
            int col = getInput("Enter column (0-2): ", 0, 2);

            while (!board.isCellEmpty(row, col)) {
                System.out.println("That cell is already taken! Try again.");
                row = getInput("Enter row (0-2): ", 0, 2);
                col = getInput("Enter column (0-2): ", 0, 2);
            }

            // d. The present board state will be displayed showing their updated play.
            //    setUserChoice(row, col, symbol) - board[row][col] = symbol
            //    displayBoard() -- loop dentro de um loop pq tem que imprimir 9x (row & col)
            board.setUserChoice(row, col, current.getSymbol());
            board.displayBoard();

            // e. The board will then be checked for a winning state or draw
            //    checkWinner()
            //      - checkRowWinner() -> symbol
            //      - checkColumnWinner()
            //      - checkDiagonalWinner()
            char winner = logic.checkWinner();
            if (winner != ' ') {
                System.out.println("\n" + current.getName() + " (" + winner + ") wins!");
                break;
            }

            //    checkDraw() - check if the grid is filled completely
            if (logic.checkDraw()) {
                System.out.println("\nIt's a draw!");
                break;
            }

            // if not in a winning state or draw, go to step c
            currentIndex = (currentIndex == 0) ? 1 : 0;
        }

        // At the end of a game, the result should be displayed.
    }

    // 2) If the user selects 1 player (versus minimax AI)
    static void onePlayerGame(Board board, GameLogic logic) {
        // implement human vs AI game
        System.out.println("Coming soon!");
    }

    // ─── Input helpers ────────────────────────────────────────────────────────

    static char getSymbol(String playerName) {
        while (true) {
            System.out.print(playerName + ", choose your symbol (X or O): ");
            String input = scanner.nextLine().trim().toUpperCase();
            if (input.equals("X") || input.equals("O")) return input.charAt(0);
            System.out.println("Invalid input. Please enter X or O.");
        }
    }

    static int getInput(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            try {
                int value = Integer.parseInt(scanner.nextLine().trim());
                if (value >= min && value <= max) return value;
                System.out.println("Please enter a number between " + min + " and " + max + ".");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }
}