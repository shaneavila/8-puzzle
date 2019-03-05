package states;

public class Board {

    private int[][] board;
    private Position blank;

    public Board(int[][] board) {
        this.board = new int[board.length][board.length];
        initializeBoard(board);
        updateBlank();
    }

    private void initializeBoard(int[][] board) {
        for(int i = 0; i < board.length; i++)
            for(int j = 0; j < board.length; j++)
                this.board[i][j] = board[i][j];
    }

    private void updateBlank() {
        for(int i = 0; i < board.length; i++)
            for(int j = 0; j < board.length; j++)
                if(board[i][j] == 0)
                    blank = new Position(i,j);
    }

    public int[][] getBoard() {
        return board;
    }

    public Position getBlank() {
        return blank;
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    @Override
    public String toString() {
        String output = "";
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board.length; j++) {
                output += board[i][j];
            }
        }
        return output;
    }
}
