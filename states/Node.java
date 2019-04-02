package states;

import heuristics.Heuristic;

import java.util.Arrays;

public class Node implements Comparable<Node> {

    private Board start;
    private Board goal;
    private Heuristic heuristic;
    private Node parent;

    public Node(Board start, Board goal, Heuristic heuristic, Node parent) {
        this.start = start;
        this.goal = goal;
        this.heuristic = heuristic;
        this.parent = parent;
    }

    public int getCost() {
        if (parent == null)
            return 0;
        return parent.getCost() + 1;
    }

    public int calcCost() {
        return getCost() + heuristic.calculate(start.getBoard(), goal.getBoard());
    }

    public Node getRoot() {
        if (parent.parent == null)
            return parent;
        return parent.getRoot();
    }

    public void getPath(Node solution) {
        System.out.println(solution);
        if (solution.parent == null)
            return;
        parent.getPath(solution.parent);
    }

    public Board getBoard() {
        return start;
    }

    @Override
    public int compareTo(Node o) {
        int current = calcCost();
        int temp = o.calcCost();
        return current - temp;
    }

    @Override
    public int hashCode() {
        return start.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (!(obj instanceof Board))
            return false;
        Board board = (Board) obj;
        return hashCode() == board.hashCode();
    }

    @Override
    public String toString() {
        int[] start = this.start.getBoard();
        String output = "";
        output += Arrays.toString(start);
        return output;
    }

}
