package states;

import java.util.Arrays;

public class Board {

    private int[] board;
    private Position blank;

    public Board(int[] board) {
        initializeBoard(board);
        updateBlankPosition();
    }

//    public Board(Board startState, Board goalState) {
//        this(startState, goalState, null);
//    }

    private void initializeBoard(int[] board) {
        this.board = new int[board.length];
        System.arraycopy(board, 0, this.board, 0, board.length);
    }

    private void updateBlankPosition() {
        int squareLength = (int) Math.sqrt(board.length);
        for (int i = 0; i < board.length; i++)
            if (board[i] == 0)
                blank = new Position(i / squareLength, i % squareLength);
    }
    /*
     * Getters, Setters, & Override
     */

    public int[] getBoard() {
        return board;
    }

    public Position getBlank() {
        return blank;
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

//    @Override
//    public int compareTo(Board o) {
//        int current = calcCost();
//        int temp = o.calcCost();
//        return current - temp;
//    }

    @Override
    public String toString() {
        String output = "";
        output += Arrays.toString(board);
        return output;
    }
}
