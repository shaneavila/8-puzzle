package states;

public class Position {
    private int row;
    private int col;

    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() { return row; }

    public int getCol() {
        return col;
    }

    @Override
    public String toString() {
        return " (" + getRow() + "," + getCol() + ")";
    }
}