package org.example;

public class AI {
    private char aiSymbol;
    private char humanSymbol;

    public AI(char aiSymbol, char humanSymbol) {
        this.aiSymbol = aiSymbol;
        this.humanSymbol = humanSymbol;
    }

    public int[] getBestMove(Board board, GameLogic logic) {
        int bestScore = Integer.MIN_VALUE;
        int[] bestMove = new int[2];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.isCellEmpty(i, j)) {
                    board.setUserChoice(i, j, aiSymbol);
                    int score = minimax(board, logic, false);
                    board.setUserChoice(i, j, ' ');

                    if (score > bestScore) {
                        bestScore = score;
                        bestMove[0] = i;
                        bestMove[1] = j;
                    }
                }
            }
        }
        return bestMove;
    }

    private int minimax(Board board, GameLogic logic, boolean isAiTurn) {
        char winner = logic.checkWinner();

        if (winner == aiSymbol) return 1;
        if (winner == humanSymbol) return -1;
        if(logic.checkDraw()) return 0;

        if(isAiTurn) {
            int bestScore = Integer.MIN_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board.isCellEmpty(i, j)) {
                        board.setUserChoice(i, j, aiSymbol);
                        int score = minimax(board, logic, false);
                        board.setUserChoice(i, j, ' ');
                        bestScore = Math.max(bestScore, score);
                    }
                }
            }
            return bestScore;
        } else {
            int bestScore = Integer.MAX_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board.isCellEmpty(i, j)) {
                        board.setUserChoice(i, j, humanSymbol);
                        int score = minimax(board, logic, true);
                        board.setUserChoice(i, j, ' ');
                        bestScore = Math.min(bestScore, score);
                    }
                }
            }
            return bestScore;
        }
    }
}
