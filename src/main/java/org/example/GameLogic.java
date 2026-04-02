package org.example;

public class GameLogic {
    private Board board;

    public GameLogic(Board board) {
        this.board = board;
    }

    public char checkWinner() {
        char row = checkRowWinner();
        if (row != ' ') return row;

        char col = checkColumnWinner();
        if (col != ' ') return col;

        char diag = checkDiagonalWinner();
        if (diag != ' ') return diag;

        return ' ';
    }

    private char checkRowWinner() {
        for (int i = 0; i < 3; i++) {
            if (board.getCell(i, 0) != ' ' &&
                    board.getCell(i, 0) == board.getCell(i, 1) &&
                    board.getCell(i, 1) == board.getCell(i, 2)) {
                return board.getCell(i, 0);
            }
        }
        return ' ';
    }

    private char checkColumnWinner() {
        for (int j = 0; j < 3; j++) {
            if (board.getCell(0, j) != ' ' &&
                    board.getCell(0, j) == board.getCell(1, j) &&
                    board.getCell(1, j) == board.getCell(2, j)) {
                return board.getCell(0, j);
            }
        }
        return ' ';
    }

    private char checkDiagonalWinner() {
        // Top-left → bottom-right
        if (board.getCell(0, 0) != ' ' &&
                board.getCell(0, 0) == board.getCell(1, 1) &&
                board.getCell(1, 1) == board.getCell(2, 2)) {
            return board.getCell(0, 0);
        }
        // Top-right → bottom-left
        if (board.getCell(0, 2) != ' ' &&
                board.getCell(0, 2) == board.getCell(1, 1) &&
                board.getCell(1, 1) == board.getCell(2, 0)) {
            return board.getCell(0, 2);
        }
        return ' ';
    }

    public boolean checkDraw() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (board.getCell(i, j) == ' ') return false;
        return true;
    }
}
