package movements;

import states.Board;
import states.Position;

public enum MovementChoice implements Movement {

    DOWN {
        @Override
        public Board move(Board board) {
            Position tempBlank = board.getBlank();
            int row = tempBlank.getRow() + 1;
            int col = tempBlank.getCol();
            int[][] tempBoard = new int[3][3];

            for(int i = 0; i < tempBoard.length; i++)
                for(int j = 0; j < tempBoard.length; j++)
                    tempBoard[i][j] = board.getBoard()[i][j];

            if(!valid(row, col, tempBoard)) {
                return board;
            }

            tempBoard = swap(row,col,tempBlank,tempBoard);
            return new Board(tempBoard);
        }
    },
    UP {
        @Override
        public Board move(Board board) {
            Position tempBlank = board.getBlank();
            int row = tempBlank.getRow() - 1;
            int col = tempBlank.getCol();
            int[][] tempBoard = new int[3][3];

            for(int i = 0; i < tempBoard.length; i++)
                for(int j = 0; j < tempBoard.length; j++)
                    tempBoard[i][j] = board.getBoard()[i][j];

            if(!valid(row, col, tempBoard)) {
                return new Board(tempBoard);
            }

            tempBoard = swap(row,col,tempBlank,tempBoard);
            return new Board(tempBoard);
        }
    },
    LEFT {
        public Board move(Board board) {
            Position tempBlank = board.getBlank();
            int row = tempBlank.getRow();
            int col = tempBlank.getCol() - 1;
            int[][] tempBoard = new int[3][3];

            for(int i = 0; i < tempBoard.length; i++)
                for(int j = 0; j < tempBoard.length; j++)
                    tempBoard[i][j] = board.getBoard()[i][j];

            if(!valid(row, col, tempBoard)) {
                return new Board(tempBoard);
            }

            tempBoard = swap(row,col,tempBlank,tempBoard);
            return new Board(tempBoard);
        }
    },
    RIGHT {
        @Override
        public Board move(Board board) {
            Position tempBlank = board.getBlank();
            int row = tempBlank.getRow();
            int col = tempBlank.getCol() + 1;
            int[][] tempBoard = new int[3][3];

            for(int i = 0; i < tempBoard.length; i++)
                for(int j = 0; j < tempBoard.length; j++)
                    tempBoard[i][j] = board.getBoard()[i][j];

            if(!valid(row, col, tempBoard)) {
                return new Board(tempBoard);
            }

            tempBoard = swap(row,col,tempBlank,tempBoard);
            return new Board(tempBoard);
        }
    };

    private static int[][] swap(int row, int col, Position tempBlank, int[][] board) {
        int x = board[row][col];
        board[row][col] = board[tempBlank.getRow()][tempBlank.getCol()];
        board[tempBlank.getRow()][tempBlank.getCol()] = x;
        return board;
    }

    private static boolean valid(int row, int col, int[][] board) {
        return row >= 0 && col >= 0 && row < board.length && col < board.length;
    }

}
