package movements;

import states.Board;
import states.Position;

import java.util.Arrays;

public enum MovementChoice implements Movement {

    DOWN {
        @Override
        public Board move(Board board) {
            Position blank = board.getBlank();
            if (!validBounds(blank.getRow() + 1, blank.getCol(), board.getBoard().length)) {
                return board;
            }
            return new Board(swap(blank.getRow() + 1, blank.getCol(), board));
        }
    },
    UP {
        @Override
        public Board move(Board board) {
            Position blank = board.getBlank();
            if (!validBounds(blank.getRow() - 1, blank.getCol(), board.getBoard().length)) {
                return board;
            }
            return new Board(swap(blank.getRow() - 1, blank.getCol(), board));
        }
    },
    LEFT {
        @Override
        public Board move(Board board) {
            Position blank = board.getBlank();
            if (!validBounds(blank.getRow(), blank.getCol() - 1, board.getBoard().length)) {
                return board;
            }
            return new Board(swap(blank.getRow(), blank.getCol() - 1, board));
        }
    },
    RIGHT {
        @Override
        public Board move(Board board) {
            Position blank = board.getBlank();
            if (!validBounds(blank.getRow(), blank.getCol() + 1, board.getBoard().length)) {
                return board;
            }
            return new Board(swap(blank.getRow(), blank.getCol() + 1, board));
        }
    };

    private static int[] swap(int row, int col, Board board) {
        Position blank = board.getBlank();
        int[] arrayCopy = Arrays.copyOf(board.getBoard(), board.getBoard().length);
        int squareLength = (int) Math.sqrt(arrayCopy.length);
        int x = arrayCopy[row * squareLength + col];
        arrayCopy[row * squareLength + col] = arrayCopy[blank.getRow() * squareLength + blank.getCol()];
        arrayCopy[blank.getRow() * squareLength + blank.getCol()] = x;
        return arrayCopy;
    }

    private static boolean validBounds(int row, int col, int boardLength) {
        int squareLength = (int) Math.sqrt(boardLength);
        return row >= 0 && col >= 0 && row < squareLength && col < squareLength;
    }

}
